package io.github.pfwikis.bots.scheduler;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DurationFormatUtils;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.common.util.concurrent.Uninterruptibles;

import io.github.pfwikis.bots.Runner;
import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.bots.Bot.RunOnPage;
import io.github.pfwikis.bots.common.bots.DualBot;
import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.meta.Meta;
import io.github.pfwikis.bots.newsfeedreader.NewsFeedReader;
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
	
	private PriorityBlockingQueue<Task> tasks = new PriorityBlockingQueue<>();
	
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
			
			for(var wiki : Wiki.values()) {
				scheduleOnce(scheduleableBot(wiki, discord, new Meta()));
				schedule(scheduleableBot(wiki, discord, new NewsFeedReader()), Duration.ofHours(1));
				
				schedule(
					new RCWatcher(this, discord, wiki),
					Duration.ofMinutes(2)
				);
			}
			
			worker();
		}
	}
	
	private void worker() {
		var thread = Thread.currentThread();
		thread.setName("Scheduler Worker");
		while(true) {
			var task = Uninterruptibles.takeUninterruptibly(tasks);
			String name = task.getSchedulable().getName();
			try {
				var waitDuration = Duration.between(Instant.now(), task.time).truncatedTo(ChronoUnit.SECONDS);
				if(waitDuration.isPositive()) {
					log.info("Sleeping for {}", DurationFormatUtils.formatDurationWords(waitDuration.toMillis(), true,  true));
					Uninterruptibles.sleepUninterruptibly(waitDuration.toSeconds(), TimeUnit.SECONDS);
				}
				log.info("Executing {}", name);
				thread.setName(name);
				task.run();
			} catch(Exception e) {
				log.error("Failed to execute job {}", name, e);
			} finally {
				thread.setName("Scheduler Worker");
			}
		}
	}
	
	private void initBot(Wiki wiki, Discord discord, SimpleBot bot) {
		bot.setRootPassword(wiki.getMasterPassword());
		var sr = new SingleRun(wiki, wiki.getMasterAccount(), wiki.getMasterPassword());
		sr.setWiki(WikiAPI.fromCache(wiki, bot.getBotName(), bot.getBotPassword()));
		
		bot.setDiscord(discord);
		bot.setLocalMode(localMode);
		if(bot.getRun() == null)
			bot.setRun(sr);
	}
	
	private void initBot(Discord discord, DualBot bot) {
		bot.setRootPassword(Wiki.PF.getMasterPassword());
		bot.setDiscord(discord);
		bot.setLocalMode(localMode);
		if(bot.getRun() == null)
			bot.setRun(bot.createRuns().get(0));
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
	
	public <T extends DualBot&RunOnPage> Schedulable scheduleableBotOnPage(Discord discord, T bot, String title) {
		return new Schedulable(bot.getId()+" on "+title) {
			@Override
			public void execute() {
				synchronized(bot) {
					initBot(discord, bot);
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
	
	public void scheduleOnce(Schedulable schedulable) {
		if(Arrays.stream(tasks.toArray(Task[]::new)).anyMatch(t->t.getSchedulable().getName().equals(schedulable.getName()))) {
			log.warn("Already scheduled a task with name '{}'. Skipping.", schedulable.getName());
			return;
		}
		schedule(new Task(schedulable, Instant.now()));
	}
	
	public void schedule(Schedulable schedulable, Duration sleepBetweenRuns) {
		schedule(new Task.Repeatable(schedulable, Instant.now(), this, sleepBetweenRuns));
	}
	
	public void schedule(Task task) {
		tasks.add(task);
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
