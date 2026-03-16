package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Set the sort order of returned results.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQuerySearchSort {
	CREATE_TIMESTAMP_ASC("create_timestamp_asc"),

	CREATE_TIMESTAMP_DESC("create_timestamp_desc"),

	INCOMING_LINKS_ASC("incoming_links_asc"),

	INCOMING_LINKS_DESC("incoming_links_desc"),

	JUST_MATCH("just_match"),

	LAST_EDIT_ASC("last_edit_asc"),

	LAST_EDIT_DESC("last_edit_desc"),

	NONE("none"),

	RANDOM("random"),

	RELEVANCE("relevance"),

	USER_RANDOM("user_random");

	private final String jsonValue;
}
