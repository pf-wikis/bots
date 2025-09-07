package io.github.pfwikis.bots.map;

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
		
		var categoriesIn = Arrays.asList(Jackson.JSON.readValue(URI.create("https://map.pathfinderwiki.com/search.json").toURL(), CategoryIn[].class));
		var labelCounts = HashMultiset.<String>create();
		categoriesIn.forEach(cat->cat.entries.forEach(e->labelCounts.add(e.label)));
		
		var categories = categoriesIn.stream()
				.map(c->new Category(
						c.category,
						c.entries.stream()
							.map(r->new Result(
									key(labelCounts, r.label, c.category),
									r.label,
									labelCounts.count(r.label),
									r.bbox,
									r.areaM2
							))
							.toList()
				))
				.toList();
		
		
		createSearch(categories);
		createSearchAspect(categories);
		createArea(categories);
	}
	
	private void createArea(List<Category> categories) {
		var sb = new StringBuilder()
			.append("<noinclude>{{Bot created|").append(this.getBotName()).append("}}\n")
			.append("""
				{{Documentation|content=
				<wikitext doc>{{Area|Ustalav}}</wikitext>
				<templatedata>
				{
					"params": {
						"1": {
							"label": "area",
							"description": "Nation or geographical feature",
							"type": "string",
							"required": true
						}
					},
					"format": "inline"
				}
				</templatedata>
				}}[[Category:Templates]]
				</noinclude><includeonly>{{#if:{{Area/Helper|{{{1}}}}}|{{formatnum:{{Area/Helper|{{{1}}}}}}} mi²}}{{Validate parameters}}</includeonly>
				""");
		run.getWiki().editIfChange("Template:Area", sb.toString(), "Automatic update");
		sb = new StringBuilder()
			.append("<noinclude>[[Category:Helper Template]]</noinclude><includeonly>")
			.append("{{#switch:{{{1}}}");
		for(var cat:categories) {
			for(var e:cat.entries) {
				if(e.areaM2 == null) continue;
				
				var area = new BigDecimal(0.000000386102d*e.areaM2);
				if(area.doubleValue()>100) {
					area = area.setScale(0,RoundingMode.HALF_UP);
				}
				else if(area.doubleValue()>10) {
					area = area.setScale(1,RoundingMode.HALF_UP);
				}
				else {
					area = area.setScale(2,RoundingMode.HALF_UP);
				}
				if(area.compareTo(BigDecimal.ZERO) == 0)
					continue;
				
				sb
					.append("\n|")
					.append(e.key)
					.append("=")
					.append(area.toPlainString());
			}
		}
		sb.append("\n}}</includeonly>");
		run.getWiki().editIfChange("Template:Area/Helper", sb.toString(), "Automatic update");
	}
	
	private void createSearchAspect(List<Category> categories) {
		var sb = new StringBuilder()
			.append("<noinclude>{{Bot created|").append(this.getBotName()).append("}}\n")
			.append("This page works like {{tl|DisplayMap/Search}}, "
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
					.append(e.key)
					.append("=")
					.append(new BigDecimal(width/height).setScale(2,RoundingMode.HALF_UP).toPlainString());
			}
		}
		sb.append("|1.62}}</includeonly>");
		
		
		run.getWiki().editIfChange("Template:DisplayMap/Search aspect", sb.toString(), "Automatic update");
	}

	private void createSearch(List<Category> categories) {
		var sb = new StringBuilder()
				.append("<noinclude>{{Bot created|").append(this.getBotName()).append("}}\n")
				.append("This page is a helper for the by name parameter.\n")
				.append("The full list of entries are:\n<ul>\n");
		
		
		for(var cat:categories) {
			sb.append("<li>").append(cat.category).append("</li>\n<ul style=\"column-width: 20rem;\">\n");
			for(var e:cat.entries) {
				sb.append("<li>").append(e.label);
				if(e.labelCount>1) {
					sb.append(" (via <code>").append(e.key).append("</code>)");
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
					.append(e.key)
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

	record CategoryIn(String category, List<ResultIn> entries) {}
	record ResultIn(String label, BigDecimal[] bbox, Double areaM2) {}
	
	record Category(String category, List<Result> entries) {}
	record Result(String key, String label, int labelCount, BigDecimal[] bbox, Double areaM2) {}
}
