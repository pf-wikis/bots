package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Section where notification would be delivered*/
@Getter
@RequiredArgsConstructor
public enum AAPIEchocreateeventSection {
	ALERT("alert"),

	NOTICE("notice");

	private final String jsonValue;
}
