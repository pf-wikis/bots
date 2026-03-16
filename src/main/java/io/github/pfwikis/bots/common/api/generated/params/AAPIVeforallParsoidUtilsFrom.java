package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**'html' or 'wikitext'*/
@Getter
@RequiredArgsConstructor
public enum AAPIVeforallParsoidUtilsFrom {
	HTML("html"),

	WIKITEXT("wikitext");

	private final String jsonValue;
}
