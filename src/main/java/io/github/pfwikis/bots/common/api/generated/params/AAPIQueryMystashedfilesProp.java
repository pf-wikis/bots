package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which properties to fetch for the files.
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryMystashedfilesProp {

	/**Fetch the file size and image dimensions.*/
	SIZE("size"),

	/**Fetch the file's MIME type and media type.*/
	TYPE("type");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryMystashedfilesProp> set =
				EnumSet.noneOf(AAPIQueryMystashedfilesProp.class);

		public AAPIQueryMystashedfilesProp[] build() {
			return set.toArray(AAPIQueryMystashedfilesProp[]::new);
		}

		/**Fetch the file size and image dimensions.*/
		public Builder SIZE() {
			set.add(SIZE);
			return this;
		}

		/**Fetch the file's MIME type and media type.*/
		public Builder TYPE() {
			set.add(TYPE);
			return this;
		}
	}
}
