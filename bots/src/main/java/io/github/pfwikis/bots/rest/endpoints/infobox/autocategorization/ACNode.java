package io.github.pfwikis.bots.rest.endpoints.infobox.autocategorization;

import java.util.stream.Stream;

import io.github.pfwikis.bots.rest.endpoints.infobox.autocategorization.ACRule.RuleDoc;

public interface ACNode {

	void calculateCategories(ACContext ctx);

	Stream<RuleDoc> docs(ACContext ctx);

}
