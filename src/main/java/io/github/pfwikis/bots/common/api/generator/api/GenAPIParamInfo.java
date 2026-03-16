package io.github.pfwikis.bots.common.api.generator.api;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.CaseFormat;

import io.github.pfwikis.bots.utils.Jackson;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tools.jackson.databind.JsonNode;
import lombok.EqualsAndHashCode;

@Data
public class GenAPIParamInfo {
	private String helpformat;
	private List<GenAPIModule> modules;
	
	
	
	@Data
	public static class GenAPIExample {
		private String query;
		private String description;
	}
	
	
	
	@Getter @Setter
	@ToString(callSuper = true)
	@EqualsAndHashCode(callSuper=true)
	public static class GenAPITemplateParameter extends GenAPIParameter {
		private JsonNode templatevars;
	}
}
