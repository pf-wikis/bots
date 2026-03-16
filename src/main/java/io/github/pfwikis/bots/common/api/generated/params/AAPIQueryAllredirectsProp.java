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
public enum AAPIQueryAllredirectsProp {

	/**Adds the fragment from the redirect, if any (cannot be used with <var>arunique</var>).*/
	FRAGMENT("fragment"),

	/**Adds the page ID of the redirecting page (cannot be used with <var>arunique</var>).*/
	IDS("ids"),

	/**Adds the interwiki prefix from the redirect, if any (cannot be used with <var>arunique</var>).*/
	INTERWIKI("interwiki"),

	/**Adds the title of the redirect.*/
	TITLE("title");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryAllredirectsProp> set =
				EnumSet.noneOf(AAPIQueryAllredirectsProp.class);

		public AAPIQueryAllredirectsProp[] build() {
			return set.toArray(AAPIQueryAllredirectsProp[]::new);
		}

		/**Adds the fragment from the redirect, if any (cannot be used with <var>arunique</var>).*/
		public Builder FRAGMENT() {
			set.add(FRAGMENT);
			return this;
		}

		/**Adds the page ID of the redirecting page (cannot be used with <var>arunique</var>).*/
		public Builder IDS() {
			set.add(IDS);
			return this;
		}

		/**Adds the interwiki prefix from the redirect, if any (cannot be used with <var>arunique</var>).*/
		public Builder INTERWIKI() {
			set.add(INTERWIKI);
			return this;
		}

		/**Adds the title of the redirect.*/
		public Builder TITLE() {
			set.add(TITLE);
			return this;
		}
	}
}
