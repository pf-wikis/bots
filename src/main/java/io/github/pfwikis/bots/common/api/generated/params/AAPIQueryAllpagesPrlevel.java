package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Filter protections based on protection level (must be used with apprtype= parameter).*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryAllpagesPrlevel {
	EMPTY(""),

	AUTOCONFIRMED("autoconfirmed"),

	EDIT_SENSITIVE("edit-sensitive"),

	SYSOP("sysop");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryAllpagesPrlevel> set =
				EnumSet.noneOf(AAPIQueryAllpagesPrlevel.class);

		public AAPIQueryAllpagesPrlevel[] build() {
			return set.toArray(AAPIQueryAllpagesPrlevel[]::new);
		}

		public Builder EMPTY() {
			set.add(EMPTY);
			return this;
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
