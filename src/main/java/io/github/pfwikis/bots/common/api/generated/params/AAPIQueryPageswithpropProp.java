package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which pieces of information to include:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryPageswithpropProp {

	/**Adds the page ID.*/
	IDS("ids"),

	/**Adds the title and namespace ID of the page.*/
	TITLE("title"),

	/**Adds the value of the page property.*/
	VALUE("value");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryPageswithpropProp> set =
				EnumSet.noneOf(AAPIQueryPageswithpropProp.class);

		public AAPIQueryPageswithpropProp[] build() {
			return set.toArray(AAPIQueryPageswithpropProp[]::new);
		}

		/**Adds the page ID.*/
		public Builder IDS() {
			set.add(IDS);
			return this;
		}

		/**Adds the title and namespace ID of the page.*/
		public Builder TITLE() {
			set.add(TITLE);
			return this;
		}

		/**Adds the value of the page property.*/
		public Builder VALUE() {
			set.add(VALUE);
			return this;
		}
	}
}
