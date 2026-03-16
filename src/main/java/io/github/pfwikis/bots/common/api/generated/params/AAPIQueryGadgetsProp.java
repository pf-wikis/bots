package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>What gadget information to get:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryGadgetsProp {

	/**Gadget description transformed into HTML (can be slow, use only if really needed).*/
	DESC("desc"),

	/**Internal gadget ID.*/
	ID("id"),

	/**The gadget metadata.*/
	METADATA("metadata");

	private final String jsonValue;
}
