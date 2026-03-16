package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which properties to get for each revision:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryDeletedrevisionsProp {

	/**Comment by the user for the revision. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
	COMMENT("comment"),

	/**Content of each revision slot. If the content has been revision deleted, a <samp>texthidden</samp> property will be returned. For performance reasons, if this option is used, <var>drvlimit</var> is enforced to 50.*/
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

	/**<span class="apihelp-deprecated">Deprecated.</span> Use <kbd><a href="/wiki/Special:ApiHelp/expandtemplates" title="Special:ApiHelp/expandtemplates">action=expandtemplates</a></kbd> or <kbd><a href="/wiki/Special:ApiHelp/parse" title="Special:ApiHelp/parse">action=parse</a></kbd> instead. The XML parse tree of revision content (requires content model <code>wikitext</code>). For performance reasons, if this option is used, <var>drvlimit</var> is enforced to 50.*/
	PARSETREE("parsetree");

	private final String jsonValue;
}
