package io.github.pfwikis.bots.facts.infoboxtemplates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.RunOnPageBot;
import io.github.pfwikis.bots.common.bots.ScatteredRunnableBot;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.common.model.Page;
import io.github.pfwikis.bots.facts.SModel;
import io.github.pfwikis.bots.utils.RockerHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class InfoboxTemplates extends SimpleBot implements RunOnPageBot, ScatteredRunnableBot<InfoboxTemplates.Shard> {

	public InfoboxTemplates() {
		super("infobox-templates", "Bot Infobox Templates");
	}
	
	@Override
	public void run(RunContext ctx) {
		if(ctx.getScatterShard() instanceof Shard shard)
			runOnShard(shard);
		else if(ctx.getPage() != null) {
			runOnPage(ctx.getPage());
		}
		else {
			run.getWiki().precacheExistence(run.getWiki()
				.getPagesInNamespace("File")
				.stream()
				.map(Page::getTitle)
				.toList()
			);
			for(var s:createScatterShards()) {
				runOnPage(s.page);
			}
		}
	}

	private void runOnPage(String page) {
		var subject = run.getWiki().semanticSubject(page);
		
		var concepts = Arrays.stream(SModel.CONCEPTS)
			.filter(c->c.getInfoboxProperties()!=null && !c.getInfoboxProperties().isEmpty())
			.filter(subject::hasConcept)
			.toList();
		
		
		
		if(!concepts.isEmpty()) {
			RockerHelper.make(
				run.getWiki(),
				"Template:Facts/Infoboxes/"+page,
				MakeInfoboxTemplate.template(run, concepts, subject)
			);
		}
	}

	private void runOnShard(Shard shard) {
		if(shard.first()) {
			run.getWiki().editIfChange("Template:Facts/Infoboxes", """
				{{Bot created|Bot Infobox Templates}}
				This is an overview over the infoboxes created by [[User:Bot Infobox Templates]] from facts pages:
				{{Subpages|Template:Facts/Infoboxes}}		
				""", "Update from bot update.");
			}
				
			runOnPage(shard.page());
	}

	@Override
	public String getDescription() {
		return
			"""
			This bot creates infoboxes in subpages of [[Template:Facts/Infoboxes]].
			""";
	}
		
	
	public static record Shard(String page, boolean first) {}
	@Override
	public List<Shard> createScatterShards() {
		var shards = new ArrayList<>(run.getWiki().getPagesInNamespace("Facts")
			.stream()
			.filter(p->!p.getTitle().endsWith("/Releases"))
			.filter(p->!p.getTitle().endsWith("/Sections"))
			.map(p->new Shard(p.getTitle(), false))
			.toList());
		shards.set(0, new Shard(shards.get(0).page(), true));
		return shards;
	}
}
