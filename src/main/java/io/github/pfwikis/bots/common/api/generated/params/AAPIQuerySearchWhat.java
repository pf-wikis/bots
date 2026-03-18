package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Which type of search to perform.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQuerySearchWhat {
	NEARMATCH("nearmatch"),

	TEXT("text"),

	TITLE("title");

	private final String jsonValue;
}
