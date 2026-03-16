package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Details to request.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryNotificationsProp {
	COUNT("count"),

	LIST("list"),

	SEENTIME("seenTime");

	private final String jsonValue;
}
