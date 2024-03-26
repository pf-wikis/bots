package io.github.pfwikis.bots.common.bots;

import java.util.List;

import com.beust.jcommander.Parameter;

import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.common.Wiki;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter @Setter
@RequiredArgsConstructor
public abstract class Bot<RUN extends Run> {
	
	protected final String id;
	protected final String botName;

	@Parameter(names = "--password")
	protected String rootPassword;
	@Parameter(names = "--discordToken")
	protected String discordToken;
	@Parameter(names = "--localMode")
	protected boolean localMode;

	protected RUN run;
	protected Discord discord;
	private boolean hadError = false;

	public static interface RunOnPage {
		void runOnPage(String page) throws Exception;
		void executeBeforeRuns();
		Run getRun();
		Discord getDiscord();
		void createBotReport();
		void reportException(Exception e);
		void executeAfterRuns();
		
		public default void runSinglePage(String page) {
			synchronized(this) {
				try {
					executeBeforeRuns();
					getRun().setDiscord(getDiscord());
					
					try {
						runOnPage(page);
						createBotReport();
					} catch (Exception e) {
						reportException(e);
					}
					executeAfterRuns();
				} catch(Exception e) {
					reportException(e);
				}
			}
		}
	}
	protected abstract void run() throws Exception;

	public void beforeRuns() throws Exception {}
	public void afterRuns() throws Exception {}

	public synchronized void reportException(Exception e) {
		hadError = true;
		log.error("Reported exception ",e);
		if(!localMode)
			discord.reportException(this, e);
	}

	public synchronized void reportException(String msg) {
		hadError = true;
		log.error("Reported exception: {}", msg);
		if(!localMode)
			discord.reportException(this, msg);
	}

	public synchronized void startSingleInstance() throws Exception {
		discord = new Discord(discordToken);
		try {
			executeBeforeRuns();
			
			var runs = createRuns();
			var beforeRunsError = hadError;
			
			for(var currentRun : runs) {
				this.run = currentRun;
				this.run.setDiscord(discord);
				
				try {
					run();
					createBotReport();
				} catch (Exception e) {
					reportException(e);
				} finally {
					hadError = beforeRunsError;
				}
			}
			
			executeAfterRuns();
		} finally {
			discord.close();
		}
	}
	
	public synchronized void startRun(Discord discord) {
		executeBeforeRuns();
		this.run.setDiscord(discord);
		try {
			run();
			createBotReport();
		} catch (Exception e) {
			reportException(e);
		}
		executeAfterRuns();
	}
	
	public void executeBeforeRuns() {
		try {
			beforeRuns();
		} catch (Exception e) {
			reportException(e);
		}
	}
	
	public void executeAfterRuns() {
		try {
			afterRuns();
		} catch (Exception e) {
			reportException(e);
		}
	}
	
	public void createBotReport() {
		//create bot report
		try {
			var status = """
			|-
			| [[User:%s|%s]] || %s || %s
			""".formatted(
				botName, botName,
				run.getTimestamp().toLocalDate().toString(),
				!hadError?"<span style=\"color:ForestGreen\">OK</span>":"<span style=\"color:Crimson\">ERROR</span>[[Category:Pages with errors]]"
			);
			
			run.withMaster(wiki->wiki.editIfChange("User:"+botName+"/Status", status, "Update "+botName+" status"));
		} catch(Exception e) {
			log.error("Failed to create bot report for {}", botName, e);
			System.exit(-1);
		}
	}

	protected abstract List<RUN> createRuns();

	public abstract String getDescription();

	public abstract Wiki getWiki();
}
