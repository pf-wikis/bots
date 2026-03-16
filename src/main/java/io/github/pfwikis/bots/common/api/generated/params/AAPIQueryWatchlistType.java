package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which types of changes to show:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryWatchlistType {

	/**Category membership changes.*/
	CATEGORIZE("categorize"),

	/**Regular page edits.*/
	EDIT("edit"),

	/**External changes.*/
	EXTERNAL("external"),

	/**Log entries.*/
	LOG("log"),

	/**Page creations.*/
	NEW("new");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryWatchlistType> set = EnumSet.noneOf(AAPIQueryWatchlistType.class);

		public AAPIQueryWatchlistType[] build() {
			return set.toArray(AAPIQueryWatchlistType[]::new);
		}

		/**Category membership changes.*/
		public Builder CATEGORIZE() {
			set.add(CATEGORIZE);
			return this;
		}

		/**Regular page edits.*/
		public Builder EDIT() {
			set.add(EDIT);
			return this;
		}

		/**External changes.*/
		public Builder EXTERNAL() {
			set.add(EXTERNAL);
			return this;
		}

		/**Log entries.*/
		public Builder LOG() {
			set.add(LOG);
			return this;
		}

		/**Page creations.*/
		public Builder NEW() {
			set.add(NEW);
			return this;
		}
	}
}
