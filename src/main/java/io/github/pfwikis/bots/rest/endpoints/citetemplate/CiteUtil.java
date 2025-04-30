package io.github.pfwikis.bots.rest.endpoints.citetemplate;

import java.util.Set;

import io.github.pfwikis.bots.common.model.subject.PageRef;
import io.github.pfwikis.bots.facts.model.SConcept;

public class CiteUtil {

	private static Set<String> TYPES_WITH_CITE = Set.of(
		"Facts/Accessory",
		"Facts/Book",
		"Facts/Map",
		"Facts/Deck",
		"Facts/Video game",
		"Facts/Web citation",
		"Facts/Miniatures"
	);

	public static boolean isCiteable(PageRef type) {
		return TYPES_WITH_CITE.contains(type.getTitle());
	}

	public static boolean isCiteable(SConcept c) {
		return TYPES_WITH_CITE.contains("Facts/"+c.getName());
	}
}
