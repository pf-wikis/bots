package io.github.pfwikis.bots.facts.blog;

import java.util.List;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class BlogFacts extends SimpleBot {

	public BlogFacts() {
		super("blog-facts", "Bot Blog Facts");
	}
	
	private static record Cit(String id, String name, String page, String url, String date, String author, List<String> pages) {}
	
	@Override
	public void run(RunContext ctx) {
		var citations = run.getWiki().semanticAsk("[[Type::Template:Cite web]]"
				+ "|?Web author"
				+ "|?Web date"
				+ "|?Web url"
				+ "|?Name"
				+ "|?Page"
		).stream()
			.map(c->new Cit(
				null,
				c.getPrintouts().getName(),
				c.getPrintouts().getPage(),
				c.getPrintouts().getWebUrl().trim(),
				c.getPrintouts().getWebDate(),
				c.getPrintouts().getWebAuthor(),
				List.of(c.getFullurl())
			))
			.toList();
		
		for(var cit:citations) {
			log.info(cit.url);
		}
		
		log.info("a");
	}
	
	@Override
	public String getDescription() {
		return
			"""
			This bot creates facts pages for blog entries.
			""";
	}
}
