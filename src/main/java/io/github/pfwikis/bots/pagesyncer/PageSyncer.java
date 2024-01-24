package io.github.pfwikis.bots.pagesyncer;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.DualBot;
import io.github.pfwikis.bots.common.model.Page;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class PageSyncer extends DualBot {

	public PageSyncer() {
		super("page-syncer", "Bot Page Syncer");
	}
	
	@Override
	protected String getDescription() {
		return "This bot is regularly copying all pages in the"
				+" [[:Category:Synced to starfinderwiki]] on"
				+" pathfinderwiki.com to starfinderwiki.com.";
	}

	@Override
	public void run() throws IOException {
		var sfToken = run.getSfWiki().requestToken("csrf");
		
		var toSyncPages = run.getPfWiki().getPagesInCategory("Category:Synced to starfinderwiki", "8|10|14|102|106|274|828");
		
        List<String> titles = toSyncPages.stream()
            .map(Page::getTitle)
            .map(t->t.endsWith("/doc")?t.substring(0,t.length()-4):t)
            .distinct()
            .toList();
        
        Set<String> synced = new HashSet<>();

        for(var page : titles) {
            sync(page, sfToken);
            synced.add(page);

            String doc = page+"/doc";
            if(run.getPfWiki().pageExists(doc)) {
                sync(doc, sfToken);
                synced.add(doc);
            }
            
            String style = "Style:"+page.substring(page.indexOf(":")+1);
            if(run.getPfWiki().pageExists(style)) {
                sync(style, sfToken);
                synced.add(style);
            }
        }
        
        //delete pages from sf that no longer exist in pf
        var pages = run.getSfWiki().getPagesInCategory("Category:Synced to starfinderwiki");
        for(var page : pages) {
        	if(!synced.contains(page.getTitle())) {
        		run.getSfWiki().delete(page.getTitle(), "Synced page was deleted in source");
        	}
        }
    }

    private void sync(String page, String token) throws IOException {
        var targetTxt = run.getPfWiki().getPageText(page);
        var sfText = run.getSfWiki().getPageText(page);
        if((!page.startsWith("Module:") || page.endsWith("/doc")) && !page.startsWith("Style:")) {
            targetTxt = "<noinclude><div class=\"banner\">"
                + "This page is automatically synced from [https://pathfinderwiki.com/wiki/{{FULLPAGENAMEE}} this] pathfinderwiki page. "
                + "Do not edit it here.</div></noinclude>"
                + targetTxt;
        }

        if(!sfText.equals(targetTxt)) {
        	run.getSfWiki().editIfChange(page, targetTxt, "Autosync from Pathfinderwiki");
        	run.getSfWiki().protect(page, "edit=sysop|move=sysop", "Automatically synced from pathfinderwiki", token);
            log.info("Synced&Protected "+page+" to Starfinderwiki");
        }
    }

}
