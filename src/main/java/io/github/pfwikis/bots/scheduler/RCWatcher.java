package io.github.pfwikis.bots.scheduler;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.github.pfwikis.bots.citetemplates.CiteTemplates;
import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.bots.Bot.RunOnPage;
import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.common.model.RecentChanges.RecentChange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor 
public class RCWatcher implements Runnable {
	private final Scheduler p;
	private final Discord discord;
	private final Wiki wiki;
	private Instant nextTimestamp;

	@Override
	public void run() {
		Thread.currentThread().setName(this.toString());
		List<RecentChange> rc;
		if(nextTimestamp == null) {
			log.info("Querying changes of the last 24h");
			rc = wiki.getMasterApi().getRecentChanges(Duration.ofHours(24), null, null);
			nextTimestamp = rc.stream()
					.map(e->e.getTimestamp()).max(Comparator.naturalOrder())
					.orElse(null);
		}
		else {
			log.info("Querying changes since {}", nextTimestamp);
			rc = wiki.getMasterApi().getRecentChanges(nextTimestamp, null, null);
		}
		Collections.reverse(rc);
		for(var change:rc) {
			handle(change);
		}
	}

	private void handle(RecentChange change) {
		log.info("RC in {}", change.getTitle());
		if(change.getTitle().startsWith("Facts:")) {
			scheduleOnceOnPage(p, wiki, discord, new CiteTemplates(), change.getTitle());
		}
	}
	
	private static void initBot(Scheduler scheduler, Wiki wiki, Discord discord, SimpleBot bot) {
		bot.setRootPassword(wiki.getMasterPassword());
		var sr = new SingleRun(wiki, wiki.getMasterAccount(), wiki.getMasterPassword());
		sr.setWiki(WikiAPI.fromCache(wiki, bot.getBotName(), bot.getBotPassword()));
		
		bot.setDiscord(discord);
		bot.setLocalMode(scheduler.isLocalMode());
		bot.setRun(sr);
	}
	
	public static <T extends SimpleBot&RunOnPage> void scheduleOnceOnPage(Scheduler scheduler, Wiki wiki, Discord discord, T bot, String title) {
		initBot(scheduler, wiki, discord, bot);
		bot.runSinglePage(title);
	}
	
	public static void scheduleOnce(Scheduler scheduler, Wiki wiki, Discord discord, SimpleBot bot) {
		initBot(scheduler, wiki, discord, bot);
		bot.startRun(discord);
	}

	@Override
	public String toString() {
		return "RCWatcher[wiki="+wiki.name()+"]";
	}
}
