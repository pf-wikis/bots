package io.github.pfwikis.bots.facts.model;

import java.util.List;
import java.util.function.BiFunction;

import com.google.common.collect.MoreCollectors;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.common.model.SemanticSubject;
import lombok.Value;

@Value
public class SDIConcept {
	
	String name;
	String pluralName;
	List<SDIProperty> properties;
	List<SDIConcept> subForms;
	List<SDIProperty> infoboxProperties;
	List<SDIProperty.Generated> generatedProperties;
	BiFunction<SingleRun, SemanticSubject, String> conceptSpecificCategoriesFunction;
	
	public String toCSSName() {
		return name.toLowerCase().replaceAll("[^a-z0-9]+", "-");
	}

	public boolean containsProperty(String name) {
		return properties.stream().anyMatch(p->p.getName().equals(name));
	}
	
	public boolean containsProperty(SDIPropertyTypeMapping<?> prop) {
		return containsProperty(prop.getProperty().replace('_', ' '));
	}
	
	public SDIProperty getProperty(SDIPropertyTypeMapping<?> prop) {
		return getProperty(prop.getProperty().replace('_', ' '));
	}
	
	public SDIProperty getProperty(String prop) {
		return properties.stream().filter(p->p.getName().equals(prop)).collect(MoreCollectors.onlyElement());
	}

	public SDIConcept getSubConcept(String name) {
		return subForms.stream().filter(sub->sub.getName().equals(name)).findAny().orElse(null);
	}

	public String conceptSpecificCategories(SingleRun run, SemanticSubject subject) {
		return conceptSpecificCategoriesFunction.apply(run, subject);
	}
}