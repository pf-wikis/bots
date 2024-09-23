package io.github.pfwikis.bots.common;

import java.time.Duration;
import java.util.concurrent.Callable;

import io.github.pfwikis.bots.utils.SimpleCache;
import io.github.pfwikis.bots.utils.SimpleCache.CacheId;
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
	
	
	private final SimpleCache<Object> cache = new SimpleCache<>(Duration.ofDays(1));
	
	@SuppressWarnings("unchecked")
	public synchronized <T> T cache(CacheId cacheId, String key, Callable<T> calc) {
		return (T)cache.cache(cacheId, key, calc);
	}
	
	public synchronized void storeInCache(CacheId cacheId, String key, Object value) {
		cache.store(cacheId, key, value);
	}
}
