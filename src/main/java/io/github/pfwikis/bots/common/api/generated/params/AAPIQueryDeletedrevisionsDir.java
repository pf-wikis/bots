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
public enum AAPIQueryDeletedrevisionsDir {

	/**List oldest first. Note: drvstart has to be before drvend.*/
	NEWER("newer"),

	/**List newest first (default). Note: drvstart has to be later than drvend.*/
	OLDER("older");

	private final String jsonValue;
}
