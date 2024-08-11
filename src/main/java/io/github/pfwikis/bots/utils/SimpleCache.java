package io.github.pfwikis.bots.utils;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class SimpleCache<T> {

	private static record CacheKey(String cacheId, String key) {}
	private final Cache<CacheKey, T> cache;
	
	public SimpleCache(Duration validTime) {
		cache = CacheBuilder.newBuilder()
			.softValues()
			.expireAfterWrite(validTime)
			.build();
	}
	
	public T cache(String cacheId, String key, Callable<? extends T> calc) {
		var cKey = new CacheKey(cacheId, key);
		try {
			return cache.get(cKey, calc);
		} catch (ExecutionException e) {
			throw new RuntimeException("Failed when trying to calculate cached value "+cKey, e);
		}
	}
}
