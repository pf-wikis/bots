package io.github.pfwikis.bots.rest.endpoints.infobox.autocategorization;

import static io.github.pfwikis.bots.facts.SFactsProperties.Artist;
import static io.github.pfwikis.bots.facts.SFactsProperties.Author;
import static io.github.pfwikis.bots.facts.SFactsProperties.Author_all;
import static io.github.pfwikis.bots.facts.SFactsProperties.Deck_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Director;
import static io.github.pfwikis.bots.facts.SFactsProperties.Errata;
import static io.github.pfwikis.bots.facts.SFactsProperties.Map_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Narrator;
import static io.github.pfwikis.bots.facts.SFactsProperties.Performer;
import static io.github.pfwikis.bots.facts.SFactsProperties.Publisher;
import static io.github.pfwikis.bots.facts.SFactsProperties.Region;
import static io.github.pfwikis.bots.facts.SFactsProperties.Rule_system;
import static io.github.pfwikis.bots.facts.SFactsProperties.Series;
import static io.github.pfwikis.bots.facts.SFactsProperties.Video_game_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Web_enhancement;
import static io.github.pfwikis.bots.facts.SModel.ACCESSORY;
import static io.github.pfwikis.bots.facts.SModel.BOARD_GAME;
import static io.github.pfwikis.bots.facts.SModel.DECK;
import static io.github.pfwikis.bots.facts.SModel.MAP_PF;
import static io.github.pfwikis.bots.facts.SModel.MAP_SF;
import static io.github.pfwikis.bots.facts.SModel.VIDEO_GAME;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.model.subject.SemanticSubject;
import io.github.pfwikis.bots.facts.SModel;
import io.github.pfwikis.bots.facts.model.SConcept;
import io.github.pfwikis.bots.rest.endpoints.infobox.autocategorization.ACRule.RuleDoc;

public enum AutoCategorizer {

	INSTANCE;
	
	private ACGroup rules = new ACGroup();
	
	private AutoCategorizer() {
		addGenericRules();
		addDeckRules(rules.group(ctx->ctx.has(DECK)));
		addBoardgameRules(rules.group(ctx->ctx.has(BOARD_GAME)));
		addAccessoryRules(rules.group(ctx->ctx.has(ACCESSORY)));
		addVideoGameRules(rules.group(ctx->ctx.has(VIDEO_GAME)));
		addMapRules(rules.group(ctx->ctx.has(MAP_PF) || ctx.has(MAP_SF)));
	}
	
	void addDeckRules(ACGroup g) {
		g.ifMatchRule(Rule_system, "PACG", "Pathfinder Adventure Card Game");
		
		g.ifMatchRule(Deck_type, Set.of("Base Set", "Adventure Deck", "Class Deck", "Character Add-On Deck", "Card game"), "{} card games");
		g.ifMatchRule(Deck_type, Set.of("Item Cards", "Face Cards", "Spell Cards", "Cards"), "{} accessories");
		g.ifYearAndMatchRule(Deck_type, Set.of("Base Set", "Adventure Deck", "Class Deck", "Character Add-On Deck", "Card game"), "Card games");
		g.ifYearAndMatchRule(Deck_type, Set.of("Item Cards", "Face Cards", "Spell Cards", "Cards"), "Accessories");
		g.ifMatchRule(Deck_type, "Base Set", "Base sets");
		g.ifMatchRule(Deck_type, "Adventure Deck", "Adventure decks");
		g.ifMatchRule(Deck_type, "Class Deck", "Class decks");
		g.ifMatchRule(Deck_type, "Character Add-On Deck", "Character add-on decks");
		g.ifMatchRule(Deck_type, "Item Cards", "Item cards");
		g.ifMatchRule(Deck_type, "Face Cards", "Face cards");
		g.ifMatchRule(Deck_type, "Spell Cards", "Spell cards");
		g.ifNoMatchRule(Deck_type, Set.of("Base Set","Adventure Deck","Class Deck",
				"Character Add-On Deck","Item Cards","Face Cards","Spell Cards",
				"Campaign Cards","Cards","Card game"),
				ctx->"Decks");
		g.ifYearRule("{} decks");
	}

	void addVideoGameRules(ACGroup g) {
		g.ifMatchRule(Video_game_type, "Computer RPG", ctx->"RPG video games");
		g.ifNoMatchRule(Video_game_type, Set.of("Computer RPG"), ctx->"Video games");
	}

	void addMapRules(ACGroup g) {
		g.rule(
				ctx-> "[[:Category:Accessories]]",
				ctx-> "always",
				ctx-> ctx.addCategory("Accessories")
			);
		g.ifYearRule("{} accessories");
		
		g.rule(
				ctx-> "Category:Images of REGION",
				ctx-> "for each [[Region::@@@]]",
				ctx-> ctx.getSubject()
					.getOr(Region, Collections.emptyList())
					.forEach(r->ctx.addCategory("Images of "+r.toDisplayTitleWikitext()))
			).onlyIf(ctx->ctx.has(Region));
		
		g.ifMatchRule(Map_type, "Poster Map Folio", "Poster Map Folios");
		g.ifMatchRule(Map_type, "Map tiles", ctx->ctx.getWiki().getName()+" Flip-Tiles");
		g.ifMatchRule(Map_type, "Map Pack", ctx->ctx.getWiki().getName()+" Map Packs");
	}

	void addAccessoryRules(ACGroup g) {
		g.rule(
				ctx-> "[[:Category:Accessories]]",
				ctx-> "always",
				ctx-> ctx.addCategory("Accessories")
			);
		g.ifYearRule("{} accessories");
	}

