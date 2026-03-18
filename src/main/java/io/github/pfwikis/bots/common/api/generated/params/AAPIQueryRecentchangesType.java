package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Which types of changes to show.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryRecentchangesType {
	CATEGORIZE("categorize"),

	EDIT("edit"),

	EXTERNAL("external"),

	LOG("log"),

	NEW("new");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryRecentchangesType> set =
				EnumSet.noneOf(AAPIQueryRecentchangesType.class);

		public AAPIQueryRecentchangesType[] build() {
			return set.toArray(AAPIQueryRecentchangesType[]::new);
		}

		public Builder CATEGORIZE() {
			set.add(CATEGORIZE);
			return this;
		}

		public Builder EDIT() {
			set.add(EDIT);
			return this;
		}

		public Builder EXTERNAL() {
			set.add(EXTERNAL);
			return this;
		}

		public Builder LOG() {
			set.add(LOG);
			return this;
		}

		public Builder NEW() {
			set.add(NEW);
			return this;
		}
	}
}
