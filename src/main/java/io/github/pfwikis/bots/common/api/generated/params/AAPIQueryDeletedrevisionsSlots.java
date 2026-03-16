package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Which revision slots to return data for, when slot-related properties are included in <var>drvprops</var>. If omitted, data from the <kbd>main</kbd> slot will be returned in a backwards-compatible format.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryDeletedrevisionsSlots {
	MAIN("main");

	private final String jsonValue;
}
