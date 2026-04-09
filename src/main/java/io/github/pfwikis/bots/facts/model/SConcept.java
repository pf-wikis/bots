package io.github.pfwikis.bots.facts.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.model.PageTitle;
import io.github.pfwikis.bots.facts.SFactsProperties;
import io.github.pfwikis.bots.facts.model.SPropertyGroup.SPropertyGroupBuilder;
import io.github.pfwikis.bots.utils.Jackson;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.With;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.node.ObjectNode;

@Value
public class SConcept {
	
	String name;
	String pluralName;
	PageTitle primaryFactType;
	List<PageTitle> allFactTypes;
	List<SConcept> merges;
	List<SPropertyGroup> propertyGroups;
	List<SConcept> subConcepts;
	List<SInfoboxProperty<?>> infoboxProperties;
	List<SProperty<?>> generatedProperties;
	
	public static Builder builder() {
		return new Builder();
	}
	
	@Accessors(fluent = true, chain = true)
	public static class Builder {
		@Setter
		private String name;
		@Setter
		private String pluralName;
		@Setter
		private List<SConcept> merges = Collections.emptyList();
		@Setter
		private List<SPropertyGroup> propertyGroups = Collections.emptyList();
		private List<SInfoboxProperty<?>> infoboxProperties = Collections.emptyList();
		@Setter @Getter
		private List<SConcept.Builder> subConcepts = Collections.emptyList();
		
		public Builder properties(SPropertyGroup.SPropertyGroupBuilder... propertyGroups) {
			this.propertyGroups=Arrays.stream(propertyGroups).map(SPropertyGroupBuilder::build).toList();
			return this;
		}
		
		@Tolerate
		public Builder subConcepts(SConcept.Builder... subs) {
			this.subConcepts=List.of(subs);
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
		
		public SConcept build() {
			return this.build(null);
		}
		
		private SConcept build(SConcept parent) {
			var gens = new ArrayList<SProperty<?>>();
			var builtSubConcepts = new ArrayList<SConcept>();
			
			PageTitle primFactType = null;
			List<PageTitle> factTypes = new ArrayList<>();
			if(parent != null) {
				primFactType = PageTitle.of(NS.TEMPLATE, parent.getPrimaryFactType().getName()+"/"+name);
				for(var p:parent.getAllFactTypes()) {
					factTypes.add(PageTitle.of(NS.TEMPLATE, p.getName()+"/"+name));
					for(var m:merges) {
						factTypes.add(PageTitle.of(NS.TEMPLATE, p.getName()+"/"+m.getName()));
					}
				}
			}
			else {
				primFactType = PageTitle.of(NS.TEMPLATE, "Facts/"+name);
				factTypes.add(primFactType);
				for(var m:merges) {
					factTypes.add(m.getPrimaryFactType());
				}
			}
			
			var c = new SConcept(
				name,
				pluralName,
				primFactType,
				factTypes,
				merges,
				propertyGroups,
				builtSubConcepts,
				infoboxProperties,
				gens
			);
			
			gens.add(SFactsProperties.Fact_type.withGenerateWikitext(c.getAllFactTypes().stream().map(PageTitle::toString).collect(Collectors.joining(";"))));
			for(var propG:propertyGroups) {
				for(var prop:propG.getProperties())
					gens.addAll(prop.generateProperties(c, parent));
			}
			
			for(var sub:subConcepts) {
				builtSubConcepts.add(sub.build(c));
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

	public boolean containsProperty(SProperty<?> p) {
		return propertyGroups.stream().anyMatch(g->g.getProperties().contains(p))
				|| generatedProperties.contains(p);
	}

	public String generateTemplateData() throws JacksonException {
		var props = Lists.newArrayList(this.allProperties()).stream()
				//.filter(p->p.getGenerateWikitext() == null)
				.toList();
		var params = new ObjectNode(Jackson.JSON.getNodeFactory());
		props.forEach(p->params.set(p.getName(), new ObjectNode(Jackson.JSON.getNodeFactory())
				.put("required", p.isRequired())
				.put("description", p.getDescription())
				.put("default", p.getDefaultValue())
				.put("type", p.getFactType().toTemplateDataType())
			));
		
		var paramOrder = props.stream().map(p->p.getName()).toList();
		return Jackson.JSON.writeValueAsString(new ObjectNode(Jackson.JSON.getNodeFactory())
			.set("params", params)
			.set("paramOrder", Jackson.JSON.valueToTree(paramOrder))
			.put("format", "block"));
	}
}