package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Use a revision relative to the revision determined from <var>fromtitle</var>, <var>fromid</var> or <var>fromrev</var>. All of the other 'to' options will be ignored.*/
@Getter
@RequiredArgsConstructor
public enum AAPICompareTorelative {
	CUR("cur"),

	NEXT("next"),

	PREV("prev");

	private final String jsonValue;
}
