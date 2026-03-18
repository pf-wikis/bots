package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**A list of sections to mark as read.*/
@Getter
@RequiredArgsConstructor
public enum AAPIEchomarkreadSections {
	ALERT("alert"),

	MESSAGE("message");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIEchomarkreadSections> set =
				EnumSet.noneOf(AAPIEchomarkreadSections.class);

		public AAPIEchomarkreadSections[] build() {
			return set.toArray(AAPIEchomarkreadSections[]::new);
		}

		public Builder ALERT() {
			set.add(ALERT);
			return this;
		}

		public Builder MESSAGE() {
			set.add(MESSAGE);
			return this;
		}
	}
}
