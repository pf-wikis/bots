package io.github.pfwikis.bots.pagesyncer;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.DualBot;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.RunOnPageBot;
import io.github.pfwikis.bots.common.model.Page;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class PageSyncer extends DualBot implements RunOnPageBot {

	public PageSyncer() {
		super("page-syncer", "Bot Page Syncer");
	}
	
	@Override
	public String getDescription() {
		return "This bot is regularly copying all pages in the"
				+" [[:Category:Synced to starfinderwiki]] on"
				+" pathfinderwiki.com to starfinderwiki.com.";
	}
	
	private static final int[] NAMESPACES = {0, 8,10,14,102,106,128,274,828};

	@Override
	public void run(RunContext ctx) throws Exception {
		if(ctx.getPage() != null) {
			runOnPage(ctx.getPage());
			return;
		}
		var sfToken = run.getSfWiki().requestToken("csrf");
		
		var toSyncPages = run.getPfWiki().getPagesInCategory("Category:Synced to starfinderwiki", Arrays.stream(NAMESPACES).mapToObj(Integer::toString).collect(Collectors.joining("|")));
		
        List<String> titles = toSyncPages.stream()
            .map(Page::getTitle)
            .map(t->t.endsWith("/doc")?t.substring(0,t.length()-4):t)
            .distinct()
            .toList();
        
        Set<String> synced = sync(titles, sfToken);
        
        //delete pages from sf that no longer exist in pf
        var pages = run.getSfWiki().getPagesInCategory("Category:Synced to starfinderwiki");
        for(var page : pages) {
        	if(!synced.contains(page.getTitle())) {
        		var txt = run.getSfWiki().getPageText(page.getTitle());
        		if(!txt.contains("{{Deletion|")) {
        			run.getSfWiki().edit(page.getTitle(), "<noinclude>{{Deletion|This page was originally synced from the pathfinderwiki"
        				+ ", where it no longer exists or is no longer synced. It should be deleted or removed from the category.}}</noinclude>"+txt,
        				"Synced page no longer found in source");
        		}
        		run.getSfWiki().delete(page.getTitle(), "Synced page was deleted in source");
        	}
        }
    }
	
	private Set<String> sync(List<String> titles, String sfToken) throws IOException {
		var synced = new HashSet<String>();
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
        return synced;
	}

	public void runOnPage(String page) throws Exception {
		if(!page.endsWith("/doc"))
			runOnPage(page+"/doc");
		
		if(!ArrayUtils.contains(NAMESPACES, run.getPfWiki().getNamespaceId(page))) return;		
		if(run.getPfWiki().getCategories(page).stream().noneMatch(c->c.getTitle().equals("Category:Synced to starfinderwiki"))) return;
		
		if(page.endsWith("/doc"))
			page = page.substring(0,page.length()-4);
        
		var sfToken = run.getSfWiki().requestToken("csrf");
        sync(List.of(page), sfToken);
	}

    private void sync(String page, String token) throws IOException {
        var targetTxt = run.getPfWiki().getPageText(page);
        var sfText = run.getSfWiki().getPageText(page);
        if((!page.startsWith("Module:") || page.endsWith("/doc")) && !page.startsWith("Style:")) {
            targetTxt = "<noinclude><div class=\"banner\">"
                + "This page is automatically synced from [https://pathfinderwiki.com/wiki/{{FULLPAGENAMEE}} this] pathfinderwiki page. "
                + "Do not edit it here.</div>\n</noinclude>" //no linebreak after noinclude!!!
                + targetTxt;
        }

        if(!sfText.equals(targetTxt)) {
        	run.getSfWiki().editIfChange(page, targetTxt, "Autosync from Pathfinderwiki");
        	run.getSfWiki().protect(page, "edit=sysop|move=sysop", "Automatically synced from pathfinderwiki", token);
            log.info("Synced&Protected "+page+" to Starfinderwiki");
        }
    }

}
