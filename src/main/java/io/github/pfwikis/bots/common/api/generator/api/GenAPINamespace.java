package io.github.pfwikis.bots.common.api.generator.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fizzed.rocker.RockerContent;

import lombok.Data;

@Data
public class GenAPINamespace {
	private int id;
	@JsonProperty("case")
	private String firstLetterCase;
	private String name;
	private boolean subpages;
	private String canonical;
	private boolean content;
	private boolean nonincludable;
	private String namespaceprotection;
	private String defaultcontentmodel;
	
	public String getJavaName() {
		if(canonical == null) {
			return "MAIN";
		}
		var name = canonical.toUpperCase().replaceAll("[^A-Z]+", "_");
		return name;
	}

	public String getPrefix() {
		if(canonical == null) {
			return "";
		}
		return canonical+":";
	}

	public String getLabel() {
		if(canonical == null) {
			return "Main";
		}
		return canonical;
	}
}
