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
public enum AAPIQueryTagsProp {

	/**Whether the tag is still being applied.*/
	ACTIVE("active"),

	/**Indicate whether the tag is defined.*/
	DEFINED("defined"),

	/**Adds description of the tag.*/
	DESCRIPTION("description"),

	/**Adds system message for the tag.*/
	DISPLAYNAME("displayname"),

	/**Adds the number of revisions and log entries that have this tag.*/
	HITCOUNT("hitcount"),

	/**Gets the sources of the tag, which may include <samp>extension</samp> for extension-defined tags and <samp>manual</samp> for tags that may be applied manually by users.*/
	SOURCE("source");

	private final String jsonValue;
}
