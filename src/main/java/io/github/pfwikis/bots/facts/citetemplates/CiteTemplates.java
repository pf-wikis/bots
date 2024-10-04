package io.github.pfwikis.bots.facts.citetemplates;

import static io.github.pfwikis.bots.facts.SFactsProperties.Author;
import static io.github.pfwikis.bots.facts.SFactsProperties.Author_all;
import static io.github.pfwikis.bots.facts.SFactsProperties.Fact_type;
import static io.github.pfwikis.bots.facts.SFactsProperties.Is_subsection;
import static io.github.pfwikis.bots.facts.SFactsProperties.On_page;
import static io.github.pfwikis.bots.facts.SFactsProperties.Primary_author;
import static io.github.pfwikis.bots.facts.SFactsProperties.Release_year;
import static io.github.pfwikis.bots.facts.SFactsProperties.To_page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.RunOnPageBot;
import io.github.pfwikis.bots.common.bots.ScatteredRunnableBot;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.common.model.subject.PageRef;
import io.github.pfwikis.bots.common.model.subject.SemanticSubject;
import io.github.pfwikis.bots.facts.citetemplates.BookDef.SectionDef;
import io.github.pfwikis.bots.utils.RockerHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class CiteTemplates extends SimpleBot implements RunOnPageBot, ScatteredRunnableBot<CiteTemplates.Shard> {

	public CiteTemplates() {
		super("cite-templates", "Bot Cite Templates");
	}
	
	@Override
	public void run(RunContext ctx) {
		if(ctx.getScatterShard() instanceof Shard shard)
			runOnShard(shard);
		else if(ctx.getPage() != null) {
			runOnPage(ctx.getPage());
		}
		else {
			for(var s:createScatterShards()) {
				if(s.page.compareTo("Facts:Giant Hunter's Handbook")<0)
					continue;
				try {
					runOnPage(s.page);
				} catch(Exception e) {
					log.error("Failed to process page {}", s.page, e);
				}
			}
		}
	}
	
	private void runOnShard(Shard shard) {
		if(shard.first()) {
			run.getWiki().editIfChange("Template:Cite", """
				{{Bot created|Bot Cite Templates}}
				<noinclude>
				{{Documentation}}
				[[Category:Citation templates]]
				</noinclude><includeonly><span class="error mw-ext-cite-error">Cite error: {{tl|Cite}} should not be used directly.[[Category:Pages with errors]]</span></includeonly>
				""", "Update from bot update.");
			}
				
			runOnPage(shard.page());
	}
	
	private static Set<String> TYPES_WITH_CITE = Set.of(
		"Facts/Book",
		"Facts/Map",
		"Facts/Deck",
		"Facts/Video game");
	private void runOnPage(String page) {
		var subject = run.getWiki().semanticSubject(page);
		if(!subject.has(Fact_type)) {
			log.error("{} has no fact type", page);
			return;
		}
		var type = subject.get(Fact_type);
		
		if(!TYPES_WITH_CITE.contains(type.getTitle())) return;
		
		var bookDef = BookDef.builder()
			.factsPage(StringUtils.removeStart(page, "Facts:"))
			.subject(subject)
			.authors(sortAuthors(subject))
			.releaseYear("unknown".equals(subject.get(Release_year))
					?null
					:Integer.parseInt(subject.get(Release_year))
			)
			.build();
		
		var rawSections = subject.getSubObjects("Facts/Book/Section");
		bookDef.getSections().addAll(rawSections.stream()
			.map(s-> new SectionDef(
				bookDef,
				s,
				null,
				sortAuthors(s),
				Boolean.TRUE.equals(s.getOr(Is_subsection, Boolean.FALSE)),
				Collections.emptyList()
			))
			.toList());
		
		for(int i=1;i<bookDef.getSections().size();i++) {
			if(bookDef.getSections().get(i).isSubsection()) {
				var prev = bookDef.getSections().get(i-1);
				var sect = bookDef.getSections().remove(i);
				sect.setParent(prev);
				prev.setSubSections(new ArrayList<>(prev.getSubSections()));
				prev.getSubSections().add(sect);
				i--;
			}
		}
		calcPageRanges(bookDef.getSections(), null);
		
		var template = MakeCiteTemplate.template(run.getWiki(), bookDef);
		RockerHelper.make(run.getWiki(), "Template:Cite/"+bookDef.getFactsPage(), template);
	}
	
	private List<PageRef> sortAuthors(SemanticSubject subject) {
		List<PageRef> result = new ArrayList<>();
		subject.getOr(Primary_author, Collections.emptyList()).stream()
			.filter(v->!result.contains(v))
			.forEachOrdered(result::add);
		
		subject.getOr(Author, Collections.emptyList()).stream()
			.filter(v->!result.contains(v))
			.forEachOrdered(result::add);

		subject.getOr(Author_all, Collections.emptyList()).stream()
			.sorted(Comparator.comparing(PageRef::getTitle))
			.filter(v->!result.contains(v))
			.forEachOrdered(result::add);
		
		return result;
	}

	private void calcPageRanges(List<SectionDef> sections, Integer max) {
		if(sections.isEmpty()) return;
		
		//first check if we have a manual end page
		for(var sect:sections) {
			sect.setEndPage(sect.getOr(To_page, null));
		}
		
		//otherwise we assume we end before the next chapter
		for(int i=0;i<sections.size()-1;i++) {
			var self = sections.get(i);
			var next = sections.get(i+1);
			var nextStartPage = next.tryParsePage();
			var myStartPage = self.tryParsePage();
			if(nextStartPage != null && myStartPage != null && self.getEndPage() == null)
				self.setEndPage(Math.max(nextStartPage-1, myStartPage));
		}
		
		var last = sections.getLast();
		if(max != null && last.getEndPage() != null) {
			if(last.getOr(On_page, null) != null)
				last.setEndPage(Math.max(last.tryParsePage(), max));
			else
				last.setEndPage(max);
		}
		
		//then do subsections
		for(var sect:sections) {
			calcPageRanges(sect.getSubSections(), sect.getEndPage());
		}
	}

	@Override
	public String getDescription() {
		return
			"""
			This bot creates {{tl|Cite}} templates for all books with a facts page.
			""";
	}
	
	public static record Shard(String page, boolean first) {}
	@Override
	public List<Shard> createScatterShards() {
		var shards = new ArrayList<>(run.getWiki().semanticAsk(
			"[[Fact type::"
				+ "Template:Facts/Book"
				+ "||Template:Facts/Map"
				+ "||Template:Facts/Deck"
				+ "||Template:Facts/Video game"
			+ "]]")
			.stream()
			.map(p->p.getPage())
			.filter(p->!p.endsWith("/Releases"))
			.filter(p->!p.endsWith("/Sections"))
			.map(p->new Shard(p, false))
			.toList());
		shards.set(0, new Shard(shards.get(0).page(), true));
		return shards;
	}	
}
