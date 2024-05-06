package io.github.pfwikis.bots.scheduler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class Schedulable {
	private final String name;
	
	public abstract void execute();
}