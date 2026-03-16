package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Override content of the revision specified by <var>fromtitle</var>, <var>fromid</var> or <var>fromrev</var>.
 * </p><p>This parameter specifies the slots that are to be modified. Use <var>fromtext-&#x7b;slot}</var>, <var>fromcontentmodel-&#x7b;slot}</var>, and <var>fromcontentformat-&#x7b;slot}</var> to specify content for each slot.
 * </p>*/
@Getter
@RequiredArgsConstructor
public enum AAPICompareFromslots {
	MAIN("main");

	private final String jsonValue;
}
