package io.github.pfwikis.bots.common.api.responses;

import io.github.pfwikis.bots.common.api.model.AnyJson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadResponse extends AnyJson implements IResponse<UploadResponse> {

	@Override
	public void validate() {
	}

}
