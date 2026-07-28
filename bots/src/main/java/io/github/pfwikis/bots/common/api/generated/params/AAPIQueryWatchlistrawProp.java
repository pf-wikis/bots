package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which additional properties to get:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryWatchlistrawProp {

	/**Adds timestamp of when the user was last notified about the edit.*/
	CHANGED("changed");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryWatchlistrawProp> set =
				EnumSet.noneOf(AAPIQueryWatchlistrawProp.class);

		public AAPIQueryWatchlistrawProp[] build() {
			return set.toArray(AAPIQueryWatchlistrawProp[]::new);
		}

		/**Adds timestamp of when the user was last notified about the edit.*/
		public Builder CHANGED() {
			set.add(CHANGED);
			return this;
		}
	}
}
