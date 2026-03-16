package io.github.pfwikis.bots.rest.endpoints.infobox.autocategorization;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.model.PageTitle;
import io.github.pfwikis.bots.common.api.responses.SemanticSubject;
import io.github.pfwikis.bots.facts.model.SConcept;
import io.github.pfwikis.bots.facts.model.SProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ACContext {
	private final Wiki wiki;
	private final SConcept concept;
	private final SemanticSubject subject;
	private final Map<PageTitle, PageTitle> series2Category;
	private Set<PageTitle> categories = new HashSet<>();

	public void addCategory(PageTitle category) {
		categories.add(category);
	}
	
	public void addCategory(String categoryName) {
		categories.add(PageTitle.of(NS.CATEGORY, categoryName));
	}
	
	public String categoriesWikitext() {
		return categories.stream()
			.sorted()
			.map(c->"[["+c+"]]")
			.collect(Collectors.joining(""));
	}

	public boolean has(SProperty<?> prop) {
		return concept.containsProperty(prop) && 
			(subject == null || subject.has(prop));
	}
	
	public boolean hasUnset(SProperty<?> prop) {
		return concept.containsProperty(prop) && 
			(subject == null || !subject.has(prop));
	}
	
	public boolean has(SConcept concept) {
		return this.concept.equals(concept);
	}

	public boolean has(Wiki wiki) {
		return this.wiki==wiki;
	}
}
