package io.github.pfwikis.bots.facts.model;

import java.util.List;

import io.github.pfwikis.bots.facts.model.SDIProperty.Generated;
import lombok.Value;

@Value
public class SDIConcept {
	
	String name;
	String pluralName;
	List<SDIProperty> properties;
	List<SDIConcept> subForms;
	List<SDIProperty> infoboxProperties;
	List<SDIProperty.Generated> generatedProperties;
	
	public String toCSSName() {
		return name.toLowerCase().replaceAll("[^a-z0-9]+", "-");
	}

	public boolean containsProperty(String name) {
		return properties.stream().anyMatch(p->p.getName().equals(name));
	}
}