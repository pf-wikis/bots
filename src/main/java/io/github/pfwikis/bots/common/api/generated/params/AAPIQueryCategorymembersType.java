package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Which type of category members to include. Ignored when <kbd>cmsort=timestamp</kbd> is set.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryCategorymembersType {
	FILE("file"),

	PAGE("page"),

	SUBCAT("subcat");

	private final String jsonValue;
}
