package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Some intro messages come with optional wrapper frames. Use <kbd>moreframes</kbd> to include them or <kbd>lessframes</kbd> to omit them.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryInfoEditintrostyle {
	LESSFRAMES("lessframes"),

	MOREFRAMES("moreframes");

	private final String jsonValue;
}
