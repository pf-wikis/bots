package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Type of token being tested.*/
@Getter
@RequiredArgsConstructor
public enum AAPIChecktokenType {
	CREATEACCOUNT("createaccount"),

	CSRF("csrf"),

	LOGIN("login"),

	PATROL("patrol"),

	ROLLBACK("rollback"),

	USERRIGHTS("userrights"),

	WATCH("watch");

	private final String jsonValue;
}
