package io.github.pfwikis.bots.scheduler;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.common.util.concurrent.Uninterruptibles;

import io.github.pfwikis.bots.Runner;
import io.github.pfwikis.bots.assistant.AssistantTaskGiver;
import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.bots.DualBot;
import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.ScatteredRunnableBot;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.facts.blog.BlogFacts;
import io.github.pfwikis.bots.facts.makecategories.MakeCategories;
import io.github.pfwikis.bots.facts.master.PropertyStatistics;
import io.github.pfwikis.bots.facts.templates.FactsTemplates;
import io.github.pfwikis.bots.healthcheck.HealthCheck;
import io.github.pfwikis.bots.maintenance.Maintenance;
import io.github.pfwikis.bots.map.MapCheckLinksWithoutArticles;
import io.github.pfwikis.bots.map.MapSearchPage;
import io.github.pfwikis.bots.meta.Meta;
import io.github.pfwikis.bots.newsfeedreader.NewsFeedReader;
import io.github.pfwikis.bots.paizoretriever.PaizoRetriever;
import io.github.pfwikis.bots.rest.RestServer;
import io.github.pfwikis.bots.scheduler.Schedulable.SchedulableBot;
import io.github.pfwikis.bots.templatestyles.TemplateStyles;
import io.github.pfwikis.bots.usagereporter.UsageReporter;
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
	@Parameter(names = "--restOnly")
	protected boolean restOnly;
	@Parameter(names = "--firefox")
	protected String firefoxBin;
	
	private Discord discord;
	
	private PriorityBlockingQueue<Task> tasks = new PriorityBlockingQueue<>();
	
	public void start() throws Exception {
		try(var d = new Discord(discordToken)) {
			this.discord = d;
			for(var wiki : Wiki.values()) {
				wiki.setMasterPassword(rootPassword);
				try {
					wiki.setMasterApi(WikiAPI.create(wiki, wiki.getMasterAccount(), wiki.getMasterPassword()));
				} catch(Exception e) {
					log.error("Failed to log in as {}", wiki.getMasterAccount(), e);
					Uninterruptibles.sleepUninterruptibly(10, TimeUnit.MINUTES);
					System.exit(-1);
				}
				
				checkAccounts(wiki);
			}
			
			schedule(new HealthCheck(discord), Duration.ofHours(24));
			schedule(scheduleableBot(discord, new PaizoRetriever().withFirefoxBin(firefoxBin), new RunContext()), Duration.ofHours(12), LocalTime.of(13, 50));
			for(var wiki : Wiki.values()) {
				scheduleOnce(scheduleableBot(wiki, discord, new Meta()));
				schedule(scheduleableBot(wiki, discord, new FactsTemplates()), Duration.ofDays(7));
				planScatter(wiki, discord, new PropertyStatistics(), Duration.ofDays(7), Duration.ofDays(7));
				
				schedule(
					new RCWatcher(this, discord, wiki),
					Duration.ofMinutes(2)
				);
				schedule(scheduleableBot(wiki, discord, new BlogFacts()), Duration.ofHours(3));
				schedule(scheduleableBot(wiki, discord, new NewsFeedReader()), Duration.ofHours(3));
				schedule(scheduleableBot(wiki, discord, new MapSearchPage()), Duration.ofDays(1), LocalTime.of(12, 00));
				schedule(scheduleableBot(wiki, discord, new Maintenance()), Duration.ofDays(7), LocalTime.of(13, 00));
				schedule(scheduleableBot(wiki, discord, new UsageReporter()), Duration.ofDays(7), LocalTime.of(14, 00));
				schedule(scheduleableBot(wiki, discord, new AssistantTaskGiver()), Duration.ofDays(1), LocalTime.of(15, 00));
				schedule(scheduleableBot(wiki, discord, new MakeCategories()), Duration.ofDays(1), LocalTime.of(16, 00));
				scheduleOnce(scheduleableBot(wiki, discord, new TemplateStyles()));
			}
			schedule(scheduleableBot(Wiki.PF, discord, new MapCheckLinksWithoutArticles()), Duration.ofDays(1), LocalTime.of(16, 00));
			//print queue from time to time
			schedule(new Schedulable("print queue") {
				@Override
				public void execute() {
					var sb = new StringBuilder();
					sb.append("It is now ")
						.append(Instant.now().toString())
						.append("\nQueue:\n");
					tasks.stream()
						.sorted()
						.forEach(t->sb
								.append("\t")
								.append(t.time.toString())
								.append("\t")
								.append(t.schedulable.getName())
								.append("\n"));
					log.info(sb.toString());
				}}, Duration.ofHours(6));
			
			var worker = new Worker(discord);
			if(!restOnly) {
				Thread.ofVirtual().start(worker);
			}
			RestServer.start(this);
			Runtime.getRuntime().addShutdownHook(Thread.ofVirtual().unstarted(()-> {
				RestServer.stop();
				worker.stop();
			}));
		}
	}
	
	@RequiredArgsConstructor
	private class Worker implements Runnable {
		
		private final Discord discord;
		private final AtomicBoolean stop = new AtomicBoolean(false);

		@Override
		public void run() {
			var thread = Thread.currentThread();
			thread.setName("Scheduler Worker");
			while(!stop.get()) {
				var task = Uninterruptibles.takeUninterruptibly(tasks);
				String name = task.getSchedulable().getName();
				try {
					var waitDuration = Duration.between(Instant.now(), task.time).truncatedTo(ChronoUnit.SECONDS);
					if(waitDuration.isPositive()) {
						log.info(
							"Sleeping for {} in wait of task {}",
							DurationFormatUtils.formatDurationWords(waitDuration.toMillis(), true,  true),
							name
						);
						Uninterruptibles.sleepUninterruptibly(waitDuration.toSeconds(), TimeUnit.SECONDS);
					}
					log.info("Executing {}", name);
					thread.setName(name);
					task.run();
				} catch(Exception e) {
					log.error("Failed to execute job {}", name, e);
					if(discord != null)
						discord.report("""
						**Bots Error**
						```java
						{}
						```
						""".replace("{}", ExceptionUtils.getStackTrace(e)));
				} finally {
					thread.setName("Scheduler Worker");
				}
			}
		}

		public void stop() {
			stop.set(true);
		}
	}

	
	public void initBot(Wiki wiki, Discord discord, SimpleBot bot) {
		bot.setRootPassword(wiki.getMasterPassword());
		var sr = new SingleRun(wiki, wiki.getMasterAccount(), wiki.getMasterPassword());
		sr.setWiki(WikiAPI.create(wiki, bot.getBotName(), bot.getBotPassword()));
		
		bot.setDiscord(discord);
		bot.setLocalMode(localMode);
		if(bot instanceof UsageReporter ur) {
			ur.setMatomoToken(matomoToken);
		}
		bot.setRun(sr);
	}
	
	private void initBot(Discord discord, DualBot bot) {
		bot.setRootPassword(Wiki.PF.getMasterPassword());
		bot.setDiscord(discord);
		bot.setLocalMode(localMode);
		bot.setRun(bot.createRuns().get(0));
	}
	
	public SchedulableBot scheduleableBot(Wiki wiki, Discord discord, SimpleBot bot, RunContext ctx) {
		return scheduleableBot(wiki, discord, bot, ctx, false);
	}
	
	public SchedulableBot scheduleableBot(Wiki wiki, Discord discord, SimpleBot bot, RunContext ctx, boolean reset) {
		return new SchedulableBot(bot, wiki.name()+"-"+bot.getId()+ctx.toInfoText()) {
			@Override
			public void execute() {
				synchronized(bot) {
					if(reset)
						bot.setRun(null);
					initBot(wiki, discord, bot);
					bot.startRun(discord, ctx);
				}
			}
		};
	}
	
	public SchedulableBot scheduleableBot(Discord discord, DualBot bot, RunContext ctx) {
		return new SchedulableBot(bot, bot.getId()+ctx.toInfoText()) {
			@Override
			public void execute() {
				synchronized(bot) {
					initBot(discord, bot);
					bot.startRun(discord, ctx);
				}
			}
		};
	}
	
	public SchedulableBot scheduleableBot(Wiki wiki, Discord discord, SimpleBot bot) {
		return scheduleableBot(wiki, discord, bot, new RunContext());
	}
	
	private <K, T extends SimpleBot&ScatteredRunnableBot<K>> void planScatter(Wiki wiki, Discord discord, T bot, Duration scatterWidth, Duration sleepBetweenRuns) {
		scheduleOnce(new SchedulableBot(bot, wiki.name()+"-"+bot.getId()+"-planScatter") {
			@Override
			public void execute() {
				synchronized(bot) {
					initBot(wiki, discord, bot);
					var shards = bot.createScatterShards();
					var scatterDur = scatterWidth.dividedBy(shards.size());
					for(int i=0;i<shards.size();i++) {
						var sbot = scheduleableBot(wiki, discord, bot, RunContext.builder().scatterShard(shards.get(i)).build(), true);
						var delay = Instant.now().plus(scatterDur.multipliedBy(i+1));
						if(sleepBetweenRuns != null) {
							schedule(sbot, sleepBetweenRuns, delay);
						}
						else {
							scheduleOnce(sbot, delay);
						}
					}
					log.info(
						"Planned {} scattered runs withing the next {} every {} for bot {}-{}",
						shards.size(),
						scatterWidth,
						scatterDur,
						wiki.getCode(),
						bot.getId()
					);
				}
			}
		});
	}
	
	public void scheduleOnce(Schedulable schedulable) {
		scheduleOnce(schedulable, Instant.now());
	}
	
	public void scheduleOnce(Schedulable schedulable, Instant firstRun) {
		if(Arrays.stream(tasks.toArray(Task[]::new)).anyMatch(t->t.getSchedulable().getName().equals(schedulable.getName()))) {
			log.warn("Already scheduled a task with name '{}'. Skipping.", schedulable.getName());
			return;
		}
		schedule(new Task(schedulable, firstRun));
	}
	
	public void schedule(Schedulable schedulable, Duration sleepBetweenRuns) {
		schedule(schedulable, sleepBetweenRuns, Instant.now());
	}
	
	public void schedule(Schedulable schedulable, Duration sleepBetweenRuns, LocalTime runTime) {
		var now = Instant.now().plusSeconds(10);
		var firstRun = LocalDate.now().atTime(runTime).toInstant(ZoneOffset.UTC);
		while(firstRun.isBefore(now)) {
			firstRun = firstRun.plus(Duration.ofDays(1));
		}
		schedule(schedulable, sleepBetweenRuns, firstRun);
	}
	
	public void schedule(Schedulable schedulable, Duration sleepBetweenRuns, Instant firstRun) {
		schedule(new Task.Repeatable(schedulable, firstRun, this, sleepBetweenRuns));
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
