package io.github.pfwikis.bots.rest.endpoints.infobox.autocategorization;

import static io.github.pfwikis.bots.facts.SFactsProperties.Release_year;

import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.github.pfwikis.bots.facts.model.SProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ACRule implements ACNode {
	
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
	
	public static record RuleDoc(String category, String explanation) {}

	@Override
	public void calculateCategories(ACContext ctx) {
		function.accept(ctx);
	}

	@Override
	public Stream<RuleDoc> docs(ACContext ctx) {
		return Optional.ofNullable(doc.apply(ctx)).stream();
	}
}
