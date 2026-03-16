package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which properties to get:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryProtectedtitlesProp {

	/**Adds the comment for the protection.*/
	COMMENT("comment"),

	/**Adds the timestamp of when the protection will be lifted.*/
	EXPIRY("expiry"),

	/**Adds the protection level.*/
	LEVEL("level"),

	/**Adds the parsed comment for the protection.*/
	PARSEDCOMMENT("parsedcomment"),

	/**Adds the timestamp of when protection was added.*/
	TIMESTAMP("timestamp"),

	/**Adds the user that added the protection.*/
	USER("user"),

	/**Adds the user ID that added the protection.*/
	USERID("userid");

	private final String jsonValue;
}
