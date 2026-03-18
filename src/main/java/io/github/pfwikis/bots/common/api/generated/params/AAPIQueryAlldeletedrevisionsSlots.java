package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Which revision slots to return data for, when slot-related properties are included in <var>adrprops</var>. If omitted, data from the <kbd>main</kbd> slot will be returned in a backwards-compatible format.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryAlldeletedrevisionsSlots {
	MAIN("main");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryAlldeletedrevisionsSlots> set =
				EnumSet.noneOf(AAPIQueryAlldeletedrevisionsSlots.class);

		public AAPIQueryAlldeletedrevisionsSlots[] build() {
			return set.toArray(AAPIQueryAlldeletedrevisionsSlots[]::new);
		}

		public Builder MAIN() {
			set.add(MAIN);
			return this;
		}
	}
}
