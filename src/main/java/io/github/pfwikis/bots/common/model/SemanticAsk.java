package io.github.pfwikis.bots.common.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SemanticAsk {

	@JsonProperty("query-continue-offset")
	private Integer queryContinueOffset;
	private Query query;
	
	@Data
	public static class Query {
		private Map<String, Result> results;
	}
	
	@Data
	public static class Result {
		private String fulltext;
        private String fullurl;
        private int namespace;
	}
}
