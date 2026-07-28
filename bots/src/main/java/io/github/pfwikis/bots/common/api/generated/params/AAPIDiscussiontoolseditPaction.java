package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Action to perform.
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIDiscussiontoolseditPaction {

	/**Add a new comment as a reply to an existing comment.*/
	ADDCOMMENT("addcomment"),

	/**Add a new discussion section and the first comment in it.*/
	ADDTOPIC("addtopic");

	private final String jsonValue;
}
