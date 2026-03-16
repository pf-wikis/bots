package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Output formatting
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIJsonFormatversion {

	/**Backwards-compatible format (XML-style booleans, <samp>*</samp> keys for content nodes, etc.).*/
	V_1("1"),

	/**Modern format.*/
	V_2("2"),

	/**Use the latest format (currently <kbd>2</kbd>), may change without warning.*/
	LATEST("latest");

	private final String jsonValue;
}
