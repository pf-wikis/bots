package io.github.pfwikis.bots.map;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.model.PageRef;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.utils.Jackson;

public class MapSearchPage extends SimpleBot {

	public MapSearchPage() {
		super("map-search-page", "Map Search Page");
	}

	@Override
	public void run(RunContext ctx) throws IOException, InterruptedException {
		if(run.getServer() == Wiki.SF) return;
		
		var categories = loadMapSearchInfo(antiProtectionSecret);
		createSearch(categories);
		createSearchAspect(categories);
		createArea(categories);
	}
	
	public static List<Category> loadMapSearchInfo(String antiProtectionSecret) throws IOException, InterruptedException {
		HttpClient httpClient = HttpClient.newHttpClient();
    	HttpRequest request = HttpRequest.newBuilder()
    		.header("User-Agent", antiProtectionSecret)
    		.uri(URI.create("https://map.pathfinderwiki.com/search.json")).build();
    	var resp = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());
        
		var categoriesIn = List.of(Jackson.JSON.readValue(resp.body(), CategoryIn[].class));
		var labelCounts = HashMultiset.<String>create();
		categoriesIn.forEach(cat->cat.entries.forEach(e->labelCounts.add(e.label)));
		
		return categoriesIn.stream()
				.map(c->new Category(
						c.category,
						c.entries.stream()
							.map(r->new Result(
									key(labelCounts, r.label, c.category),
									r.label,
									labelCounts.count(r.label),
									r.timed.stream()
										.sorted(Comparator.comparing(tr->tr.timeIndex.timeStart))
										.toList()
							))
							.sorted(Comparator.comparing(e->e.label))
							.toList()
				))
				.sorted(Comparator.comparing(Category::category))
				.toList();
	}
	
	private void createArea(List<Category> categories) {
		var sb = new StringBuilder()
			.append("<noinclude>{{Bot created|VirenerusBot#").append(this.getBotName()).append("}}\n")
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
		run.getWiki().editIfChange(PageRef.of(NS.TEMPLATE, "Area"), sb.toString(), "Automatic update");
		sb = new StringBuilder()
			.append("<noinclude>[[Category:Helper Template]]</noinclude><includeonly>")
			.append("{{#switch:{{{1}}}");
		for(var cat:categories) {
			for(var e:cat.entries) {
				var values = e.assemble(v-> {
					if(v.areaM2 == null) return null;
					
					var area = new BigDecimal(0.000000386102d*v.areaM2);
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
						return null;
					return area;
				});
				
				createSwitchEntry(sb, e.key, values, BigDecimal::toPlainString);
			}
		}
		sb.append("\n}}</includeonly>");
		run.getWiki().editIfChange(PageRef.of(NS.TEMPLATE, "Area/Helper"), sb.toString(), "Automatic update");
	}
	
	private void createSearchAspect(List<Category> categories) {
		var sb = new StringBuilder()
			.append("<noinclude>{{Bot created|VirenerusBot#").append(this.getBotName()).append("}}\n")
			.append("This page works like {{tl|DisplayMap/Search}}, "
				+ "but generetes the aspect ratio instead of a bbox.</noinclude><includeonly>{{#switch:{{{1}}}");
		for(var cat:categories) {
			for(var e:cat.entries) {
				var values = e.assemble(v-> {
					if(v.bbox.length!=4) return null;
					
					var left = WebMercator.longitudeToX(v.bbox[0].doubleValue());
					var right = WebMercator.longitudeToX(v.bbox[2].doubleValue());
					var bottom = WebMercator.latitudeToY(v.bbox[1].doubleValue());
					var top = WebMercator.latitudeToY(v.bbox[3].doubleValue());
					
					var width = right-left;
					var height = top-bottom;
					return new BigDecimal(width/height);
				});
				
				createSwitchEntry(sb, e.key, values, v->v.setScale(2,RoundingMode.HALF_UP).toPlainString());
			}
		}
		sb.append("|1.62}}</includeonly>");
		
		
		run.getWiki().editIfChange(PageRef.of(NS.TEMPLATE, "DisplayMap/Search aspect"), sb.toString(), "Automatic update");
	}

	private void createSearch(List<Category> categories) {
		var sb = new StringBuilder()
				.append("<noinclude>{{Bot created|VirenerusBot#").append(this.getBotName()).append("}}\n")
				.append("This page is a helper for the by name parameter.\n")
				.append("The full list of entries are:\n<ul>\n");
		
		
		for(var cat:categories) {
			sb.append("<li>").append(cat.category).append("</li>\n<ul style=\"column-width: 20rem;\">\n");
			for(var e:cat.entries) {
				sb.append("<li>").append(e.label);
				if(e.labelCount>1 || e.timedValues.size()>1) {
					sb.append("<ul>");
					if(e.labelCount>1) {
						sb.append("<li>via <code>").append(e.key).append("</code></li>");
					}
					if(e.timedValues.size()>1) {
						sb.append("<li>has different values for: ")
							.append(e.timedValues.stream().map(t->t.timeYear.toWikitext()).collect(Collectors.joining(", ")))
							.append("</li>");
					}
					sb.append("</ul>");
					
				}
				sb.append("</li>\n");
			}
			sb.append("</ul>\n");
		}
		sb.append("</ul></noinclude><includeonly>{{#switch:{{{1}}}");
		for(var cat:categories) {
			for(var e:cat.entries) {
				createSwitchEntry(
					sb,
					e.key,
					e.assemble(v->v.bbox),
					v->Arrays.stream(v).map(BigDecimal::toPlainString).collect(Collectors.joining(","))
				);
			}
		}
		sb.append("|}}</includeonly>");
		
		
		run.getWiki().editIfChange(PageRef.of(NS.TEMPLATE, "DisplayMap/Search"), sb.toString(), "Automatic update");
	}

	private static String key(Multiset<String> labelCounts, String label, String category) {
		var key = label;
		if(labelCounts.count(label)>1) {
			key=StringUtils.removeEnd(category, "s")+":"+key;
		}
		
		if(key.contains("=") || key.contains("|"))
			throw new IllegalStateException("Illegal key "+key);
		return key;
	}
	
	private <T> void createSwitchEntry(StringBuilder sb, String key, List<Assembled<T>> values, Function<T, String> toString) {
		if(values.isEmpty()) return;
		sb.append("\n|").append(key).append("=");
		if(values.size()==1 && values.getFirst().timeYear.timeStart==null && values.getFirst().timeYear.timeEnd==null) {
			sb.append(toString.apply(values.getFirst().value));
			return;
		}
		var last = values.stream().filter(v->v.timeYear.timeEnd==null).findAny().orElse(null);
		sb.append("{{#if:{{{year|}}}|");
		
		//open many if/else statements
		for(var v:values) {
			if(v==last) continue;
			sb.append("{{#ifexpr:").append(v.timeYear.toExpr()).append("|").append(toString.apply(v.value)).append("|");
		}
		if(last != null)
			sb.append(toString.apply(last.value));
		//close if/else statements
		for(var v:values) {
			if(v==last) continue;
			sb.append("}}");
		}
		
		sb.append("|");
		if(last != null)
			sb.append(toString.apply(last.value));
		sb.append("}}");
	}
	

	@Override
	public String getDescription() {
		return """
		This bot is keeping an up to date list of all named areas on golarion that are available on the map.
		It creates the page [[Template:DisplayMap/Search]] for this.
		""";
	}

	record CategoryIn(String category, List<ResultIn> entries) {}
	record ResultIn(String label, List<TimedResult> timed) {}
	record TimedResult(TimeRange timeYear, TimeRange timeIndex, BigDecimal[] bbox, Double areaM2) {}
	record TimeRange(Integer timeStart, Integer timeEnd) {
		public String toWikitext() {
			if(timeStart != null) {
				if(timeEnd != null)
					return "<code>"+timeStart+"—"+(timeEnd-1)+"</code>";
				else
					return "<code>≥"+timeStart+"</code>";
			}
			else {
				if(timeEnd != null)
					return "<code>≤"+(timeEnd-1)+"</code>";
				else
					return "<code>allways</code>";
			}
		}

		public String toExpr() {
			String expr = "";
			if(timeStart != null) {
				expr+="{{{year|}}} >= "+timeStart;
				if(timeEnd != null)
					expr+=" and ";
			}
			if(timeEnd != null)
				expr+="{{{year|}}} < "+timeEnd;
			return expr;
		}
	}
	
	record Category(String category, List<Result> entries) {}
	record Result(String key, String label, int labelCount, List<TimedResult> timedValues) {

		public <T> List<Assembled<T>> assemble(Function<TimedResult, T> mapping) {
			return timedValues.stream()
				.map(v->new Assembled<>(v.timeYear, mapping.apply(v)))
				.filter(v->v.value!=null)
				.toList();
		}
	}
	record Assembled<T>(TimeRange timeYear, T value) {}
	//record TimedResult(TimeRange timeYear, TimeRange timeIndex, BigDecimal[] bbox, Double areaM2) {}
}
