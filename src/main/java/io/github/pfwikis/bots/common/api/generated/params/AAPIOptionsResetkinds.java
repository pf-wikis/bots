package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**List of types of options to reset when the <var>reset</var> option is set.*/
@Getter
@RequiredArgsConstructor
public enum AAPIOptionsResetkinds {
	ALL("all"),

	REGISTERED("registered"),

	REGISTERED_CHECKMATRIX("registered-checkmatrix"),

	REGISTERED_MULTISELECT("registered-multiselect"),

	SPECIAL("special"),

	UNUSED("unused"),

	USERJS("userjs");

	private final String jsonValue;
}
