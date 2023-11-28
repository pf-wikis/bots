package io.github.pfwikis.bots.index.bookreader;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.text.TextPosition;

import com.google.common.primitives.Floats;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class PDFExtractor {

	private final BookIndex index;
	private final byte[] rawFile;
	
	public void extract() throws IOException {
		try (PDDocument doc = Loader.loadPDF(rawFile)) {
			index.setPageTexts(extractTexts(doc));
			index.setBookmarks(extractBookmarks(doc));
		}
	}

	private List<String> extractTexts(PDDocument doc) throws IOException {
		List<String> pageTexts = new ArrayList<>();
		var stripper = new BetterTextStripper() {
			protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
				var first = textPositions.get(0);
				var last = textPositions.get(textPositions.size()-1);
				
				float distanceFromTop = first.getY()/first.getPageHeight();
				float distanceFromBottom = 1f-first.getY()/first.getPageHeight();
				float distanceFromLeft = first.getX()/first.getPageWidth();
				float distanceFromRight = 1f-last.getX()/last.getPageWidth();
				float minDist = Floats.min(distanceFromBottom, distanceFromLeft, distanceFromRight, distanceFromTop);
				//skip texts that are too close to the border
				if(minDist < 0.015)
					return;
				super.writeString(text, textPositions);
			}
		};
		stripper.setLineSeparator("\n");
		for(int page=1;page<=doc.getNumberOfPages();page++) {
			stripper.setStartPage(page);
			stripper.setEndPage(page);
			String text = stripper.getText(doc);
			text = BookReadingJob.normalize(text);
			
			pageTexts.add(text);
		}
		return pageTexts;
	}
	
	private List<Bookmark> extractBookmarks(PDDocument doc) throws IOException {
		var outline = doc.getDocumentCatalog().getDocumentOutline();
		if(outline == null) return null;

		var rootBookmarks = new ArrayList<Bookmark>();
		var tasks = new ArrayDeque<Pair<PDOutlineItem, Bookmark>>();
		tasks.push(Pair.of(outline.getFirstChild(), null));
		while(!tasks.isEmpty()) {
			var p = tasks.poll();
			Bookmark b = new Bookmark();
			b.setName(p.getLeft().getTitle());
			b.setPage(doc.getPages().indexOf(p.getLeft().findDestinationPage(doc)));
			if(p.getRight() == null)
				rootBookmarks.add(b);
			else {
				if(p.getRight().getChildren() == null)
					p.getRight().setChildren(new ArrayList<>());
				p.getRight().getChildren().add(b);
			}
			if(p.getLeft().getNextSibling() != null)
				tasks.push(Pair.of(p.getLeft().getNextSibling(), p.getRight()));
			if(p.getLeft().getFirstChild() != null)
				tasks.push(Pair.of(p.getLeft().getFirstChild(), b));
		}
		
		return rootBookmarks;
	}
}
