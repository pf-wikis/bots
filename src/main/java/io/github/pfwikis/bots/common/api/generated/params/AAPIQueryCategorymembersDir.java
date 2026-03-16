package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**In which direction to sort.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryCategorymembersDir {
	ASC("asc"),

	ASCENDING("ascending"),

	DESC("desc"),

	DESCENDING("descending"),

	NEWER("newer"),

	OLDER("older");

	private final String jsonValue;
}
