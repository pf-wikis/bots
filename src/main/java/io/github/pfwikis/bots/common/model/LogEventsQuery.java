package io.github.pfwikis.bots.common.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Datapublic class LogEventsQuery {
	private List<LogEvent> logevents = new ArrayList<>();
	
	@Data
	public static class LogEvent extends JsonModel {
		private int ns;
		private String title;
		private int pageid;
		private Params params;
		private String type;
		private String action;
		private String user;
		private int userid;
		private Instant timestamp;
	}
	
	@Data
	public static class Params extends JsonModel {
		@JsonProperty("target_ns")
		private int targetNs;
		@JsonProperty("target_title")
		private String targetTitle;
		private boolean suppressredirect;
	}
}