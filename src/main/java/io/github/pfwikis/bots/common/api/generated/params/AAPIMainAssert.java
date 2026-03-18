package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Verify that the user is logged in (including possibly as a temporary user) if set to <kbd>user</kbd>, <em>not</em> logged in if set to <kbd>anon</kbd>, or has the bot user right if <kbd>bot</kbd>.*/
@Getter
@RequiredArgsConstructor
public enum AAPIMainAssert {
	ANON("anon"),

	BOT("bot"),

	USER("user");

	private final String jsonValue;
}
