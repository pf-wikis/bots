package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which types of changes to show:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryWatchlistType {

	/**Category membership changes.*/
	CATEGORIZE("categorize"),

	/**Regular page edits.*/
	EDIT("edit"),

	/**External changes.*/
	EXTERNAL("external"),

	/**Log entries.*/
	LOG("log"),

	/**Page creations.*/
	NEW("new");

	private final String jsonValue;
}
