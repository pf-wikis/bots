package io.github.pfwikis.bots.rest.endpoints.infobox.autocategorization;

import static io.github.pfwikis.bots.facts.SFactsProperties.Artist;
import static io.github.pfwikis.bots.facts.SFactsProperties.Audio_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Author;
import static io.github.pfwikis.bots.facts.SFactsProperties.Author_all;
import static io.github.pfwikis.bots.facts.SFactsProperties.Book_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Deck_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Director;
import static io.github.pfwikis.bots.facts.SFactsProperties.Errata;
import static io.github.pfwikis.bots.facts.SFactsProperties.Map_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Narrator;
import static io.github.pfwikis.bots.facts.SFactsProperties.Performer;
import static io.github.pfwikis.bots.facts.SFactsProperties.Publisher;
import static io.github.pfwikis.bots.facts.SFactsProperties.Region;
import static io.github.pfwikis.bots.facts.SFactsProperties.Rule_system;
import static io.github.pfwikis.bots.facts.SFactsProperties.Sanctioned;
import static io.github.pfwikis.bots.facts.SFactsProperties.Serialized;
import static io.github.pfwikis.bots.facts.SFactsProperties.Series;
import static io.github.pfwikis.bots.facts.SFactsProperties.Video_game_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Web_enhancement;
import static io.github.pfwikis.bots.facts.SModel.ACCESSORY;
import static io.github.pfwikis.bots.facts.SModel.AUDIO;
import static io.github.pfwikis.bots.facts.SModel.BOARD_GAME;
import static io.github.pfwikis.bots.facts.SModel.BOOK;
import static io.github.pfwikis.bots.facts.SModel.DECK;
import static io.github.pfwikis.bots.facts.SModel.MAP_PF;
import static io.github.pfwikis.bots.facts.SModel.MAP_SF;
import static io.github.pfwikis.bots.facts.SModel.MINIATURES;
import static io.github.pfwikis.bots.facts.SModel.VIDEO_GAME;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
		addMiniatureRules(rules.group(ctx->ctx.has(MINIATURES)));
		addAudioRules(rules.group(ctx->ctx.has(AUDIO)));
		addBookRules(rules.group(ctx->ctx.has(BOOK)));
	}
	
	void addBookRules(ACGroup g) {
		for(var wiki:Wiki.values()) {
			var wg = g.group(ctx->ctx.has(wiki));
			
			var adventureTypes = List.of(
				"Adventure",
				"Adventure Path issue",
				wiki.getName()+" Society scenario",
				wiki.getName()+" Society (2E) scenario",
				wiki.getName()+" Bounty",
				wiki.getName()+" Quest",
				wiki.getName()+" Quest (2E)",
				wiki.getName()+" One-Shot",
				wiki.getName()+" Society Adventure Card Guild Adventure"
			);
			var sAdventureTypes = ACGroup.sortedSet(adventureTypes);
			
			wg.ifMatchRule(Book_type, adventureTypes, "Adventures");
			wg.ifYearAndMatchRule(Book_type, adventureTypes, "{} adventures");
			
			wg.ifMatchRule(Book_type, "Adventure Path compilation", wiki.getName()+" Adventure Path");
			wg.ifMatchRule(Book_type, wiki.getName()+" Quest", wiki.getName()+" Quests");
			wg.ifMatchRule(Book_type, wiki.getName()+" Bounty", wiki.getName()+" Bounties");
			wg.ifMatchRule(Book_type, wiki.getName()+" One-Shot", wiki.getName()+" One-Shots");
			wg.ifMatchRule(Book_type, wiki.getName()+" Society Adventure Card Guild Adventure", wiki.getName()+" Society Adventure Card Guild scenarios");
			wg.ifMatchRule(Book_type, "Adventure Path issue", wiki.getName()+" Adventure Path");
			wg.ifMatchRule(Book_type, wiki.getName()+" Society scenario", wiki.getName()+" Society scenarios");
			wg.ifMatchRule(Book_type, wiki.getName()+" Society (2E) scenario", wiki.getName()+" Society (2E) scenarios");
			wg.rule(
					ctx-> "[[:Category:Retired "+wiki.getName()+" Society scenarios]]",
					ctx-> "if [[Sanctioned::@@@]] is <code>no</code> and "
							+ "[[Book type::@@@]] is <code>"+wiki.getName()+" Society scenario</code> "
									+ "or <code>"+wiki.getName()+" Society (2E) scenario</code>",
					ctx-> {
						if(Set.of(wiki.getName()+" Society scenario", wiki.getName()+" Society (2E) scenario").contains(ctx.getSubject().getOr(Book_type, ""))
								&& Boolean.FALSE.equals(ctx.getSubject().getOr(Sanctioned, null)))
							ctx.addCategory("Retired "+wiki.getName()+" Society scenarios");
					}
				).onlyIf(ctx->ctx.has(Book_type) && ctx.has(Sanctioned));

			var adventureGroup = wg.group(
					ctx->ctx.getSubject()==null || sAdventureTypes.contains(ctx.getSubject().getOr(Book_type, "")),
					ctx->" and [[Book type::@@@]] is one of "
							+ sAdventureTypes.stream().map(v->"<code>"+v+"</code>").collect(Collectors.joining(", "))
			);
			var notAdventureGroup = wg.group(
					ctx->ctx.getSubject()==null || !sAdventureTypes.contains(ctx.getSubject().getOr(Book_type, "")),
					ctx->" and [[Book type::@@@]] is '''not''' one of "
							+ sAdventureTypes.stream().map(v->"<code>"+v+"</code>").collect(Collectors.joining(", "))
			);
			
			for(boolean isAdventure:new boolean[] {true, false}) {
				String categoryWord = isAdventure?"adventures":"sourcebooks";
				var group = isAdventure?adventureGroup:notAdventureGroup;
				
				if(wiki == Wiki.PF) {
					group.ifMatchRule(Rule_system, "PFRPG", "PFRPG "+categoryWord);
					group.ifMatchRule(Rule_system, List.of("PF2", "Pathfinder Playtest"), "PFRPG 2E "+categoryWord);
				}
				group.ifMatchRule(Rule_system, "D&D 3.5", "D&D 3.5 "+categoryWord);
				group.ifMatchRule(Rule_system, "D&D 5E", "D&D 5E "+categoryWord);
				group.ifMatchRule(Rule_system, "PACG", "PACG "+categoryWord);
				group.ifMatchRule(Rule_system, "OGL", "PACG "+categoryWord);
				group.ifMatchRule(Rule_system, "Savage Worlds", "Savage Worlds "+categoryWord);
				if(wiki == Wiki.SF) {
					group.ifMatchRule(Rule_system, "SFRPG", "SFRPG "+categoryWord);
					group.ifMatchRule(Rule_system, "SF2 Playtest", "SFRPG 2E "+categoryWord);
				}
			}
		}
		
		
		
		g.ifMatchRule(Book_type, "Sourcebook", "Sourcebooks");
		g.ifYearAndMatchRule(Book_type, "Sourcebook", "{} sourcebooks");
		g.ifMatchRule(Book_type, "Novel", "Novels");
		g.ifMatchRule(Book_type, "Novel", "Fiction");
		g.ifYearAndMatchRule(Book_type, List.of("Novel", "Novella", "Periodical", "Comic book", "Short Fiction"), "{} fiction");
		g.ifMatchRule(Book_type, "Adventure Path compilation", "Adventure compilations");
		g.ifMatchRule(Book_type, "Novella", "Novellas");
		g.ifMatchRule(Book_type, "Novella", "Fiction");
		g.ifMatchRule(Book_type, "Periodical", "Periodicals");
		g.ifMatchRule(Book_type, "Periodical", "Fiction");
		g.ifMatchRule(Book_type, "Short Fiction", "Fiction");
		g.ifMatchRule(Book_type, "Comic book", "Comics");
		g.ifMatchRule(Book_type, "Comic book", "Fiction");
		
		g.ifYearAndMatchRule(Book_type, "Periodical", "{} periodicals");
		g.ifYearAndMatchRule(Book_type, "Comic book", "{} comics");
		
		var comic = g.group(
				ctx->ctx.getSubject()==null||ctx.getSubject().getOr(Book_type, "").equals("Comic book"),
				ctx->" and [[Serialized::@@@]] is <code>Comic book</code>"
		);
		
		comic.rule(
				ctx-> "Category:SERIES comics",
				ctx-> "for each [[Series::@@@]]",
				ctx-> {
					for(var e:ctx.getSubject().get(Series)) {
						ctx.addCategory(e.getTitle()+" comics");
					}
				}
			).onlyIf(ctx->ctx.has(Series));
		comic.rule(
				ctx-> "Category:Comics by AUTHOR",
				ctx-> "for each [[Author all::@@@]]",
				ctx-> {
					for(var e:ctx.getSubject().get(Author_all)) {
						ctx.addCategory("Comics by "+e.toDisplayTitleWikitext());
					}
				}
			).onlyIf(ctx->ctx.has(Series));

		g.group(ctx->ctx.has(Serialized), ctx->" and [[Serialized::@@@]] is set")
			.ifMatchRule(Book_type, List.of("Novel", "Short Fiction", "Novella"), "Serial fiction");
	}

	void addAudioRules(ACGroup g) {
		g.ifMatchRule(Audio_type, "Full-cast Audio Drama", "Audio dramas");
		g.ifMatchRule(Audio_type, "Soundscape", "Soundscapes");
		g.ifMatchRule(Audio_type, List.of("Game score", "Soundtrack"), "Soundtracks");
		g.ifYearAndMatchRule(Audio_type, "Full-cast Audio Drama", "{} audio dramas");
		g.ifYearAndMatchRule(Audio_type, "Soundscape", "{} soundscapes");
		g.ifYearAndMatchRule(Audio_type, List.of("Game score", "Soundtrack"), "{} soundtracks");
	}

	void addMiniatureRules(ACGroup g) {
		g.rule(
				ctx-> "[[:Category:Miniatures]]",
				ctx-> "always",
				ctx-> ctx.addCategory("Miniatures")
			);
		g.ifYearRule("{} miniatures");
	}

	void addDeckRules(ACGroup g) {
		g.ifMatchRule(Rule_system, "PACG", "Pathfinder Adventure Card Game");
		g.ifYearAndMatchRule(Deck_type, List.of("Base Set", "Adventure Deck", "Class Deck", "Character Add-On Deck", "Card game"), "{} card games");
		g.ifYearAndMatchRule(Deck_type, List.of("Item Cards", "Face Cards", "Spell Cards", "Cards"), "{} accessories");
		g.ifYearAndMatchRule(Deck_type, List.of("Base Set", "Adventure Deck", "Class Deck", "Character Add-On Deck", "Card game"), "Card games");
		g.ifYearAndMatchRule(Deck_type, List.of("Item Cards", "Face Cards", "Spell Cards", "Cards"), "Accessories");
		g.ifMatchRule(Deck_type, "Base Set", "Base sets");
		g.ifMatchRule(Deck_type, "Adventure Deck", "Adventure decks");
		g.ifMatchRule(Deck_type, "Class Deck", "Class decks");
		g.ifMatchRule(Deck_type, "Character Add-On Deck", "Character add-on decks");
		g.ifMatchRule(Deck_type, "Item Cards", "Item cards");
		g.ifMatchRule(Deck_type, "Face Cards", "Face cards");
		g.ifMatchRule(Deck_type, "Spell Cards", "Spell cards");
		g.ifNoMatchRule(Deck_type, List.of("Base Set","Adventure Deck","Class Deck",
				"Character Add-On Deck","Item Cards","Face Cards","Spell Cards",
				"Campaign Cards","Cards","Card game"),
				ctx->"Decks");
		g.ifYearRule("{} decks");
	}

	void addVideoGameRules(ACGroup g) {
		g.ifMatchRule(Video_game_type, "Computer RPG", ctx->"RPG video games");
		g.ifNoMatchRule(Video_game_type, List.of("Computer RPG"), ctx->"Video games");
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
