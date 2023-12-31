package io.github.pfwikis.bots.common.bots;

import java.time.temporal.ChronoUnit;
import java.util.List;

import com.beust.jcommander.Parameter;

import io.github.pfwikis.bots.common.Discord;
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

	public abstract void run() throws Exception;
	
	public void beforeRuns() throws Exception {}
	public void afterRuns() throws Exception {}
	
	public synchronized void reportException(Exception e) {
		hadError = true;
		log.error("Reported exception ",e);
		if(!localMode)
			discord.reportException(e);
	}
	
	public synchronized void reportException(String msg) {
		hadError = true;
		log.error("Reported exception: {}", msg);
		if(!localMode)
			discord.reportException(msg);
	}

	public synchronized void start() throws Exception {
		discord = new Discord(this);
		try {
			executeBeforeRuns();
			
			var runs = createRuns();
			var beforeRunsError = hadError;
			
			for(var currentRun : runs) {
				this.run = currentRun;
				this.discord.setRun(run);
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

	private void executeBeforeRuns() {
		try {
			beforeRuns();
		} catch (Exception e) {
			reportException(e);
		}
	}
	
	private void executeAfterRuns() {
		try {
			afterRuns();
		} catch (Exception e) {
			reportException(e);
		}
	}
	
	private void createBotReport() {
		//create bot report
		try {
			var userPage = """
			{{Bot|Virenerus}}
			==Description==
			%s
			
			==Status==
			
			This bot is a sub bot of [[User:VirenerusBot|]].
			
			The code for this bot can be found [%s here].
			
			{| class="wikitable" style="margin:auto"
			|-
			! Bot Name !! Last run !! Status
			{{User:%s/Status}}
			|}
			""".formatted(
				getDescription(),
				"https://github.com/pf-wikis/bots/tree/main/src/main/java/"+this.getClass().getName().replace(".", "/")+".java",
				botName
			);
			
			run.withMaster(wiki->wiki.editIfChange("User:"+botName, userPage, "Update "+botName+" description"));
			
			var status = """
			|-
			| [[User:%s|]] || %s || %s
			""".formatted(
				botName,
				run.getTimestamp().truncatedTo(ChronoUnit.SECONDS).toInstant().toString(),
				!hadError?"<span style=\"color:ForestGreen\">OK</span>":"<span style=\"color:Crimson\">ERROR</span>[[Category:Pages with errors]]"
			);
			
			run.withMaster(wiki->wiki.editIfChange("User:"+botName+"/Status", status, "Update "+botName+" status"));
		} catch(Exception e) {
			log.error("Failed to create bot report for {}", botName, e);
			System.exit(-1);
		}
	}

	protected abstract List<RUN> createRuns();

	protected abstract String getDescription();
}
