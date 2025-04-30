package io.github.pfwikis.bots.rest.endpoints.citetemplate.model;

import static io.github.pfwikis.bots.facts.SFactsProperties.On_page;

import java.util.List;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.model.subject.PageRef;
import io.github.pfwikis.bots.common.model.subject.SemanticSubject;
import io.github.pfwikis.bots.facts.model.SProperty;
import io.github.pfwikis.bots.utils.MWJsonHelper;

public abstract class BookPart {
	
	public abstract SemanticSubject getSubject();
	public abstract List<PageRef> getAuthors();
	
	protected String makeAuthors() {
		var result = formatAuthors(getAuthors());
		if(result == null)
			return "Unknown author";
		return result;
	}

	protected String formatAuthors(List<PageRef> authors) {
		if(authors == null || authors.isEmpty())
			return null;
		if(authors.size() == 1) {
			return authors.get(0).toWikiLink();
		}
		if(authors.size() == 2) {
			return authors.get(0).toWikiLink()+" & "+authors.get(1).toWikiLink();
		}
		return authors.get(0).toWikiLink()+", et al";
	}

	
	//convenience methods
	public <T> T getOr(SProperty<T> p, T defaultValue) {
		return getSubject().getOr(p, defaultValue);
	}
	
	public <T> T get(SProperty<T> p) {
		return getSubject().get(p);
	}
	
	public boolean has(SProperty<?> p) {
		return getSubject().has(p);
	}
	
	public Integer tryParsePage() {
		return MWJsonHelper.tryParseInt(this.getOr(On_page, null));
	}
}