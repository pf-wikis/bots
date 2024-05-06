package io.github.pfwikis.bots.scheduler;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.github.pfwikis.bots.citetemplates.CiteTemplates;
import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.model.RecentChanges.RecentChange;
import io.github.pfwikis.bots.factshelper.FactsHelper;
import io.github.pfwikis.bots.templatestyles.TemplateStyles;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RCWatcher extends Schedulable {
	private final Scheduler p;
	private final Discord discord;
	private final Wiki wiki;
	private Instant nextTimestamp;
	
	public RCWatcher(Scheduler p, Discord discord, Wiki wiki) {
		super(wiki.name()+"-RCWatcher");
		this.p = p;
		this.discord = discord;
		this.wiki = wiki;
	}
	
	@Override
	public void execute() {
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
			p.scheduleOnce(p.scheduleableBotOnPage(wiki, discord, new CiteTemplates(), change.getTitle()));
		}
		if(
			change.getTitle().equals("User:Bot Facts Helper/Config")
			||
			change.getTitle().startsWith("Property:")
		) {
			p.scheduleOnce(p.scheduleableBot(wiki, discord, new FactsHelper()));
		}
		if(change.getTitle().startsWith("Style:")) {
			p.scheduleOnce(p.scheduleableBot(wiki, discord, new TemplateStyles()));
		}
	}
	
	
	
	@Override
	public String toString() {
		return "RCWatcher[wiki="+wiki.name()+"]";
	}
}
