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
public enum AAPIQueryUsercontribsProp {

	/**Adds the comment of the edit. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
	COMMENT("comment"),

	/**Adds flags of the edit.*/
	FLAGS("flags"),

	/**Adds the page ID and revision ID.*/
	IDS("ids"),

	/**Adds the parsed comment of the edit. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
	PARSEDCOMMENT("parsedcomment"),

	/**Tags patrolled edits.*/
	PATROLLED("patrolled"),

	/**Adds the new size of the edit.*/
	SIZE("size"),

	/**Adds the size delta of the edit against its parent.*/
	SIZEDIFF("sizediff"),

	/**Lists tags for the edit.*/
	TAGS("tags"),

	/**Adds the timestamp of the edit.*/
	TIMESTAMP("timestamp"),

	/**Adds the title and namespace ID of the page.*/
	TITLE("title");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryUsercontribsProp> set =
				EnumSet.noneOf(AAPIQueryUsercontribsProp.class);

		public AAPIQueryUsercontribsProp[] build() {
			return set.toArray(AAPIQueryUsercontribsProp[]::new);
		}

		/**Adds the comment of the edit. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
		public Builder COMMENT() {
			set.add(COMMENT);
			return this;
		}

		/**Adds flags of the edit.*/
		public Builder FLAGS() {
			set.add(FLAGS);
			return this;
		}

		/**Adds the page ID and revision ID.*/
		public Builder IDS() {
			set.add(IDS);
			return this;
		}

		/**Adds the parsed comment of the edit. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
		public Builder PARSEDCOMMENT() {
			set.add(PARSEDCOMMENT);
			return this;
		}

		/**Tags patrolled edits.*/
		public Builder PATROLLED() {
			set.add(PATROLLED);
			return this;
		}

		/**Adds the new size of the edit.*/
		public Builder SIZE() {
			set.add(SIZE);
			return this;
		}

		/**Adds the size delta of the edit against its parent.*/
		public Builder SIZEDIFF() {
			set.add(SIZEDIFF);
			return this;
		}

		/**Lists tags for the edit.*/
		public Builder TAGS() {
			set.add(TAGS);
			return this;
		}

		/**Adds the timestamp of the edit.*/
		public Builder TIMESTAMP() {
			set.add(TIMESTAMP);
			return this;
		}

		/**Adds the title and namespace ID of the page.*/
		public Builder TITLE() {
			set.add(TITLE);
			return this;
		}
	}
}
