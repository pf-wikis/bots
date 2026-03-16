package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>How to handle redirects:
 * </p>
 * <dl></dl>
 * <p>For historical reasons, the default is "return" for format=json and "resolve" for other formats.
 * </p>*/
@Getter
@RequiredArgsConstructor
public enum AAPIOpensearchRedirects {

	/**Return the target page. May return fewer than limit results.*/
	RESOLVE("resolve"),

	/**Return the redirect itself.*/
	RETURN("return");

	private final String jsonValue;
}
