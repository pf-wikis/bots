package io.github.pfwikis.bots.common.bots;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.WikiAPI;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter @Getter
public abstract class Run {
	
	private Discord discord;
	private OffsetDateTime timestamp = OffsetDateTime.now();
	
	public abstract void withMaster(Consumer<WikiAPI> task);
	
	@RequiredArgsConstructor
	public static class SingleRun extends Run {
		@Getter
		private final Wiki server;
		private final String masterAccount;
		private final String masterPassword;
		private final Map<String, Map<String, Object>> caches = new HashMap<>();
		@Getter @Setter
		private WikiAPI wiki;
		private WikiAPI masterWiki;
		
		@Override
		public synchronized void withMaster(Consumer<WikiAPI> task) {
			if(masterWiki == null) {
				try {
					masterWiki = WikiAPI.fromCache(server, masterAccount, masterPassword);
				} catch(Exception e) {
					log.error("Failed to log in as {}", masterAccount, e);
					System.exit(-1);
				}
				
			}
			task.accept(masterWiki);
		}

		public <T> T cache(String cacheId, String key, Function<String, T> calc) {
			var cache = caches.computeIfAbsent(cacheId, a->new HashMap<>());
			T value = (T)cache.get(key);
			if(value == null) {
				value = calc.apply(key);
				cache.put(key, value);
			}
			return value;
		}
	}
	
	@Getter @Setter
	public static class DualRun extends Run {
		private SingleRun pfRun;
		private SingleRun sfRun;
		
		@Override
		public void withMaster(Consumer<WikiAPI> task) {
			pfRun.withMaster(task);
			sfRun.withMaster(task);
		}

		public WikiAPI getPfWiki() {
			return pfRun.getWiki();
		}

		public WikiAPI getSfWiki() {
			return sfRun.getWiki();
		}
	}
}