package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Filter notifications returned.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryNotificationsFilter {
	NOT_READ("!read"),

	READ("read");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryNotificationsFilter> set =
				EnumSet.noneOf(AAPIQueryNotificationsFilter.class);

		public AAPIQueryNotificationsFilter[] build() {
			return set.toArray(AAPIQueryNotificationsFilter[]::new);
		}

		public Builder NOT_READ() {
			set.add(NOT_READ);
			return this;
		}

		public Builder READ() {
			set.add(READ);
			return this;
		}
	}
}
