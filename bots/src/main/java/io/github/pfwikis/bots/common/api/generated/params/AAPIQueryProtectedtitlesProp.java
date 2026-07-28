package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which properties to get:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryProtectedtitlesProp {

	/**Adds the comment for the protection.*/
	COMMENT("comment"),

	/**Adds the timestamp of when the protection will be lifted.*/
	EXPIRY("expiry"),

	/**Adds the protection level.*/
	LEVEL("level"),

	/**Adds the parsed comment for the protection.*/
	PARSEDCOMMENT("parsedcomment"),

	/**Adds the timestamp of when protection was added.*/
	TIMESTAMP("timestamp"),

	/**Adds the user that added the protection.*/
	USER("user"),

	/**Adds the user ID that added the protection.*/
	USERID("userid");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryProtectedtitlesProp> set =
				EnumSet.noneOf(AAPIQueryProtectedtitlesProp.class);

		public AAPIQueryProtectedtitlesProp[] build() {
			return set.toArray(AAPIQueryProtectedtitlesProp[]::new);
		}

		/**Adds the comment for the protection.*/
		public Builder COMMENT() {
			set.add(COMMENT);
			return this;
		}

		/**Adds the timestamp of when the protection will be lifted.*/
		public Builder EXPIRY() {
			set.add(EXPIRY);
			return this;
		}

		/**Adds the protection level.*/
		public Builder LEVEL() {
			set.add(LEVEL);
			return this;
		}

		/**Adds the parsed comment for the protection.*/
		public Builder PARSEDCOMMENT() {
			set.add(PARSEDCOMMENT);
			return this;
		}

		/**Adds the timestamp of when protection was added.*/
		public Builder TIMESTAMP() {
			set.add(TIMESTAMP);
			return this;
		}

		/**Adds the user that added the protection.*/
		public Builder USER() {
			set.add(USER);
			return this;
		}

		/**Adds the user ID that added the protection.*/
		public Builder USERID() {
			set.add(USERID);
			return this;
		}
	}
}
