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
public enum AAPIQueryLangbacklinksProp {

	/**Adds the language code of the language link.*/
	LLLANG("lllang"),

	/**Adds the title of the language link.*/
	LLTITLE("lltitle");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryLangbacklinksProp> set =
				EnumSet.noneOf(AAPIQueryLangbacklinksProp.class);

		public AAPIQueryLangbacklinksProp[] build() {
			return set.toArray(AAPIQueryLangbacklinksProp[]::new);
		}

		/**Adds the language code of the language link.*/
		public Builder LLLANG() {
			set.add(LLLANG);
			return this;
		}

		/**Adds the title of the language link.*/
		public Builder LLTITLE() {
			set.add(LLTITLE);
			return this;
		}
	}
}
