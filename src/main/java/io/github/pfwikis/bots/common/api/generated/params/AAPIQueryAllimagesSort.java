package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Property to sort by.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryAllimagesSort {
	NAME("name"),

	TIMESTAMP("timestamp");

	private final String jsonValue;
}
