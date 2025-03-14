package io.github.pfwikis.bots.map;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
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
				.append("This page is a helper for the by name parameter.\n")
				.append("The full list of entries are:\n<ul>\n");
		var categories = Jackson.JSON.readValue(new File("E:\\workspace-paizo\\mapping\\frontend\\public\\search.json"), Category[].class);
		//var categories = Jackson.JSON.readValue(URI.create("https://map.pathfinderwiki.com/search.json").toURL(), Category[].class);
		for(var cat:categories) {
			sb.append("<li>").append(cat.category).append("</li>\n<ul>\n");
			for(var e:cat.entries) {
				sb.append("<li>").append(e.label).append("</li>\n");
			}
			sb.append("</ul>\n");
		}
		sb.append("</ul></noinclude><includeonly>{{#switch:{{{1}}}");
		for(var cat:categories) {
			for(var e:cat.entries) {
				sb
					.append("|")
					.append(e.label)
					.append("=")
					.append(Arrays.stream(e.bbox)
						.map(BigDecimal::toPlainString)
						.collect(Collectors.joining(","))
					);
			}
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

	record Category(String category, List<Result> entries) {}
	record Result(String label, BigDecimal[] bbox) {}
}
