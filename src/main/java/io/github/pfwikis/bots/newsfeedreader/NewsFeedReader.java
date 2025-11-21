package io.github.pfwikis.bots.newsfeedreader;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.Style;
import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.utils.Retry;

@Parameters
public class NewsFeedReader extends SimpleBot {

	public NewsFeedReader() {
		super("news-feed-reader", "Bot News Feed Reader");
	}

	@Override
	public void run(RunContext ctx) throws IOException {
		String pageContent = 
			"<noinclude>{{Bot created|%s}}</noinclude>".formatted(this.getBotName())
			+collectFeed("Paizo blog", "https://paizo.com/blog?feed=rss2");
		
		run.getWiki().editIfChange("Template:News feeds", pageContent, "Automatic news feeds update");
		
	}
	
	public static Stream<Item> collectFeed(String url) {
		RssReader rssReader = new RssReader();
		return Retry.times(
			()->rssReader.read(url),
			5,
			30
		);
	}
	
	private String collectFeed(String title, String url) {
		try {
			var items = collectFeed(url)
				.filter(i->filterByWiki(run.getServer(), i))
				.limit(5)
				.map(this::renderEntry)
				.toList();
			
			return """
			<div class="content-box news-feeds">
				<div class="title">Paizo blog</div>
				<div class="content">%s</div>
			</div>
			""".formatted(String.join("", items));
		} catch(Exception e) {
			reportException(e);
			return "";
		}
	}
	
	public static boolean isTaggedRelevant(Wiki server, List<String> tags) {
		for(var tag:tags) {
			if(server == Wiki.SF && StringUtils.containsIgnoreCase(tag, "Starfinder"))
				return true;
			if(server == Wiki.PF && StringUtils.containsIgnoreCase(tag, "Pathfinder"))
				return true;
		}
		return false;
	}

	private static boolean filterByWiki(Wiki server, Item i) {
		var tags = List.<String>of();
		try {
			var doc = Jsoup.connect(i.getLink().get()).get();
			tags = getTags(doc);
		} catch(Exception e) {
			//can be safely ignored
		}
		
		if(server == Wiki.SF) {
			if(
				isTaggedRelevant(Wiki.PF, tags)
				&& !isTaggedRelevant(Wiki.SF, tags)
			) {
				return false;
			}
		}
		else {
			if(
				isTaggedRelevant(Wiki.SF, tags)
				&& !isTaggedRelevant(Wiki.PF, tags)
			) {
				return false;
			}
		}
		
		return true;
	}

	private String renderEntry(Item item) {
		try {
			return """
			<div class="news-feed-entry">
				<div class="news-feed-entry-title">[%s %s]</div>
				<div class="news-feed-entry-date">%s</div>
				<div class="news-feed-entry-preview">%s</div>
			</div>
			""".formatted(
				item.getLink().get(),
				Jsoup.parseBodyFragment(item.getTitle().get()).text(),
				renderDate(item.getPubDate().get()),
				renderPreview(item.getContent().get())
			);
		} catch(Exception e) {
			reportException(e);
			return "";
		}
	}
	
	private String renderPreview(String html) {
		try {
			var doc = Jsoup.parseBodyFragment(html);
			var text = doc.text();
			
			return Arrays.stream(text.split("\\s+"))
				.limit(100)
				.collect(Collectors.joining(" "));
		} catch(Exception e) {
			reportException(e);
			return "";
		}
	}

	private String renderDate(String dateStr) {
		var date = DateTimeFormatter.RFC_1123_DATE_TIME.parse(dateStr, OffsetDateTime::from).toLocalDate();
		var today = LocalDate.now();
		
		var display = switch((int)(today.toEpochDay()-date.toEpochDay())) {
			case 1 -> "yesterday"; 
			case 0 -> "today";
			default -> Style.DATE_FORMAT.format(date);
		};
		
		return "<time datetime=\""+dateStr+"\">"+display+"</time>";
	}

	@Override
	public String getDescription() {
		return """
		This bot is parsing the Paizo news feeds and creating the page [[Template:News feeds]] from them.
		It skips entries that are tagged only for %s.
		""".formatted(run.getServer().getName());
	}

	public static List<String> getTags(Document doc) {
		return doc.getElementsByClass("tags").stream()
				.flatMap(e->e.getElementsByTag("a").stream())
				.map(e->e.text().trim())
				.filter(StringUtils::isNotBlank)
				.toList();
	}

}
