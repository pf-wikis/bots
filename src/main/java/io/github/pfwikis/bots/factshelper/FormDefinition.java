package io.github.pfwikis.bots.factshelper;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
}
