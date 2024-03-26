package io.github.pfwikis.bots.scheduler;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.coreoz.wisp.SchedulerConfig;
import com.coreoz.wisp.schedule.Schedules;
import com.google.common.util.concurrent.Uninterruptibles;

import io.github.pfwikis.bots.Runner;
import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.meta.Meta;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Parameters
public class Scheduler {

	@Parameter(names = "--password")
	protected String rootPassword;
	@Parameter(names = "--discordToken")
	protected String discordToken;
	@Parameter(names = "--localMode")
	protected boolean localMode;
	@Parameter(names = "--matomoToken")
	protected String matomoToken;
	
	public void start() throws Exception {
		try(var discord = new Discord(discordToken)) {
			for(var wiki : Wiki.values()) {
				wiki.setMasterPassword(rootPassword);
				try {
					wiki.setMasterApi(WikiAPI.fromCache(wiki, wiki.getMasterAccount(), wiki.getMasterPassword()));
				} catch(Exception e) {
					log.error("Failed to log in as {}", wiki.getMasterAccount(), e);
					Uninterruptibles.sleepUninterruptibly(10, TimeUnit.MINUTES);
					System.exit(-1);
				}
				
				checkAccounts(wiki);
			}
			
			var scheduler = new com.coreoz.wisp.Scheduler(SchedulerConfig.builder()
				.maxThreads(2)
				.build()
			);
			
			for(var wiki : Wiki.values()) {
				RCWatcher.scheduleOnce(this, wiki, discord, new Meta());
				
				scheduler.schedule(
					new RCWatcher(this, discord, wiki),
					Schedules.afterInitialDelay(Schedules.fixedDelaySchedule(Duration.ofMinutes(2)), Duration.ZERO)
				);
			}
		}
	}

	private void checkAccounts(Wiki wiki) {
		for(var bot : Runner.getAllBots()) {
			try {
				if(!wiki.getMasterApi().accountExists(bot.getBotName())) {
					wiki.getMasterApi().createAccount(bot.getBotName(), rootPassword+bot.getBotName());
					wiki.getMasterApi().addRight(bot.getBotName(), "bot|sysop|techadmin", "never");
				}
			} catch(Exception e) {
				log.error("Failed to check account {}", bot.getBotName(), e);
				System.exit(-1);
			}
		}
	}

}
