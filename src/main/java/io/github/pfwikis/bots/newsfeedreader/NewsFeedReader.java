package io.github.pfwikis.bots.newsfeedreader;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

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
			+collectFeed("Paizo blog", "https://paizo.com/community/blog&xml=atom")
			//TODO maybe use https://paizo.com/community/blog/tags/pathfinder&xml=atom
			+collectFeed("Paizo news", "https://paizo.com/paizo/press&xml=atom");
		
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
				.limit(3)
				.map(this::renderEntry)
				.toList();
			
			return """
			<div class="content-box news-feeds">
				<div class="title">%s</div>
				<div class="content">%s</div>
			</div>
			""".formatted(title, String.join("", items));
		} catch(Exception e) {
			reportException(e);
			return "";
		}
	}
	
	private final static String[] PF_TAGS = {
		"<a href=\"https://paizo.com/community/blog/tags/pathfinder\">Pathfinder</a>",
		"<a href=\"https://paizo.com/community/blog/tags/pathfinderRoleplayingGame\">Pathfinder Roleplaying Game</a>"
	};
	
	private final static String[] SF_TAGS = {
		"<a href=\"https://paizo.com/community/blog/tags/starfinder\">Starfinder</a>",
		"<a href=\"https://paizo.com/community/blog/tags/starfinderRoleplayingGame\">Starfinder Roleplaying Game</a>"
	};
	
	public static boolean isTaggedRelevant(Wiki server, Item entry) {
		if(server == Wiki.SF) {
			return StringUtils.containsAny(entry.getDescription().get(), SF_TAGS);
		}
		else
			return StringUtils.containsAny(entry.getDescription().get(), PF_TAGS);
	}

	private static boolean filterByWiki(Wiki server, Item entry) {
		if(server == Wiki.SF) {
			if(
				isTaggedRelevant(Wiki.PF, entry)
				&& !isTaggedRelevant(Wiki.SF, entry)
			) {
				return false;
			}
		}
		else {
			if(
				isTaggedRelevant(Wiki.SF, entry)
				&& !isTaggedRelevant(Wiki.PF, entry)
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
				item.getTitle().get(),
				renderDate(item.getPubDate().get()),
				renderPreview(item.getDescription().get())
			);
		} catch(Exception e) {
			reportException(e);
			return "";
		}
	}
	
	private String renderPreview(String html) {
		try {
			var doc = Jsoup.parseBodyFragment(html);
			var articleBody = doc
				.getElementsByAttributeValue("itemprop", "articleBody");
			String text;
			if(articleBody.isEmpty()) {
				text = doc.text();
			}
			else {
				text = articleBody.getFirst().text();
			}
			
			return Arrays.stream(text.split("\\s+"))
				.limit(100)
				.collect(Collectors.joining(" "));
		} catch(Exception e) {
			reportException(e);
			return "";
		}
	}

	private String renderDate(String dateStr) {
		var date = OffsetDateTime.parse(dateStr).toLocalDate();
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

}
