package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Format of help strings.*/
@Getter
@RequiredArgsConstructor
public enum AAPIParaminfoHelpformat {
	HTML("html"),

	NONE("none"),

	RAW("raw"),

	WIKITEXT("wikitext");

	private final String jsonValue;
}
