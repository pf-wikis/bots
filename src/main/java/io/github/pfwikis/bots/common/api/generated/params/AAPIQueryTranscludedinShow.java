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
public enum AAPIQueryTranscludedinShow {

	/**Only show non-redirects.*/
	NOT_REDIRECT("!redirect"),

	/**Only show redirects.*/
	REDIRECT("redirect");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryTranscludedinShow> set =
				EnumSet.noneOf(AAPIQueryTranscludedinShow.class);

		public AAPIQueryTranscludedinShow[] build() {
			return set.toArray(AAPIQueryTranscludedinShow[]::new);
		}

		/**Only show non-redirects.*/
		public Builder NOT_REDIRECT() {
			set.add(NOT_REDIRECT);
			return this;
		}

		/**Only show redirects.*/
		public Builder REDIRECT() {
			set.add(REDIRECT);
			return this;
		}
	}
}
