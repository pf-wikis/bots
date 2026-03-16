package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which additional properties to get:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryWatchlistProp {

	/**Adds comment of the edit. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
	COMMENT("comment"),

	/**Adds the expiry time.*/
	EXPIRY("expiry"),

	/**Adds flags for the edit.*/
	FLAGS("flags"),

	/**Adds revision IDs and page IDs.*/
	IDS("ids"),

	/**Adds log information where appropriate.*/
	LOGINFO("loginfo"),

	/**Adds timestamp of when the user was last notified about the edit.*/
	NOTIFICATIONTIMESTAMP("notificationtimestamp"),

	/**Adds parsed comment of the edit. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
	PARSEDCOMMENT("parsedcomment"),

	/**Tags edits that are patrolled.*/
	PATROL("patrol"),

	/**Adds the old and new lengths of the page.*/
	SIZES("sizes"),

	/**Lists tags for the entry.*/
	TAGS("tags"),

	/**Adds timestamp of the edit.*/
	TIMESTAMP("timestamp"),

	/**Adds title of the page.*/
	TITLE("title"),

	/**Adds the user who made the edit. If the user has been revision deleted, a <samp>userhidden</samp> property will be returned.*/
	USER("user"),

	/**Adds user ID of whoever made the edit. If the user has been revision deleted, a <samp>userhidden</samp> property will be returned.*/
	USERID("userid");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryWatchlistProp> set = EnumSet.noneOf(AAPIQueryWatchlistProp.class);

		public AAPIQueryWatchlistProp[] build() {
			return set.toArray(AAPIQueryWatchlistProp[]::new);
		}

		/**Adds comment of the edit. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
		public Builder COMMENT() {
			set.add(COMMENT);
			return this;
		}

		/**Adds the expiry time.*/
		public Builder EXPIRY() {
			set.add(EXPIRY);
			return this;
		}

		/**Adds flags for the edit.*/
		public Builder FLAGS() {
			set.add(FLAGS);
			return this;
		}

		/**Adds revision IDs and page IDs.*/
		public Builder IDS() {
			set.add(IDS);
			return this;
		}

		/**Adds log information where appropriate.*/
		public Builder LOGINFO() {
			set.add(LOGINFO);
			return this;
		}

		/**Adds timestamp of when the user was last notified about the edit.*/
		public Builder NOTIFICATIONTIMESTAMP() {
			set.add(NOTIFICATIONTIMESTAMP);
			return this;
		}

		/**Adds parsed comment of the edit. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
		public Builder PARSEDCOMMENT() {
			set.add(PARSEDCOMMENT);
			return this;
		}

		/**Tags edits that are patrolled.*/
		public Builder PATROL() {
			set.add(PATROL);
			return this;
		}

		/**Adds the old and new lengths of the page.*/
		public Builder SIZES() {
			set.add(SIZES);
			return this;
		}

		/**Lists tags for the entry.*/
		public Builder TAGS() {
			set.add(TAGS);
			return this;
		}

		/**Adds timestamp of the edit.*/
		public Builder TIMESTAMP() {
			set.add(TIMESTAMP);
			return this;
		}

		/**Adds title of the page.*/
		public Builder TITLE() {
			set.add(TITLE);
			return this;
		}

		/**Adds the user who made the edit. If the user has been revision deleted, a <samp>userhidden</samp> property will be returned.*/
		public Builder USER() {
			set.add(USER);
			return this;
		}

		/**Adds user ID of whoever made the edit. If the user has been revision deleted, a <samp>userhidden</samp> property will be returned.*/
		public Builder USERID() {
			set.add(USERID);
			return this;
		}
	}
}
