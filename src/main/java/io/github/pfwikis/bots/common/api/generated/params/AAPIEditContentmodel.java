package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Content model of the new content.*/
@Getter
@RequiredArgsConstructor
public enum AAPIEditContentmodel {
	GADGETDEFINITION("GadgetDefinition"),

	GEOJSON("GeoJSON"),

	GEOJSON_ALT("GeoJson"),

	CSS("css"),

	JAVASCRIPT("javascript"),

	JSON("json"),

	SMW_SCHEMA("smw/schema"),

	TEXT("text"),

	UNKNOWN("unknown"),

	WIKITEXT("wikitext");

	private final String jsonValue;
}
