package io.github.pfwikis.bots.scheduler;

import java.time.Duration;
import java.time.Instant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Task implements Comparable<Task> {
	protected final Schedulable schedulable;
	protected final Instant time;
	
	public void run() {
		schedulable.execute();
	}
	
	@Override
	public int compareTo(Task o) {
		return this.time.compareTo(o.time);
	}
	
	public static class Repeatable extends Task {
		
		private final Scheduler scheduler;
		private final Duration sleepBetweenRuns;

		public Repeatable(Schedulable schedulable, Instant time, Scheduler scheduler, Duration sleepBetweenRuns) {
			super(schedulable, time);
			this.scheduler = scheduler;
			this.sleepBetweenRuns = sleepBetweenRuns;
		}
		
		@Override
		public void run() {
			try {
				super.run();
			} finally {
				schedulable.reset();
				scheduler.schedule(new Repeatable(
					schedulable,
					Instant.now().plus(sleepBetweenRuns),
					scheduler,
					sleepBetweenRuns
				));
			}
		}
		
	}
}