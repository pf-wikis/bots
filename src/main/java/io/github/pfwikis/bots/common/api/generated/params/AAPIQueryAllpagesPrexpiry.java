package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which protection expiry to filter the page on:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryAllpagesPrexpiry {

	/**Get pages with any protections expiry.*/
	ALL("all"),

	/**Get only pages with a definite (specific) protection expiry.*/
	DEFINITE("definite"),

	/**Get only pages with indefinite protection expiry.*/
	INDEFINITE("indefinite");

	private final String jsonValue;
}
