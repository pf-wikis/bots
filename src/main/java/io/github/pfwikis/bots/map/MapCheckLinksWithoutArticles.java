package io.github.pfwikis.bots.map;

import java.io.IOException;
import java.util.stream.Collectors;

import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.model.PageRef;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapCheckLinksWithoutArticles extends SimpleBot {

	public MapCheckLinksWithoutArticles() {
		super("map-check-links-without-articles", "Map Search Page");
	}

	@Override
	public void run(RunContext ctx) throws IOException {
		if(run.getServer() == Wiki.SF) return;
		
		var res = run.getWiki().getHTML(PageRef.of(NS.PROJECT, "Map Locations Without Articles"));
		
		var links = res.getLinks()
			.stream()
			.filter(l->
				l.isExists()
				&& !l.getNs().isTalk()
				&& !l.getTitle().getName().equals("Map")
				&& l.getNs() != NS.USER
			)
			.toList();
		
		if(!links.isEmpty()) {
			discord.reportToMapping(this, 	
				"There are pages on "
				+"[Map Locations Without Articles](https://pathfinderwiki.com/wiki/PathfinderWiki:Map_Locations_Without_Articles) "
				+"that seem to have an article now. The map location should be moved to the articles.\n%s"
				.formatted(links.stream().map(l->"* %s\n".formatted(
					Discord.wikiLink(run.getServer(), l.getTitle().toString(), "/wiki/"+l.getTitle())
				)).collect(Collectors.joining())),
				true
			);
		}
	}
	

	@Override
	public String getDescription() {
		return new MapSearchPage().getDescription();
	}
}
