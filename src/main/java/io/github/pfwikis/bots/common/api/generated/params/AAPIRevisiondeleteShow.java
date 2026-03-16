package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**What to unhide for each revision.*/
@Getter
@RequiredArgsConstructor
public enum AAPIRevisiondeleteShow {
	COMMENT("comment"),

	CONTENT("content"),

	USER("user");

	private final String jsonValue;
}
