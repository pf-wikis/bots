package io.github.pfwikis.bots.infoboxtemplates;

import java.util.Arrays;
import java.util.List;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.RunOnPageBot;
import io.github.pfwikis.bots.common.bots.ScatteredRunnableBot;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.facts.SDIModel;
import io.github.pfwikis.bots.facts.model.SDIProperty;
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
			var pages = run.getWiki().getPagesInNamespace("Facts");
			for(var p:pages) {
				runOnPage(p.getTitle());
			}
		}
	}

	private void runOnPage(String page) {
		var properties = SDIProperty.load(run);
		
		var subject = run.getWiki().semanticSubject(page).getQuery();
		var concepts = Arrays.stream(SDIModel.CONCEPTS)
			.map(c->c.resolve(properties))
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
		var shards = run.getWiki().getPagesInNamespace("Facts")
			.stream()
			.map(p->new Shard(p.getTitle(), false))
			.toList();
		shards.set(0, new Shard(shards.get(0).page(), true));
		return shards;
	}
}
