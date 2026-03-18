package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
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

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryNotificationsProp> set =
				EnumSet.noneOf(AAPIQueryNotificationsProp.class);

		public AAPIQueryNotificationsProp[] build() {
			return set.toArray(AAPIQueryNotificationsProp[]::new);
		}

		public Builder COUNT() {
			set.add(COUNT);
			return this;
		}

		public Builder LIST() {
			set.add(LIST);
			return this;
		}

		public Builder SEENTIME() {
			set.add(SEENTIME);
			return this;
		}
	}
}
