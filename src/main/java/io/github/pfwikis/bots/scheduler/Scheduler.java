package io.github.pfwikis.bots.scheduler;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.coreoz.wisp.SchedulerConfig;
import com.coreoz.wisp.schedule.Schedule;
import com.coreoz.wisp.schedule.Schedules;
import com.google.common.util.concurrent.Uninterruptibles;

import io.github.pfwikis.bots.Runner;
import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.common.bots.Bot.RunOnPage;
import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.meta.Meta;
import io.github.pfwikis.bots.newsfeedreader.NewsFeedReader;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
	private com.coreoz.wisp.Scheduler scheduler;
	
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
			
			scheduler = new com.coreoz.wisp.Scheduler(SchedulerConfig.builder()
				.maxThreads(2)
				.build()
			);
			
			for(var wiki : Wiki.values()) {
				scheduleOnce(scheduleableBot(wiki, discord, new Meta()));
				schedule(scheduleableBot(wiki, discord, new NewsFeedReader()), Schedules.fixedDelaySchedule(Duration.ofHours(1)));
				
				schedule(
					new RCWatcher(this, discord, wiki),
					Schedules.fixedDelaySchedule(Duration.ofMinutes(2))
				);
			}
		}
	}
	
	private void initBot(Wiki wiki, Discord discord, SimpleBot bot) {
		bot.setRootPassword(wiki.getMasterPassword());
		var sr = new SingleRun(wiki, wiki.getMasterAccount(), wiki.getMasterPassword());
		sr.setWiki(WikiAPI.fromCache(wiki, bot.getBotName(), bot.getBotPassword()));
		
		bot.setDiscord(discord);
		bot.setLocalMode(localMode);
		bot.setRun(sr);
	}
	
	@RequiredArgsConstructor
	public static abstract class Schedulable implements Runnable {
		private final String name;
		
		public abstract void execute();
		
		public void run() {
			var thread = Thread.currentThread();
			var oldThreadName = thread.getName();
			try {
				thread.setName(name);
				execute();
			} finally {
				thread.setName(oldThreadName);
			}
		}
	}
	
	public <T extends SimpleBot&RunOnPage> Schedulable scheduleableBotOnPage(Wiki wiki, Discord discord, T bot, String title) {
		return new Schedulable(wiki.name()+"-"+bot.getId()+" on "+title) {
			@Override
			public void execute() {
				synchronized(bot) {
					initBot(wiki, discord, bot);
					bot.runSinglePage(title);
				}
			}
		};
	}
	
	public Schedulable scheduleableBot(Wiki wiki, Discord discord, SimpleBot bot) {
		return new Schedulable(wiki.name()+"-"+bot.getId()) {
			@Override
			public void execute() {
				synchronized(bot) {
					initBot(wiki, discord, bot);
					bot.startRun(discord);
				}
			}
		};
	}
	
	public void scheduleOnce(Schedulable task) {
		try {
			scheduler.schedule(task.name, task, Schedules.executeOnce(Schedules.fixedDelaySchedule(Duration.ZERO)));
		} catch(IllegalArgumentException e) {
			if(!e.getMessage().contains("A job is already scheduled with the name"))
				throw e;
		}
	}
	
	public void schedule(Schedulable task, Schedule schedule) {
		scheduler.schedule(task.name, task, schedule);
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
