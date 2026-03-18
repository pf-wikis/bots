package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Type of notifications to mark as seen: 'alert', 'message' or 'all'.*/
@Getter
@RequiredArgsConstructor
public enum AAPIEchomarkseenType {
	ALERT("alert"),

	ALL("all"),

	MESSAGE("message");

	private final String jsonValue;
}
