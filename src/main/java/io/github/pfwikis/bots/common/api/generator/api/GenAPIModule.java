package io.github.pfwikis.bots.common.api.generator.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.base.CaseFormat;

import io.github.pfwikis.bots.common.api.generator.api.GenAPIParamInfo.GenAPIExample;
import io.github.pfwikis.bots.common.api.generator.api.GenAPIParamInfo.GenAPITemplateParameter;
import lombok.Data;

@Data
public class GenAPIModule {
	private String name;
	private String classname;
	private String group;
	private String path;
	private String prefix;
	private String source;
	private String sourcename;
	private String licensetag;
	private String licenselink;
	private String description;
	private List<String> helpurls;
	private List<GenAPIExample> examples;
	@JsonManagedReference
	private List<GenAPIParameter> parameters;
	@JsonManagedReference
	private List<GenAPITemplateParameter> templatedparameters;
	private boolean readrights;
	private boolean writerights;
	private boolean mustbeposted;
	private String dynamicparameters;
	private boolean deprecated;
	private boolean internal;
	private boolean generator;
}