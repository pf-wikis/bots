package io.github.pfwikis.bots.common.api.responses;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.jfree.data.json.impl.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.model.AnyJson;
import io.github.pfwikis.bots.common.api.model.ContainsPageRef;
import io.github.pfwikis.bots.common.api.model.PageRef;
import io.github.pfwikis.bots.common.api.model.PageTitle;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import tools.jackson.databind.annotation.JsonDeserialize;

@Data
public class QueryResponse extends AnyJson implements IResponse<QueryResponse> {
	private List<QRPage> pages;
	private Map<String, String> tokens;
	private List<MWUser> allusers;
	private List<LogEvent> logevents;
	private List<RecentChange> recentchanges;
	private List<QRPage> allpages;
	private List<QRPage> imageusage;
	private QueryPage querypage;
	
	@Override
	public void addContinuedResults(QueryResponse more) {
		boolean merged = true;
		merged|=merge(this, more, QueryResponse::getPages);
		merged|=merge(this, more, QueryResponse::getAllusers);
		merged|=merge(this, more, QueryResponse::getLogevents);
		merged|=merge(this, more, QueryResponse::getRecentchanges);
		merged|=merge(this, more, QueryResponse::getAllpages);
		merged|=merge(this, more, QueryResponse::getImageusage);
		merged|=merge(this, more, qr->qr.getQuerypage()==null?null:qr.getQuerypage().getResults());
		
		if(!merged) {
			throw new IllegalStateException("Failed to merge "+this.getClass().getSimpleName());
		}
	}
	
	private <T> boolean merge(QueryResponse a, QueryResponse b, Function<QueryResponse, List<T>> getter) {
		var va = getter.apply(a);
		var vb = getter.apply(b);
		if(va != null) {
			if(vb != null) {
				va.addAll(vb);
				return true;
			}
			else {
				return false;
			}
		}
		if(vb != null) {
			throw new IllegalStateException("Right side of merge has field that left one is missing");
		}
		else {
			return false;
		}
	}

	@Data
	public static class QueryPage {
		private String name;
		private List<QRPage> results;
	}
		
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
	public static class ResponsePageRef implements ContainsPageRef {
		@JsonUnwrapped
		private PageRef page;
		private NS ns;
		
		@Override
		public PageRef toPageRef() {
			return page;
		}
	}
	
	@Data
	@EqualsAndHashCode(callSuper = true)
	@ToString(callSuper = true)
	public static class QRPage extends ResponsePageRef {
		private Boolean missing;
		private List<Category> categories;
		private List<JSONObject> protection;
		private List<ResponsePageRef> transcludedin;
		private List<QRPage> linkshere;
		private DatabaseResult databaseResult;
	}
	
	@Data
	public static class DatabaseResult {
		@JsonProperty("rd_namespace")
		private NS redirectNamespace;
		@JsonProperty("rd_title")
		private String redirectTitle;
		@JsonProperty("redirid")
        private long redirectId;
		
		public PageTitle toPageTitle() {
			return PageTitle.of(
					redirectNamespace,
					redirectTitle);
		}
	}
	
	@Data
	public static class Category {
		@JsonUnwrapped
		private PageRef page;
		private NS ns;
	}
}
