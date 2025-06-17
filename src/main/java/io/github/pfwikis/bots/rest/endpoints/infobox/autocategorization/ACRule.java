package io.github.pfwikis.bots.rest.endpoints.infobox.autocategorization;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ACRule {
	
	@NonNull
	private Function<ACContext, String> doc;
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
}
