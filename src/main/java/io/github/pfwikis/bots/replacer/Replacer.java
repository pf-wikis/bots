package io.github.pfwikis.bots.replacer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.common.model.Page;
import io.github.pfwikis.bots.common.model.subject.PageRef;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class Replacer extends SimpleBot {

	public Replacer() {
		super("replacer", "Bot Manual Bulk Operations");
	}
	
	@Override
	public String getDescription() {
		return "This bot is only started by hand for manual bulk changes to the wiki.";
	}

	@Override
	public void run(RunContext ctx) throws IOException {
		var pages = new ArrayList<Page>();
		pages.addAll(run.getWiki().getPagesInCategory("Category:Pages with errors"));
		pages.addAll(run.getWiki().getPagesTranscluding("Facts:Mechageddon!"));
		pages.addAll(run.getWiki().getPagesTranscluding("Facts:Guilt of the Graveworld"));
		pages.addAll(run.getWiki().getPagesTranscluding("Facts:Scoured Stars (adventure path)"));
		for(var p:pages) {
			if(p.getTitle().equals("Imperial conquest"))  {
				int i=0;
			}
			var txt = run.getWiki().getPageText(p.getTitle());
			var ntxt = txt
					.replace("{{Ref|Mechageddon!|", "{{Ref|Mechageddon!/Book|")
					.replace("{{Ref|Guilt of the Graveworld|", "{{Ref|Guilt of the Graveworld/Book|")
					.replace("{{Ref|Scoured Stars (adventure path)|", "{{Ref|Scoured Stars (adventure path)/Book|")
					.replace("{{Cite|Mechageddon!|", "{{Cite|Mechageddon!/Book|")
					.replace("{{Cite|Guilt of the Graveworld|", "{{Cite|Guilt of the Graveworld/Book|")
					.replace("{{Cite|Scoured Stars (adventure path)|", "{{Cite|Scoured Stars (adventure path)/Book|")
					;
			if(!txt.equals(ntxt)) {
				run.getWiki().edit(p.getTitle(), ntxt, "Fix citations after facts page move");
			}
		}
		
		/*var quotes = run.getWiki().semanticAsk(Debug.class, "[[Has fact type::Quote]][[Debug::+]]|?Debug=debug");
		for (var q : quotes) {
			if (run.getWiki().getNamespaceId(q.getPage()) != 0)
				continue;
			var page = q.getPage().substring(0, q.getPage().indexOf('#'));
			if (page.startsWith("Burnt Offer")) {
				int i = 0;
			}
			log.info(page);
			var facts = run.getWiki().getPageText("Facts:" + page);
			if (facts == null || facts.trim().equals(""))
				continue;
			if (facts.contains("Blurb"))
				continue;

			// parse debug
			String header = null;
			String quote = null;
			String speaker = null;
			String extras = null;
			for (var v : q.getPrintouts().debug) {
				if (v.startsWith("header:"))
					header = v.substring(7).trim();
				else if (v.startsWith("quote:"))
					quote = v.substring(6).trim();
				else if (v.startsWith("speaker:"))
					speaker = v.substring(8).trim();
				else if (v.startsWith("quote-extras:"))
					extras = v.substring(13).trim();
			}
			if (quote == null)
				continue;

			var txt = run.getWiki().getPageText(page);

			var pp = new PseudoParser(txt);
			var sb = new StringBuilder();
			var pref = pp.goTo("\\{\\{ *Quote\\s*\\|");
			if (pref == null)
				continue;
			sb.append(pref);
			sb.append("{{Quote blurb}}");
			var content = pp.subParserTo(2, new OC('{', '}'));
			sb.append(pp.getRemainingText());

			if (content == null || !content.getRemainingText().contains(quote))
				continue;

			var ntxt = sb.toString();
			if (ntxt.equals(txt))
				continue;

			int insertPos = facts.lastIndexOf("}}");
			if (insertPos < 0)
				continue;
			
			if(!StringUtils.isEmpty(quote) && StringUtils.isEmpty(extras)) {
				String blurb = quote;
				int cutPoint = blurb.indexOf("\n\n");
				int alt = blurb.indexOf("\n*");
				if(cutPoint == -1 || (alt>0 && alt<cutPoint))
					cutPoint = alt;
				
				if(cutPoint > 0) {
					quote = blurb.substring(0,cutPoint).trim();
					extras = blurb.substring(cutPoint).trim();
				}
			}
			
			
			var nfacts = new StringBuilder().append(facts.substring(0, insertPos));
			if (header != null)
				nfacts.append("|Blurb heading=").append(header).append("\n");
			if (quote != null)
				nfacts.append("|Blurb text=").append(quote).append("\n");
			if (extras != null)
				nfacts.append("|Blurb text extras=").append(extras).append("\n");
			if (speaker != null)
				nfacts.append("|Blurb quotee=").append(speaker).append("\n");
			nfacts.append(facts.substring(insertPos));

			if (nfacts.toString().equals(facts))
				continue;

			run.getWiki().edit("Facts:" + page, nfacts.toString(), "Add blurb");
			run.getWiki().edit(page, ntxt, "Use semantic blurb");

		}*/
	}
	
	public static record Debug(List<String> debug) {}
	public static record Out(String blurbText, String blurbExtras) {}
}
