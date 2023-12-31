package io.github.pfwikis.bots.index.bookreader;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.api.services.drive.model.File;

import io.github.pfwikis.bots.index.common.GDrive;
import io.github.pfwikis.bots.utils.Jackson;
import io.github.pfwikis.bots.utils.StringHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BookReadingJob implements Runnable {

	private final BookReader bot;
	private final File book;
	private final File yaml;

	@Override
	public void run() {
		try {
			if(yaml != null) {
				if(yaml.getModifiedTime().getValue() > book.getModifiedTime().getValue()) {
					log.info("{} index already up to date", book.getName());
					return;
				}
			}
			
			var index = readBook();
			String name = FilenameUtils.removeExtension(book.getName());
			
			var bytes = Jackson.YAML.writeValueAsBytes(index);
			if(yaml != null) {
				GDrive.INSTANCE.updateFile(yaml.getId(), name+".yaml", "application/yaml", bytes);
			}
			else {
				GDrive.INSTANCE.createFile(GDrive.DIR_INDICES, name+".yaml", "application/yaml", bytes);
			}
		} catch(Exception e) {
			log.error("Failed to read book {}", book.getName(), e);
			bot.reportException(e);
		}
	}

	
	private BookIndex readBook() throws Exception {
		var index = new BookIndex();
		index.setName(book.getName());
		index.setBookId(book.getId());
		index.setBookModifiedTime(book.getModifiedTime().toString());
		
		log.info("Loading "+book.getName());
		var rawFile = GDrive.INSTANCE.downloadFile(book.getId());
		
		
		if(book.getName().endsWith(".pdf")) {
			new PDFExtractor(index, rawFile).extract();
		}
		else if(book.getName().endsWith(".epub")) {
			new EpubExtractor(index, rawFile).extract();
		}
		removeLongestCommon(index);
		index.setPageOffset(findPageOffset(index));
		removePageNumbers(index);
		
		calculateBookmarkEnds(index.getBookmarks(), index.getPageTexts().size());
		removeUnwantedPages(index);
		
		return index;
	}
	
	private void removeUnwantedPages(BookIndex index) {
		if(index.getBookmarks() == null) return;
		index.getBookmarks().forEach(bm->removeUnwantedPages(index, bm));
	}
	
	private static final Set<String> UNWANTED_BOOKMARKS = Set.of(
		"cover",
		"frontcover",
		"front cover",
		"table of contents",
		"title page",
		"credits",
		"ogl",
		"open game license",
		"advertisements",
		"ads",
		"back cover",
		"rear cover"
	);
	private void removeUnwantedPages(BookIndex index, Bookmark bm) {
		if(bm == null) return;
		
		if(UNWANTED_BOOKMARKS.contains(bm.getName().toLowerCase())) {
			for(int i=bm.getPage();i<bm.getEndPageExclusive();i++) {
				index.getPageTexts().set(i, "");
			}
		}
		
		if(bm.getChildren() == null) return;
		bm.getChildren().forEach(c->removeUnwantedPages(index, c));
	}


	private void calculateBookmarkEnds(List<Bookmark> list, int max) {
		if(list == null) return;
		list.get(list.size()-1).setEndPageExclusive(max);
		for(int i=0;i<list.size()-1;i++) {
			list.get(i).setEndPageExclusive(list.get(i+1).getPage());
		}
		list.forEach(bm->calculateBookmarkEnds(bm.getChildren(), bm.getEndPageExclusive()));
	}

	private static final Pattern ALL_WHITESPACE = Pattern.compile("\\p{IsWhiteSpace}+");
	private static final Pattern PUNCTUATION = Pattern.compile("[\\p{Punct}\\p{Cntrl}]");
	private static final Pattern NON_SIMPLE = Pattern.compile(" *[^\\p{Alnum} ]+ *");
	

	public static String normalize(String text) {
		text = StringUtils.stripAccents(text);
		text = PUNCTUATION.matcher(text).replaceAll(" ");
		text = ALL_WHITESPACE.matcher(text).replaceAll(" ");
		text = NON_SIMPLE.matcher(text).replaceAll("-");
		return text.strip();
	}
	
	private int findPageOffset(BookIndex index) {
		int bestOffset = 0;
		int best = Integer.MAX_VALUE;
		for(int d=-2;d<10;d++) {
			int nonMatchingPages = 0;
			for(int p=0;p<index.getPageTexts().size();p++) {
				if(!index.getPageTexts().get(p).contains(Integer.toString(p+d))) {
					nonMatchingPages++;
				}
			}
			
			if(nonMatchingPages < best) {
				bestOffset = d;
				best = nonMatchingPages;
			}
		}
		if(best > index.getPageTexts().size()/4)
			return 0;
		else
			return bestOffset;
	}
	
	private void removePageNumbers(BookIndex index) {
		for(int i=0;i<index.getPageTexts().size();i++) {
			var res = index.getPageTexts().get(i).replaceAll("(?<![\\w\\d])"+(i+index.getPageOffset())+" +", "");
 			index.getPageTexts().set(i, res);
		}
	}
	
	private void removeLongestCommon(BookIndex index) {
		if(index.getPageTexts().size() < 16) {
			return;
		}
		
		int mid = index.getPageTexts().size()/2;
		
		String cand1 = StringHelper.findLongestCommonSubstring(
			index.getPageTexts().get(mid-6),
			index.getPageTexts().get(mid-4),
			index.getPageTexts().get(mid-2),
			index.getPageTexts().get(mid),
			index.getPageTexts().get(mid+2),
			index.getPageTexts().get(mid+4),
			index.getPageTexts().get(mid+6)
		);
		
		String cand2 = StringHelper.findLongestCommonSubstring(
			index.getPageTexts().get(mid-7),
			index.getPageTexts().get(mid-5),
			index.getPageTexts().get(mid-3),
			index.getPageTexts().get(mid-1),
			index.getPageTexts().get(mid+1),
			index.getPageTexts().get(mid+3),
			index.getPageTexts().get(mid+5)
		);
		
		String cand = cand1.length()>cand2.length()?cand1:cand2;
		
		if(cand.length() > 30) {
			index.setPageTexts(new ArrayList<>(index.getPageTexts()
				.stream()
				.map(txt->normalize(txt.replace(cand, " ")))
				.toList()));
			index.setRemovedTexts(new ArrayList<>());
			index.getRemovedTexts().add(cand);
		}
	}
}
