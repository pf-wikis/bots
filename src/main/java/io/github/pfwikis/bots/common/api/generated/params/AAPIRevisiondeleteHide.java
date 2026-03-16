package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**What to hide for each revision.*/
@Getter
@RequiredArgsConstructor
public enum AAPIRevisiondeleteHide {
	COMMENT("comment"),

	CONTENT("content"),

	USER("user");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIRevisiondeleteHide> set = EnumSet.noneOf(AAPIRevisiondeleteHide.class);

		public AAPIRevisiondeleteHide[] build() {
			return set.toArray(AAPIRevisiondeleteHide[]::new);
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
