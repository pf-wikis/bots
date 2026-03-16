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
public enum AAPIQueryAllcategoriesProp {

	/**Tags categories that are hidden with <code>_&#95;HIDDENCAT_&#95;</code>.*/
	HIDDEN("hidden"),

	/**Adds number of pages in the category.*/
	SIZE("size");

	private final String jsonValue;
}
