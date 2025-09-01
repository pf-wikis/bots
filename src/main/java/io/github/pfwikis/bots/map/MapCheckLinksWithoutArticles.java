package io.github.pfwikis.bots.map;

import java.io.IOException;
import java.util.stream.Collectors;

import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapCheckLinksWithoutArticles extends SimpleBot {

	public MapCheckLinksWithoutArticles() {
		super("map-check-links-without-articles", "Bot Map Search Page");
	}

	@Override
	public void run(RunContext ctx) throws IOException {
		if(run.getServer() == Wiki.SF) return;
		
		var res = run.getWiki().getParsed("PathfinderWiki:Map Locations Without Articles");
		
		var links = res.links()
			.stream()
			.filter(l->
				l.exists()
				&& l.ns()%2==0 //skip talk pages
				&& !l.title().equals("Map")
				&& l.ns()!=2 //skip User NS
			)
			.toList();
		
		if(!links.isEmpty()) {
			discord.reportToMapping(this, 	
				"There are pages on "
				+"[Map Locations Without Articles](https://pathfinderwiki.com/wiki/PathfinderWiki:Map_Locations_Without_Articles) "
				+"that seem to have an article now. The map location should be moved to the articles.\n%s"
				.formatted(links.stream().map(l->"* %s\n".formatted(
					Discord.wikiLink(run.getServer(), l.title(), "/wiki/"+l.title())
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
