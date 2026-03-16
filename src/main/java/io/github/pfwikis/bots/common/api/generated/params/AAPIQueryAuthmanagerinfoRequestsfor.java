package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Fetch information about the authentication requests needed for the specified authentication action.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryAuthmanagerinfoRequestsfor {
	CHANGE("change"),

	CREATE("create"),

	CREATE_CONTINUE("create-continue"),

	LINK("link"),

	LINK_CONTINUE("link-continue"),

	LOGIN("login"),

	LOGIN_CONTINUE("login-continue"),

	REMOVE("remove"),

	UNLINK("unlink");

	private final String jsonValue;
}
