package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Show only items that meet these criteria. For example, to see only minor edits done by logged-in users, set wlshow=minor|!anon.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryWatchlistShow {
	_ANON("!anon"),

	_AUTOPATROLLED("!autopatrolled"),

	_BOT("!bot"),

	_MINOR("!minor"),

	_PATROLLED("!patrolled"),

	_UNREAD("!unread"),

	ANON("anon"),

	AUTOPATROLLED("autopatrolled"),

	BOT("bot"),

	MINOR("minor"),

	PATROLLED("patrolled"),

	UNREAD("unread");

	private final String jsonValue;
}
