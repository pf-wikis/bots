package io.github.pfwikis.bots.rest.endpoints.infobox.autocategorization;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.model.subject.SemanticSubject;
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
	private final Map<String, String> series2Category;
	private Set<String> categories = new HashSet<>();

	public void addCategory(String category) {
		categories.add(StringUtils.prependIfMissing(category, "Category:"));
	}
	
	public String categoriesWikitext() {
		return categories.stream()
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
}
