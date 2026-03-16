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
}
