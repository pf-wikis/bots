package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>In which direction to enumerate:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryRecentchangesDir {

	/**List oldest first. Note: rcstart has to be before rcend.*/
	NEWER("newer"),

	/**List newest first (default). Note: rcstart has to be later than rcend.*/
	OLDER("older");

	private final String jsonValue;
}
