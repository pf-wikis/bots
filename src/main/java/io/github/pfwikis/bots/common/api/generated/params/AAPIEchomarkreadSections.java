package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**A list of sections to mark as read.*/
@Getter
@RequiredArgsConstructor
public enum AAPIEchomarkreadSections {
	ALERT("alert"),

	MESSAGE("message");

	private final String jsonValue;
}
