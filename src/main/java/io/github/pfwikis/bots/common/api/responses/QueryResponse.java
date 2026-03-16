package io.github.pfwikis.bots.common.api.responses;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.model.ContainsPageRef;
import io.github.pfwikis.bots.common.api.model.PageRef;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QueryResponse implements IResponse<QueryResponse> {
	private List<QRPage> pages;
	private Map<String, String> tokens;
	
	@Getter
	@Setter
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
	
	@Getter
	@Setter
	public static class Category {
		@JsonUnwrapped
		private PageRef page;
		private NS ns;
	}
}
