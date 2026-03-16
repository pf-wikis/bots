package io.github.pfwikis.bots.common.bots;

import java.util.ArrayList;
import java.util.List;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class SimpleBot extends Bot<SingleRun> {

	public SimpleBot(String id, String botName) {
		super(id, botName);
	}
	
	@Override
	public Wiki getWiki() {
		return run.getServer();
	}
	
	@Override
	protected List<SingleRun> createRuns() {
		var runs = new ArrayList<SingleRun>(2);
		for(var server:Wiki.values()) {
			var run = new SingleRun(server);
			run.setWiki(WikiAPI.create(server, botName, server==Wiki.PF?pfkey:sfkey, antiProtectionSecret));
			runs.add(run);
		}
		return runs;
	}
}
