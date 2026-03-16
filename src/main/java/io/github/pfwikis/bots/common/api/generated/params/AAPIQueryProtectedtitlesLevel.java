package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Only list titles with these protection levels.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryProtectedtitlesLevel {
	AUTOCONFIRMED("autoconfirmed"),

	EDIT_SENSITIVE("edit-sensitive"),

	SYSOP("sysop");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryProtectedtitlesLevel> set =
				EnumSet.noneOf(AAPIQueryProtectedtitlesLevel.class);

		public AAPIQueryProtectedtitlesLevel[] build() {
			return set.toArray(AAPIQueryProtectedtitlesLevel[]::new);
		}

		public Builder AUTOCONFIRMED() {
			set.add(AUTOCONFIRMED);
			return this;
		}

		public Builder EDIT_SENSITIVE() {
			set.add(EDIT_SENSITIVE);
			return this;
		}

		public Builder SYSOP() {
			set.add(SYSOP);
			return this;
		}
	}
}
