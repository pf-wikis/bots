package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Return only messages in this customisation state.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryAllmessagesCustomised {
	ALL("all"),

	MODIFIED("modified"),

	UNMODIFIED("unmodified");

	private final String jsonValue;
}
