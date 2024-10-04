package io.github.pfwikis.bots.facts.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.common.model.subject.PageRef;
import io.github.pfwikis.bots.common.model.subject.SemanticSubject;
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
	List<SInfoboxProperty<?>> infoboxProperties;
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
		private List<SInfoboxProperty<?>> infoboxProperties = Collections.emptyList();
		private List<SConcept> subConcepts = Collections.emptyList();
		private BiFunction<SingleRun, SemanticSubject, String> conceptSpecificCategoriesFunction;
		
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
					case SInfoboxProperty<?> p -> p; 
					case SProperty<?> p -> new SInfoboxProperty<>(p); 
					default -> throw new IllegalArgumentException("Unexpected param type "+o.getClass());
				})
				.toList();
			return this;
		}
		
		public Builder conceptSpecificCategoriesFunction(BiFunction<SingleRun, SemanticSubject, String> cscf) {
			this.conceptSpecificCategoriesFunction = cscf;
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
				conceptSpecificCategoriesFunction
			);
			
			gens.add(SFactsProperties.Fact_type.withGenerateWikitext("Template:Facts/"+name));
			for(var prop:properties) {
				gens.addAll(prop.generateProperties(c, null));
			}
			for(var sub:subConcepts) {
				for(var prop:sub.properties) {
					sub.getGeneratedProperties().addAll(prop.generateProperties(sub, c));
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

	public String conceptSpecificCategories(SingleRun run, SemanticSubject subject) {
		return conceptSpecificCategoriesFunction.apply(run, subject);
	}

	public boolean containsProperty(SProperty<?> p) {
		return properties.contains(p) || generatedProperties.contains(p);
	}
}