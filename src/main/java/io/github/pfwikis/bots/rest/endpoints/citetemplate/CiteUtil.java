package io.github.pfwikis.bots.rest.endpoints.citetemplate;

import java.util.Set;

import io.github.pfwikis.bots.common.api.model.PageTitle;
import io.github.pfwikis.bots.facts.SModel;
import io.github.pfwikis.bots.facts.model.SConcept;

public class CiteUtil {

	private static Set<String> TYPES_WITH_CITE = Set.of(
		"Facts/"+SModel.ACCESSORY.getName(),
		"Facts/"+SModel.BOOK.getName(),
		"Facts/Map",
		"Facts/Deck"+SModel.DECK.getName(),
		"Facts/Single book adventure path",
		"Facts/"+SModel.VIDEO_GAME.getName(),
		"Facts/Web citation"+SModel.WEB_CITATION.getName(),
		"Facts/Miniatures"+SModel.MINIATURES.getName()
	);

	public static boolean isCiteable(PageTitle type) {
		return TYPES_WITH_CITE.contains(type.getName());
	}

	public static boolean isCiteable(SConcept c) {
		return TYPES_WITH_CITE.contains("Facts/"+c.getName());
	}
}
