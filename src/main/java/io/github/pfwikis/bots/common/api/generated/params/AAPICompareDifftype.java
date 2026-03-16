package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Return the comparison formatted as inline HTML.*/
@Getter
@RequiredArgsConstructor
public enum AAPICompareDifftype {
	INLINE("inline"),

	TABLE("table");

	private final String jsonValue;
}
