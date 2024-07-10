package io.github.pfwikis.bots.citetemplates;

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
import com.google.common.collect.TreeRangeMap;

import io.github.pfwikis.bots.utils.MWJsonHelper;
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
	private String name;
	private String representedByPage;
	@Builder.Default
	private List<String> authors = new ArrayList<>();
	@Builder.Default
	private List<String> artists = new ArrayList<>();
	@Builder.Default
	private List<SectionDef> sections = new ArrayList<>();
	@Builder.Default
	private List<String> publisher = new ArrayList<>();
	private Integer releaseYear;
	private String fullTitle;
	private String isbn;
	
	private Map<String, List<Range<Integer>>> makeRanges(Function<BookPart, String> makeValue) {
		var bookValue = makeValue.apply(this);
		var ranges = TreeRangeMap.<Integer, String>create();
		ranges.put(Range.<Integer>all(), makeValue.apply(this));
		for(var sect:sections) {
			var sectValue = makeValue.apply(sect);
			var page = MWJsonHelper.tryParseInt(sect.getPage());
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
				var page = MWJsonHelper.tryParseInt(subSect.getPage());
				if(subSectValue == null || subSectValue.equals(bookValue) || subSectValue.equals(sectValue) || page == null) continue;
				if(subSect.getEndPage() != null)
					ranges.put(Range.closed(page, subSect.getEndPage()).canonical(DiscreteDomain.integers()), subSectValue);
				else
					ranges.put(Range.atLeast(page).canonical(DiscreteDomain.integers()), subSectValue);
			}
		}
		
		var result = ranges.asMapOfRanges().entrySet()
			.stream()
			.collect(
				Collectors.groupingBy(
					e->e.getValue(),
					Collectors.mapping(e->e.getKey(), Collectors.toList())
				)
			);
		//sort just to make the templates easier to read
		result.values().forEach(e->Collections.sort(e, RANGE_COMP));
		var sortedResult = new LinkedHashMap<String, List<Range<Integer>>>();
		result.entrySet()
			.stream()
			.sorted(Comparator.<Entry<String, List<Range<Integer>>>,Range<Integer>>comparing(e->e.getValue().get(0), RANGE_COMP))
			.forEachOrdered(e->sortedResult.put(e.getKey(), e.getValue()));
		return sortedResult;
	}
	
	private static final Comparator<Range<Integer>> RANGE_COMP = Comparator.<Range<Integer>, Boolean>comparing(Range::hasLowerBound).thenComparing(Range::lowerEndpoint);

	public Map<String, List<Range<Integer>>> makeAuthorPageRanges() {
		return makeRanges(BookPart::makeAuthors);
	}

	@Override
	public String makeAuthors() {
		var result = formatAuthors(getAuthors());
		if(result == null)
			result = formatAuthors(getArtists());
		if(result == null)
			return "Unknown author";
		return result;
	}
	
	public Map<String, List<Range<Integer>>> makeArticlePageRanges() {
		return makeRanges(bp-> {
			if(bp instanceof SectionDef sec) return sec.getName();
			return ""; 
		});
	}
	
	public Map<String, String> makeAuthorSpecialCases() {
		return makeSpecialCases(BookPart::makeAuthors);
	}
	
	public Map<String, String> makeArticleSpecialCases() {
		return makeSpecialCases(bp-> {
			if(bp instanceof SectionDef sec) return sec.getName();
			return ""; 
		});
	}
	
	private Map<String, String> makeSpecialCases(Function<BookPart, String> makeValue) {
		var bookValue = makeValue.apply(this);
		var res = new HashMap<String, String>();
		for(var sect : sections) {
			if(sect.getPage()!=null && MWJsonHelper.tryParseInt(sect.getPage())==null && !bookValue.equals(makeValue.apply(sect))) {
				res.put(sect.getPage(), makeValue.apply(sect));
			}
			
			for(var subSect : sect.getSubSections()) {
				if(subSect.getPage()!=null && MWJsonHelper.tryParseInt(subSect.getPage())==null && !bookValue.equals(makeValue.apply(subSect))) {
					res.put(subSect.getPage(), makeValue.apply(subSect));
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
		private String name;
		private String page;
		private String toPage;
		private Integer endPage;
		private List<String> authors = new ArrayList<>();
		private boolean isSubsection;
		private List<SectionDef> subSections = new ArrayList<>();
		
		@Override
		public String makeAuthors() {
			if(authors != null && !authors.isEmpty())
				return BookPart.super.makeAuthors();
			return parent.makeAuthors();
		}
	}
}
