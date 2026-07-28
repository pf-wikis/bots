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
public enum AAPIQueryAllfileusagesProp {

	/**Adds the page IDs of the using pages (cannot be used with afunique).*/
	IDS("ids"),

	/**Adds the title of the file.*/
	TITLE("title");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryAllfileusagesProp> set =
				EnumSet.noneOf(AAPIQueryAllfileusagesProp.class);

		public AAPIQueryAllfileusagesProp[] build() {
			return set.toArray(AAPIQueryAllfileusagesProp[]::new);
		}

		/**Adds the page IDs of the using pages (cannot be used with afunique).*/
		public Builder IDS() {
			set.add(IDS);
			return this;
		}

		/**Adds the title of the file.*/
		public Builder TITLE() {
			set.add(TITLE);
			return this;
		}
	}
}
