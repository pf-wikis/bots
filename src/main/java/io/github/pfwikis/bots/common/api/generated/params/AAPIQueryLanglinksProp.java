package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which additional properties to get for each interlanguage link:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryLanglinksProp {

	/**Adds the native language name.*/
	AUTONYM("autonym"),

	/**Adds the localised language name (best effort). Use <var>llinlanguagecode</var> to control the language.*/
	LANGNAME("langname"),

	/**Adds the full URL.*/
	URL("url");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryLanglinksProp> set = EnumSet.noneOf(AAPIQueryLanglinksProp.class);

		public AAPIQueryLanglinksProp[] build() {
			return set.toArray(AAPIQueryLanglinksProp[]::new);
		}

		/**Adds the native language name.*/
		public Builder AUTONYM() {
			set.add(AUTONYM);
			return this;
		}

		/**Adds the localised language name (best effort). Use <var>llinlanguagecode</var> to control the language.*/
		public Builder LANGNAME() {
			set.add(LANGNAME);
			return this;
		}

		/**Adds the full URL.*/
		public Builder URL() {
			set.add(URL);
			return this;
		}
	}
}
