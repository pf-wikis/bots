package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**How to filter for redirects. If set to <kbd>nonredirects</kbd> when <var>blredirect</var> is enabled, this is only applied to the second level.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryBacklinksFilterredir {
	ALL("all"),

	NONREDIRECTS("nonredirects"),

	REDIRECTS("redirects");

	private final String jsonValue;
}
