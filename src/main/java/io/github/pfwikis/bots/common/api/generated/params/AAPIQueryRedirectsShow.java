package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Show only items that meet these criteria:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryRedirectsShow {

	/**Only show redirects without a fragment.*/
	NOT_FRAGMENT("!fragment"),

	/**Only show redirects with a fragment.*/
	FRAGMENT("fragment");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryRedirectsShow> set = EnumSet.noneOf(AAPIQueryRedirectsShow.class);

		public AAPIQueryRedirectsShow[] build() {
			return set.toArray(AAPIQueryRedirectsShow[]::new);
		}

		/**Only show redirects without a fragment.*/
		public Builder NOT_FRAGMENT() {
			set.add(NOT_FRAGMENT);
			return this;
		}

		/**Only show redirects with a fragment.*/
		public Builder FRAGMENT() {
			set.add(FRAGMENT);
			return this;
		}
	}
}
