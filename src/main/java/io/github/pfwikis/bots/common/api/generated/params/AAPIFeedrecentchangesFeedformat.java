package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**The format of the feed.*/
@Getter
@RequiredArgsConstructor
public enum AAPIFeedrecentchangesFeedformat {
	ATOM("atom"),

	RSS("rss");

	private final String jsonValue;
}
