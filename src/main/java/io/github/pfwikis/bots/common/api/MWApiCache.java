package io.github.pfwikis.bots.common.api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.github.fastily.jwiki.core.Wiki;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;

public enum MWApiCache {

	INSTANCE;
	
	private static record Key(io.github.pfwikis.bots.common.Wiki wiki, String name, String password) {}
	private Map<Key, MWApi> cache = new HashMap<>();
	private Map<Method, Method> interfaceToImplMap = new ConcurrentHashMap<>();
	
	public static synchronized MWApi get(io.github.pfwikis.bots.common.Wiki wiki, String name, String password) {
		return INSTANCE.cache.computeIfAbsent(new Key(wiki, name, password), INSTANCE::create);
	}
	
	private MWApi create(Key key) {
		try {
			var handler = new WikiMethodHandler(key);
			return (MWApi) Proxy.newProxyInstance(
				this.getClass().getClassLoader(),
				new Class<?>[] {MWApi.class},
				handler
			);
		} catch (Exception e) {
			throw new IllegalStateException("Could not proxy JWIKI", e);
		}
	}
	
	private Method getImplMethod(Method interfaceMethod) {
		return interfaceToImplMap.computeIfAbsent(
			interfaceMethod,
			m -> {
				try {
					return Wiki.class.getDeclaredMethod(
						interfaceMethod.getName(),
						interfaceMethod.getParameterTypes()
					);
				} catch (NoSuchMethodException | SecurityException e) {
					throw new IllegalStateException("Can not proxy "+interfaceMethod, e);
				}
			}
		);
	}
	
	@Slf4j
	@RequiredArgsConstructor
	public static class WikiMethodHandler implements InvocationHandler {
		private final Key key;
		private Wiki wiki;

		@Override
		public synchronized Object invoke(Object proxy, Method interfaceMethod, Object[] args) throws Throwable {
			if(wiki == null) login();
			var implMethod = INSTANCE.getImplMethod(interfaceMethod);
			try {
				return implMethod.invoke(wiki, args);
			} catch(Exception e1) {
				try {
					//on failure we want to first try to login again and retry
					login();
					return implMethod.invoke(wiki, args);
				} catch(Exception e2) {
					log.error("Request and retry to {} failed.", e1);
					log.error("Retry error", e2);
					throw e1;
				}
			}
		}

		private void login() {
			Exception first = null;
			Exception last = null;
			for (int tryCount = 0; tryCount < 60; tryCount++) {
				try {
					var b = new Wiki.Builder().withDomain(key.wiki.getUrl())
							.withApiEndpoint(HttpUrl.get(key.wiki.getUrl() + "/w/api.php"));
					if (key.name != null) {
						b = b.withLogin(key.name, key.password);
					}
					this.wiki = b.build();
					return;
				} catch(Exception e) {
					log.warn(
						"Login to {} as {} failed: {}\nWill retry",
						key.wiki.getName(),
						key.name,
						e.getMessage()
					);
					if(tryCount==0) first = e;
					last = e;
				}
			}
			log.error(
				"Login to {} as {} failed even after retries",
				key.wiki.getName(),
				key.name
			);
			log.error("First fail error", first);
			log.error("Last fail error", last);
			throw new IllegalStateException("Could not login", last);
		}
	}
	 
	
	
	/*
	 
	
	 
	 */

}
