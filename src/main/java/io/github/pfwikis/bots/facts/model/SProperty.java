package io.github.pfwikis.bots.facts.model;

import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;
import lombok.experimental.Accessors;

@Getter @Setter
@ToString
@Accessors(chain = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class SProperty<JType> {
	private final String name;
	private final SFactType<JType> factType;
	private String description;
	private boolean autocompleteDisabled;
	private String suggestValuesFrom;
	private String formNote;
	private String allowsPattern;
	@With
	private String defaultValue;
	@With
	private String generateWikitext;


	public String wikitextToDisplayFact() {
		String v = generateWikitext!=null?generateWikitext:("{{{"+name+"|}}}");
		return factType.wikitextToDisplayFact(this, v);
	}


	public String wikitextToStoreFact() {
		String v = generateWikitext!=null?generateWikitext:("{{{"+name+"|}}}");
		return factType.wikitextToStoreFact(this, v);
	}


	public List<SProperty<?>> generateProperties(SConcept c) {
		return Collections.emptyList();
	}
}