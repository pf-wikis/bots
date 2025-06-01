package io.github.pfwikis.bots.usagereporter;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.nio.file.Files;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.hc.core5.net.URIBuilder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.svg.SVGGraphics2D;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.utils.Jackson;
import io.github.pfwikis.bots.utils.MWTable;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.jfree.data.time.Day;

@Slf4j
@Setter
@Parameters
public class UsageReporter extends SimpleBot {
	
	private static final String WEEK_RANGE = LocalDate.now().minusDays(8)+","+LocalDate.now().minusDays(1);
	
	@Parameter(names = "--matomoToken")
	protected String matomoToken;

	public UsageReporter() {
		super("usage-reporter", "Bot Usage Reporter");
	}
	
	@Override
	public String getDescription() {
		return "This bot is regularly querying usage information"
			+ "and creating [["+reportPage()+"|]].";
	}

	private String reportPage() {
		return run.getServer().getWikiNamespace()+":Usage Report";
	}

	@Override
	public void run(RunContext ctx) throws Exception {
		if(StringUtils.isBlank(matomoToken)) {
			throw new IllegalArgumentException("Missing matomo token");
		}
		chart();
		referrer();
		topPages();
		topSearches();
		outlinks();
		
		run.getWiki().editIfChange(
			reportPage(),
			"[[File:Unique users.svg]]\n"
			+"==Last 7 days==\n"
			+"* [["+reportPage()+"/Top Pages|Top Pages]]\n"
			+"* [["+reportPage()+"/Top Search Term|Top Search Term]]\n"
			+"* [["+reportPage()+"/Outlinks|Outlinks]]\n"
			+"* [["+reportPage()+"/Referrers|Referrers]]\n",
			"Update top pages data"
		);
	}
	
	private String bot(){
		return "<noinclude>{{Bot created|%s}}</noinclude>".formatted(getBotName());
	}
	
	private void chart() {
		try {
			List<Pair<LocalDate, Long>> data = new ArrayList<>();
			var lastMonday = LocalDate.now().minusDays(1);
			for(int i=0;i<366;i++) {
				var date = lastMonday.minusDays(i);
				var result = matomo(true, MatomoValue.class,
					"method", "VisitsSummary.getUniqueVisitors",
					"period", "day",
					"date", date.toString()
				);
				data.add(Pair.of(date, result.getValue()));
			}
				
			
			int width = 800;
		    int height = 450;
		    SVGGraphics2D svg2d = new SVGGraphics2D(width, height);
	
		    var line = new TimeSeries("Unique users");
		    for(var p:data.reversed()) {
	    		line.add(new Day(p.getKey().getDayOfMonth(), p.getKey().getMonthValue(), p.getKey().getYear()), p.getValue());
		    } 
		    JFreeChart chart = ChartFactory.createTimeSeriesChart(
		    		"Daily users",
		    		null,
		    		"daily unique users",
		    		new TimeSeriesCollection(line),
		    		false,
		    		true,
		    		false
		    );
		    chart.setBackgroundPaint(Color.white);
		    chart.getPlot().setBackgroundPaint(Color.white);
		    chart.draw(svg2d,new Rectangle2D.Double(0, 0, width, height));
	
		    String svg = svg2d.getSVGElement();
		    var f = Files.createTempFile("wiki_bots", ".svg");
		    Files.writeString(f, svg);
		    run.getWiki().upload(f, "File:Unique users.svg", "Daily usage of the wiki", "Update");
		    FileUtils.deleteQuietly(f.toFile());
		} catch(Exception e) {
			log.error("Failed to generate usage chart");
		}
	}
	
	private void referrer() throws Exception {
		var referrers = matomo(true, Referrers.class,
			"method", "Referrers.get",
			"period", "range",
			"date", WEEK_RANGE
		);
		
		var txt = MWTable.makeTable(
				List.of(
					Pair.of("Search engines", referrers.getVisitorsFromSearchEngines()),
					Pair.of("Direct entries", referrers.getVisitorsFromDirectEntry()),
					Pair.of("Other websites", referrers.getVisitorsFromWebsites()),
					Pair.of("Social networks", referrers.getVisitorsFromSocialNetworks())
					
				),
				List.of(
						"Referrer",
						"Avg Daily Visitors"
				),
				r->r.getKey(),
				r->"%.1f".formatted(r.getValue()/7d)
		);
		
		
		var results = matomos(true, MatomoPage.class,
			"method", "Referrers.getWebsites",
			"period", "range",
			"date", WEEK_RANGE
		);
		results.addAll(matomos(true, MatomoPage.class,
			"method", "Referrers.getWebsites",
			"period", "range",
			"date", WEEK_RANGE
		));
		results.removeIf(r->"Others".equals(r.getLabel()));
		results.removeIf(r->r.getSumOfDailyUniqueVisitors()<14);
		
		var summed = results.stream()
				.collect(Collectors.toMap(
					r->domain(r.getLabel()),
					r->r.getSumOfDailyUniqueVisitors(),
					(a,b)->a+b
				))
				.entrySet()
				.stream()
				.sorted(Comparator.comparing(e->-e.getValue()))
				.toList();
		
		txt += "This shows from which pages people come into the Wiki. So if they google someting and then"
				+ "click on a wikilink this counts as <code>Search Engine</code> refferal. This information often blocked"
				+ "in modern browsers, so the counts here can not be seen as total counts, but as a representative group.\n"
				+ MWTable.makeTable(
				summed,
				List.of(
						"Referrer",
						"Avg Daily Visitors"
				),
				r->"<code><nowiki>"+r.getKey()+"</nowiki></code>",
				r->"%.1f".formatted(r.getValue()/7d)
		);
		run.getWiki().edit(reportPage()+"/Referrers", bot()+"\n"+txt, "Update reporting pages data");
	}
	
