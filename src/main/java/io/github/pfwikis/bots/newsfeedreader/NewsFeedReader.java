package io.github.pfwikis.bots.newsfeedreader;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.Style;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.utils.Retry;

@Parameters
public class NewsFeedReader extends SimpleBot {

	public NewsFeedReader() {
		super("news-feed-reader", "Bot News Feed Reader");
	}

	@Override
	public void run() throws IOException {
		String pageContent = 
			"""
			<noinclude>
				This page was automatically created by the bot [[User:%s|]].<br><br>
			</noinclude>
			""".formatted(this.getBotName())
			+collectFeed("Paizo blog", "https://paizo.com/community/blog&xml=atom")
			//TODO maybe use https://paizo.com/community/blog/tags/pathfinder&xml=atom
			+collectFeed("Paizo news", "https://paizo.com/paizo/press&xml=atom")
			+"""
			<noinclude>
				[[Category:Bot created pages]]
			</noinclude>
			""";
		
		run.getWiki().editIfChange("Template:News feeds", pageContent, "Automatic news feeds update");
		
	}
	
	private String collectFeed(String title, String url) {
		try {
			RssReader rssReader = new RssReader();
			var items = Retry.times(
				()->rssReader.read(url)
					.filter(i->filterByWiki(i))
					.limit(3)
					.map(this::renderEntry)
					.toList(),
				5,
				30
			);
			
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

	private boolean filterByWiki(Item entry) {
		if(run.isStarfinder()) {
			if(
				StringUtils.containsAny(entry.getDescription().get(), PF_TAGS)
				&& !StringUtils.containsAny(entry.getDescription().get(), SF_TAGS)
			) {
				return false;
			}
		}
		else {
			if(
				StringUtils.containsAny(entry.getDescription().get(), SF_TAGS)
				&& !StringUtils.containsAny(entry.getDescription().get(), PF_TAGS)
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
	protected String getDescription() {
		return """
		This bot is parsing the Paizo news feeds and creating the page [[Template:News feeds]] from them.
		It skips entries that are tagged only for %s.
		""".formatted(run.isStarfinder()?"Pathfinder":"Starfinder");
	}

}
