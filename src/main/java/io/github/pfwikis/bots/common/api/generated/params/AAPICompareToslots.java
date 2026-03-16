package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Override content of the revision specified by <var>totitle</var>, <var>toid</var> or <var>torev</var>.
 * </p><p>This parameter specifies the slots that are to be modified. Use <var>totext-&#x7b;slot}</var>, <var>tocontentmodel-&#x7b;slot}</var>, and <var>tocontentformat-&#x7b;slot}</var> to specify content for each slot.
 * </p>*/
@Getter
@RequiredArgsConstructor
public enum AAPICompareToslots {
	MAIN("main");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPICompareToslots> set = EnumSet.noneOf(AAPICompareToslots.class);

		public AAPICompareToslots[] build() {
			return set.toArray(AAPICompareToslots[]::new);
		}

		public Builder MAIN() {
			set.add(MAIN);
			return this;
		}
	}
}
