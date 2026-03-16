package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Only list items that meet these criteria.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryWatchlistrawShow {
	_CHANGED("!changed"),

	CHANGED("changed");

	private final String jsonValue;
}
