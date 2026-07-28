package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Types of token to request.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryTokensType {
	CREATEACCOUNT("createaccount"),

	CSRF("csrf"),

	LOGIN("login"),

	PATROL("patrol"),

	ROLLBACK("rollback"),

	USERRIGHTS("userrights"),

	WATCH("watch");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryTokensType> set = EnumSet.noneOf(AAPIQueryTokensType.class);

		public AAPIQueryTokensType[] build() {
			return set.toArray(AAPIQueryTokensType[]::new);
		}

		public Builder CREATEACCOUNT() {
			set.add(CREATEACCOUNT);
			return this;
		}

		public Builder CSRF() {
			set.add(CSRF);
			return this;
		}

		public Builder LOGIN() {
			set.add(LOGIN);
			return this;
		}

		public Builder PATROL() {
			set.add(PATROL);
			return this;
		}

		public Builder ROLLBACK() {
			set.add(ROLLBACK);
			return this;
		}

		public Builder USERRIGHTS() {
			set.add(USERRIGHTS);
			return this;
		}

		public Builder WATCH() {
			set.add(WATCH);
			return this;
		}
	}
}
