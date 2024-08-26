package io.github.pfwikis.bots.scheduler;

import io.github.pfwikis.bots.common.bots.Bot;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class Schedulable {
	private final String name;
	
	public abstract void execute();
	
	@Getter
	public static abstract class SchedulableBot extends Schedulable {
		
		private final Bot<?> bot;
		
		public SchedulableBot(Bot<?> bot, String name) {
			super(name);
			this.bot = bot;
		}
	}
}
