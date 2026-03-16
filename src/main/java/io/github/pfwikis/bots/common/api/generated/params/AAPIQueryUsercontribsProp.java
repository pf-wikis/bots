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
}
