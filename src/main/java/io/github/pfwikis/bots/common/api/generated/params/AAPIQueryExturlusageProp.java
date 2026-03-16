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
public enum AAPIQueryExturlusageProp {

	/**Adds the ID of page.*/
	IDS("ids"),

	/**Adds the title and namespace ID of the page.*/
	TITLE("title"),

	/**Adds the URL used in the page.*/
	URL("url");

	private final String jsonValue;
}
