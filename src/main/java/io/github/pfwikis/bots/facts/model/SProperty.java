package io.github.pfwikis.bots.facts.model;

import java.util.Collections;
import java.util.List;

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
	private List<SGeneratedProperty> generatedProperties = Collections.emptyList();
	private String formNote;
	private String allowsPattern;
	/*
	private String factDisplayFormat;
	
	
	
	

	public String infoboxLabel(List<Object> values) {
		return factType.infoboxLabel(this, values);
	}

	public String infoboxValue(WikiAPI wiki, List<Object> values) {
		return factType.infoboxValue(wiki, this, values);
	}
	
	public String infoboxValue(WikiAPI wiki, Object value) {
		return factType.infoboxValue(wiki, this, value);
	}
	*/
}