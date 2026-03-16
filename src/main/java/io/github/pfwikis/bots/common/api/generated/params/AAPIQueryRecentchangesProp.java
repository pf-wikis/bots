package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
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
}
