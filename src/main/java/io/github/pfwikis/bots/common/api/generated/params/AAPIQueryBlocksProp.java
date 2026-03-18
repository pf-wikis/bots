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
public enum AAPIQueryBlocksProp {

	/**Adds the username of the blocking user.*/
	BY("by"),

	/**Adds the user ID of the blocking user.*/
	BYID("byid"),

	/**Adds the timestamp of when the block expires.*/
	EXPIRY("expiry"),

	/**Tags the ban with (autoblock, anononly, etc.).*/
	FLAGS("flags"),

	/**Adds the ID of the block.*/
	ID("id"),

	/**Adds the range of IP addresses affected by the block.*/
	RANGE("range"),

	/**Adds the reason given for the block.*/
	REASON("reason"),

	/**Adds the partial block restrictions if the block is not sitewide.*/
	RESTRICTIONS("restrictions"),

	/**Adds the timestamp of when the block was given.*/
	TIMESTAMP("timestamp"),

	/**Adds the username of the blocked user.*/
	USER("user"),

	/**Adds the user ID of the blocked user.*/
	USERID("userid");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryBlocksProp> set = EnumSet.noneOf(AAPIQueryBlocksProp.class);

		public AAPIQueryBlocksProp[] build() {
			return set.toArray(AAPIQueryBlocksProp[]::new);
		}

		/**Adds the username of the blocking user.*/
		public Builder BY() {
			set.add(BY);
			return this;
		}

		/**Adds the user ID of the blocking user.*/
		public Builder BYID() {
			set.add(BYID);
			return this;
		}

		/**Adds the timestamp of when the block expires.*/
		public Builder EXPIRY() {
			set.add(EXPIRY);
			return this;
		}

		/**Tags the ban with (autoblock, anononly, etc.).*/
		public Builder FLAGS() {
			set.add(FLAGS);
			return this;
		}

		/**Adds the ID of the block.*/
		public Builder ID() {
			set.add(ID);
			return this;
		}

		/**Adds the range of IP addresses affected by the block.*/
		public Builder RANGE() {
			set.add(RANGE);
			return this;
		}

		/**Adds the reason given for the block.*/
		public Builder REASON() {
			set.add(REASON);
			return this;
		}

		/**Adds the partial block restrictions if the block is not sitewide.*/
		public Builder RESTRICTIONS() {
			set.add(RESTRICTIONS);
			return this;
		}

		/**Adds the timestamp of when the block was given.*/
		public Builder TIMESTAMP() {
			set.add(TIMESTAMP);
			return this;
		}

		/**Adds the username of the blocked user.*/
		public Builder USER() {
			set.add(USER);
			return this;
		}

		/**Adds the user ID of the blocked user.*/
		public Builder USERID() {
			set.add(USERID);
			return this;
		}
	}
}
