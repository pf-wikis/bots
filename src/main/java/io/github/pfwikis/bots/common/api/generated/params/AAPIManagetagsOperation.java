package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which operation to perform:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIManagetagsOperation {

	/**Activate a change tag, allowing users to apply it manually.*/
	ACTIVATE("activate"),

	/**Create a new change tag for manual use.*/
	CREATE("create"),

	/**Deactivate a change tag, preventing users from applying it manually.*/
	DEACTIVATE("deactivate"),

	/**Remove a change tag from the database, including removing the tag from all revisions, recent change entries and log entries on which it is used.*/
	DELETE("delete");

	private final String jsonValue;
}
