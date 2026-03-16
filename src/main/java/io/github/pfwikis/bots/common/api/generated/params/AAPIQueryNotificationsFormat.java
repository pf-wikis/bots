package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>If specified, notifications will be returned formatted this way.
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryNotificationsFormat {

	/**<span class="apihelp-deprecated">Deprecated</span>. Use <kbd>notformat=model</kbd> for raw data*/
	FLYOUT("flyout"),

	/**<span class="apihelp-deprecated">Deprecated</span>. Use <kbd>notformat=model</kbd> for raw data*/
	HTML("html"),

	/**Raw notification data*/
	MODEL("model"),

	/**Formatted for Special:Notifications page (and only that!) Do not rely on the HTML as it may change at any given time.*/
	SPECIAL("special");

	private final String jsonValue;
}
