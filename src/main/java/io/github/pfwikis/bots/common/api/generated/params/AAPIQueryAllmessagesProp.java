package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Which properties to get.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryAllmessagesProp {
	DEFAULT("default");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryAllmessagesProp> set =
				EnumSet.noneOf(AAPIQueryAllmessagesProp.class);

		public AAPIQueryAllmessagesProp[] build() {
			return set.toArray(AAPIQueryAllmessagesProp[]::new);
		}

		public Builder DEFAULT() {
			set.add(DEFAULT);
			return this;
		}
	}
}
