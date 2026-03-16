package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**List of types of options to reset when the <var>reset</var> option is set.*/
@Getter
@RequiredArgsConstructor
public enum AAPIOptionsResetkinds {
	ALL("all"),

	REGISTERED("registered"),

	REGISTERED_CHECKMATRIX("registered-checkmatrix"),

	REGISTERED_MULTISELECT("registered-multiselect"),

	SPECIAL("special"),

	UNUSED("unused"),

	USERJS("userjs");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIOptionsResetkinds> set = EnumSet.noneOf(AAPIOptionsResetkinds.class);

		public AAPIOptionsResetkinds[] build() {
			return set.toArray(AAPIOptionsResetkinds[]::new);
		}

		public Builder ALL() {
			set.add(ALL);
			return this;
		}

		public Builder REGISTERED() {
			set.add(REGISTERED);
			return this;
		}

		public Builder REGISTERED_CHECKMATRIX() {
			set.add(REGISTERED_CHECKMATRIX);
			return this;
		}

		public Builder REGISTERED_MULTISELECT() {
			set.add(REGISTERED_MULTISELECT);
			return this;
		}

		public Builder SPECIAL() {
			set.add(SPECIAL);
			return this;
		}

		public Builder UNUSED() {
			set.add(UNUSED);
			return this;
		}

		public Builder USERJS() {
			set.add(USERJS);
			return this;
		}
	}
}
