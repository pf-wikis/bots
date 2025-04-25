package io.github.pfwikis.bots.facts.blog;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	private static Pattern ID_PATTERN = Pattern.compile("^https?://(www\\.)?paizo.com/(community|paizo)/blog/(?<id>\\w+?)([\\?#].*)?$");

	@Override
	public void run(RunContext ctx) {
		var entries = NewsFeedReader.collectFeed("https://paizo.com/community/blog&xml=atom")
			.limit(5)
			.toList();
		for(var entry:entries) {
			var id = entry.getLink().map(ID_PATTERN::matcher).filter(Matcher::matches).map(m->m.group("id"));
			if(id.isEmpty()) continue;
			
			String page = "Facts:Paizo blog/"+id.get().toLowerCase();
			if(run.getWiki().pageExists(page)) continue;
			
			boolean sync = false;
			if(run.getServer() == Wiki.SF) {
				// on starfinderwiki we only want to have blog posts that are only relevant for starfinder
				// the other we sync
				if(! NewsFeedReader.isTaggedRelevant(Wiki.SF, entry)
					|| NewsFeedReader.isTaggedRelevant(Wiki.PF, entry)
				) continue;
			}
			else {
				//skip SF only articles
				if(NewsFeedReader.isTaggedRelevant(Wiki.SF, entry)
					&& !NewsFeedReader.isTaggedRelevant(Wiki.PF, entry)
				) {
					continue;
				}
				//sync others
				if(!NewsFeedReader.isTaggedRelevant(Wiki.PF, entry)
					|| NewsFeedReader.isTaggedRelevant(Wiki.SF, entry)
				) {
					sync = true;
				}
			}
			
			run.getWiki().edit(
				page,
				"""
				{{Facts/Web citation
				|Name=%s
				|Author=
				|Website=https://paizo.com/community/blog/%s
				|Website name=Paizo blog
				|Release date=%s
				}}%s
				""".formatted(
					entry.getTitle().map(String::trim).orElse(""),
					id.get().toLowerCase(),
					OffsetDateTime.parse(entry.getPubDate().get()).toLocalDate().toString(),
					sync?"\n[[Category:Synced to starfinderwiki]]":""
				),
				"A new blog post was released"
			);
			run.getDiscord().reportToBlogWatch(
				this,
				"I created a facts page for "
				+ Discord.wikiLink(
					run.getServer(),
					entry.getTitle().map(String::trim).orElse(id.get()),
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
