package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Type of revision deletion being performed.*/
@Getter
@RequiredArgsConstructor
public enum AAPIRevisiondeleteType {
	ARCHIVE("archive"),

	FILEARCHIVE("filearchive"),

	LOGGING("logging"),

	OLDIMAGE("oldimage"),

	REVISION("revision");

	private final String jsonValue;
}
