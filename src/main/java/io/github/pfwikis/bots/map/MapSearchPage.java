package io.github.pfwikis.bots.map;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

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
		var categories = Arrays.asList(Jackson.JSON.readValue(URI.create("https://map.pathfinderwiki.com/search.json").toURL(), Category[].class));
		var labelCounts = HashMultiset.<String>create();
		categories.forEach(cat->cat.entries.forEach(e->labelCounts.add(e.label)));
		
		for(var cat:categories) {
			sb.append("<li>").append(cat.category).append("</li>\n<ul style=\"column-width: 20rem;\">\n");
			for(var e:cat.entries) {
				sb.append("<li>").append(e.label);
				if(labelCounts.count(e.label)>1) {
					sb.append(" (via <code>").append(key(labelCounts, e.label, cat.category)).append("</code>)");
				}
				sb.append("</li>\n");
			}
			sb.append("</ul>\n");
		}
		sb.append("</ul></noinclude><includeonly>{{#switch:{{{1}}}");
		for(var cat:categories) {
			for(var e:cat.entries) {
				sb
					.append("|")
					.append(key(labelCounts, e.label, cat.category))
					.append("=")
					.append(Arrays.stream(e.bbox)
						.map(BigDecimal::toPlainString)
						.collect(Collectors.joining(","))
					);
			}
		}
		sb.append("|}}</includeonly>");
		
		
		run.getWiki().editIfChange("Template:DisplayMap/Search", sb.toString(), "Automatic update");
		
		
		sb = new StringBuilder();
		sb.append("<noinclude>This page works like {{tl|DisplayMap/Search}}, "
				+ "but generetes the aspect ratio instead of a bbox.</noinclude><includeonly>{{#switch:{{{1}}}");
		for(var cat:categories) {
			for(var e:cat.entries) {
				if(e.bbox.length!=4) continue;
				
				var left = WebMercator.longitudeToX(e.bbox[0].doubleValue());
				var right = WebMercator.longitudeToX(e.bbox[2].doubleValue());
				var top = WebMercator.latitudeToY(e.bbox[1].doubleValue());
				var bottom = WebMercator.latitudeToY(e.bbox[3].doubleValue());
				
				var width = right-left;
				var height = bottom-top;
				
				sb
					.append("|")
					.append(key(labelCounts, e.label, cat.category))
					.append("=")
					.append(new BigDecimal(width/height).setScale(2,RoundingMode.HALF_UP).toPlainString());
			}
		}
		sb.append("|1.62}}</includeonly>");
		
		
		run.getWiki().editIfChange("Template:DisplayMap/Search aspect", sb.toString(), "Automatic update");
		
	}
	
	private String key(Multiset<String> labelCounts, String label, String category) {
		var key = label;
		if(labelCounts.count(label)>1) {
			key+=";"+category;
		}
		
		if(key.contains("=") || key.contains("|"))
			throw new IllegalStateException("Illegal key "+key);
		return key;
	}
	

	@Override
	public String getDescription() {
		return """
		This bot is keeping an up to date list of all named areas on golarion that are available on the map.
		It creates the page [[Template:DisplayMap/Search]] for this.
		""";
	}

	record Category(String category, List<Result> entries) {}
	record Result(String label, BigDecimal[] bbox) {}
}
