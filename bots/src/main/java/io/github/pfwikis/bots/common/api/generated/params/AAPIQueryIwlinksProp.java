package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which additional properties to get for each interwiki link:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryIwlinksProp {

	/**Adds the full URL.*/
	URL("url");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryIwlinksProp> set = EnumSet.noneOf(AAPIQueryIwlinksProp.class);

		public AAPIQueryIwlinksProp[] build() {
			return set.toArray(AAPIQueryIwlinksProp[]::new);
		}

		/**Adds the full URL.*/
		public Builder URL() {
			set.add(URL);
			return this;
		}
	}
}
