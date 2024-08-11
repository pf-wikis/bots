package io.github.pfwikis.bots.infoboxtemplates;

import java.io.IOException;
import java.util.Arrays;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.Bot.RunOnPage;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.facts.SDIModel;
import io.github.pfwikis.bots.facts.model.SDIProperty;
import io.github.pfwikis.bots.utils.RockerHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class InfoboxTemplates extends SimpleBot implements RunOnPage {

	public InfoboxTemplates() {
		super("infobox-templates", "Bot Infobox Templates");
	}
	
	@Override
	public void runOnPage(String page) {
		if(page == null) {
			var pages = run.getWiki().getPagesInNamespace("Facts");
			for(var p:pages) {
				runOnPage(p.getTitle());
			}
			return;
		}
		
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

	@Override
	public void run() throws IOException {
		runOnPage(null);
	}

	@Override
	public String getDescription() {
		return
			"""
			This bot creates infoboxes in subpages of [[Template:Facts/Infoboxes]]
			""";
	}
		
}
