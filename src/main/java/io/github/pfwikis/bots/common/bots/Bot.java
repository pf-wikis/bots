package io.github.pfwikis.bots.common.bots;

import java.util.List;

import org.slf4j.MDC;
import org.slf4j.MDC.MDCCloseable;

import com.beust.jcommander.Parameter;

import io.github.pfwikis.bots.Runner;
import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.bots.Run.DualRun;
import io.github.pfwikis.bots.common.bots.Run.SingleRun;
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

	@Parameter(names = "--pfkey")
	protected String pfkey;
	@Parameter(names = "--sfkey")
	protected String sfkey;
	@Parameter(names = "--antiProtectionSecret")
	protected String antiProtectionSecret;
	@Parameter(names = "--discordToken")
	protected String discordToken;
	@Parameter(names = "--localMode")
	protected boolean localMode;
	public static boolean globalLocalMode;

	protected RUN run;
	protected Discord discord;
	private boolean hadError = false;

	protected abstract void run(RunContext ctx) throws Exception;

	public void beforeRuns() throws Exception {}
	public void afterRuns() throws Exception {}
	
	public void setLocalMode(boolean v) {
		this.localMode=v;
		Bot.globalLocalMode|=v;
	}

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
				
				try(var mdccloser = flavorLog()) {
					run(new RunContext());
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
	
	private MDCCloseable flavorLog() {
		if(run instanceof SingleRun sr)
			return MDC.putCloseable(Runner.MDC_KEY, sr.getServer().getCode());
		else if (run instanceof DualRun dr)
			return MDC.putCloseable(Runner.MDC_KEY, "dual");
		return MDC.putCloseable(Runner.MDC_KEY, Runner.MDC_VALUE_NONE);
	}
	
	public synchronized void startRun(Discord discord, RunContext ctx) {
		try(var mdccloser = flavorLog()) {
			executeBeforeRuns();
			this.run.setDiscord(discord);
			try {
				run(ctx);
			} catch (Exception e) {
				reportException(e);
			}
			executeAfterRuns();
		} catch(Exception e) {
			reportException(e);
		}
	}
	
	public void executeBeforeRuns() {
		try {
			Bot.globalLocalMode = localMode;
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

	protected abstract List<RUN> createRuns();

	public abstract String getDescription();

	public abstract Wiki getWiki();
}
