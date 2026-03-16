package io.github.pfwikis.bots.common.api.responses;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import io.github.pfwikis.bots.common.api.model.ContainsPageRef;
import io.github.pfwikis.bots.common.api.model.PageRef;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParseResponse implements IResponse<ParseResponse>, ContainsPageRef {
	@JsonUnwrapped
	private PageRef page;
	private String wikitext;
	
	@Override
	public PageRef toPageRef() {
		return page;
	}
}