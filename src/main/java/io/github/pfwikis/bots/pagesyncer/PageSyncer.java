package io.github.pfwikis.bots.pagesyncer;

import java.io.IOException;
import java.util.List;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.DualBot;
import io.github.pfwikis.bots.common.model.Page;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters(commandDescription = "Syncs some pages from SF wiki to PF wiki")
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
		
		var toSyncPages = run.getPfWiki().getPagesInCategory("Category:Synced to starfinderwiki", "8|10|102|274|828");
		
        List<String> titles = toSyncPages.stream()
            .map(Page::getTitle)
            .map(t->t.endsWith("/doc")?t.substring(0,t.length()-4):t)
            .distinct()
            .toList();

        for(var page : titles) {
            sync(page, sfToken);

            String doc = page+"/doc";
            if(run.getPfWiki().pageExists(doc)) {
                sync(doc, sfToken);
            }
        }
    }

    private void sync(String page, String token) throws IOException {
        var targetTxt = run.getPfWiki().getPageText(page);
        var sfText = run.getSfWiki().getPageText(page);
        if(!page.startsWith("Module:") || page.endsWith("/doc")) {
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
