package io.github.pfwikis.bots.maintenance;

import java.util.HashSet;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.common.model.Page;
import io.github.pfwikis.bots.utils.StringHelper;
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
		var redirects = run.getWiki().getRedirects().stream()
			.filter(r->StringUtils.startsWithAny(r.getTitle(), "File:", "Template:", "Facts:"))
			.toList();
		log.info("There are {} redirects that are not necessary", redirects.size());
		for(var red:redirects) {
			var from = red.getTitle();
			var fromTitle = run.getWiki().withoutNamespace(from);
			var fromTitlePattern = StringHelper.titleToPattern(fromTitle, true);
			var fromPattern = Pattern.quote(from.substring(0, from.indexOf(fromTitle)))+fromTitlePattern;
			
			//temporary workaround until manually resolved
			if(from.equals("Template:Iconic")) continue;
			var to = red.getDatabaseResult().toFullPageTitle(run.getServer());
			var toTitle = red.getDatabaseResult().getRedirectTitle().replace('_', ' ');
			
			var uses = new HashSet<Page>(run.getWiki().getImageUsage(from));
			uses.addAll(run.getWiki().getPagesLinkingTo(from));
			uses.addAll(run.getWiki().getPagesTranscluding(from));
			
			log.info(from);
			boolean resolveFailed = false;
			if(!uses.isEmpty()) {
				
				for(var use:uses) {
					var txt = run.getWiki().getPageText(use.getTitle());
					var ntxt = txt.replaceAll(fromPattern, to);
					//special case: Templates can be transcluded
					if(from.startsWith("Template:")) {
						ntxt = ntxt
							.replaceAll("(?s)(\\{\\{\\s*)"+fromTitlePattern+"(\\s*[\\|\\}])", "$1"+toTitle+"$2")
							.replaceAll("(?s)(\\{\\{\\s*tl\\s*\\|)"+fromTitlePattern+"(\\s*[\\|\\}])", "$1"+toTitle+"$2");
					}
					//special case: citation templates
					if(from.startsWith("Template:Cite/")) {
						ntxt = ntxt.replaceAll(
							"(\\{\\{ *(Cite|Ref) *\\| *)"+StringHelper.titleToPattern(from.substring(14), false)+"( *[\\|\\}])",
							"$1"+to.substring(14)+"$3");
					}
					//special case: image Files can be used via the Image: ns
					if(from.startsWith("File:")) {
						ntxt = ntxt.replaceAll(fromTitlePattern, toTitle);
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
				run.getWiki().delete(from, "No longer needed redirect to [[:"+to+"]]");
			else
				log.warn("Can not delete {}, as I am not able to resolve all uses", from);
		}
	}

	private void removeCreatedPagesWithoutSource() {
		var entries = run.getWiki().semanticAsk("[[Created from::+]]|?Created from");
		entries.forEach(r-> {
			var generated = r.getPage();
			var from = r.getPrintouts().getCreatedFrom();
			
			if(from != null && from.getExists().equals("") && !run.getWiki().pageExists(from.getPage())) {
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
