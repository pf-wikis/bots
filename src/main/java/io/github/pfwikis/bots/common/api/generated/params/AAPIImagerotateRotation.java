package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Degrees to rotate image clockwise.*/
@Getter
@RequiredArgsConstructor
public enum AAPIImagerotateRotation {
	V_90("90"),

	V_180("180"),

	V_270("270");

	private final String jsonValue;
}
