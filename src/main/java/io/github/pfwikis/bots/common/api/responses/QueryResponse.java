package io.github.pfwikis.bots.common.api.responses;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.model.AnyJson;
import io.github.pfwikis.bots.common.api.model.ContainsPageRef;
import io.github.pfwikis.bots.common.api.model.PageRef;
import io.github.pfwikis.bots.common.model.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import tools.jackson.databind.annotation.JsonDeserialize;

@Data
public class QueryResponse implements IResponse<QueryResponse> {
	private List<QRPage> pages;
	private Map<String, String> tokens;
	private List<MWUser> allusers;
	private List<LogEvent> logevents;
	private List<RecentChange> recentchanges;
	private List<QRPage> allpages;
		
	@Data
	public static class RecentChange {
		private String type;
        private NS ns;
        @JsonUnwrapped
        private PageRef title;
        private long revid;
        private long old_revid;
        private long rcid;
        private Instant timestamp;
        private String user;
        private long oldlen;
        private long newlen;
	}
	
	@Data
	@EqualsAndHashCode(callSuper = true)
	@ToString(callSuper = true)
	public static class LogEvent extends AnyJson {
		private int ns; //can't be NS because this might contain outdated namespaces
		@JsonUnwrapped
		private PageRef title;
		private Params params;
		private String type;
		private String action;
		private String user;
		private int userid;
		private Instant timestamp;
	}
	
	@Data
	@EqualsAndHashCode(callSuper = true)
	@ToString(callSuper = true)
	public static class Params extends AnyJson {
		@JsonProperty("target_ns")
		private NS targetNs;
		@JsonProperty("target_title")
		@JsonDeserialize(using = PageRef.FromString.class)
		private PageRef targetTitle;
		private boolean suppressredirect;
	}
	
	@Data
	public static class MWUser {
		private int userid;
		private String name;
	}
	
	@Data
	public static class QRPage implements ContainsPageRef {
		@JsonUnwrapped
		private PageRef page;
		private NS ns;
		private Boolean missing;
		private List<Category> categories;

		@Override
		public PageRef toPageRef() {
			return page;
		}
	}
	
	@Data
	public static class Category {
		@JsonUnwrapped
		private PageRef page;
		private NS ns;
	}
}
