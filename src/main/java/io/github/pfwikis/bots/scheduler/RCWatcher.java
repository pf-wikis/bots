package io.github.pfwikis.bots.scheduler;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.base.Supplier;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

import io.github.pfwikis.bots.citetemplates.CiteTemplates;
import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.bots.Bot;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.model.RecentChanges.RecentChange;
import io.github.pfwikis.bots.factshelper.FactsHelper;
import io.github.pfwikis.bots.infoboxtemplates.InfoboxTemplates;
import io.github.pfwikis.bots.pagesyncer.PageSyncer;
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
		ClassToInstanceMap<Bot<?>> botCache = MutableClassToInstanceMap.create();
		for(var change:rc) {
			handle(change, botCache);
		}
	}

	private void handle(RecentChange change, ClassToInstanceMap<Bot<?>> botCache) {
		log.info("RC in {}", change.getTitle());
		var ctx = RunContext.builder().page(change.getTitle()).build();
		if(change.getTitle().startsWith("Facts:")) {
			p.scheduleOnce(p.scheduleableBot(wiki, discord, makeBot(botCache, CiteTemplates.class, CiteTemplates::new), ctx));
			p.scheduleOnce(p.scheduleableBot(wiki, discord, makeBot(botCache, InfoboxTemplates.class, InfoboxTemplates::new), ctx));
		}
		if(
			change.getTitle().equals("User:Bot Facts Helper/Config")
			||
			change.getTitle().startsWith("Property:")
		) {
			p.scheduleOnce(p.scheduleableBot(wiki, discord, makeBot(botCache, FactsHelper.class, FactsHelper::new)));
		}
		if(change.getTitle().startsWith("Style:")) {
			p.scheduleOnce(p.scheduleableBot(wiki, discord, makeBot(botCache, TemplateStyles.class, TemplateStyles::new)));
		}
		if(wiki == Wiki.PF) {
			p.scheduleOnce(p.scheduleableBot(discord, makeBot(botCache, PageSyncer.class, PageSyncer::new), ctx));
		}
	}
	
	private <T extends Bot<?>> T makeBot(ClassToInstanceMap<Bot<?>> botCache, Class<T> type, Supplier<T> constructor) {
		var result = botCache.getInstance(type);
		if(result == null) {
			result = constructor.get();
			botCache.putInstance(type, result);
		}
		return result;
	}
	
	
	
	@Override
	public String toString() {
		return "RCWatcher[wiki="+wiki.name()+"]";
	}
}
