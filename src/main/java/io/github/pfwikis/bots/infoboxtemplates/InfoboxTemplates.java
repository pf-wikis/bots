package io.github.pfwikis.bots.infoboxtemplates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.citetemplates.BookDef.SectionDef;
import io.github.pfwikis.bots.common.bots.Bot.RunOnPage;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.common.model.SemanticAsk.Labeled;
import io.github.pfwikis.bots.common.model.SemanticAsk.Ordered;
import io.github.pfwikis.bots.common.model.SemanticAsk.Printouts;
import io.github.pfwikis.bots.common.model.SemanticAsk.Result;
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
		//also check superpages
		if(page != null && page.contains("/")) {
			runOnPage(page.substring(0,page.lastIndexOf("/")));
		}
		
		var properties = FactsHelper.loadPropertyDefinitions(run);
		var books = run.getWiki().semanticAsk(
			(page==null?"":("[["+page+"]]"))
			+ "[[Fact type::Template:Facts/Book]]"
			+ "|?Name"
			+ "|?Represented by page"
			+ "|?Release year"
			+ "|?Primary author"
			+ "|?Author ordered"
			+ "|?Primary author ordered"
			+ "|?Author"
			+ "|?Artist"
			+ "|?Full title"
			+ "|?Isbn"
			+ "|?Publisher");
		for(var book : books) {
			try {
				var name = book.getPrintouts().getName();
	
				var template = MakeInfoboxTemplate.template(bookDef);
				RockerHelper.make(run.getWiki(), "Template:Cite/"+bookDef.getFactsPage(), template);
			} catch(Exception e) {
				reportException(new RuntimeException("Could not generate citations for "+book.getFulltext(), e));
			}
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
			This bot creates {{tl|Cite}} templates for all books with a facts page.
			""";
	}
		
}
