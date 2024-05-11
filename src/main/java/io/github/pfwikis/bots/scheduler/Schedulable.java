package io.github.pfwikis.bots.scheduler;

import io.github.pfwikis.bots.common.bots.Bot;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class Schedulable {
	private final Bot<?> bot;
	private final String name;
	
	public abstract void execute();

	public void reset() {
		bot.setRun(null);
	}
}