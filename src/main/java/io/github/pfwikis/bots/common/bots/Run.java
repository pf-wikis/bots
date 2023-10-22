package io.github.pfwikis.bots.common.bots;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import io.github.pfwikis.bots.common.WikiAPI;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter @Getter
public abstract class Run {
	
	private OffsetDateTime timestamp = OffsetDateTime.now();
	private List<Exception> exceptions = new ArrayList<>();
	
	public abstract void withMaster(Consumer<WikiAPI> task);
	public void addException(Exception e) {
		exceptions.add(e);
	}
	
	@Getter @Setter
	@RequiredArgsConstructor
	public static class SingleRun extends Run {
		private final boolean starfinder;
		private WikiAPI wiki;
		private WikiAPI masterWiki;
		
		@Override
		public void withMaster(Consumer<WikiAPI> task) {
			task.accept(masterWiki);
		}
	}
	
	@Getter @Setter
	public static class DualRun extends Run {
		private SingleRun pfRun;
		private SingleRun sfRun;
		
		@Override
		public void withMaster(Consumer<WikiAPI> task) {
			task.accept(pfRun.masterWiki);
			task.accept(sfRun.masterWiki);
		}

		public WikiAPI getPfWiki() {
			return pfRun.getWiki();
		}

		public WikiAPI getSfWiki() {
			return sfRun.getWiki();
		}
	}

	
}