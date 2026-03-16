package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Only list titles with these protection levels.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryProtectedtitlesLevel {
	AUTOCONFIRMED("autoconfirmed"),

	EDIT_SENSITIVE("edit-sensitive"),

	SYSOP("sysop");

	private final String jsonValue;
}
