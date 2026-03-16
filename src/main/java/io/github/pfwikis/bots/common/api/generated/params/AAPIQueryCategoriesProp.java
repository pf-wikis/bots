package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which additional properties to get for each category:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryCategoriesProp {

	/**Tags categories that are hidden with <code>_&#95;HIDDENCAT_&#95;</code>.*/
	HIDDEN("hidden"),

	/**Adds the sortkey (hexadecimal string) and sortkey prefix (human-readable part) for the category.*/
	SORTKEY("sortkey"),

	/**Adds timestamp of when the category was added.*/
	TIMESTAMP("timestamp");

	private final String jsonValue;
}
