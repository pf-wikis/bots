package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>What to do if the option was set globally using the GlobalPreferences extension.
 * </p>
 * <ul><li><kbd>ignore</kbd>: Do nothing. The option remains with its previous value.</li>
 * <li><kbd>override</kbd>: Add a local override.</li>
 * <li><kbd>update</kbd>: Update the option globally.</li></ul>*/
@Getter
@RequiredArgsConstructor
public enum AAPIOptionsGlobal {
	IGNORE("ignore"),

	OVERRIDE("override"),

	UPDATE("update");

	private final String jsonValue;
}
