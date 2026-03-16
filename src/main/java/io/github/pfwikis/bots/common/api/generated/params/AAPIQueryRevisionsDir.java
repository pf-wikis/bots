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
public enum AAPIQueryRevisionsDir {

	/**List oldest first. Note: rvstart has to be before rvend.*/
	NEWER("newer"),

	/**List newest first (default). Note: rvstart has to be later than rvend.*/
	OLDER("older");

	private final String jsonValue;
}
