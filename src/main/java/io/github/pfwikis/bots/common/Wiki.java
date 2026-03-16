package io.github.pfwikis.bots.common;

import java.time.Duration;
import java.util.concurrent.Callable;

import io.github.pfwikis.bots.common.api.model.PageRef;
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
		"https://pathfinderwiki.com/w/api.php"
	),
	SF(
		"https://starfinderwiki.com",
		"Starfinder",
		"sf",
		"https://starfinderwiki.com/w/api.php"
	);
	
	private final String url;
	private final String name;
	private final String code;
	private final String apiURL;
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
	
	@SuppressWarnings("unchecked")
	public synchronized <T> T cache(CacheId cacheId, PageRef key, Callable<T> calc) {
		T result = null;
		if(key.hasId())
			result = (T) cache.cache(cacheId, Integer.toString(key.getId()), calc);
		if(result != null)
			cache.store(cacheId, key.getTitle().toFullTitle(), result);
		else
			result = (T) cache.cache(cacheId, key.getTitle().toFullTitle(), calc);
		return result;
	}
	
	public synchronized void storeInCache(CacheId cacheId, String key, Object value) {
		cache.store(cacheId, key, value);
	}
	
	public synchronized void storeInCache(CacheId cacheId, PageRef key, Object value) {
		if(key.hasId())
			cache.store(cacheId, Integer.toString(key.getId()), value);
		cache.store(cacheId, key.getTitle().toFullTitle(), value);
	}
}
