package io.github.pfwikis.bots.pagesyncer;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.model.PageRef;
import io.github.pfwikis.bots.common.bots.DualBot;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.RunOnPageBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class PageSyncer extends DualBot implements RunOnPageBot {

	public PageSyncer() {
		super("page-syncer", "Page Syncer");
	}
	
	@Override
	public String getDescription() {
		return "This bot is regularly copying all pages in the"
				+" [[:Category:Synced to starfinderwiki]] on"
				+" pathfinderwiki.com to starfinderwiki.com.";
	}
	
	private static final NS[] NAMESPACES = {
			NS.MAIN,
			NS.MEDIAWIKI,
			NS.TEMPLATE,
			NS.CATEGORY,
			NS.PROPERTY,
			NS.FORM,
			NS.FACTS,
			NS.WIDGET
	};
	
	private static final PageRef SYNCED_CAT = PageRef.of("Category:Synced to starfinderwiki");

	@Override
	public void run(RunContext ctx) throws Exception {
		if(ctx.getPage() != null) {
			runOnPage(ctx.getPage());
			return;
		}
		var toSyncPages = run.getPfWiki().getPagesInCategory(SYNCED_CAT, NAMESPACES);

        List<PageRef> titles = toSyncPages.stream()
            .map(p->p.getPage().getTitle().toFullTitle())
            .map(t->t.endsWith("/doc")?t.substring(0,t.length()-4):t)
            .distinct()
            .map(PageRef::of)
            .toList();
        
        
        Set<String> synced = sync(titles);

        //delete pages from sf that no longer exist in pf
        var pages = run.getSfWiki().getPagesInCategory(SYNCED_CAT);
        for(var page : pages) {
        	if(!synced.contains(page.getTitle().toFullTitle())) {
        		var txt = run.getSfWiki().getWikitext(page);
        		if(!txt.contains("{{Deletion|")) {
        			run.getSfWiki().edit(page, "<noinclude>{{Deletion|This page was originally synced from the pathfinderwiki"
        				+ ", where it no longer exists or is no longer synced. It should be deleted or removed from the category.}}</noinclude>"+txt,
        				"Synced page no longer found in source");
        		}
        	}
        }
    }
	
	private Set<String> sync(List<PageRef> titles) throws IOException {
		var synced = new HashSet<String>();
        for(var page : titles) {
            sync(page);
            synced.add(page.getTitle().toFullTitle());

            PageRef doc = PageRef.of(page.getTitle()+"/doc");
            if(run.getPfWiki().exists(doc)) {
                sync(doc);
                synced.add(doc.getTitle().toFullTitle());
            }
            
            PageRef style = PageRef.of(NS.STYLE, page.getTitle().getName());
            if(run.getPfWiki().exists(style)) {
                sync(style);
                synced.add(style.getTitle().toFullTitle());
            }
        }
        return synced;
	}

	public void runOnPage(PageRef page) throws Exception {
		if(!page.getTitle().getName().endsWith("/doc"))
			runOnPage(PageRef.of(page.getTitle()+"/doc"));
		
		if(!ArrayUtils.contains(NAMESPACES, page.getTitle().getNs())) return;		
		if(run.getPfWiki().getCategories(page).stream().noneMatch(c->c.equals(SYNCED_CAT))) return;
		
		if(page.getTitle().getName().endsWith("/doc"))
			page = PageRef.of(page.getTitle().getName().substring(0, page.getTitle().getName().length()-4));
        
        sync(List.of(page));
	}

    private void sync(PageRef page) throws IOException {
    	log.info("Syncing {}", page);
        var targetTxt = run.getPfWiki().getWikitext(page);
        if(!page.getTitle().getNs().equals(NS.STYLE)) {
            targetTxt = "<noinclude>{{Bot created|VirenerusBot/"+botName+"|"
                + "This page is automatically synced from [https://pathfinderwiki.com/wiki/{{FULLPAGENAMEE}} this] pathfinderwiki page. "
                + "Do not edit it here.}}\n\n</noinclude>" //no linebreak after noinclude!!!
                + targetTxt;
        }
        else {
        	targetTxt = "/*This page is automatically synced from https://pathfinderwiki.com/wiki/"
    			+ page.getTitle()
    			+ ". Do not edit it here.*/\n"
                + targetTxt;
        }

    	run.getSfWiki().editIfChange(page, targetTxt, "Autosync from Pathfinderwiki");
    }

}
