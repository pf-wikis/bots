package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Filter protections based on cascadingness (ignored when apprtype isn't set).*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryAllpagesPrfiltercascade {
	ALL("all"),

	CASCADING("cascading"),

	NONCASCADING("noncascading");

	private final String jsonValue;
}
