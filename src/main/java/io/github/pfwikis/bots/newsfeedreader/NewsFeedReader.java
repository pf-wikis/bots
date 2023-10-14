package io.github.pfwikis.bots.newsfeedreader;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.Bot;
import io.github.pfwikis.bots.common.Style;

@Parameters(commandDescription = "Reading paizo feeds and updating PathfinderWiki:News feeds")
public class NewsFeedReader extends Bot {

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
		
		run.getWiki().editIfChange("Widget:News feeds", pageContent, "Automatic news feeds update");
		
	}
	
	private String collectFeed(String title, String url) {
		try {
			RssReader rssReader = new RssReader();
			var items = rssReader.read(url)
					.limit(3)
					.map(this::renderEntry)
					.toList();
			
			return """
			<div class="content-box">
				<div class="title">%s</div>
				<div class="content">%s</div>
			</div>
			""".formatted(title, String.join("", items));
		} catch(Exception e) {
			run.getExceptions().add(e);
			return "";
		}
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
			run.getExceptions().add(e);
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
			run.getExceptions().add(e);
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
		return "This bot is parsing the Paizo news feeds and creating the page [[Widget:News feeds]] from them.";
	}

}
