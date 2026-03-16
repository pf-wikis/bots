package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which additional properties to get for each interlanguage link:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryLanglinksProp {

	/**Adds the native language name.*/
	AUTONYM("autonym"),

	/**Adds the localised language name (best effort). Use <var>llinlanguagecode</var> to control the language.*/
	LANGNAME("langname"),

	/**Adds the full URL.*/
	URL("url");

	private final String jsonValue;
}
