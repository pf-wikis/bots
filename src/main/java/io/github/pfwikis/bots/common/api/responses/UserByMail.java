package io.github.pfwikis.bots.common.api.responses;

import io.github.pfwikis.bots.common.api.model.AnyJson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserByMail extends AnyJson implements IResponse<UserByMail> {
	private boolean found;
	private int id;
	private String name;
}
