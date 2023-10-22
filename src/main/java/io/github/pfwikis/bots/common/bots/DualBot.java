package io.github.pfwikis.bots.common.bots;

import java.io.IOException;
import java.util.List;

import io.github.pfwikis.bots.common.bots.Run.DualRun;

public abstract class DualBot extends Bot<DualRun> {

	public DualBot(String id, String botName) {
		super(id, botName);
	}
	
	@Override
	protected List<DualRun> createRuns() {
		var run = new DualRun();
		var sBot = new SimpleBot(id, botName) {
			@Override
			public void run() throws IOException {}
			
			@Override
			protected String getDescription() {return null;}
		};
		sBot.rootPassword = this.rootPassword;
		var runs = sBot.createRuns();
		run.setPfRun(runs.get(0));
		run.setSfRun(runs.get(1));
		return List.of(run);
	}
}
