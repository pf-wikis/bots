package io.github.pfwikis.bots.common.model;

import java.util.ArrayList;
import java.util.List;

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
		@JsonProperty("continue")
		private String cont;
	}
}
