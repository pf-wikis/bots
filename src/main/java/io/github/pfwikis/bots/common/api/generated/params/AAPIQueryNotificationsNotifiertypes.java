package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
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

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryNotificationsNotifiertypes> set =
				EnumSet.noneOf(AAPIQueryNotificationsNotifiertypes.class);

		public AAPIQueryNotificationsNotifiertypes[] build() {
			return set.toArray(AAPIQueryNotificationsNotifiertypes[]::new);
		}

		public Builder EMAIL() {
			set.add(EMAIL);
			return this;
		}

		public Builder WEB() {
			set.add(WEB);
			return this;
		}
	}
}
