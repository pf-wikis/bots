package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Format to use for warning and error text output
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIMainErrorformat {

	/**Format used prior to MediaWiki 1.29. <var>errorlang</var> and <var>errorsuselocal</var> are ignored.*/
	BC("bc"),

	/**HTML*/
	HTML("html"),

	/**No text output, only the error codes.*/
	NONE("none"),

	/**Wikitext with HTML tags removed and entities replaced.*/
	PLAINTEXT("plaintext"),

	/**Message key and parameters.*/
	RAW("raw"),

	/**Unparsed wikitext.*/
	WIKITEXT("wikitext");

	private final String jsonValue;
}
