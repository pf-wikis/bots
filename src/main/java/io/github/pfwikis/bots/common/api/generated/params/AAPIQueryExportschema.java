package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Target the given version of the XML dump format when exporting. Can only be used with <var>query+export</var>.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryExportschema {
	V_0_10("0.10"),

	V_0_11("0.11");

	private final String jsonValue;
}
