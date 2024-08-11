package io.github.pfwikis.bots.common;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

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
	
	
	
	
	private static record CacheEntry(Instant loadTime, Object value) {}
	private final Map<String, Map<String, CacheEntry>> caches = new HashMap<>();
	
	@SuppressWarnings("unchecked")
	public synchronized <T> T cache(String cacheId, String key, Supplier<T> calc) {
		var cache = caches.computeIfAbsent(cacheId, a->new HashMap<>());
		var entry = cache.get(key);
		if(entry != null && entry.loadTime().isAfter(Instant.now().minusSeconds(3600))) {
			return (T) entry.value();
		}
		var value = calc.get();
		cache.put(key, new CacheEntry(Instant.now(), value));
		return value;
	}
}
