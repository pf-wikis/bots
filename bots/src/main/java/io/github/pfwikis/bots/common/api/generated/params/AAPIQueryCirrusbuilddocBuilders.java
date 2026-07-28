package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Type of data to extract*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryCirrusbuilddocBuilders {
	CONTENT("content"),

	LINKS("links");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryCirrusbuilddocBuilders> set =
				EnumSet.noneOf(AAPIQueryCirrusbuilddocBuilders.class);

		public AAPIQueryCirrusbuilddocBuilders[] build() {
			return set.toArray(AAPIQueryCirrusbuilddocBuilders[]::new);
		}

		public Builder CONTENT() {
			set.add(CONTENT);
			return this;
		}

		public Builder LINKS() {
			set.add(LINKS);
			return this;
		}
	}
}
