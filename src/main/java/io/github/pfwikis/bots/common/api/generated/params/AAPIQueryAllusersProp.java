package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which pieces of information to include:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryAllusersProp {

	/**Adds the information about a current block on the user.*/
	BLOCKINFO("blockinfo"),

	/**Adds the central IDs and attachment status for the user.*/
	CENTRALIDS("centralids"),

	/**Adds the edit count of the user.*/
	EDITCOUNT("editcount"),

	/**Lists groups that the user is in. This uses more server resources and may return fewer results than the limit.*/
	GROUPS("groups"),

	/**Lists all the groups the user is automatically in.*/
	IMPLICITGROUPS("implicitgroups"),

	/**Adds the timestamp of when the user registered if available (may be blank).*/
	REGISTRATION("registration"),

	/**Lists rights that the user has.*/
	RIGHTS("rights");

	private final String jsonValue;
}
