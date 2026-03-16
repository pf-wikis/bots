package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>In which direction to enumerate:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryWatchlistDir {

	/**List oldest first. Note: wlstart has to be before wlend.*/
	NEWER("newer"),

	/**List newest first (default). Note: wlstart has to be later than wlend.*/
	OLDER("older");

	private final String jsonValue;
}
