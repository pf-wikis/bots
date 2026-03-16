package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which pieces of information to get.
 * </p><p>Note that if no values are selected, the result will contain the wikitext, but the output will be in a deprecated format.
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIExpandtemplatesProp {

	/**Any categories present in the input that are not represented in the wikitext output.*/
	CATEGORIES("categories"),

	/**Gives the JavaScript configuration variables specific to the page as a JSON string.*/
	ENCODEDJSCONFIGVARS("encodedjsconfigvars"),

	/**Gives the JavaScript configuration variables specific to the page.*/
	JSCONFIGVARS("jsconfigvars"),

	/**Any ResourceLoader modules that parser functions have requested be added to the output. Either <kbd>jsconfigvars</kbd> or <kbd>encodedjsconfigvars</kbd> must be requested jointly with <kbd>modules</kbd>.*/
	MODULES("modules"),

	/**The XML parse tree of the input.*/
	PARSETREE("parsetree"),

	/**Page properties defined by expanded magic words in the wikitext.*/
	PROPERTIES("properties"),

	/**The maximum time after which caches of the result should be invalidated.*/
	TTL("ttl"),

	/**Whether the output is volatile and should not be reused elsewhere within the page.*/
	VOLATILE("volatile"),

	/**The expanded wikitext.*/
	WIKITEXT("wikitext");

	private final String jsonValue;
}
