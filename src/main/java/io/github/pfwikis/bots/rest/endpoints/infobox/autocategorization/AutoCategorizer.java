package io.github.pfwikis.bots.rest.endpoints.infobox.autocategorization;

import static io.github.pfwikis.bots.facts.SFactsProperties.Artist;
import static io.github.pfwikis.bots.facts.SFactsProperties.Author;
import static io.github.pfwikis.bots.facts.SFactsProperties.Author_all;
import static io.github.pfwikis.bots.facts.SFactsProperties.Director;
import static io.github.pfwikis.bots.facts.SFactsProperties.Errata;
import static io.github.pfwikis.bots.facts.SFactsProperties.Narrator;
import static io.github.pfwikis.bots.facts.SFactsProperties.Performer;
import static io.github.pfwikis.bots.facts.SFactsProperties.Publisher;
import static io.github.pfwikis.bots.facts.SFactsProperties.Release_year;
import static io.github.pfwikis.bots.facts.SFactsProperties.Series;
import static io.github.pfwikis.bots.facts.SFactsProperties.Web_enhancement;
import static io.github.pfwikis.bots.facts.SModel.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Comparators;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.model.subject.SemanticSubject;
import io.github.pfwikis.bots.facts.SModel;
import io.github.pfwikis.bots.facts.model.SConcept;
import io.github.pfwikis.bots.rest.endpoints.infobox.autocategorization.ACRule.RuleDoc;

public enum AutoCategorizer {

	INSTANCE;
	
	private List<ACRule> rules = new ArrayList<>();
	
	private AutoCategorizer() {
		addGenericRules();
		addBoardgameRules();
	}
	
	void addBoardgameRules() {
		int index = rules.size();
		rules.add(new ACRule(
				ctx-> new RuleDoc("[[:Category:Board games]]", "if [[Release year::@@@]] is '''not''' set"),
				ctx-> ctx.addCategory("Board games")
			).onlyIf(ctx->ctx.hasUnset(Release_year)));
		rules.add(ACRule.ifYear("{} board games"));
		rules.add(new ACRule(
				ctx-> new RuleDoc("[[:Category:Licensed board games]]", "if [[Publisher::@@@]] does not contain [[Paizo Inc.]]"),
				ctx-> {
					if(ctx.getSubject().getOr(Publisher, Collections.emptyList()).stream().noneMatch(p->p.getTitle().equals("Paizo Inc."))) {
						ctx.addCategory("Licensed board games");
					}
				}
			));

		for(;index<rules.size();index++) {
			rules.set(index, rules.get(index).onlyIf(ctx->ctx.has(BOARD_GAME)));
		}
	}

