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
public enum AAPIQueryRedirectsProp {

	/**Fragment of each redirect, if any.*/
	FRAGMENT("fragment"),

	/**Page ID of each redirect.*/
	PAGEID("pageid"),

	/**Title of each redirect.*/
	TITLE("title");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryRedirectsProp> set = EnumSet.noneOf(AAPIQueryRedirectsProp.class);

		public AAPIQueryRedirectsProp[] build() {
			return set.toArray(AAPIQueryRedirectsProp[]::new);
		}

		/**Fragment of each redirect, if any.*/
		public Builder FRAGMENT() {
			set.add(FRAGMENT);
			return this;
		}

		/**Page ID of each redirect.*/
		public Builder PAGEID() {
			set.add(PAGEID);
			return this;
		}

		/**Title of each redirect.*/
		public Builder TITLE() {
			set.add(TITLE);
			return this;
		}
	}
}
