package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
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

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryNotificationsSections> set =
				EnumSet.noneOf(AAPIQueryNotificationsSections.class);

		public AAPIQueryNotificationsSections[] build() {
			return set.toArray(AAPIQueryNotificationsSections[]::new);
		}

		public Builder ALERT() {
			set.add(ALERT);
			return this;
		}

		public Builder MESSAGE() {
			set.add(MESSAGE);
			return this;
		}
	}
}
