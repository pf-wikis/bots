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
public enum AAPIQueryTranscludedinProp {

	/**Page ID of each page.*/
	PAGEID("pageid"),

	/**Flag if the page is a redirect.*/
	REDIRECT("redirect"),

	/**Title of each page.*/
	TITLE("title");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryTranscludedinProp> set =
				EnumSet.noneOf(AAPIQueryTranscludedinProp.class);

		public AAPIQueryTranscludedinProp[] build() {
			return set.toArray(AAPIQueryTranscludedinProp[]::new);
		}

		/**Page ID of each page.*/
		public Builder PAGEID() {
			set.add(PAGEID);
			return this;
		}

		/**Flag if the page is a redirect.*/
		public Builder REDIRECT() {
			set.add(REDIRECT);
			return this;
		}

		/**Title of each page.*/
		public Builder TITLE() {
			set.add(TITLE);
			return this;
		}
	}
}
