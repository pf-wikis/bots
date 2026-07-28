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
public enum AAPIQueryCategorymembersProp {

	/**Adds the page ID.*/
	IDS("ids"),

	/**Adds the sortkey used for sorting in the category (hexadecimal string).*/
	SORTKEY("sortkey"),

	/**Adds the sortkey prefix used for sorting in the category (human-readable part of the sortkey).*/
	SORTKEYPREFIX("sortkeyprefix"),

	/**Adds the timestamp of when the page was included.*/
	TIMESTAMP("timestamp"),

	/**Adds the title and namespace ID of the page.*/
	TITLE("title"),

	/**Adds the type that the page has been categorised as (<samp>page</samp>, <samp>subcat</samp> or <samp>file</samp>).*/
	TYPE("type");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryCategorymembersProp> set =
				EnumSet.noneOf(AAPIQueryCategorymembersProp.class);

		public AAPIQueryCategorymembersProp[] build() {
			return set.toArray(AAPIQueryCategorymembersProp[]::new);
		}

		/**Adds the page ID.*/
		public Builder IDS() {
			set.add(IDS);
			return this;
		}

		/**Adds the sortkey used for sorting in the category (hexadecimal string).*/
		public Builder SORTKEY() {
			set.add(SORTKEY);
			return this;
		}

		/**Adds the sortkey prefix used for sorting in the category (human-readable part of the sortkey).*/
		public Builder SORTKEYPREFIX() {
			set.add(SORTKEYPREFIX);
			return this;
		}

		/**Adds the timestamp of when the page was included.*/
		public Builder TIMESTAMP() {
			set.add(TIMESTAMP);
			return this;
		}

		/**Adds the title and namespace ID of the page.*/
		public Builder TITLE() {
			set.add(TITLE);
			return this;
		}

		/**Adds the type that the page has been categorised as (<samp>page</samp>, <samp>subcat</samp> or <samp>file</samp>).*/
		public Builder TYPE() {
			set.add(TYPE);
			return this;
		}
	}
}
