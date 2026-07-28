package io.github.pfwikis.bots.common.api.responses;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import tools.jackson.databind.JsonNode;

@Data
public class AAPIWrappedResponse {

	private Boolean batchcomplete;
	@JsonProperty("query-continue-offset")
	private Integer queryContinueOffset;
	private List<AAPIWarning> warnings;
	private List<AAPIError> errors;
	@JsonProperty("continue")
	private Map<String, String> continueMap;
	private Map<String, JsonNode> otherFields = new LinkedHashMap<>();
	
	@JsonAnySetter
	public void setAnyJson(String prop, JsonNode val) {
		otherFields.put(prop, val);
	}

	@Data
	public static class AAPIWarning {
		private String code;
		private String text;
		private String module;
	}
	
	@Data
	public static class AAPIError {
		private String code;
		private String module;
		private String text;
	}
}
