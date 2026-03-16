package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Detail level for <var>intestactions</var>. Use the <a href="/wiki/Special:ApiHelp/main" title="Special:ApiHelp/main">main module's</a> <var>errorformat</var> and <var>errorlang</var> parameters to control the format of the messages returned.
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryInfoTestactionsdetail {

	/**Return a boolean value for each action.*/
	BOOLEAN("boolean"),

	/**Return messages describing why the action is disallowed, or an empty array if it is allowed.*/
	FULL("full"),

	/**Like <kbd>full</kbd> but skipping expensive checks.*/
	QUICK("quick");

	private final String jsonValue;
}
