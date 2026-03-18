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
public enum AAPIQueryExturlusageProp {

	/**Adds the ID of page.*/
	IDS("ids"),

	/**Adds the title and namespace ID of the page.*/
	TITLE("title"),

	/**Adds the URL used in the page.*/
	URL("url");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryExturlusageProp> set =
				EnumSet.noneOf(AAPIQueryExturlusageProp.class);

		public AAPIQueryExturlusageProp[] build() {
			return set.toArray(AAPIQueryExturlusageProp[]::new);
		}

		/**Adds the ID of page.*/
		public Builder IDS() {
			set.add(IDS);
			return this;
		}

		/**Adds the title and namespace ID of the page.*/
		public Builder TITLE() {
			set.add(TITLE);
			return this;
		}

		/**Adds the URL used in the page.*/
		public Builder URL() {
			set.add(URL);
			return this;
		}
	}
}
