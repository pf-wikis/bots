package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which pieces of information to get.
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPICompareProp {

	/**The comment on the 'from' and 'to' revisions. If the comment has been revision deleted, a <samp>fromcommenthidden</samp> or <samp>tocommenthidden</samp> property will be returned.*/
	COMMENT("comment"),

	/**The diff HTML.*/
	DIFF("diff"),

	/**The size of the diff HTML, in bytes.*/
	DIFFSIZE("diffsize"),

	/**The page and revision IDs of the 'from' and 'to' revisions.*/
	IDS("ids"),

	/**The parsed comment on the 'from' and 'to' revisions. If the comment has been revision deleted, a <samp>fromcommenthidden</samp> or <samp>tocommenthidden</samp> property will be returned.*/
	PARSEDCOMMENT("parsedcomment"),

	/**The revision IDs of the revision previous to 'from' and after 'to', if any.*/
	REL("rel"),

	/**The size of the 'from' and 'to' revisions.*/
	SIZE("size"),

	/**The timestamp of the 'from' and 'to' revisions.*/
	TIMESTAMP("timestamp"),

	/**The page titles of the 'from' and 'to' revisions.*/
	TITLE("title"),

	/**The username and ID of the 'from' and 'to' revisions. If the user has been revision deleted, a <samp>fromuserhidden</samp> or <samp>touserhidden</samp> property will be returned.*/
	USER("user");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPICompareProp> set = EnumSet.noneOf(AAPICompareProp.class);

		public AAPICompareProp[] build() {
			return set.toArray(AAPICompareProp[]::new);
		}

		/**The comment on the 'from' and 'to' revisions. If the comment has been revision deleted, a <samp>fromcommenthidden</samp> or <samp>tocommenthidden</samp> property will be returned.*/
		public Builder COMMENT() {
			set.add(COMMENT);
			return this;
		}

		/**The diff HTML.*/
		public Builder DIFF() {
			set.add(DIFF);
			return this;
		}

		/**The size of the diff HTML, in bytes.*/
		public Builder DIFFSIZE() {
			set.add(DIFFSIZE);
			return this;
		}

		/**The page and revision IDs of the 'from' and 'to' revisions.*/
		public Builder IDS() {
			set.add(IDS);
			return this;
		}

		/**The parsed comment on the 'from' and 'to' revisions. If the comment has been revision deleted, a <samp>fromcommenthidden</samp> or <samp>tocommenthidden</samp> property will be returned.*/
		public Builder PARSEDCOMMENT() {
			set.add(PARSEDCOMMENT);
			return this;
		}

		/**The revision IDs of the revision previous to 'from' and after 'to', if any.*/
		public Builder REL() {
			set.add(REL);
			return this;
		}

		/**The size of the 'from' and 'to' revisions.*/
		public Builder SIZE() {
			set.add(SIZE);
			return this;
		}

		/**The timestamp of the 'from' and 'to' revisions.*/
		public Builder TIMESTAMP() {
			set.add(TIMESTAMP);
			return this;
		}

		/**The page titles of the 'from' and 'to' revisions.*/
		public Builder TITLE() {
			set.add(TITLE);
			return this;
		}

		/**The username and ID of the 'from' and 'to' revisions. If the user has been revision deleted, a <samp>fromuserhidden</samp> or <samp>touserhidden</samp> property will be returned.*/
		public Builder USER() {
			set.add(USER);
			return this;
		}
	}
}
