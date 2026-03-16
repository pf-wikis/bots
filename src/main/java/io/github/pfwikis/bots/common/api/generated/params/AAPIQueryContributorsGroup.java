package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Only include users in the given groups. Does not include implicit or auto-promoted groups like *, user, or autoconfirmed.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryContributorsGroup {
	BOT("bot"),

	PUSH_SUBSCRIPTION_MANAGER("push-subscription-manager"),

	SMWADMINISTRATOR("smwadministrator"),

	SMWCURATOR("smwcurator"),

	SMWEDITOR("smweditor"),

	SYSOP("sysop"),

	TECHADMIN("techadmin");

	private final String jsonValue;
}
