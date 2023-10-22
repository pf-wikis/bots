package io.github.pfwikis.bots.common.bots;

import java.util.ArrayList;
import java.util.List;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class SimpleBot extends Bot<SingleRun> {

	public SimpleBot(String id, String botName) {
		super(id, botName);
	}
	
	@Override
	protected List<SingleRun> createRuns() {
		var runs = new ArrayList<SingleRun>(2);
		for(boolean starfinder : new boolean[] {false, true}) {
			var run = new SingleRun(starfinder);
			try {
				run.setMasterWiki(new WikiAPI(starfinder, "VirenerusBot", rootPassword));
			} catch(Exception e) {
				log.error("Failed to log in as {}", botName, e);
				System.exit(-1);
			}
			
			checkAccount(run);
			
			try {
				run.setWiki(new WikiAPI(starfinder, botName, rootPassword+botName));
			} catch(Exception e) {
				log.error("Failed to log in as {}", botName, e);
				System.exit(-1);
			}
			runs.add(run);
		}
		return runs;
	}
}
