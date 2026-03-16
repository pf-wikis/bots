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
public enum AAPIQueryAllredirectsProp {

	/**Adds the fragment from the redirect, if any (cannot be used with <var>arunique</var>).*/
	FRAGMENT("fragment"),

	/**Adds the page ID of the redirecting page (cannot be used with <var>arunique</var>).*/
	IDS("ids"),

	/**Adds the interwiki prefix from the redirect, if any (cannot be used with <var>arunique</var>).*/
	INTERWIKI("interwiki"),

	/**Adds the title of the redirect.*/
	TITLE("title");

	private final String jsonValue;
}
