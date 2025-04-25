package io.github.pfwikis.bots.facts.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.beust.jcommander.internal.Maps;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Lists;

import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.common.model.subject.SemanticSubject;
import io.github.pfwikis.bots.facts.SFactsProperties;
import io.github.pfwikis.bots.facts.model.SPropertyGroup.SPropertyGroupBuilder;
import io.github.pfwikis.bots.utils.Jackson;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
public class SConcept {
	
	String name;
	String pluralName;
	List<SPropertyGroup> propertyGroups;
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
		private List<SPropertyGroup> propertyGroups = Collections.emptyList();
		private List<SInfoboxProperty<?>> infoboxProperties = Collections.emptyList();
		private List<SConcept> subConcepts = Collections.emptyList();
		private BiFunction<SingleRun, SemanticSubject, String> conceptSpecificCategoriesFunction;
		
		public Builder properties(SPropertyGroup.SPropertyGroupBuilder... propertyGroups) {
			this.propertyGroups=Arrays.stream(propertyGroups).map(SPropertyGroupBuilder::build).toList();
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
				propertyGroups,
				subConcepts,
				infoboxProperties,
				gens,
				conceptSpecificCategoriesFunction
			);
			
			gens.add(SFactsProperties.Fact_type.withGenerateWikitext("Template:Facts/"+name));
			for(var propG:propertyGroups) {
				for(var prop:propG.getProperties())
					gens.addAll(prop.generateProperties(c, null));
			}
			for(var sub:subConcepts) {
				for(var propG:sub.propertyGroups) {
					for(var prop:propG.getProperties())
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
	
	public Iterable<SProperty<?>> allProperties() {
		return propertyGroups.stream()
			.flatMap(pg->pg.getProperties().stream())
			.toList();
	}

	public String conceptSpecificCategories(SingleRun run, SemanticSubject subject) {
		return conceptSpecificCategoriesFunction.apply(run, subject);
	}

	public boolean containsProperty(SProperty<?> p) {
		return propertyGroups.stream().anyMatch(g->g.getProperties().contains(p))
				|| generatedProperties.contains(p);
	}

	public String generateTemplateData() throws JsonProcessingException {
		var props = Lists.newArrayList(this.allProperties()).stream()
				//.filter(p->p.getGenerateWikitext() == null)
				.toList();
		var params = props.stream()
			.map(p-> Pair.of(p.getName(), new ObjectNode(Jackson.JSON.getNodeFactory())
				.put("required", p.isRequired())
				.put("description", p.getDescription())
				.put("default", p.getDefaultValue())
				.put("type", p.getFactType().toTemplateDataType())
			))
			.collect(Collectors.toMap(Pair::getLeft, Pair::getRight));
		
		var paramOrder = props.stream().map(p->p.getName()).toList();
		return Jackson.JSON.writeValueAsString(Map.of(
				"params", params,
				"paramOrder", paramOrder,
				"format", "block"));
	}
}