package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which properties to get:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryAllcategoriesProp {

	/**Tags categories that are hidden with <code>_&#95;HIDDENCAT_&#95;</code>.*/
	HIDDEN("hidden"),

	/**Adds number of pages in the category.*/
	SIZE("size");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryAllcategoriesProp> set =
				EnumSet.noneOf(AAPIQueryAllcategoriesProp.class);

		public AAPIQueryAllcategoriesProp[] build() {
			return set.toArray(AAPIQueryAllcategoriesProp[]::new);
		}

		/**Tags categories that are hidden with <code>_&#95;HIDDENCAT_&#95;</code>.*/
		public Builder HIDDEN() {
			set.add(HIDDEN);
			return this;
		}

		/**Adds number of pages in the category.*/
		public Builder SIZE() {
			set.add(SIZE);
			return this;
		}
	}
}
