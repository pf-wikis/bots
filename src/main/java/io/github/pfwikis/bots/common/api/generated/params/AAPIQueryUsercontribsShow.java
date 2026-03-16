package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Show only items that meet these criteria, e.g. non minor edits only: <kbd>ucshow=!minor</kbd>.
 * </p><p>If <kbd>ucshow=patrolled</kbd> or <kbd>ucshow=!patrolled</kbd> is set, revisions older than <var><a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:$wgRCMaxAge&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:$wgRCMaxAge (page does not exist)">$wgRCMaxAge</a></var> (7776000 seconds) won't be shown.
 * </p>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryUsercontribsShow {
	_AUTOPATROLLED("!autopatrolled"),

	_MINOR("!minor"),

	_NEW("!new"),

	_PATROLLED("!patrolled"),

	_TOP("!top"),

	AUTOPATROLLED("autopatrolled"),

	MINOR("minor"),

	NEW("new"),

	PATROLLED("patrolled"),

	TOP("top");

	private final String jsonValue;
}
