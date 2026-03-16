package io.github.pfwikis.bots.common.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import tools.jackson.databind.JsonNode;

@Getter @Setter
public class QueryResponse<T> {

	private boolean batchcomplete;
	private T query;
	private List<JsonNode> warnings = new ArrayList<>();
	private List<JsonNode> errors = new ArrayList<>();
	@JsonProperty("continue")
	private Continue continueInfo;

	public void setError(JsonNode n) {
		errors = List.of(n);
	}
	
	public void setWarning(JsonNode n) {
		errors = List.of(n);
	}
	
	@Getter @Setter
	public static class Continue {
		private Map<String, String> genericValues = new HashMap<>();
		@JsonProperty("continue")
		private String cont;
		
		@JsonAnySetter
        public void setGenericValue(String key, String value) {
			genericValues.put(key, value);
        }
		
		public String getGenericValue(String key) {
			return genericValues.get(key);
		}
	}
}
