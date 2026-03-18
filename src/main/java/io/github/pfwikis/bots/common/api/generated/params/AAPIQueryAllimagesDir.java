package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**The direction in which to list.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryAllimagesDir {
	ASCENDING("ascending"),

	DESCENDING("descending"),

	NEWER("newer"),

	OLDER("older");

	private final String jsonValue;
}
