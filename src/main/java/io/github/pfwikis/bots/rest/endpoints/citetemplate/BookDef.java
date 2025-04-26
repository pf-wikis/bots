package io.github.pfwikis.bots.rest.endpoints.citetemplate;

import static io.github.pfwikis.bots.facts.SFactsProperties.Artist;
import static io.github.pfwikis.bots.facts.SFactsProperties.Name;
import static io.github.pfwikis.bots.facts.SFactsProperties.On_page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

import io.github.pfwikis.bots.common.model.subject.PageRef;
import io.github.pfwikis.bots.common.model.subject.SemanticSubject;
import io.github.pfwikis.bots.utils.StringHelper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDef implements BookPart {
	private String factsPage;
	private SemanticSubject subject;
	@Builder.Default
	private List<PageRef> authors = new ArrayList<>();
	@Builder.Default
	private List<SectionDef> sections = new ArrayList<>();
	private Integer releaseYear;
	private boolean webCitation;
	
	private RangeMap<Integer, String> makeRanges(Function<BookPart, String> makeValue) {
		
		var bookValue = makeValue.apply(this);
		var ranges = TreeRangeMap.<Integer, String>create();
		ranges.put(Range.<Integer>all(), makeValue.apply(this));
		for(var sect:sections) {
			var sectValue = makeValue.apply(sect);
			var page = sect.tryParsePage();
			if(sectValue == null || sectValue.equals(bookValue) || page == null) continue;
			if(sect.getEndPage() != null)
				ranges.put(Range.closed(page, sect.getEndPage()).canonical(DiscreteDomain.integers()), sectValue);
			else
				ranges.put(Range.atLeast(page).canonical(DiscreteDomain.integers()), sectValue);
		}
		
		for(var sect:sections) {
			var sectValue = makeValue.apply(sect);
			for(var subSect:sect.getSubSections()) {
				var subSectValue = makeValue.apply(subSect);
				var page = subSect.tryParsePage();
				if(subSectValue == null || subSectValue.equals(bookValue) || subSectValue.equals(sectValue) || page == null) continue;
				if(subSect.getEndPage() != null)
					ranges.put(Range.closed(page, subSect.getEndPage()).canonical(DiscreteDomain.integers()), subSectValue);
				else
					ranges.put(Range.atLeast(page).canonical(DiscreteDomain.integers()), subSectValue);
			}
		}
		
		return ranges;
	}
	
	public RangeMap<Integer, String> makeAuthorPageRanges() {
		return makeRanges(bp->bp.makeAuthors());
	}

	@Override
	public String makeAuthors() {
		var result = formatAuthors(getAuthors());
		if(result == null)
			result = formatAuthors(getOr(Artist, null));
		if(result == null)
			return "Unknown author";
		return result;
	}
	
	public RangeMap<Integer, String> makeArticlePageRanges() {
		return makeRanges(bp-> {
			if(bp instanceof SectionDef sec) return sec.get(Name);
			return ""; 
		});
	}
	
	public Map<String, String> makeAuthorSpecialCases() {
		return makeSpecialCases(bp->bp.makeAuthors());
	}
	
	public Map<String, String> makeArticleSpecialCases() {
		return makeSpecialCases(bp-> {
			if(bp instanceof SectionDef sec) return sec.get(Name);
			return ""; 
		});
	}
	
	private Map<String, String> makeSpecialCases(Function<BookPart, String> makeValue) {
		var bookValue = makeValue.apply(this);
		var res = new HashMap<String, String>();
		for(var sect : sections) {
			if(sect.has(On_page) && sect.tryParsePage()==null && !bookValue.equals(makeValue.apply(sect))) {
				res.put(sect.get(On_page), makeValue.apply(sect));
			}
			
			for(var subSect : sect.getSubSections()) {
				if(subSect.has(On_page) && subSect.tryParsePage()==null && !bookValue.equals(makeValue.apply(subSect))) {
					res.put(subSect.get(On_page), makeValue.apply(subSect));
				}
			}
		}
		return res;
	}

	@Setter
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SectionDef implements BookPart {
		private BookPart parent;
		private SemanticSubject subject;
		private Integer endPage;
		private List<PageRef> authors = new ArrayList<>();
		private boolean isSubsection;
		private List<SectionDef> subSections = new ArrayList<>();
		
		@Override
		public String makeAuthors() {
			if(authors != null && !authors.isEmpty())
				return BookPart.super.makeAuthors();
			return parent.makeAuthors();
		}
	}

	public String getName() {
		if(subject.has(Name))
			return subject.get(Name);
		return StringHelper.pageToTitle(subject.getSubject().getTitle());
	}
}
