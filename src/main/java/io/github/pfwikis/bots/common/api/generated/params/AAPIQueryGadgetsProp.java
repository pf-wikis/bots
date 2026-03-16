package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>What gadget information to get:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryGadgetsProp {

	/**Gadget description transformed into HTML (can be slow, use only if really needed).*/
	DESC("desc"),

	/**Internal gadget ID.*/
	ID("id"),

	/**The gadget metadata.*/
	METADATA("metadata");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryGadgetsProp> set = EnumSet.noneOf(AAPIQueryGadgetsProp.class);

		public AAPIQueryGadgetsProp[] build() {
			return set.toArray(AAPIQueryGadgetsProp[]::new);
		}

		/**Gadget description transformed into HTML (can be slow, use only if really needed).*/
		public Builder DESC() {
			set.add(DESC);
			return this;
		}

		/**Internal gadget ID.*/
		public Builder ID() {
			set.add(ID);
			return this;
		}

		/**The gadget metadata.*/
		public Builder METADATA() {
			set.add(METADATA);
			return this;
		}
	}
}
