package io.github.pfwikis.bots.infoboxtemplates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.citetemplates.BookDef.SectionDef;
import io.github.pfwikis.bots.common.bots.Bot.RunOnPage;
import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.common.model.SemanticAsk.Labeled;
import io.github.pfwikis.bots.common.model.SemanticAsk.Ordered;
import io.github.pfwikis.bots.common.model.SemanticAsk.Printouts;
import io.github.pfwikis.bots.common.model.SemanticAsk.Result;
import io.github.pfwikis.bots.facts.model.SDIConcept;
import io.github.pfwikis.bots.facts.model.SDIProperty;
import io.github.pfwikis.bots.facts.model.SDIRawConcept;
import io.github.pfwikis.bots.factshelper.FactsHelper;
import io.github.pfwikis.bots.utils.MWJsonHelper;
import io.github.pfwikis.bots.utils.RockerHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class InfoboxTemplates extends SimpleBot implements RunOnPage {

	public InfoboxTemplates() {
		super("infobox-templates", "Bot Cite Templates");
	}
	
	@Override
	public void runOnPage(String page) {
		if(page == null) {
			page = "Facts:Player Core 2";
			if(run.getServer()==Wiki.SF) return;
		}
		
		var properties = SDIProperty.load(run);
		
		//temporary
		var factsHelper = new FactsHelper();
		factsHelper.setRun(run);
		var concepts = Arrays.stream(factsHelper.loadConfig(SDIRawConcept[].class)).map(c->c.resolve(properties)).toList();
		var subject = run.getWiki().semanticSubject(page).getQuery();
		
		RockerHelper.make(run.getWiki(), "Template:Facts/Infoboxes/"+page, MakeInfoboxTemplate.template(run, concepts, subject));
		
	}

	@Override
	public void run() throws IOException {
		runOnPage(null);
	}

	@Override
	public String getDescription() {
		return
			"""
			This bot creates {{tl|Cite}} templates for all books with a facts page.
			""";
	}
		
}
