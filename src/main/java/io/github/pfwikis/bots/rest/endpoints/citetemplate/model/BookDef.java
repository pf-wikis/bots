package io.github.pfwikis.bots.rest.endpoints.citetemplate.model;

import static io.github.pfwikis.bots.facts.SFactsProperties.Artist;
import static io.github.pfwikis.bots.facts.SFactsProperties.Name;
import static io.github.pfwikis.bots.facts.SFactsProperties.On_page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

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
public class BookDef extends BookPart {
	private String factsPage;
	private SemanticSubject subject;
	@Builder.Default
	private List<PageRef> authors = new ArrayList<>();
	@Builder.Default
	private List<SectionDef> sections = new ArrayList<>();
	private Integer releaseYear;
	private boolean webCitation;
	@Getter(lazy=true)
	private final RangeMap<Integer, String> authorPageRanges = makeAuthorPageRanges();
	@Getter(lazy=true)
	private final Map<String, String> authorSpecialCases = makeAuthorSpecialCases();
	@Getter(lazy=true)
	private final RangeMap<Integer, String> articlePageRanges = makeArticlePageRanges();
	@Getter(lazy=true)
	private final Map<String, String> articleSpecialCases = makeArticleSpecialCases();
	@Getter(lazy=true)
	private final String authorsString = makeAuthors();
	
	
	private RangeMap<Integer, String> makeRanges(Function<BookPart, Optional<String>> makeValue) {
		
		var bookValue = makeValue.apply(this);
		var ranges = TreeRangeMap.<Integer, String>create();
		makeValue.apply(this).ifPresent(v->ranges.put(Range.<Integer>all(), v));
		for(var sect:sections) {
			var sectValue = makeValue.apply(sect);
			var page = sect.tryParsePage();
			if(sectValue.isEmpty() || sectValue.equals(bookValue) || page == null) continue;
			if(sect.getEndPage() != null)
				ranges.put(Range.closed(page, sect.getEndPage()).canonical(DiscreteDomain.integers()), sectValue.get());
			else
				ranges.put(Range.atLeast(page).canonical(DiscreteDomain.integers()), sectValue.get());
		}
		
		for(var sect:sections) {
			var sectValue = makeValue.apply(sect);
			for(var subSect:sect.getSubSections()) {
				var subSectValue = makeValue.apply(subSect);
				var page = subSect.tryParsePage();
				if(subSectValue.isEmpty() || subSectValue.equals(bookValue) || subSectValue.equals(sectValue) || page == null) continue;
				if(subSect.getEndPage() != null)
					ranges.put(Range.closed(page, subSect.getEndPage()).canonical(DiscreteDomain.integers()), subSectValue.get());
				else
					ranges.put(Range.atLeast(page).canonical(DiscreteDomain.integers()), subSectValue.get());
			}
		}
		
		return ranges;
	}
	
	protected RangeMap<Integer, String> makeAuthorPageRanges() {
		return makeRanges(bp->Optional.of(bp.makeAuthors()));
	}

	@Override
	protected String makeAuthors() {
		var result = formatAuthors(getAuthors());
		if(result == null)
			result = formatAuthors(getOr(Artist, null));
		if(result == null)
			return "Unknown author";
		return result;
	}
	
	protected RangeMap<Integer, String> makeArticlePageRanges() {
		return makeRanges(bp-> {
			if(bp instanceof SectionDef sec) return Optional.of(sec.get(Name));
			return Optional.empty(); 
		});
	}
	
	protected Map<String, String> makeAuthorSpecialCases() {
		return makeSpecialCases(bp->Optional.of(bp.makeAuthors()));
	}
	
	protected Map<String, String> makeArticleSpecialCases() {
		return makeSpecialCases(bp-> {
			if(bp instanceof SectionDef sec) return Optional.of(sec.get(Name));
			return Optional.empty(); 
		});
	}
	
	private Map<String, String> makeSpecialCases(Function<BookPart, Optional<String>> makeValue) {
		var bookValue = makeValue.apply(this);
		var res = new HashMap<String, String>();
		for(var sect : sections) {
			if(sect.has(On_page) && sect.tryParsePage()==null && !bookValue.equals(makeValue.apply(sect))) {
				makeValue.apply(sect).ifPresent(v->res.put(sect.get(On_page), v));
			}
			
			for(var subSect : sect.getSubSections()) {
				if(subSect.has(On_page) && subSect.tryParsePage()==null && !bookValue.equals(makeValue.apply(subSect))) {
					makeValue.apply(subSect).ifPresent(v->res.put(subSect.get(On_page), v));
				}
			}
		}
		return res;
	}

	public String getName() {
		if(subject.has(Name))
			return subject.get(Name);
		return StringHelper.pageToTitle(subject.getSubject().getTitle());
	}
}
