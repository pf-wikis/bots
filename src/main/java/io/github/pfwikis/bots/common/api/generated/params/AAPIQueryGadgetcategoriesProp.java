package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>What gadget category information to get:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryGadgetcategoriesProp {

	/**Number of gadgets in category.*/
	MEMBERS("members"),

	/**Internal category name.*/
	NAME("name"),

	/**Category title.*/
	TITLE("title");

	private final String jsonValue;
}
