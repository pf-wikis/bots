package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**The format of the output.*/
@Getter
@RequiredArgsConstructor
public enum AAPIOpensearchFormat {
	JSON("json"),

	JSONFM("jsonfm"),

	XML("xml"),

	XMLFM("xmlfm");

	private final String jsonValue;
}
