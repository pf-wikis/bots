package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**The notification sections to query (i.e. some combination of 'alert' and 'message').*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryNotificationsSections {
	ALERT("alert"),

	MESSAGE("message");

	private final String jsonValue;
}
