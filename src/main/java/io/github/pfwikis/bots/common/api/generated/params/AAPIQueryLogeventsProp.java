package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which properties to get:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryLogeventsProp {

	/**Adds the comment of the log event. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
	COMMENT("comment"),

	/**Lists additional details about the log event. If the log event has been revision deleted, an <samp>actionhidden</samp> property will be returned.*/
	DETAILS("details"),

	/**Adds the ID of the log event.*/
	IDS("ids"),

	/**Adds the parsed comment of the log event. If the comment has been revision deleted, a <samp>commenthidden</samp> property will be returned.*/
	PARSEDCOMMENT("parsedcomment"),

	/**Lists tags for the log event.*/
	TAGS("tags"),

	/**Adds the timestamp for the log event.*/
	TIMESTAMP("timestamp"),

	/**Adds the title of the page for the log event.*/
	TITLE("title"),

	/**Adds the type of log event.*/
	TYPE("type"),

	/**Adds the user responsible for the log event. If the user has been revision deleted, a <samp>userhidden</samp> property will be returned.*/
	USER("user"),

	/**Adds the user ID who was responsible for the log event. If the user has been revision deleted, a <samp>userhidden</samp> property will be returned.*/
	USERID("userid");

	private final String jsonValue;
}
