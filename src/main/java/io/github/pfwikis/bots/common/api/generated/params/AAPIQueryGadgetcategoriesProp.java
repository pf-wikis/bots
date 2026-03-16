package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>What gadget category information to get:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryGadgetcategoriesProp {

	/**Number of gadgets in category.*/
	MEMBERS("members"),

	/**Internal category name.*/
	NAME("name"),

	/**Category title.*/
	TITLE("title");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryGadgetcategoriesProp> set =
				EnumSet.noneOf(AAPIQueryGadgetcategoriesProp.class);

		public AAPIQueryGadgetcategoriesProp[] build() {
			return set.toArray(AAPIQueryGadgetcategoriesProp[]::new);
		}

		/**Number of gadgets in category.*/
		public Builder MEMBERS() {
			set.add(MEMBERS);
			return this;
		}

		/**Internal category name.*/
		public Builder NAME() {
			set.add(NAME);
			return this;
		}

		/**Category title.*/
		public Builder TITLE() {
			set.add(TITLE);
			return this;
		}
	}
}
