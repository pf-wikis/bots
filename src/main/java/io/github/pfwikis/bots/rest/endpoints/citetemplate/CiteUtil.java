package io.github.pfwikis.bots.rest.endpoints.citetemplate;

import java.util.Collection;
import java.util.Set;

import io.github.pfwikis.bots.common.api.model.PageTitle;
import io.github.pfwikis.bots.facts.SModel;
import io.github.pfwikis.bots.facts.model.SConcept;

public class CiteUtil {

	private static Set<PageTitle> TYPES_WITH_CITE = Set.of(
		SModel.ACCESSORY.getFactType(),
		SModel.BOOK.getFactType(),
		PageTitle.of("Template:Facts/Map"),
		SModel.DECK.getFactType(),
		PageTitle.of("Template:Facts/Single book adventure path"),
		SModel.VIDEO_GAME.getFactType(),
		SModel.WEB_CITATION.getFactType(),
		SModel.MINIATURES.getFactType()
	);
	
	public static boolean isCiteable(Collection<PageTitle> types) {
		for(var t:types) {
			if(isCiteable(t)) return true;
		}
		return false;
	}

	public static boolean isCiteable(PageTitle type) {
		return TYPES_WITH_CITE.contains(type);
	}

	public static boolean isCiteable(SConcept c) {
		return TYPES_WITH_CITE.contains(c.getFactType());
	}
}
