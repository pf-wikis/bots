package io.github.pfwikis.bots.index.bookreader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import org.jsoup.Jsoup;

import io.documentnode.epub4j.domain.Book;
import io.documentnode.epub4j.domain.TOCReference;
import io.documentnode.epub4j.epub.EpubReader;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class EpubExtractor {

	private final BookIndex index;
	private final byte[] rawFile;
	
	public void extract() throws IOException {
		EpubReader epubReader = new EpubReader();
		var doc = epubReader.readEpub(new ByteArrayInputStream(rawFile));
		index.setChapterBased(true);
		index.setPageTexts(extractTexts(doc));
		index.setBookmarks(extractBookmarks(doc));
	}

	private static final Set<String> PAGES_TO_SKIP = Set.of(
		"cover",
		"teaser",
		"title page",
		"pathfinder tales library",
		"copyright page",
		"dedication",
		"about the author",
		"acknowledgments",
		"glossary"
	);
	private List<String> extractTexts(Book book) throws IOException {
		var pages = new ArrayList<String>();
		for(var sr : book.getSpine().getSpineReferences()) {
			var res = sr.getResource();
			var doc = Jsoup.parse(res.getInputStream(), res.getInputEncoding(), res.getHref());
			if(PAGES_TO_SKIP.contains(doc.title().toLowerCase())) {
				pages.add("");
				continue;
			}
			String text = BookReadingJob.normalize(doc.text());
			pages.add(text);
		}
		return pages;
	}
	
	private List<Bookmark> extractBookmarks(Book book) throws IOException {
		return extractBookmarks(book, book.getTableOfContents().getTocReferences());
	}

	private List<Bookmark> extractBookmarks(Book book, List<TOCReference> tocReferences) {
		var results = new ArrayList<Bookmark>();
		for(var tocRef : tocReferences) {
			var bookmark = new Bookmark();
			bookmark.setName(tocRef.getTitle());
			int page = IntStream.range(0, book.getSpine().getSpineReferences().size())
				.filter(i->book.getSpine().getSpineReferences().get(i).getResourceId().equals(tocRef.getResourceId()))
				.findFirst().getAsInt();
			bookmark.setPage(page);
			bookmark.setEndPageExclusive(page+1);
			results.add(bookmark);
			if(tocRef.getChildren() != null && !tocRef.getChildren().isEmpty()) {
				bookmark.setChildren(extractBookmarks(book, tocRef.getChildren()));
			}
		}
		return results;
	}
}