	private void addGenericRules() {
		rules.add(new ACRule(
				ctx-> new RuleDoc("[[:Category:Products with errata]]", "if [[Errata::@@@]] is set"),
				ctx-> ctx.addCategory("Products with errata")
			).onlyIf(ctx->ctx.has(Errata)));
		
		rules.add(new ACRule(
				ctx-> new RuleDoc("[[:Category:Products with web enhancements]]", "if [[Web enhancement::@@@]] is set"),
				ctx-> ctx.addCategory("Products with web enhancements")
			).onlyIf(ctx->ctx.has(Web_enhancement)));
		
		rules.add(new ACRule(
				ctx-> new RuleDoc("Category:Works by AUTHOR", "for each [[Author_all::@@@]]"),
				ctx-> {
					for(var e:ctx.getSubject().get(Author_all))
						ctx.addCategory("Works by "+e.toDisplayTitleWikitext());
				}
			).onlyIf(ctx->ctx.has(Author_all)));
		
		rules.add(new ACRule(
				ctx-> new RuleDoc("Category:Works by AUTHOR", "for each [[Author::@@@]]"),
				ctx-> {
					for(var e:ctx.getSubject().get(Author))
						ctx.addCategory("Works by "+e.toDisplayTitleWikitext());
				}
			).onlyIf(ctx->ctx.has(Author)));
		
		rules.add(new ACRule(
				ctx-> new RuleDoc("Category:Artwork by ARTIST", "for each [[Artist::@@@]]"),
				ctx-> {
					for(var e:ctx.getSubject().get(Artist))
						ctx.addCategory("Artwork by "+e.toDisplayTitleWikitext());
				}
			).onlyIf(ctx->ctx.has(Artist)));
		
		rules.add(new ACRule(
				ctx-> new RuleDoc("Category:Works by DIRECTOR", "for each [[Director::@@@]]"),
				ctx-> {
					for(var e:ctx.getSubject().get(Director))
						ctx.addCategory("Works by "+e.toDisplayTitleWikitext());
				}
			).onlyIf(ctx->ctx.has(Director)));
		
		rules.add(new ACRule(
				ctx-> new RuleDoc("Category:Works starring PERFORMER", "for each [[Performer::@@@]]"),
				ctx-> {
					for(var e:ctx.getSubject().get(Performer))
						ctx.addCategory("Works starring "+e.toDisplayTitleWikitext());
				}
			).onlyIf(ctx->ctx.has(Performer)));
		
		rules.add(new ACRule(
				ctx-> new RuleDoc("Category:Works starring NARRATOR", "for each [[Narrator::@@@]]"),
				ctx-> {
					for(var e:ctx.getSubject().get(Narrator))
						ctx.addCategory("Works starring "+e.toDisplayTitleWikitext());
				}
			).onlyIf(ctx->ctx.has(Narrator)));
		
		rules.add(new ACRule(
				ctx-> new RuleDoc("the category for each [[Series::@@@]]", "as set on the Series Facts page; see [[#Details|Details]]"),
				ctx-> {
					for(var e:ctx.getSubject().get(Series)) {
						var cat = ctx.getSeries2Category().get(e.getTitle());
						if(cat != null)
							ctx.addCategory(cat);
					}
				}
			).onlyIf(ctx->ctx.has(Series)));
		
		
	}

	public static String categoriesWikitext(SConcept concept, SemanticSubject subject, Map<String, String> series2Category) {
		var ctx = new ACContext(concept, subject, series2Category);
		for(var r:INSTANCE.rules) {
			r.getFunction().accept(ctx);
		}
		return ctx.categoriesWikitext();
	}
	
	public static String generateDocs(Wiki server, Map<String, String> series2Category) {
		var sb = new StringBuilder();
		for(var concept : SModel.getConcepts(server).stream().sorted(Comparator.comparing(SConcept::getName)).toList()) {
			var ctx = new ACContext(concept, null, series2Category);
			
			sb.append("===").append(concept.getPluralName()).append("===\n");
			var ruleDocs = INSTANCE.rules
				.stream()
				.map(r-> r.getDoc().apply(ctx))
				.filter(Objects::nonNull)
				.sorted(Comparator.comparing(RuleDoc::category).thenComparing(RuleDoc::explanation))
				.toList();
			
			if(ruleDocs.isEmpty()) {
				sb.append("No automatic categories yet.\n");
			}
			else {
				sb.append("<table class=\"wikitable\"><tr><th>Category added</th><th>rules</th></tr>");
				
				ruleDocs.forEach(rd->sb
						.append("<tr><td><code>")
						.append(rd.category())
						.append("</code></td><td>")
						.append(rd.explanation())
						.append("</tr>"));
				sb.append("</table>");
			}
			sb.append("\n\n");
		}
		sb
			.append("===Details===\n")
			.append("====Series ⇒ Category====\n")
			.append("<table class=\"wikitable\">")
			.append("<tr><th>Series</th><th>Resulting categories</th></tr>");
		
		series2Category.entrySet()
			.stream()
			.sorted(Comparator.comparing(e->e.getKey()))
			.forEach(e->sb
				.append("<tr><td>[[")
				.append(e.getKey())
				.append("]]</td><td>[[:")
				.append(e.getValue())
				.append("]]</td></tr>")
			);
		
		sb.append("</table>");
		return sb.toString();
	}
}
