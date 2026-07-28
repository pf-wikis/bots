package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Filter based on whether a page has langlinks. Note that this may not consider langlinks added by extensions.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryAllpagesFilterlanglinks {
	ALL("all"),

	WITHLANGLINKS("withlanglinks"),

	WITHOUTLANGLINKS("withoutlanglinks");

	private final String jsonValue;
}
