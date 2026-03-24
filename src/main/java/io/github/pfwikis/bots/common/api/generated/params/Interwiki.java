package io.github.pfwikis.bots.common.api.generated.params;

// import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.RequiredArgsConstructor;
import lombok.Getter;
import io.github.pfwikis.bots.common.Wiki;

@Getter
@RequiredArgsConstructor
public enum Interwiki {
	SFW("sfw", "https://starfinderwiki.com/wiki/$1", Wiki.SF),

	PFW("pfw", "https://pathfinderwiki.com/wiki/$1", Wiki.PF);

	@Getter(onMethod_ = @JsonValue)
	private final String prefix;

	private final String url;
	private final Wiki wiki;

	public static Interwiki fromPrefix(String prefix) {
		var res = fromPrefixOrNull(prefix);
		if (res == null) throw new IllegalArgumentException("Unknown interwiki prefix " + prefix);
		return res;
	}

	public static Interwiki fromPrefixOrNull(String prefix) {
		return switch (prefix) {
			case "sfw" -> SFW;

			case "pfw" -> PFW;
			case null -> null;
			default -> null;
		};
	}
}
