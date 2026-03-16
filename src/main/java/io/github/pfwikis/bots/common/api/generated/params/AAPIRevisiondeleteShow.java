package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**What to unhide for each revision.*/
@Getter
@RequiredArgsConstructor
public enum AAPIRevisiondeleteShow {
	COMMENT("comment"),

	CONTENT("content"),

	USER("user");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIRevisiondeleteShow> set = EnumSet.noneOf(AAPIRevisiondeleteShow.class);

		public AAPIRevisiondeleteShow[] build() {
			return set.toArray(AAPIRevisiondeleteShow[]::new);
		}

		public Builder COMMENT() {
			set.add(COMMENT);
			return this;
		}

		public Builder CONTENT() {
			set.add(CONTENT);
			return this;
		}

		public Builder USER() {
			set.add(USER);
			return this;
		}
	}
}
