package io.github.pfwikis.bots.rest.endpoints.infobox.autocategorization;

import static io.github.pfwikis.bots.facts.SFactsProperties.Artist;
import static io.github.pfwikis.bots.facts.SFactsProperties.Author;
import static io.github.pfwikis.bots.facts.SFactsProperties.Author_all;
import static io.github.pfwikis.bots.facts.SFactsProperties.Director;
import static io.github.pfwikis.bots.facts.SFactsProperties.Errata;
import static io.github.pfwikis.bots.facts.SFactsProperties.Narrator;
import static io.github.pfwikis.bots.facts.SFactsProperties.Performer;
import static io.github.pfwikis.bots.facts.SFactsProperties.Series;
import static io.github.pfwikis.bots.facts.SFactsProperties.Web_enhancement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.model.subject.SemanticSubject;
import io.github.pfwikis.bots.facts.SModel;
import io.github.pfwikis.bots.facts.model.SConcept;

public enum AutoCategorizer {

	INSTANCE;
	
	private List<ACRule> rules = new ArrayList<>();
	
	private AutoCategorizer() {
		rules.add(new ACRule(
				ctx-> "if [[Errata::@@@]] is set: add [[:Category:Products with errata]]",
				ctx-> ctx.addCategory("Products with errata")
			).onlyIf(ctx->ctx.has(Errata)));
		
		rules.add(new ACRule(
				ctx-> "if [[Web enhancement::@@@]] is set: add [[:Category:Products with web enhancements]]",
				ctx-> ctx.addCategory("Products with web enhancements")
			).onlyIf(ctx->ctx.has(Web_enhancement)));
		
		rules.add(new ACRule(
				ctx-> "add <code>Category:Works by AUTHOR</code> for each [[Author_all::@@@]]",
				ctx-> {
					for(var e:ctx.getSubject().get(Author_all))
						ctx.addCategory("Works by "+e.toDisplayTitleWikitext());
				}
			).onlyIf(ctx->ctx.has(Author_all)));
		
		rules.add(new ACRule(
				ctx-> "add <code>Category:Works by AUTHOR</code> for each [[Author::@@@]]",
				ctx-> {
					for(var e:ctx.getSubject().get(Author))
						ctx.addCategory("Works by "+e.toDisplayTitleWikitext());
				}
			).onlyIf(ctx->ctx.has(Author)));
		
		rules.add(new ACRule(
				ctx-> "add <code>Category:Artwork by ARTIST</code> for each [[Artist::@@@]]",
				ctx-> {
					for(var e:ctx.getSubject().get(Artist))
						ctx.addCategory("Artwork by "+e.toDisplayTitleWikitext());
				}
			).onlyIf(ctx->ctx.has(Artist)));
		
		rules.add(new ACRule(
				ctx-> "add <code>Category:Works by DIRECTOR</code> for each [[Director::@@@]]",
				ctx-> {
					for(var e:ctx.getSubject().get(Director))
						ctx.addCategory("Works by "+e.toDisplayTitleWikitext());
				}
			).onlyIf(ctx->ctx.has(Director)));
		
		rules.add(new ACRule(
				ctx-> "add <code>Category:Works starring PERFORMER</code> for each [[Performer::@@@]]",
				ctx-> {
					for(var e:ctx.getSubject().get(Performer))
						ctx.addCategory("Works starring "+e.toDisplayTitleWikitext());
				}
			).onlyIf(ctx->ctx.has(Performer)));
		
		rules.add(new ACRule(
				ctx-> "add <code>Category:Works starring NARRATOR</code> for each [[Narrator::@@@]]",
				ctx-> {
					for(var e:ctx.getSubject().get(Narrator))
						ctx.addCategory("Works starring "+e.toDisplayTitleWikitext());
				}
			).onlyIf(ctx->ctx.has(Narrator)));
		
		rules.add(new ACRule(
				ctx-> "add the category for each [[Series::@@@]], that the <code>SERIES</code> is the {{tl|Main}} article of (see [[#Details|Details]])",
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
			for(var r:INSTANCE.rules) {
				var txt = r.getDoc().apply(ctx);
				if(StringUtils.isNotBlank(txt)) {
					sb.append("* ").append(txt).append("\n");
				}
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
