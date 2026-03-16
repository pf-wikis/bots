package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Return individual diffs for these slots, rather than one combined diff for all slots.*/
@Getter
@RequiredArgsConstructor
public enum AAPICompareSlots {
	MAIN("main");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPICompareSlots> set = EnumSet.noneOf(AAPICompareSlots.class);

		public AAPICompareSlots[] build() {
			return set.toArray(AAPICompareSlots[]::new);
		}

		public Builder MAIN() {
			set.add(MAIN);
			return this;
		}
	}
}
