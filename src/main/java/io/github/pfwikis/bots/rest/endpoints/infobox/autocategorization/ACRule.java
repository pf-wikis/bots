package io.github.pfwikis.bots.rest.endpoints.infobox.autocategorization;

import static io.github.pfwikis.bots.facts.SFactsProperties.Release_year;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import io.github.pfwikis.bots.facts.model.SProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ACRule {
	
	@NonNull
	private Function<ACContext, RuleDoc> doc;
	@NonNull
	private Consumer<ACContext> function;
	
	public ACRule onlyIf(Predicate<ACContext> filter) {
		var oldDoc = doc;
		doc = ctx -> {
			if(filter.test(ctx)) {
				return oldDoc.apply(ctx);
			}
			return null;
		};
		
		var oldFunction = function;
		function = ctx -> {
			if(filter.test(ctx)) {
				oldFunction.accept(ctx);
			}
		};
		return this;
	}

	public static ACRule ifYear(String category) {
		return new ACRule(
			ctx-> new RuleDoc("Category:"+category.replace("{}", "YEAR"), "if [[Release year::@@@]] is set"),
			ctx-> ctx.addCategory(category.replace("{}", ctx.getSubject().get(Release_year).toString()))
		).onlyIf(ctx->ctx.has(Release_year));
	}
	
	public static ACRule ifMatch(SProperty<String> prop, String value, Function<ACContext, String> categoryName) {
		return new ACRule(
			ctx-> new RuleDoc("Category:"+categoryName.apply(ctx), "if [["+prop.getName()+"::@@@]] is exactly <code>"+value+"</code>"),
			ctx-> {
				if(ctx.getSubject().getOr(prop, "").equals(value))
					ctx.addCategory(categoryName.apply(ctx));
			}
		).onlyIf(ctx->ctx.has(prop));
	}
	
	public static record RuleDoc(String category, String explanation) {}
}
