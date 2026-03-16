package io.github.pfwikis.bots.maintenance;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.model.AAPIExceptions.AAPIException;
import io.github.pfwikis.bots.common.api.model.PageRef;
import io.github.pfwikis.bots.common.api.model.PageTitle;
import io.github.pfwikis.bots.common.api.responses.SemanticAsk.Result;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.utils.StringHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class Maintenance extends SimpleBot {


	public Maintenance() {
		super("maintenance", "Maintenance");
	}

	@Override
	protected void run(RunContext ctx) throws Exception {
		resolveAndRemoveRedirects();
		protectBotCreatedPages();
		removeCreatedPagesWithoutSource();
	}

	private void protectBotCreatedPages() {
		
		for(var p:run.getWiki().getPagesTranscluding(PageRef.of(NS.TEMPLATE, "Bot created"))) {
			if(p.getPage().toString().startsWith("Template:Bot created")) continue;
			
			log.info("Checking protection of {}", p.toPageTitle());
			if(!run.getWiki().isProtected(p.getPage())) {
				log.info("Protecting {}", p.getPage());
				run.getWiki().protect(p.getPage(), "edit=sysop|move=sysop", "Bot created page");
			}
		}
	}

	private static Set<NS> REDIRECT_NS = EnumSet.of(NS.FILE, NS.TEMPLATE, NS.FACTS);
	private void resolveAndRemoveRedirects() throws AAPIException {
		var redirects = run.getWiki().getAllRedirects().stream()
			.filter(r->REDIRECT_NS.contains(r.getPage().getTitle().getNs()))
			.toList();
		log.info("There are {} redirects that are not necessary", redirects.size());
		for(var red:redirects) {
			var from = red.getPage().getTitle();
			var fromTitle = from.getName();
			var fromTitlePattern = StringHelper.titleToPattern(from.getName(), true);
			var fromPattern = Pattern.quote(from.getNs().getPrefix())+fromTitlePattern;
			
			//temporary workaround until manually resolved
			if(from.equals(PageTitle.of(NS.TEMPLATE, "Iconic"))) continue;
			var to = red.getDatabaseResult().toPageTitle();
			
			var uses = new HashSet<PageRef>(run.getWiki().getImageUsage(from));
			uses.addAll(run.getWiki().getPagesLinkingTo(from));
			run.getWiki().getPagesTranscluding(from).forEach(r->uses.add(r.getPage()));
			
			log.info("{}", from);
			boolean resolveFailed = false;
			if(!uses.isEmpty()) {
				
				for(var use:uses) {
					var txt = run.getWiki().getWikitext(use);
					var ntxt = txt.replaceAll(fromPattern, to.toFullTitle());
					//special case: Templates can be transcluded
					if(from.getNs().equals(NS.TEMPLATE)) {
						ntxt = ntxt
							.replaceAll("(?s)(\\{\\{\\s*)"+fromTitlePattern+"(\\s*[\\|\\}])", "$1"+to.getName()+"$2")
							.replaceAll("(?s)(\\{\\{\\s*tl\\s*\\|)"+fromTitlePattern+"(\\s*[\\|\\}])", "$1"+to.getName()+"$2");
					}
					//special case: image Files can be used via the Image: ns
					if(from.getNs().equals(NS.FILE)) {
						ntxt = ntxt.replaceAll(fromTitlePattern, to.getName());
					}
					if(!ntxt.equals(txt)) {
						run.getWiki().edit(use, ntxt, "Resolved unnecessary redirect");
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
		var entries = run.getWiki().semanticAsk(Printouts.class, "[[Created from::+]]|?Created from=from");
		entries.forEach(r-> {
			var generated = r.getPage();
			var from = r.getPrintouts().from();
			
			if(from != null && from.getExists().equals("") && !run.getWiki().exists(from.getPage())) {
				run.getWiki().delete(generated, "Source [["+from.getPage()+"]] was deleted.");
			}
		});
	}
	
	private static record Printouts(Result<Printouts> from) {}

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
