package io.github.pfwikis.bots.usagereporter;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.client.utils.URIBuilder;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.common.collect.Multiset.Entry;

import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.utils.Jackson;
import io.github.pfwikis.bots.utils.MWTable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	public void run() throws Exception {
		topCategories();
		topResolvedCategories();
		referrer();
		topPages();
		topSearches();
		outlinks();
		
		run.getWiki().editIfChange(
			reportPage(),
			"==Last 7 days==\n"
			+"* [["+reportPage()+"/Top Pages|Top Pages]]\n"
			+"* [["+reportPage()+"/Top Search Term|Top Search Term]]\n"
			+"* [["+reportPage()+"/Top Categories|Top Categories]]\n"
			+"* [["+reportPage()+"/Top Appealing Categories|Top Appealing Categories]]\n"
			+"* [["+reportPage()+"/Outlinks|Outlinks]]\n"
			+"* [["+reportPage()+"/Referrers|Referrers]]\n",
			"Update top pages data"
		);
	}
	
	private String bot(){
		return "<noinclude>{{Bot created|%s}}</noinclude>".formatted(getBotName());
	}
	
	private void referrer() throws Exception {
		var referrers = matomo(Referrers.class,
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
		
		
		var results = matomos(MatomoPage.class,
			"method", "Referrers.getWebsites",
			"period", "range",
			"date", WEEK_RANGE
		);
		results.addAll(matomos(MatomoPage.class,
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
		run.getWiki().edit(reportPage()+"/Referrers", bot()+txt, "Update reporting pages data");
	}
	
	private void topCategories() throws Exception {
		var all = matomos(MatomoPage.class,
			"method", "Actions.getPageUrls",
			"period", "range",
			"date", WEEK_RANGE
		).get(0);
		
		var results = matomos(MatomoEvent.class,
			"method", "Events.getNameFromActionId",
			"period", "range",
			"date", WEEK_RANGE,
			"idSubtable", "1"
		);
		results.removeIf(r->"Others".equals(r.getLabel()));
		
		var txt = "This shows the percentage of page views in a category. It does not "
				+ "incorporate category hierarchies yet. That means a pageview of [[Dwarf]] "
				+ "would count for [[:Category:Dwarf]], but not [[:Category:Races]].\n"
				+ MWTable.makeTable(
				results,
				List.of(
						"Category",
						"Percentage of pageviews"
				),
				r->"[[:Category:"+r.getLabel().replace('_', ' ')+"]]",
				r->"%.1f%%".formatted(100*((double)r.getVisits())/all.getVisits())
		);
		run.getWiki().edit(reportPage()+"/Top Categories", bot()+txt, "Update reporting pages data");
	}
	
	private void topResolvedCategories() throws Exception {
		var results = matomos(MatomoEvent.class,
			"method", "Events.getNameFromActionId",
			"period", "range",
			"date", WEEK_RANGE,
			"idSubtable", "1"
		);
		results.removeIf(r->"Others".equals(r.getLabel()));
		var resolved = CategoryResolver.resolve(run.getWiki(), results);
		var sorted = resolved.entrySet().stream().sorted(Comparator.<Entry<String>, Integer>comparing(Entry::getCount).reversed()).toList();
		
		var txt = "This shows how important every category is for page views. It does "
				+ "incorporate category hierarchies. That means a pageview of [[Dwarf]] "
				+ "would count as a [[:Category:Dwarf]] and a [[:Category:Races]] view. "
				+ "A single page view can count as multiple visits of the same category, which "
				+ "is why this value is only given as an appeal score.\n"
				+ MWTable.makeTable(
				sorted,
				List.of(
						"Category",
						"Appeal"
				),
				r->"[[:"+r.getElement()+"|"+StringUtils.removeStart(r.getElement(), "Category:")+"]]",
				r->"%d".formatted(100*r.getCount()/sorted.get(0).getCount())
		);
		run.getWiki().edit(reportPage()+"/Top Appealing Categories", bot()+txt, "Update reporting pages data");
	}

	private void outlinks() throws Exception {
		var results = matomos(MatomoSearch.class,
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
		var results = matomos(MatomoPage.class,
			"method", "Actions.getPageUrls",
			"period", "range",
			"date", WEEK_RANGE,
			"idSubtable", "1"
		);
		results.removeIf(r->"Others".equals(r.getLabel()));
		
		var txt = "This shows the most visited pages, how many distinct people visited them, and how long they stayed on average.\n"
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
		var results = matomos(MatomoSearch.class,
			"method", "Actions.getSiteSearchKeywords",
			"period", "range",
			"date", WEEK_RANGE
		);
		results.removeIf(r->"Others".equals(r.getLabel()));
		results.removeIf(r->r.getVisits() < 3);
		
		
		var txt = "This shows the most searched for terms on the wiki.\n"
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

	private <T> T matomo(Class<T> type, String... args) throws Exception {
		var url = new URIBuilder("https://matomo.pathfinderwiki.com/index.php?module=API")
				.addParameter("idSite", run.getServer().getMatomoId())
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
	private <T> List<T> matomos(Class<T> type, String... args) throws Exception {
		T[] results = matomo((Class<T[]>)type.arrayType(), args);
		return new ArrayList<T>(List.of(results));
	}

}
