package io.github.pfwikis.bots.articleoftheweek;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.beust.jcommander.Parameters;
import com.beust.jcommander.internal.Lists;

import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.common.model.ParseResponse.Content;
import io.github.pfwikis.bots.common.model.RecentChanges.RecentChange;
import io.github.pfwikis.bots.common.model.SemanticAsk.Result;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class ArticleOfTheWeek extends SimpleBot {

	public ArticleOfTheWeek() {
		super("article-of-the-week", "Bot Article of the Week");
	}

	@Override
	public void run(RunContext ctx) throws IOException {
		var article = findArticle();
		if(article == null) return;
		
		log.info("Chose article {}", article.title);
		
		String pageContent = 
			"""
			<noinclude>
			{{Bot created|%s}}
			{{#widget:Article of the Week}}
			</noinclude><includeonly>%s</includeonly>
			""".formatted(this.getBotName(), cutText(article.html));
		
		run.getWiki().editIfChange("Widget:Article of the Week", pageContent, "Automatic article of the week pick.");
		
		pageContent = 
			"""
			<noinclude>{{Bot created|%s}}</noinclude>
			{{Content box
			|title=This week's featured article: [[%s]]
			|content=%s}}
			""".formatted(this.getBotName(), article.getTitle(), render(article));
		
		run.getWiki().editIfChange("Template:Article of the Week", pageContent, "Automatic article of the week pick.");
		
		addBadge(article);
	}

	private static Pattern FEATURED_PATTERN = Pattern.compile("\\| *featured *[\\}\\|]");
	private void addBadge(Candidate article) {
		var wikiText = run.getWiki().getPageText(article.getTitle());
		
		if(!FEATURED_PATTERN.matcher(wikiText.toLowerCase()).find()) {
			log.info("Did not find badge");
			var newText = wikiText.replaceAll("(\\{\\{\\s*Badges\\s*\\|)", "$1featured|");
			if(newText.equals(wikiText)) {
				newText = "{{Badges|featured}}"+newText;
			}
			run.getWiki().editIfChange(article.getTitle(), newText, "Add featured badge.");
		}
	}

	private String render(Candidate article) {
		return
		"""
		<div class="article-of-the-week">
			<div class="article-of-the-week-content">
				[[File:%s|frameless|upright=0.75|left|link=%s]]
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
		var raw = html.body().html();
		
		//cut off sections
		raw = raw.replaceAll("(?s)<h\\d.*", "");
		html = Jsoup.parseBodyFragment(raw);
		return html.body().html();
	}

	private Candidate findArticle() {
		Duration timeRange = Duration.ofDays(7);
		var options = run.getWiki().getRecentChanges(Instant.now().minus(timeRange), "0", "!minor|!bot");
		var candidates = Lists.newArrayList(options.stream()
			.collect(Collectors.groupingBy(c->c.getTitle()))
			.entrySet()
			.stream()
			.map(e-> new Candidate(e.getKey(), e.getValue()))
			.toList());
		
		var shouldNotFeatureArticles = run.getWiki().semanticAsk("[[Category:Should not be featured]][[:+]]").stream().map(Result::getPage).collect(Collectors.toSet());
		
		
		candidates.removeIf(c->!c.loadHTML(run));
		candidates.removeIf(c->!isValidPick(c, shouldNotFeatureArticles));
		candidates.forEach(c->c.calculateChangeSize(run));
		candidates.sort(Comparator.comparing(Candidate::getChangeSize).reversed());
		candidates.removeIf(c->c.getChangeSize()==0);
		
		var beforeRed = new ArrayList<>(candidates);
		candidates.removeIf(c->hasTooManyRedLinks(c));
		
		if(beforeRed.isEmpty()) {
			discord.report(this, "There was no candidate matching my requirements.");
			return null;
		}
		if(candidates.isEmpty()) {
			discord.report(this, "I have to ignoring the red link criterium to find candidates.");
			candidates = beforeRed;
		}
		var sb = new StringBuilder()
			.append("Chose ")
			.append(Discord.wikiLink(run.getServer(), candidates.get(0).getTitle(), "/wiki/"+candidates.get(0).getTitle()))
			.append("\nCandidates were:\n");
		candidates.forEach(c-> {
			sb
				.append("* ")
				.append(Discord.wikiLink(run.getServer(), c.getTitle(), "/wiki/"+c.getTitle()))
				.append(" with an effective change size of "+c.getChangeSize()+"\n");
		});
		discord.report(this, sb.toString());
		return candidates.get(0);
	}
	
	private boolean isValidPick(Candidate cand, Set<String> shouldNotFeatureArticles) {
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
		
		//not on ignore list
		if(shouldNotFeatureArticles.contains(cand.title)) {
			log.info("Excluded {}\tbecause it 'Should not be featured'", cand.title);
			return false;
		}
		
		//minimum length
		if(cand.html.text().length() < 600) {
			log.info("Excluded {}\tbecause it is too short", cand.title);
			return false;
		}
		
		return true;
	}
	
	private boolean hasTooManyRedLinks(Candidate cand) {
		//no red links
		int links = cand.parsed.links().size();
		long nonRedLinks = cand.parsed.links().stream().filter(l->l.exists()).count();
		if(((double)nonRedLinks)/links < .95) {
			log.info("Excluded {}\tbecause it has too many red links", cand.title);
			return true;
		}
		return false;
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
		
		public void calculateChangeSize(SingleRun run) {
			log.info("Loading past text for {}", title);
			
			var oldestChange = changes.stream().min(Comparator.comparing(RecentChange::getOld_revid)).get();
			
			
			//page was moved
			if(oldestChange.getNewlen()-oldestChange.getOldlen() == 0) {
				changeSize = 0;
				return;
			}
			beforeEditsParsed = run.getWiki().getParsed(oldestChange.getOld_revid());
			
			//new page
			if(beforeEditsParsed == null) {
				changeSize = html.text().length();
			}
			else {
				beforeEditsHtml=cleanHTML(beforeEditsParsed.text());
				changeSize = Math.max(0, html.text().length()-beforeEditsHtml.text().length());
			}
		}

		public boolean loadHTML(SingleRun run) {
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
	public String getDescription() {
		return
			"""
			This bot is looking through the changes of the last week and
			updates [[Template:Article of the Week]] (and its helper [[Widget:Article of the Week]]) according to these criteria.
			* the article has to have a PageImage
			* the article needs to have a minimum length
			* the article needs to be in the MAIN namespace
			* the article must not be in [[:Category:Real-world articles]]
			* the article must not be in [[:Category:Should not be featured]]
			* from the remaining articles pick the one with the most additions
			* the article contains less than 5% red links
			If no article is found this way, a second round is launched. In this round the red links criterium is ignored. If
			there is still no match, the current article is kept.
			""";
	}

}
