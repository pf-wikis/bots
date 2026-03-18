package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>How to format sections in plaintext mode:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryExtractsSectionformat {

	/**No formatting.*/
	PLAIN("plain"),

	/**This module's internal representation (section titles prefixed with &lt;ASCII 1&gt;&lt;ASCII 2&gt;&lt;section level&gt;&lt;ASCII 2&gt;&lt;ASCII 1&gt;).*/
	RAW("raw"),

	/**Wikitext-style formatting (== like this ==).*/
	WIKI("wiki");

	private final String jsonValue;
}
