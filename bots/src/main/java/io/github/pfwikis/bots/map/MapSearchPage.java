package io.github.pfwikis.bots.map;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Chars;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.api.generated.params.AAPIEditContentmodel;
import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.model.PageRef;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.utils.Hash32;
import io.github.pfwikis.bots.utils.Jackson;
import tools.jackson.databind.JsonNode;

public class MapSearchPage extends SimpleBot {

	public MapSearchPage() {
		super("map-search-page", "Map Search Page");
	}
	
	private static record WikiJson(
			String name,
			@JsonProperty("year start")Integer yearStart,
			@JsonProperty("year end")Integer yearEnd,
			BigDecimal area,
			@JsonProperty("aspect ratio")BigDecimal aspectRatio,
			String bbox
			) implements Comparable<WikiJson> {
		
		@Override
		public int compareTo(WikiJson o) {
			return Comparator.comparing(WikiJson::name)
					.thenComparing(WikiJson::yearStart)
					.compare(this, o);
		}
	}

	@Override
	public void run(RunContext ctx) throws IOException, InterruptedException {
		if(run.getServer() == Wiki.SF) return;
		
		var categories = loadMapSearchInfo(antiProtectionSecret);
		createSearchDocs(categories);
		var result = new ArrayList<WikiJson>();
		
		for(var cat:categories) {
			for(var e:cat.entries) {
				for(var t:e.timedValues) {
					var r = new WikiJson(
							e.key,
							Optional.ofNullable(t.timeYear.timeStart).orElse(-9999),
							Optional.ofNullable(t.timeYear.timeEnd).orElse(9999),
							getArea(t),
							getAspect(t),
							Arrays.stream(t.bbox).map(BigDecimal::toPlainString).collect(Collectors.joining(","))
					);
					result.add(r);
				}
			}
		}
		
		var perHash=result.stream()
			.collect(Collectors.groupingBy(e->Hash32.hash32(e.name, 1)));
		for(var e:perHash.entrySet()) {
			var arr = Jackson.JSON.createArrayNode();
			e.getValue().stream()
				.sorted()
				.<JsonNode>map(Jackson.JSON::valueToTree)
				.forEach(arr::add);
			var page = PageRef.of(NS.FACTS, "Map/"+e.getKey());
			run.getWiki().editJsonIfChange(page, arr, "Automatic Update", AAPIEditContentmodel.JSONDATA);
		}
	}
	
	private BigDecimal getArea(TimedResult t) {
		if(t.areaM2 == null) return null;
		
		var area = new BigDecimal(0.000000386102d*t.areaM2);
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
	}
	
	private BigDecimal getAspect(TimedResult t) {
		if(t.bbox.length!=4) return null;
		
		var left = WebMercator.longitudeToX(t.bbox[0].doubleValue());
		var right = WebMercator.longitudeToX(t.bbox[2].doubleValue());
		var bottom = WebMercator.latitudeToY(t.bbox[1].doubleValue());
		var top = WebMercator.latitudeToY(t.bbox[3].doubleValue());
		
		var width = right-left;
		var height = top-bottom;
		return new BigDecimal(width/height).setScale(2,RoundingMode.HALF_UP);
	}

	public static List<Category> loadMapSearchInfo(String antiProtectionSecret) throws IOException, InterruptedException {
		HttpClient httpClient = HttpClient.newHttpClient();
    	HttpRequest request = HttpRequest.newBuilder()
    		.header("User-Agent", antiProtectionSecret)
    		.uri(URI.create("https://map.pathfinderwiki.com/latest/search.json")).build();
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

	private void createSearchDocs(List<Category> categories) {
		//create free text
		var sb = new StringBuilder()
				.append("{{Bot created|VirenerusBot#").append(this.getBotName()).append("}}\n")
				.append("[[Category:Facts owned by bots]]")
				.append("This page collectrs facts about the [[Map]]. Those facts can easily be accessed via {{tl|Map fact}}.\n")
				.append("The actual facts are stored in")
				.append(Chars.asList(Hash32.ALPHABET).stream().map(v->"[[/"+v+"]]").collect(Collectors.joining(", ")))
				.append(".\n<ul>\n");
		
		
		for(var cat:categories) {
			sb.append("<li>").append(cat.category).append("</li>\n<ul style=\"column-width: 20rem;\">\n");
			for(var e:cat.entries) {
				sb.append("<li>").append(e.label);
				if(e.labelCount>1 || e.timedValues.size()>1 || !e.timedValues.getFirst().timeYear().isAlways()) {
					sb.append("<ul>");
					if(e.labelCount>1) {
						sb.append("<li>via <code>").append(e.key).append("</code></li>");
					}
					if(e.timedValues.size()>1 || !e.timedValues.getFirst().timeYear().isAlways()) {
						sb.append("<li>has different values for: ")
							.append(e.timedValues.stream().map(t->t.timeYear.toWikitext()).collect(Collectors.joining(", ")))
							.append("</li>");
					}
					else if(!e.timedValues.getFirst().timeYear().isAlways()) {
						sb.append("<li>has only values for: ")
							.append(e.timedValues.stream().map(t->t.timeYear.toWikitext()).collect(Collectors.joining(", ")))
							.append("</li>");
					}
					sb.append("</ul>");
					
				}
				sb.append("</li>\n");
			}
			sb.append("</ul>\n");
		}
		sb.append("</ul>");
		run.getWiki().editIfChange(PageRef.of(NS.FACTS, "Map"), sb.toString(), "Automatic update");
	}

	private static String key(Multiset<String> labelCounts, String label, String category) {
		var key = label;
		if(labelCounts.count(label)>1) {
			key=StringUtils.capitalize(StringUtils.removeEnd(category, "s"))+":"+key;
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

		public boolean isAlways() {
			return timeStart == null && timeEnd == null;
		}

		public String toExpr() {
			String expr = "";
			if(timeStart != null) {
				expr+="{{{year}}} >= "+timeStart;
				if(timeEnd != null)
					expr+=" and ";
			}
			if(timeEnd != null)
				expr+="{{{year}}} < "+timeEnd;
			return expr;
		}
	}
	
	record Category(String category, List<Result> entries) {}
	record Result(String key, String label, int labelCount, List<TimedResult> timedValues) {}
}