	private void outlinks() throws Exception {
		var results = matomos(false, MatomoSearch.class,
				"method", "Actions.getOutlinks",
				"period", "range",
				"date", WEEK_RANGE
			);
			results.removeIf(r->"Others".equals(r.getLabel()));
			var summed = results.stream()
				.collect(Collectors.toMap(
					r->domain(r.getLabel()),
					r->r.getVisits(),
					(a,b)->a+b
				))
				.entrySet()
				.stream()
				.filter(e->e.getValue()>=7)
				.sorted(Comparator.comparing(e->-e.getValue()))
				.toList();
			
			var txt = "This shows websites people go to from the pathfinderwiki through a link on the wiki.\n"
					+ "These values are calculated daily over the last seven days.\n"
					+ "The numbers are based on voluntary tracking, so they are not absolute numbers but a representative selection.\n"
					+ MWTable.makeTable(
					summed,
					List.of(
						"Domain",
						"Avg Daily Clicks"
					),
					r->"<code>"+r.getKey()+"</code>",
					r->"%.1f".formatted(r.getValue()/7d)
			);
			run.getWiki().edit(reportPage()+"/Outlinks", bot()+txt, "Update reporting pages data");
	}

	private String domain(String label) {
		var parts = StringUtils.split(label, '.');
		if(parts.length<3) return label;
		if("github".equals(parts[parts.length-2]) && parts.length >= 3)
			return String.join(".", ArrayUtils.subarray(parts, parts.length-3, parts.length));
		else
			return String.join(".", ArrayUtils.subarray(parts, parts.length-2, parts.length));
	}

	private void topPages() throws Exception {
		var results = matomos(false, MatomoPage.class,
			"method", "Actions.getPageUrls",
			"period", "range",
			"date", WEEK_RANGE,
			"idSubtable", "1"
		);
		results.removeIf(r->"Others".equals(r.getLabel()));
		
		var txt = "This shows the most visited pages, how many distinct people visited them, and how long they stayed on average.\n"
				+"These values are calculated daily over the last seven days.\n"
				+"The numbers are based on voluntary tracking, so they are not absolute numbers but a representative selection.\n"
				+MWTable.makeTable(
				results,
				List.of(
						"Page",
						"Avg Daily Visitors",
						"Average Time"
				),
				r->"[[:"+r.getLabel().substring(1).replace('_', ' ')+"]]",
				r->"%.1f".formatted(r.getSumOfDailyUniqueVisitors()/7d),
				r->"data-sort-value=\""+r.getAverageSecondsOnPage()+"\"|"
					+Duration.ofSeconds(r.getAverageSecondsOnPage()).toString().substring(2).replaceAll("(\\d[HMS])(?!$)", "$1 ").toLowerCase()
		);
		run.getWiki().edit(reportPage()+"/Top Pages", bot()+txt, "Update reporting pages data");
	}
	
	private void topSearches() throws Exception {
		var results = matomos(true, MatomoSearch.class,
			"method", "Actions.getSiteSearchKeywords",
			"period", "range",
			"date", WEEK_RANGE
		);
		results.removeIf(r->"Others".equals(r.getLabel()));
		results.removeIf(r->r.getVisits() < 3);
		
		
		var txt = "This shows the most searched for terms on the wiki.\n"
				+ "These values are calculated daily over the last seven days.\n"
				+ "These numbers currently include partial searches!\n"
				+ MWTable.makeTable(
				results,
				List.of(
						"Search",
						"Avg Daily Searches",
						"Average Time"
				),
				r->"<code><nowiki>"+r.getLabel()+"</nowiki></code>",
				r->"%.1f".formatted(r.getVisits()/7d),
				r->"data-sort-value=\""+r.getAverageSecondsOnPage()+"\"|"
					+Duration.ofSeconds(r.getAverageSecondsOnPage()).toString().substring(2).replaceAll("(\\d[HMS])(?!$)", "$1 ").toLowerCase()
		);
		run.getWiki().edit(reportPage()+"/Top Search Term", bot()+txt, "Update reporting pages data");
	}

	private <T> T matomo(boolean logBased, Class<T> type, String... args) throws Exception {
		var url = new URIBuilder("https://matomo.pathfinderwiki.com/index.php?module=API")
				.addParameter("idSite", Integer.toString(run.getServer().getMatomoId()+(logBased?2:0)))
				.addParameter("format", "JSON")
				.addParameter("token_auth", matomoToken);
		for(int i=0;i<args.length;i+=2) {
			url.addParameter(args[i], args[i+1]);
		}
		try {
			return Jackson.JSON.readValue(url.build().toURL(), type);
		} catch(Exception e) {
			log.error("Failed to parse matomo answer:", e);
			//so we won't log matomo errors to the wiki
			throw new RuntimeException("Failed to parse matomo answer");
		}
	}

	@SuppressWarnings("unchecked")
	private <T> List<T> matomos(boolean logBased, Class<T> type, String... args) throws Exception {
		T[] results = matomo(logBased, (Class<T[]>)type.arrayType(), args);
		return new ArrayList<T>(List.of(results));
	}

}
