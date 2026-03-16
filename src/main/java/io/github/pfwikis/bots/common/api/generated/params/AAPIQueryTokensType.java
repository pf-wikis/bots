package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Types of token to request.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryTokensType {
	CREATEACCOUNT("createaccount"),

	CSRF("csrf"),

	LOGIN("login"),

	PATROL("patrol"),

	ROLLBACK("rollback"),

	USERRIGHTS("userrights"),

	WATCH("watch");

	private final String jsonValue;
}