	void addBoardgameRules(ACGroup g) {
		g.rule(
				ctx-> "[[:Category:Board games]]",
				ctx-> "always",
				ctx-> ctx.addCategory("Board games")
			);
		g.ifYearRule("{} board games");
		g.rule(
				ctx-> "[[:Category:Licensed board games]]",
				ctx-> "if [[Publisher::@@@]] does not contain [[Paizo Inc.]]",
				ctx-> {
					if(ctx.getSubject().getOr(Publisher, Collections.emptyList()).stream().noneMatch(p->p.getTitle().equals("Paizo Inc."))) {
						ctx.addCategory("Licensed board games");
					}
				}
			);
	}

	private void addGenericRules() {
		rules.rule(
				ctx-> "[[:Category:Products with errata]]",
				ctx-> "if [[Errata::@@@]] is set",
				ctx-> ctx.addCategory("Products with errata")
			).onlyIf(ctx->ctx.has(Errata));
		
		rules.rule(
				ctx-> "[[:Category:Products with web enhancements]]",
				ctx-> "if [[Web enhancement::@@@]] is set",
				ctx-> ctx.addCategory("Products with web enhancements")
			).onlyIf(ctx->ctx.has(Web_enhancement));
		
		rules.rule(
				ctx-> "Category:Works by AUTHOR",
				ctx-> "for each [[Author_all::@@@]]",
				ctx-> {
					for(var e:ctx.getSubject().get(Author_all))
						ctx.addCategory("Works by "+e.toDisplayTitleWikitext());
				}
			).onlyIf(ctx->ctx.has(Author_all));
		
		rules.rule(
				ctx-> "Category:Works by AUTHOR",
				ctx-> "for each [[Author::@@@]]",
				ctx-> {
					for(var e:ctx.getSubject().get(Author))
						ctx.addCategory("Works by "+e.toDisplayTitleWikitext());
				}
			).onlyIf(ctx->ctx.has(Author));
		
		rules.rule(
				ctx-> "Category:Artwork by ARTIST",
				ctx-> "for each [[Artist::@@@]]",
				ctx-> {
					for(var e:ctx.getSubject().get(Artist))
						ctx.addCategory("Artwork by "+e.toDisplayTitleWikitext());
				}
			).onlyIf(ctx->ctx.has(Artist));
		
		rules.rule(
				ctx-> "Category:Works by DIRECTOR",
				ctx-> "for each [[Director::@@@]]",
				ctx-> {
					for(var e:ctx.getSubject().get(Director))
						ctx.addCategory("Works by "+e.toDisplayTitleWikitext());
				}
			).onlyIf(ctx->ctx.has(Director));
		
		rules.rule(
				ctx-> "Category:Works starring PERFORMER",
				ctx-> "for each [[Performer::@@@]]",
				ctx-> {
					for(var e:ctx.getSubject().get(Performer))
						ctx.addCategory("Works starring "+e.toDisplayTitleWikitext());
				}
			).onlyIf(ctx->ctx.has(Performer));
		
		rules.rule(
				ctx-> "Category:Works starring NARRATOR",
				ctx-> "for each [[Narrator::@@@]]",
				ctx-> {
					for(var e:ctx.getSubject().get(Narrator))
						ctx.addCategory("Works starring "+e.toDisplayTitleWikitext());
				}
			).onlyIf(ctx->ctx.has(Narrator));
		
		rules.rule(
				ctx-> "the category for each [[Series::@@@]]",
				ctx-> "as set on the Series Facts page; see [[#Details|Details]]",
				ctx-> {
					for(var e:ctx.getSubject().get(Series)) {
						var cat = ctx.getSeries2Category().get(e.getTitle());
						if(cat != null)
							ctx.addCategory(cat);
					}
				}
			).onlyIf(ctx->ctx.has(Series));
		
		
	}

	public static String categoriesWikitext(Wiki wiki, SConcept concept, SemanticSubject subject, Map<String, String> series2Category) {
		var ctx = new ACContext(wiki, concept, subject, series2Category);
		INSTANCE.rules.calculateCategories(ctx);
		return ctx.categoriesWikitext();
	}
	
	public static String generateDocs(Wiki server, Map<String, String> series2Category) {
		var sb = new StringBuilder();
		for(var concept : SModel.getConcepts(server).stream().sorted(Comparator.comparing(SConcept::getName)).toList()) {
			var ctx = new ACContext(server, concept, null, series2Category);
			
			sb.append("===").append(concept.getPluralName()).append("===\n");
			var ruleDocs = INSTANCE.rules
				.docs(ctx)
				.filter(Objects::nonNull)
				.sorted(Comparator.comparing(RuleDoc::category).thenComparing(RuleDoc::explanation))
				.toList();
			
			if(ruleDocs.isEmpty()) {
				sb.append("No automatic categories yet.");
			}
			else {
				sb.append("<table class=\"wikitable\">\n<tr><th>Category added</th><th>rules</th></tr>\n");
				
				ruleDocs.forEach(rd->sb
						.append("<tr><td><code>")
						.append(rd.category())
						.append("</code></td><td>")
						.append(rd.explanation())
						.append("</tr>\n"));
				sb.append("</table>");
			}
			sb.append("\n\n");
		}
		sb
			.append("===Details===\n")
			.append("====Series ⇒ Category====\n")
			.append("<table class=\"wikitable\">\n")
			.append("<tr><th>Series</th><th>Resulting categories</th></tr>\n");
		
		series2Category.entrySet()
			.stream()
			.sorted(Comparator.comparing(e->e.getKey()))
			.forEach(e->sb
				.append("<tr><td>[[")
				.append(e.getKey())
				.append("]]</td><td>[[:")
				.append(e.getValue())
				.append("]]</td></tr>\n")
			);
		
		sb.append("</table>");
		return sb.toString();
	}
}
