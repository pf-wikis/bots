package io.github.pfwikis.bots.factshelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.ListUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
public class FormDefinition {
	private String name;
	private String pluralName;
	private List<String> properties;
	@JsonIgnore
	private List<FormDefinition> subForms = new ArrayList<>();
	private List<String> infoboxProperties;
	
	@Value
	public static class Resolved {
		
		String name;
		String pluralName;
		List<PropertyDefinition> properties;
		List<FormDefinition.Resolved> subForms;
		List<PropertyDefinition> infoboxProperties;
		
		public String toCSSName() {
			return name.toLowerCase().replaceAll("[^a-z0-9]+", "-");
		}

		public boolean containsProperty(String name) {
			return properties.stream().anyMatch(p->p.getName().equals(name));
		}
	}

	public Resolved resolve(Map<String, PropertyDefinition> props) {
		return new Resolved(
			name,
			pluralName,
			resolve(properties, props),
			subForms.stream().map(f->f.resolve(props)).toList(),
			resolve(properties, props)
		);
	}
	
	private static List<PropertyDefinition> resolve(List<String> properties, Map<String, PropertyDefinition> props) {
		var result = properties.stream()
			.map(props::get)
			.map(Optional::ofNullable)
			.map(p->p.orElseThrow(()->new RuntimeException("Could not find definition for Property:"+p)))
			.toList();
		
		for(var p:result) {
			if(p.getFactType() == null) {
				throw new IllegalStateException("Fact type is null for "+p.getName());
			}
		}
		return result;
	}
}
