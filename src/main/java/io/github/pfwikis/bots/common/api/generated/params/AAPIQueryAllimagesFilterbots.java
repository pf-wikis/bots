package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**How to filter files uploaded by bots. Can only be used with aisort=timestamp. Cannot be used together with aiuser.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryAllimagesFilterbots {
	ALL("all"),

	BOTS("bots"),

	NOBOTS("nobots");

	private final String jsonValue;
}
