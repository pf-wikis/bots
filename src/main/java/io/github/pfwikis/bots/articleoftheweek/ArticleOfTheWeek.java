package io.github.pfwikis.bots.articleoftheweek;

import java.io.IOException;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.beust.jcommander.Parameters;
import com.beust.jcommander.internal.Lists;

import io.github.pfwikis.bots.common.Bot;
import io.github.pfwikis.bots.common.model.ParseResponse.Content;
import io.github.pfwikis.bots.common.model.RecentChanges.RecentChange;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters(commandDescription = "Find the article with the most additions of the last week")
public class ArticleOfTheWeek extends Bot {

	public ArticleOfTheWeek() {
		super("article-of-the-week", "Bot Article of the Week");
	}

	@Override
	public void run() throws IOException {
		var article = findArticle();
		if(article == null) return;
		
		log.info("Chose article {}", article.title);
		
		String pageContent = 
			"""
			<includeonly>%s</includeonly><noinclude>
			This page was automatically created by the bot [[User:%s|]]. See its page for the criteria used.<br><br>
			{{#widget:Article of the Week}}
			[[Category:Bot created pages]]
			</noinclude>
			""".formatted(cutText(article.html), this.getBotName());
		
		run.getWiki().editIfChange("Widget:Article of the Week", pageContent, "Automatic article of the week pick.");
		
		pageContent = 
			"""
			{{Content box
			|title=This week's featured article: [[%s]]
			|content=%s}}<noinclude>
			This page was automatically created by the bot [[User:%s|]]. See its page for the criteria used.<br><br>
			
			[[Category:Bot created pages]]
			</noinclude>
			""".formatted(article.getTitle(), render(article), this.getBotName());
		
		run.getWiki().editIfChange("Template:Article of the Week", pageContent, "Automatic article of the week pick.");
	}

	private String render(Candidate article) {
		return
		"""
		<div class="article-of-the-week">
			<div class="article-of-the-week-image">
					[[File:%s|200px|link=%s]]
			</div>
			<div class="article-of-the-week-content">
				<div class="article-of-the-week-text">{{#widget:Article of the Week}}</div>
				<div class="article-of-the-week-more">[[%s|read more]]</div>
			</div>
			<div class="article-of-the-week-thanks">'''Thanks to:''' %s</div>
		</div>
		""".formatted(
			article.parsed.properties().getPage_image_free(),
			article.title,
			article.title,
			article.changes.stream()
				.map(c->"[[User:"+c.getUser()+"|]]")
				.distinct()
				.sorted(String.CASE_INSENSITIVE_ORDER)
				.collect(Collectors.joining(", "))
		);
	}

	private String cutText(Document html) {
		var raw = html.body().children().toString();
		
		//cut off sections
		raw = raw.replaceAll("(?s)<h\\d.*", "");
		html = Jsoup.parseBodyFragment(raw);
		return html.body().children().toString();
	}

	private Candidate findArticle() {
		Duration timeRange = Duration.ofDays(7);
		var options = run.getWiki().getRecentChanges(timeRange);
		var candidates = Lists.newArrayList(options.stream()
			.collect(Collectors.groupingBy(c->c.getTitle()))
			.entrySet()
			.stream()
			.map(e-> new Candidate(e.getKey(), e.getValue()))
			.toList());
		
		
		candidates.removeIf(c->!c.loadHTML(run));
		candidates.removeIf(c->!isValidPick(c));
		candidates.forEach(c->c.calculateChangeSize(run));
		candidates.sort(Comparator.comparing(Candidate::getChangeSize).reversed());
		
		if(candidates.size() == 0)
			return null;
		else
			return candidates.get(0);
	}
	
	private boolean isValidPick(Candidate cand) {
		//requires image
		if(StringUtils.isBlank(cand.parsed.properties().getPage_image_free())) {
			log.info("Excluded {}\tbecause it has no image", cand.title);
			return false;
		}
		
		//not real world
		if(cand.parsed.categories().stream().anyMatch(c->"Real-world_articles".equals(c.category()))) {
			log.info("Excluded {}\tbecause it is a real-world article", cand.title);
			return false;
		}
		
		//minimum length
		if(cand.html.text().length() < 600) {
			log.info("Excluded {}\tbecause it is too short", cand.title);
			return false;
		}
		
		//no red links
		if(cand.parsed.links().stream().anyMatch(l->!l.exists())) {
			log.info("Excluded {}\tbecause it has red links", cand.title);
			return false;
		}
		return true;
	}

	
	private static String[] REMOVED_SELECTORS = {
		"table",
		"div",
		"script",
		"input",
		"style",
		"ul.gallery",
		".mw-editsection",
		"sup.reference",
		".reference-group",
		"ol.references",
		".error",
		".nomobile",
		".noprint",
		".noexcerpt",
		".sortkey",
		"#spoilerWarning"
	};
	private static Document cleanHTML(String html) {
		var doc = Jsoup.parseBodyFragment(html);
		var output = doc.select(".mw-parser-output").first();
		doc.body().children().remove();
		output.children().forEach(doc.body()::appendChild);
		
		for(var select:REMOVED_SELECTORS) {
			doc.select(select).remove();
		}
		return doc;
	}

	@Data
	@RequiredArgsConstructor
	private static class Candidate {
		private final String title;
		private final List<RecentChange> changes;
		private long changeSize;
		private Content parsed;
		private Content beforeEditsParsed;
		private Document html;
		private Document beforeEditsHtml;
		
		public void calculateChangeSize(Run run) {
			log.info("Loading past text for {}", title);
			beforeEditsParsed = run.getWiki().getParsed(changes.stream().mapToLong(RecentChange::getOld_revid).min().getAsLong());
			
			//new page
			if(beforeEditsParsed == null) {
				changeSize = html.text().length();
			}
			else {
				beforeEditsHtml=cleanHTML(beforeEditsParsed.text());
				changeSize = Math.max(0, html.text().length()-beforeEditsHtml.text().length());
			}
		}

		public boolean loadHTML(Run run) {
			log.info("Loading text for {}", title);
			parsed = run.getWiki().getParsed(title);
			if(parsed != null) {
				html=cleanHTML(parsed.text());
				return true;
			}
			//this happens if the article has been deleted
			return false;
		}
	}

	@Override
	protected String getDescription() {
		return
			"""
			This bot is looking through the changes of the last week and
			updates [[Template:Article of the Week]] (and its helper [[Widget:Article of the Week]]) according to these criteria.
			* the article has to have a PageImage
			* the article needs to have a minimum length
			* the article needs to be in the MAIN namespace
			* the aricle must not be in [[:Category:Real-world articles]]
			* the article contains no red links
			* from the remaining articles pick the one with the most additions
			If no article is found the current article of the week is left in place.
			""";
	}

}
