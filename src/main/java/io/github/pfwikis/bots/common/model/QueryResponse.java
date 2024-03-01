package io.github.pfwikis.bots.common.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;

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
		private String ticontinue;
		private String apcontinue;
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
