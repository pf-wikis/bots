package io.github.pfwikis.bots.citetemplates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.citetemplates.BookDef.SectionDef;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.common.model.SemanticAsk.Labeled;
import io.github.pfwikis.bots.common.model.SemanticAsk.Ordered;
import io.github.pfwikis.bots.common.model.SemanticAsk.Printouts;
import io.github.pfwikis.bots.common.model.SemanticAsk.Result;
import io.github.pfwikis.bots.utils.MWJsonHelper;
import io.github.pfwikis.bots.utils.RockerHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class CiteTemplates extends SimpleBot {

	public CiteTemplates() {
		super("cite-templates", "Bot Cite Templates");
	}

	@Override
	public void run() throws IOException {
		var books = run.getWiki().semanticAsk("[[Fact type::"
				+"Template:Facts/Book||Template:Facts/Map||Template:Facts/Deck||Template:Facts/Video game"
				+"]]|?Name|?Represented by page|?Release year|?Primary author|?Author ordered|?Primary author ordered|?Author|?Full title|?Isbn|?Publisher");
		for(var book : books) {
			try {
				var name = book.getPrintouts().getName();
				var bookDef = BookDef.builder()
					.factsPage(StringUtils.removeStart(book.getFulltext(), "Facts:"))
					.name(name)
					.representedByPage(book.getPrintouts().getRepresentedByPage()!=null?book.getPrintouts().getRepresentedByPage().getFulltext():null)
					.authors(sortAuthors(book.getPrintouts()))
					.releaseYear("unknown".equals(book.getPrintouts().getReleaseYear())
							?null
							:Integer.parseInt(book.getPrintouts().getReleaseYear())
					)
					.fullTitle(book.getPrintouts().getFullTitle())
					.isbn(book.getPrintouts().getIsbn())
					.publisher(book.getPrintouts().getPublisher().stream().map(Result::getFulltext).toList())
					.build();
				
				var rawSections = run.getWiki().semanticAsk("[[Fact type::Template:Facts/Book/Section]][[-Has subobject::"+book.getFulltext()+"]]|?Name|?On page|?Is subsection|?Author|?Author ordered");
				bookDef.getSections().addAll(rawSections.stream()
					.map(s-> new SectionDef(
						bookDef,
						s.getPrintouts().getName(),
						s.getPrintouts().getOnPage(),
						null,
						sortAuthors(s.getPrintouts()),
						Boolean.TRUE.equals(s.getPrintouts().getIsSubsection()),
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
				
				var template = MakeCiteTemplate.template(bookDef);
				RockerHelper.make(run.getWiki(), "Template:Cite/"+bookDef.getFactsPage(), template);
			} catch(Exception e) {
				reportException(new RuntimeException("Could not generate citations for "+book.getFulltext(), e));
			}
		}
	}
	
	private List<String> sortAuthors(Printouts out) {
		List<String> result = new ArrayList<>();
		out.getPrimaryAuthorsOrdered().stream()
			.sorted()
			.map(Ordered::getPrimaryAuthor)
			.map(Labeled::getValue)
			.filter(i->i!=null)
			.map(Result::getFulltext)
			.filter(v->!result.contains(v))
			.forEachOrdered(result::add);
		
		out.getPrimaryAuthors().stream()
			.map(Result::getFulltext)
			.filter(v->!result.contains(v))
			.forEachOrdered(result::add);
		
		out.getAuthorsOrdered().stream()
			.sorted()
			.map(Ordered::getAuthor)
			.map(Labeled::getValue)
			.filter(i->i!=null)
			.map(Result::getFulltext)
			.filter(v->!result.contains(v))
			.forEachOrdered(result::add);
		
		out.getAuthors().stream()
			.map(Result::getFulltext)
			.filter(v->!result.contains(v))
			.forEachOrdered(result::add);
		
		return result;
	}

	private void calcPageRanges(List<SectionDef> sections, Integer max) {
		if(sections.isEmpty()) return;
		for(int i=0;i<sections.size()-1;i++) {
			var page = MWJsonHelper.tryParseInt(sections.get(i+1).getPage());
			var myPage = MWJsonHelper.tryParseInt(sections.get(i).getPage());
			if(page != null && myPage != null)
				sections.get(i).setEndPage(Math.max(page-1, myPage));
		}
		sections.get(sections.size()-1).setEndPage(max);
		for(var sect:sections) {
			calcPageRanges(sect.getSubSections(), sect.getEndPage());
		}
	}

	@Override
	protected String getDescription() {
		return
			"""
			This bot creates {{tl|Cite}} templates for all books with a facts page.
			""";
	}
		
}
