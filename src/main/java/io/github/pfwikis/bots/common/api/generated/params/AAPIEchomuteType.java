package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Which mute list to add to or remove from*/
@Getter
@RequiredArgsConstructor
public enum AAPIEchomuteType {
	PAGE_LINKED_TITLE("page-linked-title"),

	USER("user");

	private final String jsonValue;
}
