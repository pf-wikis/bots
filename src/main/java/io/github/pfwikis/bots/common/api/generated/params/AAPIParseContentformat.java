package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Content serialization format used for the input text. Only valid when used with text.*/
@Getter
@RequiredArgsConstructor
public enum AAPIParseContentformat {
	APPLICATION_JSON("application/json"),

	APPLICATION_OCTET_STREAM("application/octet-stream"),

	APPLICATION_UNKNOWN("application/unknown"),

	APPLICATION_X_BINARY("application/x-binary"),

	TEXT_CSS("text/css"),

	TEXT_JAVASCRIPT("text/javascript"),

	TEXT_PLAIN("text/plain"),

	TEXT_UNKNOWN("text/unknown"),

	TEXT_X_WIKI("text/x-wiki"),

	UNKNOWN_UNKNOWN("unknown/unknown");

	private final String jsonValue;
}
