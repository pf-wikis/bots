package io.github.pfwikis.bots.map;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;
import java.util.stream.Collectors;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.utils.Jackson;

public class MapSearchPage extends SimpleBot {

	public MapSearchPage() {
		super("map-search-page", "Bot Map Search Page");
	}

	@Override
	public void run(RunContext ctx) throws IOException {
		if(run.getServer() == Wiki.SF) return;
		
		var sb = new StringBuilder()
				.append("<noinclude>{{Bot created|").append(this.getBotName()).append("}}\n")
				.append("This page is a helper for the byName parameter.\n")
				.append("The full list of entries are:\n<ul>\n");
		var entries = Jackson.JSON.readValue(URI.create("https://map.pathfinderwiki.com/search.json").toURL(), Entry[].class);
		for(var e:entries) {
			sb.append("<li>").append(e.label).append("</li>\n");
		}
		sb.append("<ul></noinclude><includeonly>{{#switch:{{{1}}}");
		for(var e:entries) {
			sb
				.append("|")
				.append(e.label)
				.append("=")
				.append(Arrays.stream(e.bbox)
						.map(BigDecimal::toPlainString)
						.collect(Collectors.joining(","))
				);
		}
		sb.append("|}}</includeonly>");
		
		
		run.getWiki().editIfChange("Template:DisplayMap/Search", sb.toString(), "Automatic update");
		
	}
	

	@Override
	public String getDescription() {
		return """
		This bot is keeping an up to date list of all named areas on golarion that are available on the map.
		It creates the page [[Template:DisplayMap/Search]] for this.
		""".formatted(run.getServer().getName());
	}

	private record Entry(String label, BigDecimal[] bbox){}
}
