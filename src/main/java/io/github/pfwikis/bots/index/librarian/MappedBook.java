package io.github.pfwikis.bots.index.librarian;

import java.util.List;

import com.google.common.collect.Multiset;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MappedBook {
	
	private final String name;
	private final String bookId;
	private final String bookModifiedTime;
	private final boolean chapterBased;
	private final int pageOffset;
	private final List<Token> tokens;

	
	@Data
	@RequiredArgsConstructor
	public static class Token {
		private final String word;
		private final double tfidf;
		private final Multiset<Integer> occurences;
	}
	
}
