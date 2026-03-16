package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Filter notifications returned.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryNotificationsFilter {
	_READ("!read"),

	READ("read");

	private final String jsonValue;
}
