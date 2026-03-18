package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Whether to suppress data from administrators as well as others.*/
@Getter
@RequiredArgsConstructor
public enum AAPIRevisiondeleteSuppress {
	NO("no"),

	NOCHANGE("nochange"),

	YES("yes");

	private final String jsonValue;
}
