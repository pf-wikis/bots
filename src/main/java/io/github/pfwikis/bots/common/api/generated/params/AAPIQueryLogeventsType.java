package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Filter log entries to only this type.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryLogeventsType {
	EMPTY(""),

	BLOCK("block"),

	CONTENTMODEL("contentmodel"),

	CREATE("create"),

	DELETE("delete"),

	IMPORT("import"),

	INTERWIKI("interwiki"),

	MANAGETAGS("managetags"),

	MERGE("merge"),

	MOVE("move"),

	NEWUSERS("newusers"),

	PATROL("patrol"),

	PROTECT("protect"),

	RENAMEUSER("renameuser"),

	RIGHTS("rights"),

	SMW("smw"),

	SUPPRESS("suppress"),

	TAG("tag"),

	THANKS("thanks"),

	UPLOAD("upload"),

	USERMERGE("usermerge");

	private final String jsonValue;
}
