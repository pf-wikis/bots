package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Show only items that meet these criteria.
 * For example, to see only indefinite blocks on IP addresses, set <kbd>bkshow=ip|!temp</kbd>.*/
@Getter
@RequiredArgsConstructor
public enum AAPIQueryBlocksShow {
	_ACCOUNT("!account"),

	_IP("!ip"),

	_RANGE("!range"),

	_TEMP("!temp"),

	ACCOUNT("account"),

	IP("ip"),

	RANGE("range"),

	TEMP("temp");

	private final String jsonValue;
}
