package io.github.pfwikis.bots.scheduler;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import io.github.pfwikis.bots.citetemplates.CiteTemplates;
import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.model.LogEventsQuery.LogEvent;
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
	private Instant nextEditTimestamp;
	private Instant nextMoveTimestamp;
	private List<RecentChange> edits;
	private List<LogEvent> moves;
	
	public RCWatcher(Scheduler p, Discord discord, Wiki wiki) {
		super(wiki.name()+"-RCWatcher");
		this.p = p;
		this.discord = discord;
		this.wiki = wiki;
	}
	
	@Override
	public void execute() {
		Thread.currentThread().setName(this.toString());
		
		loadChanges();
		
		for(var change:edits) {
			handleChange(change.getTitle(), change.getTimestamp());
		}
		edits = null;
		for(var change:moves) {
			handleChange(change.getParams().getTargetTitle(), change.getTimestamp());
		}
		moves=null;
	}

	private void loadChanges() {
		if(nextEditTimestamp == null) nextEditTimestamp = Instant.now().minus(24, ChronoUnit.HOURS);
		if(nextMoveTimestamp == null) nextMoveTimestamp = Instant.now().minus(24, ChronoUnit.HOURS);
		
		log.info("Querying edits since {}"+nextEditTimestamp);
		edits = wiki.getMasterApi().getRecentChanges(nextEditTimestamp, null, null);
		log.info("Querying moves since {}"+nextMoveTimestamp);
		moves = wiki.getMasterApi().getRecentLogEvents("move", nextMoveTimestamp);
		Collections.reverse(edits);
		Collections.reverse(moves);
		
		nextEditTimestamp=edits.stream().map(e->e.getTimestamp())
			.max(Comparator.naturalOrder())
			.orElse(nextEditTimestamp);
		nextMoveTimestamp=moves.stream().map(e->e.getTimestamp())
			.max(Comparator.naturalOrder())
			.orElse(nextMoveTimestamp);
	}
	
	
	private CiteTemplates botCiteTemplates = new CiteTemplates();
	private InfoboxTemplates botInfoboxTemplates = new InfoboxTemplates();
	private FactsHelper botFactsHelper = new FactsHelper();
	private TemplateStyles botTemplateStyles = new TemplateStyles();
	private PageSyncer botPageSyncer = new PageSyncer();
	
	private void handleChange(String changedPage, Instant changeTime) {
		log.info("RC in {}", changedPage);
		var ctx = RunContext.builder().page(changedPage).build();
		if(changedPage.startsWith("Facts:")) {
			var title = changedPage;
			//special handling since those never contain facts themselves but feed their parent page
			title = StringUtils.removeEnd(title, "/Releases");
			title = StringUtils.removeEnd(title, "/Sections");
			var localCtx = RunContext.builder().page(title).build();
			
			p.scheduleOnce(p.scheduleableBot(wiki, discord, botCiteTemplates, localCtx), changeTime);
			p.scheduleOnce(p.scheduleableBot(wiki, discord, botInfoboxTemplates, localCtx), changeTime);
		}
		if(changedPage.startsWith("Property:")) {
			p.scheduleOnce(p.scheduleableBot(wiki, discord, botFactsHelper), changeTime);
		}
		if(changedPage.startsWith("Style:")) {
			p.scheduleOnce(p.scheduleableBot(wiki, discord, botTemplateStyles), changeTime);
		}
		if(wiki == Wiki.PF) {
			p.scheduleOnce(p.scheduleableBot(discord, botPageSyncer, ctx), changeTime);
		}
	}
	
	@Override
	public String toString() {
		return "RCWatcher[wiki="+wiki.name()+"]";
	}
}
