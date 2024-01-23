package io.github.pfwikis.bots.replacer;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.HashMultiset;

import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.common.model.Page;
import io.github.pfwikis.bots.replacer.SemanticDataIniHelper.TemplateDef;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SemanticDataIniHelperReplacer {

	private final SingleRun run;
	private Page page;

	public void start() {
		start("Map");
		/*start("Miniatures");
		start("Audio");
		start("Video game");
		start("Deck");*/
	}

	private void start(String template) {
		if(StringUtils.isAllBlank(run.getWiki().getPageText("Template:"+template)))
			return;
		
		var pages = run.getWiki().getPagesTranscluding("Template:"+template);
		var tDef = new TemplateDef(template, HashMultiset.create());
		
		for(var p:pages) {
			page = p;
			try {
				extractPage(tDef);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void extractPage(TemplateDef template) {
		var txt = run.getWiki().getPageText(page.getTitle());
		
		
		var m = Pattern.compile("(\\{\\{\\s*)("+template+"\\s*\\|\\s*)").matcher(txt);
		var newTxt=m.replaceAll(mr-> {
			return "$1subst:$2";
		});
		
		if(!newTxt.equals(txt)) {
			run.getWiki().edit(page.getTitle(), newTxt, "Adding counter to infobox to handle multiple "+template+" infoboxes");
		}
		
		txt = run.getWiki().getPageText(page.getTitle());
		m = Pattern.compile("\\{\\{Infobox\\|"+template+"\\}\\}").matcher(txt);
		AtomicInteger counter = new AtomicInteger(0);
		newTxt=m.replaceAll(mr-> {
			return "{{Infobox|"+template+"|"+page.getTitle()+"/"+template+" "+counter.incrementAndGet()+"}}";
		});
		
		if(counter.get()==1) {
			newTxt = newTxt.replace("|"+page.getTitle()+"/"+template+" 1", "");
		}
		
		if(!newTxt.equals(txt)) {
			run.getWiki().edit(page.getTitle(), newTxt, "Adding counter to infobox to handle multiple "+template+" infoboxes");
		}
		
	}

}
