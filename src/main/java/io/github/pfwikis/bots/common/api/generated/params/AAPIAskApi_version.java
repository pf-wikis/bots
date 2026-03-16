package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Output formatting:
 * </p>
 * <dl><dt>2</dt>
 * <dd>Backwards-compatible format using {} for the result list.</dd>
 * <dt>3</dt>
 * <dd>Experimental format using [] as result list.</dd></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIAskApi_version {
	V_2("2"),

	V_3("3");

	private final String jsonValue;
}
