package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Which kind of categories to show.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryCategoriesShow {
	NOT_HIDDEN("!hidden"),

	HIDDEN("hidden");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryCategoriesShow> set =
				EnumSet.noneOf(AAPIQueryCategoriesShow.class);

		public AAPIQueryCategoriesShow[] build() {
			return set.toArray(AAPIQueryCategoriesShow[]::new);
		}

		public Builder NOT_HIDDEN() {
			set.add(NOT_HIDDEN);
			return this;
		}

		public Builder HIDDEN() {
			set.add(HIDDEN);
			return this;
		}
	}
}
