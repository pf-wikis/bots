package io.github.pfwikis.bots.common.api;

import java.util.HashMap;
import java.util.Map;

import io.github.pfwikis.bots.common.Wiki;

public enum MWApiCache {

	INSTANCE;
	
	private static record Key(Wiki wiki, String botname, String password) {}
	private Map<Key, MWApi> cache = new HashMap<>();
	
	public static synchronized MWApi get(io.github.pfwikis.bots.common.Wiki wiki, String name, String password, String antiProtectionSecret) {
		return INSTANCE.cache.computeIfAbsent(new Key(wiki, name, password), k->INSTANCE.create(k, antiProtectionSecret));
	}
	
	private MWApi create(Key key, String secret) {
		try {
			return new MWApi(key.wiki, key.botname, key.password, secret);
		} catch (Exception e) {
			throw new IllegalStateException("Could not proxy JWIKI", e);
		}
	}

}
