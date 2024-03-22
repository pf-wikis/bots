package io.github.pfwikis.bots.common;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public enum Wiki {
	PF(
		"https://pathfinderwiki.com",
		"Pathfinder",
		"pf",
		"1"
	),
	SF(
		"https://starfinderwiki.com",
		"Starfinder",
		"sf",
		"2"
	);
	
	private final String url;
	private final String name;
	private final String code;
	private final String matomoId;
	@Getter(lazy = true)
	private final String wikiNamespace = name+"Wiki";
	private String masterAccount = "VirenerusBot";
	@Setter
	private String masterPassword;
	@Setter
	private WikiAPI masterApi;
	private final Map<String, Map<String, Object>> caches = new HashMap<>();
}
