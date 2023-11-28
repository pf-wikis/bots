package io.github.pfwikis.bots.index.bookreader;

import java.util.List;

import lombok.Data;

@Data
public class BookIndex {
	private String name;
	private String bookId;
	private String bookModifiedTime;
	private boolean chapterBased;
	private int pageOffset;
	private List<Bookmark> bookmarks;
	private List<String> removedTexts;
	private List<String> pageTexts;
}
