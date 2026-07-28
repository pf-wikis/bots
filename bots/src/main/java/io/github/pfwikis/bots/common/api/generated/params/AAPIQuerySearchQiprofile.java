package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**<p>Query independent profile to use (affects ranking algorithm).
 * </p>
 * <dl></dl>*/
@Getter
@RequiredArgsConstructor
public enum AAPIQuerySearchQiprofile {

	/**Ranking based on the number of incoming links, some templates, page language and recency (templates/language/recency may not be activated on this wiki).*/
	CLASSIC("classic"),

	/**Ranking based on some templates, page language and recency when activated on this wiki.*/
	CLASSIC_NOBOOSTLINKS("classic_noboostlinks"),

	/**Ranking based solely on query dependent features (for debug only).*/
	EMPTY("empty"),

	/**Let the search engine decide on the best profile to use.*/
	ENGINE_AUTOSELECT("engine_autoselect"),

	/**Ranking based primarily on incoming link counts*/
	POPULAR_INCLINKS("popular_inclinks"),

	/**Ranking based primarily on page views*/
	POPULAR_INCLINKS_PV("popular_inclinks_pv"),

	/**Weighted sum based on incoming links*/
	WSUM_INCLINKS("wsum_inclinks"),

	/**Weighted sum based on incoming links and weekly pageviews*/
	WSUM_INCLINKS_PV("wsum_inclinks_pv");

	private final String jsonValue;
}
