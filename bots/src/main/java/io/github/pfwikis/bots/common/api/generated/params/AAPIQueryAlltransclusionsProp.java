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
public enum AAPIQueryAlltransclusionsProp {

	/**Adds the page ID of the transcluding page (cannot be used with atunique).*/
	IDS("ids"),

	/**Adds the title of the transclusion.*/
	TITLE("title");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryAlltransclusionsProp> set =
				EnumSet.noneOf(AAPIQueryAlltransclusionsProp.class);

		public AAPIQueryAlltransclusionsProp[] build() {
			return set.toArray(AAPIQueryAlltransclusionsProp[]::new);
		}

		/**Adds the page ID of the transcluding page (cannot be used with atunique).*/
		public Builder IDS() {
			set.add(IDS);
			return this;
		}

		/**Adds the title of the transclusion.*/
		public Builder TITLE() {
			set.add(TITLE);
			return this;
		}
	}
}
