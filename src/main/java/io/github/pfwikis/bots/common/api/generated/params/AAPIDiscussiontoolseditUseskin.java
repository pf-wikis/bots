package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Apply the selected skin to the parser output. May affect the following properties: <kbd>text</kbd>, <kbd>langlinks</kbd>, <kbd>headitems</kbd>, <kbd>modules</kbd>, <kbd>jsconfigvars</kbd>, <kbd>indicators</kbd>.*/
@Getter
@RequiredArgsConstructor
public enum AAPIDiscussiontoolseditUseskin {
	APIOUTPUT("apioutput"),

	AUTHENTICATION_POPUP("authentication-popup"),

	FALLBACK("fallback"),

	JSON("json"),

	VECTOR("vector"),

	VECTOR_2022("vector-2022");

	private final String jsonValue;
}
