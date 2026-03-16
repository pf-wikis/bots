package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Notifier types for which to return notifications.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryNotificationsNotifiertypes {
	EMAIL("email"),

	WEB("web");

	private final String jsonValue;
}
