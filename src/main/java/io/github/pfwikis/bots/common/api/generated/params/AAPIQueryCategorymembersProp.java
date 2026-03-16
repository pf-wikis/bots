package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which pieces of information to include:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryCategorymembersProp {

	/**Adds the page ID.*/
	IDS("ids"),

	/**Adds the sortkey used for sorting in the category (hexadecimal string).*/
	SORTKEY("sortkey"),

	/**Adds the sortkey prefix used for sorting in the category (human-readable part of the sortkey).*/
	SORTKEYPREFIX("sortkeyprefix"),

	/**Adds the timestamp of when the page was included.*/
	TIMESTAMP("timestamp"),

	/**Adds the title and namespace ID of the page.*/
	TITLE("title"),

	/**Adds the type that the page has been categorised as (<samp>page</samp>, <samp>subcat</samp> or <samp>file</samp>).*/
	TYPE("type");

	private final String jsonValue;
}
