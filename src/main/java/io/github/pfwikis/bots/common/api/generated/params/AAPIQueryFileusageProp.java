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
public enum AAPIQueryFileusageProp {

	/**Page ID of each page.*/
	PAGEID("pageid"),

	/**Flag if the page is a redirect.*/
	REDIRECT("redirect"),

	/**Title of each page.*/
	TITLE("title");

	private final String jsonValue;
}
