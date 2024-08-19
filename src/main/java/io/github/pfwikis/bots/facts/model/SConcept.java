package io.github.pfwikis.bots.facts.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.common.model.SemanticSubject;
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
	List<SGeneratedProperty<?>> generatedProperties;
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
		private List<SProperty<?>> properties;
		private List<SInfoboxProperty> infoboxProperties;
		private List<SConcept> subConcepts;
		
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
					case SProperty<?> p -> new SInfoboxProperty.Simple(p); 
					default -> throw new IllegalArgumentException("Unexpected param type "+o.getClass());
				})
				.toList();
			return this;
		}
		
		public SConcept build() {
			var gens = new ArrayList<SGeneratedProperty<?>>();
			
			var c = new SConcept(
				name,
				pluralName,
				properties,
				subConcepts,
				infoboxProperties,
				gens,
				null
			);
			
			for(var prop:properties) {
				gens.addAll(prop.generateProperties(c));
			}
			
			return c;
			//TODO return SConcept
			//generate generated properties
		}
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