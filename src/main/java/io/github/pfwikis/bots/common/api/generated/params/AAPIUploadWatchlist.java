package io.github.pfwikis.bots.common.api.generated.params;

import com.fasterxml.jackson.annotation.JsonValue;
import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**Unconditionally add or remove the page from the current user's watchlist, use preferences (ignored for bot users) or do not change watch.*/
@Getter
@RequiredArgsConstructor
public enum AAPIUploadWatchlist {
	NOCHANGE("nochange"),

	PREFERENCES("preferences"),

	WATCH("watch");

	private final String jsonValue;
}
