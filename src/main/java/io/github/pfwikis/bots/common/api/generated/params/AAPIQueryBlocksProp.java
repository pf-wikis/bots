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
public enum AAPIQueryBlocksProp {

	/**Adds the username of the blocking user.*/
	BY("by"),

	/**Adds the user ID of the blocking user.*/
	BYID("byid"),

	/**Adds the timestamp of when the block expires.*/
	EXPIRY("expiry"),

	/**Tags the ban with (autoblock, anononly, etc.).*/
	FLAGS("flags"),

	/**Adds the ID of the block.*/
	ID("id"),

	/**Adds the range of IP addresses affected by the block.*/
	RANGE("range"),

	/**Adds the reason given for the block.*/
	REASON("reason"),

	/**Adds the partial block restrictions if the block is not sitewide.*/
	RESTRICTIONS("restrictions"),

	/**Adds the timestamp of when the block was given.*/
	TIMESTAMP("timestamp"),

	/**Adds the username of the blocked user.*/
	USER("user"),

	/**Adds the user ID of the blocked user.*/
	USERID("userid");

	private final String jsonValue;
}
