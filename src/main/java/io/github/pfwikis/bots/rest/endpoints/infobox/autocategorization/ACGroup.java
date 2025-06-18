package io.github.pfwikis.bots.rest.endpoints.infobox.autocategorization;

import static io.github.pfwikis.bots.facts.SFactsProperties.Release_year;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
			.flatMap(r->r.docs(ctx));
	}
	
	
	public ACGroup group(Predicate<ACContext> filter) {
		var g = new ACGroup();
		g.filter = filter;
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
	
	public ACRule ifYearAndMatchRule(SProperty<String> prop, Set<String> values, String category) {
		return rule(
				ctx-> "Category:"+category.replace("{}", "YEAR"),
				ctx-> "if [[Release year::@@@]] is set and [["+prop.getName()+"::@@@]] is "+(values.size()==1?"exactly ":"one of ")
					+ values.stream().map(v->"<code>"+v+"</code>").collect(Collectors.joining(", ")),
				ctx-> {
					if(values.contains(ctx.getSubject().getOr(prop, "")))
						ctx.addCategory(category.replace("{}", ctx.getSubject().get(Release_year).toString()));
				}
			).onlyIf(ctx->ctx.has(Release_year));
	}
	
	public ACRule ifMatchRule(SProperty<String> prop, String value, String categoryName) {
		return ifMatchRule(prop, value, ctx->categoryName);
	}
	
	public ACRule ifMatchRule(SProperty<String> prop, String value, Function<ACContext, String> categoryName) {
		return ifMatchRule(prop, Set.of(value), categoryName);
	}
	
	public ACRule ifMatchRule(SProperty<String> prop, Set<String> values, String categoryName) {
		return ifMatchRule(prop, values, ctx->categoryName);
	}
	
	public ACRule ifMatchRule(SProperty<String> prop, Set<String> values, Function<ACContext, String> categoryName) {
		return rule(
			ctx-> "[[:Category:"+categoryName.apply(ctx)+"]]",
			ctx-> "if [["+prop.getName()+"::@@@]] is "+(values.size()==1?"exactly ":"one of ")
				+ values.stream().map(v->"<code>"+v+"</code>").collect(Collectors.joining(", ")),
			ctx-> {
				if(values.contains(ctx.getSubject().getOr(prop, "")))
					ctx.addCategory(categoryName.apply(ctx));
			}
		).onlyIf(ctx->ctx.has(prop));
	}
	
	public ACRule ifNoMatchRule(SProperty<String> prop, Set<String> values, Function<ACContext, String> categoryName) {
		return rule(
			ctx-> "[[:Category:"+categoryName.apply(ctx)+"]]",
			ctx -> "if [["+prop.getName()+"::@@@]] is '''not''' "+(values.size()==1?"exactly ":"one of ")
				+ values.stream().map(v->"<code>"+v+"</code>").collect(Collectors.joining(", ")),
			ctx-> {
				if(!values.contains(ctx.getSubject().getOr(prop, "")))
					ctx.addCategory(categoryName.apply(ctx));
			}
		).onlyIf(ctx->ctx.has(prop));
	}
}
