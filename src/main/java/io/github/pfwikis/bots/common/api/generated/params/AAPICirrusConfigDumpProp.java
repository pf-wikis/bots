package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Type of configuration variables to dump*/
@Getter
@RequiredArgsConstructor
public enum AAPICirrusConfigDumpProp {
	GLOBALS("globals"),

	NAMESPACEMAP("namespacemap"),

	PROFILES("profiles"),

	REPLICAGROUP("replicagroup"),

	USERTESTING("usertesting");

	private final String jsonValue;
}
