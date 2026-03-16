package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<span class="apihelp-empty">(no description)</span>*/
@Getter
@RequiredArgsConstructor
public enum AAPISmwinfoInfo {
	CONCEPTCOUNT("conceptcount"),

	DECLAREDPROPCOUNT("declaredpropcount"),

	DELETECOUNT("deletecount"),

	ERRORCOUNT("errorcount"),

	FORMATCOUNT("formatcount"),

	JOBCOUNT("jobcount"),

	PROPCOUNT("propcount"),

	PROPPAGECOUNT("proppagecount"),

	QUERYCOUNT("querycount"),

	QUERYSIZE("querysize"),

	SUBOBJECTCOUNT("subobjectcount"),

	TOTALPROPCOUNT("totalpropcount"),

	USEDPROPCOUNT("usedpropcount");

	private final String jsonValue;
}
