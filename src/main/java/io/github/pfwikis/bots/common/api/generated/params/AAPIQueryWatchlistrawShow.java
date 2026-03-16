package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Only list items that meet these criteria.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryWatchlistrawShow {
	NOT_CHANGED("!changed"),

	CHANGED("changed");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryWatchlistrawShow> set =
				EnumSet.noneOf(AAPIQueryWatchlistrawShow.class);

		public AAPIQueryWatchlistrawShow[] build() {
			return set.toArray(AAPIQueryWatchlistrawShow[]::new);
		}

		public Builder NOT_CHANGED() {
			set.add(NOT_CHANGED);
			return this;
		}

		public Builder CHANGED() {
			set.add(CHANGED);
			return this;
		}
	}
}
