package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which additional properties to get for each category:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryCategoriesProp {

	/**Tags categories that are hidden with <code>_&#95;HIDDENCAT_&#95;</code>.*/
	HIDDEN("hidden"),

	/**Adds the sortkey (hexadecimal string) and sortkey prefix (human-readable part) for the category.*/
	SORTKEY("sortkey"),

	/**Adds timestamp of when the category was added.*/
	TIMESTAMP("timestamp");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryCategoriesProp> set =
				EnumSet.noneOf(AAPIQueryCategoriesProp.class);

		public AAPIQueryCategoriesProp[] build() {
			return set.toArray(AAPIQueryCategoriesProp[]::new);
		}

		/**Tags categories that are hidden with <code>_&#95;HIDDENCAT_&#95;</code>.*/
		public Builder HIDDEN() {
			set.add(HIDDEN);
			return this;
		}

		/**Adds the sortkey (hexadecimal string) and sortkey prefix (human-readable part) for the category.*/
		public Builder SORTKEY() {
			set.add(SORTKEY);
			return this;
		}

		/**Adds timestamp of when the category was added.*/
		public Builder TIMESTAMP() {
			set.add(TIMESTAMP);
			return this;
		}
	}
}
