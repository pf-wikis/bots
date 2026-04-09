package io.github.pfwikis.bots.rest.endpoints.citetemplate;

import java.util.Collection;
import java.util.Set;

import io.github.pfwikis.bots.common.api.model.PageTitle;
import io.github.pfwikis.bots.facts.SModel;
import io.github.pfwikis.bots.facts.model.SConcept;

public class CiteUtil {

	private static Set<PageTitle> TYPES_WITH_CITE = Set.of(
		SModel.ACCESSORY.getPrimaryFactType(),
		SModel.BOOK.getPrimaryFactType(),
		SModel.SINGLE_BOOK_AP.getPrimaryFactType(),
		PageTitle.of("Template:Facts/Map"),
		SModel.DECK.getPrimaryFactType(),
		SModel.VIDEO_GAME.getPrimaryFactType(),
		SModel.WEB_CITATION.getPrimaryFactType(),
		SModel.MINIATURES.getPrimaryFactType()
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
		return TYPES_WITH_CITE.contains(c.getPrimaryFactType());
	}
}
