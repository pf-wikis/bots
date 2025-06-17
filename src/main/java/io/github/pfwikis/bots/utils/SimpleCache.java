package io.github.pfwikis.bots.utils;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class SimpleCache<T> {

	private static record CacheKey(CacheId cacheId, String key) {}
	private final Cache<CacheKey, T> cache;
	
	public SimpleCache(Duration validTime) {
		cache = CacheBuilder.newBuilder()
			.softValues()
			.expireAfterWrite(validTime)
			.build();
	}
	
	public void store(CacheId cacheId, String key, T value) {
		cache.put(new CacheKey(cacheId, key), value);
	}
	
	public T cache(CacheId cacheId, String key, Callable<? extends T> calc) {
		var cKey = new CacheKey(cacheId, key);
		try {
			return cache.get(cKey, calc);
		} catch (ExecutionException e) {
			throw new RuntimeException("Failed when trying to calculate cached value "+cKey, e);
		}
	}
	
	public static enum CacheId {
		PAGE_EXISTS,
		DISPLAY_TITLE,
		RESOLVED_REDIRECT,
		REPLACER_RESOLVE,
		SERIES_2_CATEGORY
	}
}
