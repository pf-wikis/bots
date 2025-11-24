package io.github.pfwikis.bots.facts.blog;

import java.net.URI;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.common.model.SemanticAsk.Result;
import io.github.pfwikis.bots.newsfeedreader.NewsFeedReader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class BlogFacts extends SimpleBot {

	public BlogFacts() {
		super("blog-facts", "Bot Blog Facts");
	}
	
	@Getter @Setter
	@AllArgsConstructor
	private static class Cit {
		private String id;
		private String name;
		private Result page;
		private String url;
		private String date;
		private String author;
		private Set<String> pages;
		private boolean error = false;
	}

	private static Pattern ID_PATTERN = Pattern.compile("^https://paizo.com/blog/(?<id>.+?)([\\?#].*)?$");

	@Override
	public void run(RunContext ctx) {
		var entries = NewsFeedReader.collectFeed("https://paizo.com/blog?feed=rss2")
			.limit(10)
			.toList();
		for(var entry:entries) {
			var id = entry.getLink().map(ID_PATTERN::matcher).filter(Matcher::matches).map(m->m.group("id"));
			if(id.isEmpty()) continue;
			
			var date = DateTimeFormatter.RFC_1123_DATE_TIME.parse(entry.getPubDate().get(), OffsetDateTime::from);
			String page = "Facts:Paizo blog/"+date.toLocalDate()+"-"+id.get();
			if(run.getWiki().pageExists(page)) continue;
			
			var title = Jsoup.parseBodyFragment(entry.getTitle().map(String::trim).orElse("")).text();
			String authors = "";
			boolean sync = false;
			try {
				var doc = Jsoup.connect(entry.getLink().get()).get();
				
				//get authors
				var byline = doc.getElementsByClass("byline").getFirst();
				authors = byline.getElementsByClass("name").stream()
					.map(e->e.text())
					.map(a->StringUtils.removeEnd(a.trim(), ",").trim())
					.map(a->a.contains("(")?a.substring(0, a.indexOf('(')).trim():a)
					.filter(StringUtils::isNotBlank)
					
					.collect(Collectors.joining(";"));
				
				
				//get tags
				var tags = NewsFeedReader.getTags(doc);
				if(run.getServer() == Wiki.SF) {
					// on starfinderwiki we only want to have blog posts that are only relevant for starfinder
					// the other we sync
					if(! NewsFeedReader.isTaggedRelevant(Wiki.SF, tags)
						|| NewsFeedReader.isTaggedRelevant(Wiki.PF, tags)
					) continue;
				}
				else {
					//skip SF only articles
					if(NewsFeedReader.isTaggedRelevant(Wiki.SF, tags)
						&& !NewsFeedReader.isTaggedRelevant(Wiki.PF, tags)
					) {
						continue;
					}
					//sync others
					if(!NewsFeedReader.isTaggedRelevant(Wiki.PF, tags)
						|| NewsFeedReader.isTaggedRelevant(Wiki.SF, tags)
					) {
						sync = true;
					}
				}
			} catch(Exception e) {
				log.warn("Failed to load authors for {}", entry.getLink().get(), e);
			}
			
			run.getWiki().edit(
				page,
				"""
				{{Facts/Web citation
				|Name=%s
				|Author=%s
				|Website=%s
				|Website name=Paizo blog
				|Release date=%s
				}}%s[[Category:Paizo blog articles]]
				""".formatted(
					title,
					authors,
					entry.getLink().get(),
					date.toString(),
					sync?"\n[[Category:Synced to starfinderwiki]]":""
				),
				"A new blog post was released"
			);
			run.getDiscord().reportToBlogWatch(
				this,
				"I created a facts page for "
				+ Discord.wikiLink(
					run.getServer(),
					title,
					"/wiki/"+page
				),
				true
			);
		}
	}

	@Override
	public String getDescription() {
		return
			"""
			This bot creates facts pages for blog entries.
			""";
	}
}
