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
public enum AAPIQueryProtectedtitlesDir {

	/**List oldest first. Note: ptstart has to be before ptend.*/
	NEWER("newer"),

	/**List newest first (default). Note: ptstart has to be later than ptend.*/
	OLDER("older");

	private final String jsonValue;
}
