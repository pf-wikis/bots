package io.github.pfwikis.bots.common.api.responses;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import io.github.pfwikis.bots.common.api.model.AAPIExceptions;
import io.github.pfwikis.bots.common.api.model.AnyJson;
import io.github.pfwikis.bots.common.api.model.ContainsPageRef;
import io.github.pfwikis.bots.common.api.model.PageRef;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditResponse extends AnyJson implements IResponse<EditResponse>, ContainsPageRef {
	private String result;
	@JsonUnwrapped
	private PageRef page;
	private String contentmodel;
	private long oldrevid;
	private long newrevid;
	private Instant newtimestamp;
	private boolean watched;
	
	@Override
	public void validate() {
		if(!"Success".equals(result)) {
			throw new AAPIExceptions.AAPIValidationException("Unsuccessful edit", this);
		}
	}

	@Override
	public PageRef toPageRef() {
		return page;
	}
}
