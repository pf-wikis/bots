package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Which pages to list.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryAllpagesFilterredir {
	ALL("all"),

	NONREDIRECTS("nonredirects"),

	REDIRECTS("redirects");

	private final String jsonValue;
}
