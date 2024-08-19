package io.github.pfwikis.bots.maintenance;

import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.common.model.Page;
import io.github.pfwikis.bots.common.model.SemanticSubject.PageRef;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class Maintenance extends SimpleBot {


	public Maintenance() {
		super("maintenance", "Bot Maintenance");
	}

	@Override
	protected void run(RunContext ctx) throws Exception {
		resolveAndRemoveRedirects();
		removeCreatedPagesWithoutSource();
	}

	private void resolveAndRemoveRedirects() {
		var redirects = run.getWiki().getRedirects();
		for(var red:redirects) {
			var from = red.getTitle();
			if(!StringUtils.startsWithAny(from, "File:", "Template:", "Facts:")) continue;
			//temporary workaround until manually resolved
			if(from.equals("Template:Iconic")) continue;
			var to = red.getDatabaseResult().toFullPageTitle(run.getServer());
			var toTitle = red.getDatabaseResult().getRedirectTitle();
			
			var uses = new HashSet<Page>(run.getWiki().getImageUsage(from));
			uses.addAll(run.getWiki().getPagesLinkingTo(from));
			uses.addAll(run.getWiki().getPagesTranscluding(from));
			
			log.info(from);
			boolean resolveFailed = false;
			if(!uses.isEmpty()) {
				
				for(var use:uses) {
					var txt = run.getWiki().getPageText(use.getTitle());
					var ntxt = txt.replace(from, to);
					//special case: Templates can be transcluded
					if(from.startsWith("Template:")) {
						ntxt = ntxt.replace("{{"+from.substring(9), "{{"+toTitle);
					}
					//special case: image Files can be used via the Image: ns
					if(from.startsWith("File:")) {
						ntxt = ntxt.replace("Image:"+from.substring(5), "Image:"+toTitle);
					}
					if(!ntxt.equals(txt)) {
						run.getWiki().edit(use.getTitle(), ntxt, "Resolved unnecessary redirect");
					}
					else {
						resolveFailed = true;
					}
				}
			}
			if(!resolveFailed)
				run.getWiki().delete(from, "No longer needed redirect");
			else
				log.warn("Can not delete {}, as I am not able to resolve all uses", from);
		}
	}

	private void removeCreatedPagesWithoutSource() {
		var entries = run.getWiki().semanticAsk("[[Created from::+]]|?Created from");
		entries.forEach(r-> {
			var generated = r.getPage();
			var from = r.getPrintouts().getCreatedFrom();
			
			if(from.getExists().equals("") && !run.getWiki().pageExists(from.getPage())) {
				run.getWiki().delete(generated, "Source [["+from.getPage()+"]] was deleted.");
			}
		});
	}

	@Override
	public String getDescription() {
		return """
			This bot executes regular maintenance tasks. Currently these are:
			* remove cite templates that were generated from a no longer existing facts page
			* remove infobox templates that were generated from a no longer existing facts page
			* resolve and remove File/Template/Facts redirects
		""";
	}

}
