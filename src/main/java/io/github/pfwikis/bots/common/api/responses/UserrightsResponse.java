package io.github.pfwikis.bots.common.api.responses;

import io.github.pfwikis.bots.common.api.model.AnyJson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserrightsResponse extends AnyJson implements IResponse<UserrightsResponse> {
	
	@Override
	public void validate() {
		
	}

}
