package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Limit to protected pages only.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryAllpagesPrtype {
	EDIT("edit"),

	MOVE("move"),

	UPLOAD("upload");

	private final String jsonValue;
}
