package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Which metadata to return.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQuerySearchInfo {
	REWRITTENQUERY("rewrittenquery"),

	SUGGESTION("suggestion"),

	TOTALHITS("totalhits");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQuerySearchInfo> set = EnumSet.noneOf(AAPIQuerySearchInfo.class);

		public AAPIQuerySearchInfo[] build() {
			return set.toArray(AAPIQuerySearchInfo[]::new);
		}

		public Builder REWRITTENQUERY() {
			set.add(REWRITTENQUERY);
			return this;
		}

		public Builder SUGGESTION() {
			set.add(SUGGESTION);
			return this;
		}

		public Builder TOTALHITS() {
			set.add(TOTALHITS);
			return this;
		}
	}
}
