package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Which metadata to return.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQuerySearchInfo {
	REWRITTENQUERY("rewrittenquery"),

	SUGGESTION("suggestion"),

	TOTALHITS("totalhits");

	private final String jsonValue;
}
