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
public enum AAPIQueryIwbacklinksProp {

	/**Adds the prefix of the interwiki.*/
	IWPREFIX("iwprefix"),

	/**Adds the title of the interwiki.*/
	IWTITLE("iwtitle");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryIwbacklinksProp> set =
				EnumSet.noneOf(AAPIQueryIwbacklinksProp.class);

		public AAPIQueryIwbacklinksProp[] build() {
			return set.toArray(AAPIQueryIwbacklinksProp[]::new);
		}

		/**Adds the prefix of the interwiki.*/
		public Builder IWPREFIX() {
			set.add(IWPREFIX);
			return this;
		}

		/**Adds the title of the interwiki.*/
		public Builder IWTITLE() {
			set.add(IWTITLE);
			return this;
		}
	}
}
