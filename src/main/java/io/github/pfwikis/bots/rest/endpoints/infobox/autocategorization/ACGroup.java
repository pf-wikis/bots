package io.github.pfwikis.bots.rest.endpoints.infobox.autocategorization;

import static io.github.pfwikis.bots.facts.SFactsProperties.Release_year;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.SequencedSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import io.github.pfwikis.bots.facts.model.SProperty;
import io.github.pfwikis.bots.rest.endpoints.infobox.autocategorization.ACRule.RuleDoc;

public class ACGroup implements ACNode {

	private Predicate<ACContext> filter = v->true;
	private List<ACNode> rules = new ArrayList<>();
	private Function<ACContext, String> extraDocs;
	
	@Override
	public void calculateCategories(ACContext ctx) {
		if(!filter.test(ctx)) return;
		for(var n:rules) {
			n.calculateCategories(ctx);
		}
	}

	@Override
	public Stream<RuleDoc> docs(ACContext ctx) {
		if(!filter.test(ctx)) return Stream.empty();
		return rules.stream()
			.flatMap(r->r.docs(ctx))
			.map(rd->new RuleDoc(rd.category(), rd.explanation()+(extraDocs!=null?extraDocs.apply(ctx):"")));
	}
	
	
	public ACGroup group(Predicate<ACContext> filter) {
		return group(filter, null);
	}
	
	public ACGroup group(Predicate<ACContext> filter, Function<ACContext, String> extraDocs) {
		var g = new ACGroup();
		g.filter = filter;
		g.extraDocs = extraDocs;
		rules.add(g);
		return g;
	}
	
	public ACRule rule(
			Function<ACContext, String> docCategory,
			Function<ACContext, String> docExplanation,
			Consumer<ACContext> function
	) {
		var r = new ACRule(
				ctx->new RuleDoc(docCategory.apply(ctx), docExplanation.apply(ctx)),
				function
		);
		rules.add(r);
		return r;
	}

	public ACRule ifYearRule(String category) {
		return rule(
				ctx-> "Category:"+category.replace("{}", "YEAR"),
				ctx-> "if [[Release year::@@@]] is set",
				ctx-> ctx.addCategory(category.replace("{}", ctx.getSubject().get(Release_year).toString()))
			).onlyIf(ctx->ctx.has(Release_year));
	}
	
	public ACRule ifYearAndMatchRule(SProperty<String> prop, String value, String category) {
		return ifYearAndMatchRule(prop, List.of(value), category);
	}
	
	public ACRule ifYearAndMatchRule(SProperty<String> prop, List<String> values, String category) {
		var valueSet = sortedSet(values);
		return rule(
				ctx-> "Category:"+category.replace("{}", "YEAR"),
				ctx-> "if [[Release year::@@@]] is set and [["+prop.getName()+"::@@@]] is "+(valueSet.size()==1?"":"one of ")
					+ valueSet.stream().map(v->"<code>"+v+"</code>").collect(Collectors.joining(", ")),
				ctx-> {
					if(valueSet.contains(ctx.getSubject().getOr(prop, "")))
						ctx.addCategory(category.replace("{}", ctx.getSubject().get(Release_year).toString()));
				}
			).onlyIf(ctx->ctx.has(Release_year));
	}
	
	public ACRule ifMatchRule(SProperty<String> prop, String value, String categoryName) {
		return ifMatchRule(prop, value, ctx->categoryName);
	}
	
	public ACRule ifMatchRule(SProperty<String> prop, String value, Function<ACContext, String> categoryName) {
		return ifMatchRule(prop, List.of(value), categoryName);
	}
	
	public ACRule ifMatchRule(SProperty<String> prop, List<String> values, String categoryName) {
		return ifMatchRule(prop, values, ctx->categoryName);
	}
	
	public ACRule ifMatchRule(SProperty<String> prop, List<String> values, Function<ACContext, String> categoryName) {
		var valueSet = sortedSet(values);
		return rule(
			ctx-> "[[:Category:"+categoryName.apply(ctx)+"]]",
			ctx-> "if [["+prop.getName()+"::@@@]] is "+(valueSet.size()==1?"":"one of ")
				+ valueSet.stream().map(v->"<code>"+v+"</code>").collect(Collectors.joining(", ")),
			ctx-> {
				if(valueSet.contains(ctx.getSubject().getOr(prop, "")))
					ctx.addCategory(categoryName.apply(ctx));
			}
		).onlyIf(ctx->ctx.has(prop));
	}
	
	public ACRule ifNoMatchRule(SProperty<String> prop, List<String> values, Function<ACContext, String> categoryName) {
		var valueSet = sortedSet(values);
		return rule(
			ctx-> "[[:Category:"+categoryName.apply(ctx)+"]]",
			ctx -> "if [["+prop.getName()+"::@@@]] is '''not''' "+(valueSet.size()==1?"":"one of ")
				+ valueSet.stream().map(v->"<code>"+v+"</code>").collect(Collectors.joining(", ")),
			ctx-> {
				if(!valueSet.contains(ctx.getSubject().getOr(prop, "")))
					ctx.addCategory(categoryName.apply(ctx));
			}
		).onlyIf(ctx->ctx.has(prop));
	}

	public static SequencedSet<String> sortedSet(List<String> values) {
		var res = new LinkedHashSet<String>();
		values.stream().sorted().forEach(res::add);
		return res;
	}
}
