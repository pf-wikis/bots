package io.github.pfwikis.bots.facts.citetemplates;

import static io.github.pfwikis.bots.facts.SFactsProperties.On_page;

import java.util.List;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.model.subject.PageRef;
import io.github.pfwikis.bots.common.model.subject.SemanticSubject;
import io.github.pfwikis.bots.facts.model.SProperty;
import io.github.pfwikis.bots.utils.MWJsonHelper;

public interface BookPart {
	
	SemanticSubject getSubject();
	List<PageRef> getAuthors();
	
	default String makeAuthors(WikiAPI wiki) {
		var result = formatAuthors(wiki, getAuthors());
		if(result == null)
			return "Unknown author";
		return result;
	}

	default String formatAuthors(WikiAPI wiki, List<PageRef> authors) {
		if(authors == null || authors.isEmpty())
			return null;
		if(authors.size() == 1) {
			return authors.get(0).toWikiLink(wiki);
		}
		if(authors.size() == 2) {
			return authors.get(0).toWikiLink(wiki)+" & "+authors.get(1).toWikiLink(wiki);
		}
		return authors.get(0).toWikiLink(wiki)+", et al";
	}

	
	//convenience methods
	default <T> T getOr(SProperty<T> p, T defaultValue) {
		return getSubject().getOr(p, defaultValue);
	}
	
	default <T> T get(SProperty<T> p) {
		return getSubject().get(p);
	}
	
	default boolean has(SProperty<?> p) {
		return getSubject().has(p);
	}
	
	default Integer tryParsePage() {
		return MWJsonHelper.tryParseInt(this.getOr(On_page, null));
	}
}