package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Include additional pieces of information:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryRecentchangesProp {

	/**Adds the comment for the edit. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
	COMMENT("comment"),

	/**Adds flags for the edit.*/
	FLAGS("flags"),

	/**Adds the page ID, recent changes ID and the new and old revision ID.*/
	IDS("ids"),

	/**Adds log information (log ID, log type, etc) to log entries.*/
	LOGINFO("loginfo"),

	/**Adds the parsed comment for the edit. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
	PARSEDCOMMENT("parsedcomment"),

	/**Tags patrollable edits as being patrolled or unpatrolled.*/
	PATROLLED("patrolled"),

	/**Tags edit if page is a redirect.*/
	REDIRECT("redirect"),

	/**Adds the content checksum for entries associated with a revision. If the content has been revision deleted, a <samp>sha1hidden</samp> property will be returned.*/
	SHA1("sha1"),

	/**Adds the new and old page length in bytes.*/
	SIZES("sizes"),

	/**Lists tags for the entry.*/
	TAGS("tags"),

	/**Adds timestamp of the edit.*/
	TIMESTAMP("timestamp"),

	/**Adds the page title of the edit.*/
	TITLE("title"),

	/**Adds the user responsible for the edit and tags if they are an IP. If the user has been revision deleted, a <samp>userhidden</samp> property will be returned.*/
	USER("user"),

	/**Adds the user ID responsible for the edit. If the user has been revision deleted, a <samp>userhidden</samp> property will be returned.*/
	USERID("userid");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryRecentchangesProp> set =
				EnumSet.noneOf(AAPIQueryRecentchangesProp.class);

		public AAPIQueryRecentchangesProp[] build() {
			return set.toArray(AAPIQueryRecentchangesProp[]::new);
		}

		/**Adds the comment for the edit. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
		public Builder COMMENT() {
			set.add(COMMENT);
			return this;
		}

		/**Adds flags for the edit.*/
		public Builder FLAGS() {
			set.add(FLAGS);
			return this;
		}

		/**Adds the page ID, recent changes ID and the new and old revision ID.*/
		public Builder IDS() {
			set.add(IDS);
			return this;
		}

		/**Adds log information (log ID, log type, etc) to log entries.*/
		public Builder LOGINFO() {
			set.add(LOGINFO);
			return this;
		}

		/**Adds the parsed comment for the edit. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
		public Builder PARSEDCOMMENT() {
			set.add(PARSEDCOMMENT);
			return this;
		}

		/**Tags patrollable edits as being patrolled or unpatrolled.*/
		public Builder PATROLLED() {
			set.add(PATROLLED);
			return this;
		}

		/**Tags edit if page is a redirect.*/
		public Builder REDIRECT() {
			set.add(REDIRECT);
			return this;
		}

		/**Adds the content checksum for entries associated with a revision. If the content has been revision deleted, a <samp>sha1hidden</samp> property will be returned.*/
		public Builder SHA1() {
			set.add(SHA1);
			return this;
		}

		/**Adds the new and old page length in bytes.*/
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

		/**Adds the page title of the edit.*/
		public Builder TITLE() {
			set.add(TITLE);
			return this;
		}

		/**Adds the user responsible for the edit and tags if they are an IP. If the user has been revision deleted, a <samp>userhidden</samp> property will be returned.*/
		public Builder USER() {
			set.add(USER);
			return this;
		}

		/**Adds the user ID responsible for the edit. If the user has been revision deleted, a <samp>userhidden</samp> property will be returned.*/
		public Builder USERID() {
			set.add(USERID);
			return this;
		}
	}
}
