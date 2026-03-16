package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Timestamp format to use for output, 'ISO_8601' or 'MW'. 'MW' is deprecated here, so all clients should switch to 'ISO_8601'. This parameter will be removed, and 'ISO_8601' will become the only output format.*/
@Getter
@RequiredArgsConstructor
public enum AAPIEchomarkseenTimestampFormat {
	ISO_8601("ISO_8601"),

	MW("MW");

	private final String jsonValue;
}
