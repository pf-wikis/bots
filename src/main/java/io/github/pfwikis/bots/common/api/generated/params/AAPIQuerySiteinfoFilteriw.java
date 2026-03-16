package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Return only local or only nonlocal entries of the interwiki map.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQuerySiteinfoFilteriw {
	_LOCAL("!local"),

	LOCAL("local");

	private final String jsonValue;
}
