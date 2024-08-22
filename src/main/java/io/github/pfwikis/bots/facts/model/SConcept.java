package io.github.pfwikis.bots.facts.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.common.model.SemanticSubject;
import io.github.pfwikis.bots.facts.SFactsProperties;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
public class SConcept {
	
	String name;
	String pluralName;
	List<SProperty<?>> properties;
	List<SConcept> subConcepts;
	List<SInfoboxProperty> infoboxProperties;
	List<SProperty<?>> generatedProperties;
	BiFunction<SingleRun, SemanticSubject, String> conceptSpecificCategoriesFunction;
	
	public static Builder builder() {
		return new Builder();
	}
	
	@Accessors(fluent = true, chain = true)
	public static class Builder {
		@Setter
		private String name;
		@Setter
		private String pluralName;
		private List<SProperty<?>> properties = Collections.emptyList();
		private List<SInfoboxProperty> infoboxProperties = Collections.emptyList();
		private List<SConcept> subConcepts = Collections.emptyList();
		
		public Builder properties(SProperty<?>... properties) {
			this.properties=List.of(properties);
			return this;
		}
		
		public Builder subConcepts(SConcept.Builder... subs) {
			this.subConcepts=List.of(subs).stream().map(Builder::build).toList();
			return this;
		}
		
		public Builder infoboxProperties(Object... properties) {
			this.infoboxProperties= Arrays.stream(properties)
				.map(o->switch(o) {
					case SInfoboxProperty p -> p; 
					case SProperty<?> p -> new SInfoboxProperty(p); 
					default -> throw new IllegalArgumentException("Unexpected param type "+o.getClass());
				})
				.toList();
			return this;
		}
		
		public SConcept build() {
			var gens = new ArrayList<SProperty<?>>();
			
			var c = new SConcept(
				name,
				pluralName,
				properties,
				subConcepts,
				infoboxProperties,
				gens,
				null
			);
			
			gens.add(SFactsProperties.Fact_type.withGenerateWikitext("Template:Facts/"+name));
			for(var prop:properties) {
				gens.addAll(prop.generateProperties(c));
			}
			for(var sub:subConcepts) {
				for(var prop:sub.properties) {
					gens.addAll(prop.generateProperties(c));
				}
			}
			
			return c;
		}
	}

	public SConcept getSubConcept(String name) {
		for(var sub:subConcepts) {
			if(sub.getName().equals(name))
				return sub;
		}
		return null;
	}
	
	/*

	public boolean containsProperty(String name) {
		return properties.stream().anyMatch(p->p.getName().equals(name));
	}
	
	public boolean containsProperty(SPropertyTypeMapping<?> prop) {
		return containsProperty(prop.getProperty().replace('_', ' '));
	}
	
	public SProperty getProperty(SPropertyTypeMapping<?> prop) {
		return getProperty(prop.getProperty().replace('_', ' '));
	}
	
	public SProperty getProperty(String prop) {
		return properties.stream().filter(p->p.getName().equals(prop)).collect(MoreCollectors.onlyElement());
	}

	public SConcept getSubConcept(String name) {
		return subForms.stream().filter(sub->sub.getName().equals(name)).findAny().orElse(null);
	}

	public String conceptSpecificCategories(SingleRun run, SemanticSubject subject) {
		return conceptSpecificCategoriesFunction.apply(run, subject);
	}*/
}