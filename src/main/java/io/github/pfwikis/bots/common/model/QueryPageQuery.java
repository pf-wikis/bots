package io.github.pfwikis.bots.common.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.model.SemanticSubject.PageRef;
import lombok.Data;

@Datapublic class QueryPageQuery {
	private QueryPage querypage;
	
	@Data
	public static class QueryPage {
		private String name;
		private List<QPPage> results;
	}
	
	@Data
	public static class QPPage {
		private int ns;
		private String title;
		private Boolean redirect;
		private DatabaseResult databaseResult;
	}
	
	@Data
	public static class DatabaseResult {
		@JsonProperty("rd_namespace")
		private int redirectNamespace;
		@JsonProperty("rd_title")
		private String redirectTitle;
		@JsonProperty("redirid")
        private long redirectId;
		
		public String toFullPageTitle(Wiki server) {
			return PageRef.of(
					redirectNamespace,
					redirectTitle)
				.toFullTitle()
				.replaceFirst("^Project:", server.getWikiNamespace()+":");
		}
	}
}