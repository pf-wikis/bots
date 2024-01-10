package io.github.pfwikis.bots.factshelper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fizzed.rocker.RockerContent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormDefinition {
	private String name;
	private String pluralName;
	private List<String> properties;
	@JsonIgnore
	private List<PropertyDefinition> rProperties;
	private List<FormDefinition> subForms = new ArrayList<>();
	
	public String toCSSName() {
		return name.toLowerCase().replaceAll("[^a-z0-9]+", "-");
	}
}
