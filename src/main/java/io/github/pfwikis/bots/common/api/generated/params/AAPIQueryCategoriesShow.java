package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Which kind of categories to show.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryCategoriesShow {
	_HIDDEN("!hidden"),

	HIDDEN("hidden");

	private final String jsonValue;
}
