package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Filter protections based on protection level (must be used with apprtype= parameter).*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryAllpagesPrlevel {
	EMPTY(""),

	AUTOCONFIRMED("autoconfirmed"),

	EDIT_SENSITIVE("edit-sensitive"),

	SYSOP("sysop");

	private final String jsonValue;
}
