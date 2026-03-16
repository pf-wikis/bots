package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which properties to get for each revision:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryAllrevisionsProp {

	/**Comment by the user for the revision. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
	COMMENT("comment"),

	/**Content of each revision slot. If the content has been revision deleted, a <samp>texthidden</samp> property will be returned. For performance reasons, if this option is used, <var>arvlimit</var> is enforced to 50.*/
	CONTENT("content"),

	/**Content model ID of each revision slot.*/
	CONTENTMODEL("contentmodel"),

	/**Revision flags (minor).*/
	FLAGS("flags"),

	/**The ID of the revision.*/
	IDS("ids"),

	/**Parsed comment by the user for the revision. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
	PARSEDCOMMENT("parsedcomment"),

	/**List content slot roles that exist in the revision.*/
	ROLES("roles"),

	/**SHA-1 (base 16) of the revision. If the content has been revision deleted, a <samp>sha1hidden</samp> property will be returned.*/
	SHA1("sha1"),

	/**Length (bytes) of the revision.*/
	SIZE("size"),

	/**SHA-1 (base 16) of each revision slot. If the content has been revision deleted, a <samp>sha1hidden</samp> property will be returned.*/
	SLOTSHA1("slotsha1"),

	/**Length (bytes) of each revision slot.*/
	SLOTSIZE("slotsize"),

	/**Tags for the revision.*/
	TAGS("tags"),

	/**The timestamp of the revision.*/
	TIMESTAMP("timestamp"),

	/**User that made the revision. If the user has been revision deleted, a <samp>userhidden</samp> property will be returned.*/
	USER("user"),

	/**User ID of the revision creator. If the user has been revision deleted, a <samp>userhidden</samp> property will be returned.*/
	USERID("userid"),

	/**<span class="apihelp-deprecated">Deprecated.</span> Use <kbd><a href="/wiki/Special:ApiHelp/expandtemplates" title="Special:ApiHelp/expandtemplates">action=expandtemplates</a></kbd> or <kbd><a href="/wiki/Special:ApiHelp/parse" title="Special:ApiHelp/parse">action=parse</a></kbd> instead. The XML parse tree of revision content (requires content model <code>wikitext</code>). For performance reasons, if this option is used, <var>arvlimit</var> is enforced to 50.*/
	PARSETREE("parsetree");

	private final String jsonValue;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private EnumSet<AAPIQueryAllrevisionsProp> set =
				EnumSet.noneOf(AAPIQueryAllrevisionsProp.class);

		public AAPIQueryAllrevisionsProp[] build() {
			return set.toArray(AAPIQueryAllrevisionsProp[]::new);
		}

		/**Comment by the user for the revision. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
		public Builder COMMENT() {
			set.add(COMMENT);
			return this;
		}

		/**Content of each revision slot. If the content has been revision deleted, a <samp>texthidden</samp> property will be returned. For performance reasons, if this option is used, <var>arvlimit</var> is enforced to 50.*/
		public Builder CONTENT() {
			set.add(CONTENT);
			return this;
		}

		/**Content model ID of each revision slot.*/
		public Builder CONTENTMODEL() {
			set.add(CONTENTMODEL);
			return this;
		}

		/**Revision flags (minor).*/
		public Builder FLAGS() {
			set.add(FLAGS);
			return this;
		}

		/**The ID of the revision.*/
		public Builder IDS() {
			set.add(IDS);
			return this;
		}

		/**Parsed comment by the user for the revision. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
		public Builder PARSEDCOMMENT() {
			set.add(PARSEDCOMMENT);
			return this;
		}

		/**List content slot roles that exist in the revision.*/
		public Builder ROLES() {
			set.add(ROLES);
			return this;
		}

		/**SHA-1 (base 16) of the revision. If the content has been revision deleted, a <samp>sha1hidden</samp> property will be returned.*/
		public Builder SHA1() {
			set.add(SHA1);
			return this;
		}

		/**Length (bytes) of the revision.*/
		public Builder SIZE() {
			set.add(SIZE);
			return this;
		}

		/**SHA-1 (base 16) of each revision slot. If the content has been revision deleted, a <samp>sha1hidden</samp> property will be returned.*/
		public Builder SLOTSHA1() {
			set.add(SLOTSHA1);
			return this;
		}

		/**Length (bytes) of each revision slot.*/
		public Builder SLOTSIZE() {
			set.add(SLOTSIZE);
			return this;
		}

		/**Tags for the revision.*/
		public Builder TAGS() {
			set.add(TAGS);
			return this;
		}

		/**The timestamp of the revision.*/
		public Builder TIMESTAMP() {
			set.add(TIMESTAMP);
			return this;
		}

		/**User that made the revision. If the user has been revision deleted, a <samp>userhidden</samp> property will be returned.*/
		public Builder USER() {
			set.add(USER);
			return this;
		}

		/**User ID of the revision creator. If the user has been revision deleted, a <samp>userhidden</samp> property will be returned.*/
		public Builder USERID() {
			set.add(USERID);
			return this;
		}

		/**<span class="apihelp-deprecated">Deprecated.</span> Use <kbd><a href="/wiki/Special:ApiHelp/expandtemplates" title="Special:ApiHelp/expandtemplates">action=expandtemplates</a></kbd> or <kbd><a href="/wiki/Special:ApiHelp/parse" title="Special:ApiHelp/parse">action=parse</a></kbd> instead. The XML parse tree of revision content (requires content model <code>wikitext</code>). For performance reasons, if this option is used, <var>arvlimit</var> is enforced to 50.*/
		public Builder PARSETREE() {
			set.add(PARSETREE);
			return this;
		}
	}
}
