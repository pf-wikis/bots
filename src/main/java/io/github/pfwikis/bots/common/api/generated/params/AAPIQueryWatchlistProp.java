package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
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
}
