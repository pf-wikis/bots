package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Return individual diffs for these slots, rather than one combined diff for all slots.*/
@Getter
@RequiredArgsConstructor
public enum AAPICompareSlots {
	MAIN("main");

	private final String jsonValue;
}
