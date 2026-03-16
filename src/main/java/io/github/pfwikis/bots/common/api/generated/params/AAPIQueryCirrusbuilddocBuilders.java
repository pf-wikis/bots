package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Type of data to extract*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryCirrusbuilddocBuilders {
	CONTENT("content"),

	LINKS("links");

	private final String jsonValue;
}
