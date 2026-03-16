package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Which information to return:
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryPageimagesProp {

	/**Image title.*/
	NAME("name"),

	/**URL and original dimensions of image associated with page, if any.*/
	ORIGINAL("original"),

	/**URL and dimensions of thumbnail image associated with page, if any.*/
	THUMBNAIL("thumbnail");

	private final String jsonValue;
}
