package io.github.pfwikis.bots.facts.model;

import java.util.Collections;
import java.util.List;

import com.fizzed.rocker.RockerContent;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter @Setter
@ToString
@Accessors(chain = true)
@RequiredArgsConstructor
public class SProperty<JType> {
	private final String name;
	private final SFactType<JType> factType;
	private String description;
	private boolean autocompleteDisabled;
	private String suggestValuesFrom;
	private List<SGeneratedProperty<?>> generatedProperties = Collections.emptyList();
	private String formNote;
	private String allowsPattern;
	private String defaultValue;


	public String wikitextToDisplayFact() {
		return factType.wikitextToDisplayFact(this);
	}


	public String wikitextToStoreFact() {
		return factType.wikitextToStoreFact(this);
	}
}