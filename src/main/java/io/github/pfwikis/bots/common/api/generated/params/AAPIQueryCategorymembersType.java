package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Which type of category members to include. Ignored when <kbd>cmsort=timestamp</kbd> is set.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryCategorymembersType {
	FILE("file"),

	PAGE("page"),

	SUBCAT("subcat");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryCategorymembersType> set =
				EnumSet.noneOf(AAPIQueryCategorymembersType.class);

		public AAPIQueryCategorymembersType[] build() {
			return set.toArray(AAPIQueryCategorymembersType[]::new);
		}

		public Builder FILE() {
			set.add(FILE);
			return this;
		}

		public Builder PAGE() {
			set.add(PAGE);
			return this;
		}

		public Builder SUBCAT() {
			set.add(SUBCAT);
			return this;
		}
	}
}
