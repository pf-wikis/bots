package io.github.pfwikis.bots.common.api.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.model.AnyJson;
import io.github.pfwikis.bots.common.api.model.ContainsPageRef;
import io.github.pfwikis.bots.common.api.model.PageRef;
import io.github.pfwikis.bots.common.api.model.PageTitle;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParseResponse extends AnyJson implements IResponse<ParseResponse>, ContainsPageRef {
	@JsonUnwrapped
	private PageRef page;
	private String wikitext;
	private String text;
	private Properties properties;
	private List<Category> categories;
	private List<Link> links;
	
	@Override
	public PageRef toPageRef() {
		return page;
	}
	
	@Data
	public static class Link {
		private NS ns;
		private PageTitle title;
		private boolean exists;
	}
	
	@Data
	public static class Category {
		private String sortkey;
		private String category;
	}
	
	@Data
	public static class Properties {
		@JsonProperty("page_image_free")
		private String pageImageFree;
	}
}