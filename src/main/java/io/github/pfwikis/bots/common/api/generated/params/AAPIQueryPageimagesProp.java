package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which information to return:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryPageimagesProp {

	/**Image title.*/
	NAME("name"),

	/**URL and original dimensions of image associated with page, if any.*/
	ORIGINAL("original"),

	/**URL and dimensions of thumbnail image associated with page, if any.*/
	THUMBNAIL("thumbnail");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryPageimagesProp> set =
				EnumSet.noneOf(AAPIQueryPageimagesProp.class);

		public AAPIQueryPageimagesProp[] build() {
			return set.toArray(AAPIQueryPageimagesProp[]::new);
		}

		/**Image title.*/
		public Builder NAME() {
			set.add(NAME);
			return this;
		}

		/**URL and original dimensions of image associated with page, if any.*/
		public Builder ORIGINAL() {
			set.add(ORIGINAL);
			return this;
		}

		/**URL and dimensions of thumbnail image associated with page, if any.*/
		public Builder THUMBNAIL() {
			set.add(THUMBNAIL);
			return this;
		}
	}
}
