package io.github.pfwikis.bots.rest.endpoints.citetemplate;

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

import io.github.pfwikis.bots.common.model.subject.PageRef;
import io.github.pfwikis.bots.common.model.subject.SemanticSubject;
import io.github.pfwikis.bots.rest.RPEndpoint;
import io.github.pfwikis.bots.rest.RestProviderBot;
import io.github.pfwikis.bots.rest.endpoints.citetemplate.BookDef.SectionDef;
import io.github.pfwikis.bots.utils.RockerHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class RPCiteTemplate extends RPEndpoint<RPCiteParam> {

	public RPCiteTemplate() {
		super(RPCiteParam.class, "citetemplate");
	}
	
	private static Set<String> TYPES_WITH_CITE = Set.of(
		"Facts/Book",
		"Facts/Map",
		"Facts/Deck",
		"Facts/Video game",
		"Facts/Web citation");
	
	@Override
	public RPResult handle(RestProviderBot bot, RPCiteParam param) throws Exception {
		if(!param.validate()) {
			return error(param.getFactsPage(), "Invalid arguments");
		}
		
		BookDef bookDef = fromCacheOrCalc(bot, param);
		
		return RPResult.builder()
			.block(new RPBlock(RPBlockType.WIKITEXT, RockerHelper.makeWikitext(MakeCitation.template(bookDef, param))))
			.dependency(param.getFactsPage())
			.build();
	}	
	
	private BookDef fromCacheOrCalc(RestProviderBot bot, RPCiteParam param) {
		var subject = bot.getRun().getWiki().semanticSubject(param.getFactsPage());
		if(!subject.has(Fact_type)) {
			throw new IllegalArgumentException("Page is no facts page");
		}
		var type = subject.get(Fact_type);
		if(!TYPES_WITH_CITE.contains(type.getTitle())) {
			throw new IllegalArgumentException("Facts page is of a non-citable type");
		}
		
		BookDef bookDef = BookDef.builder()
			.factsPage(StringUtils.removeStart(param.getFactsPage(), "Facts:"))
			.subject(subject)
			.authors(sortAuthors(subject))
			.releaseYear("unknown".equals(subject.get(Release_year))
					?null
					:Integer.parseInt(subject.get(Release_year))
			)
			.webCitation(subject.get(Fact_type).toFullTitle().equals("Template:Facts/Web citation"))
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
		return bookDef;
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
}
