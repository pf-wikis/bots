package io.github.pfwikis.bots.common.bots;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.utils.SimpleCache;
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
	public abstract void withOwnUser(Consumer<WikiAPI> task);
	
	@RequiredArgsConstructor
	public static class SingleRun extends Run {
		@Getter
		private final Wiki server;
		private final String masterAccount;
		private final String masterPassword;
		@Getter @Setter
		private WikiAPI wiki;
		private WikiAPI masterWiki;
		
		@Override
		public synchronized void withMaster(Consumer<WikiAPI> task) {
			if(masterWiki == null) {
				try {
					masterWiki = WikiAPI.create(server, masterAccount, masterPassword);
				} catch(Exception e) {
					log.error("Failed to log in as {}", masterAccount, e);
					System.exit(-1);
				}
				
			}
			task.accept(masterWiki);
		}
		
		@Override
		public void withOwnUser(Consumer<WikiAPI> task) {
			task.accept(wiki);
		}

		private final SimpleCache<Object> cache = new SimpleCache<>(Duration.ofMinutes(10));
		
		@SuppressWarnings("unchecked")
		public synchronized <T> T cache(String cacheId, String key, Callable<T> calc) {
			return (T)cache.cache(cacheId, key, calc);
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
		
		@Override
		public void withOwnUser(Consumer<WikiAPI> task) {
			pfRun.withOwnUser(task);
			sfRun.withOwnUser(task);
		}
	}
}