package io.github.pfwikis.bots.index.librarian;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import io.github.pfwikis.bots.index.bookreader.BookIndex;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TokenizedBook {
	
	private final BookIndex book;
	private final Map<String, Token> tokens = new HashMap<>();

	public void addWord(String word, int pageNumber) {
		addWord(word, pageNumber, 1);
	}
	
	public void addWord(String word, int pageNumber, int numberOfOccurences) {
		var token = tokens.computeIfAbsent(word, k->new Token(k));
		var old = token.occurences.add(pageNumber, numberOfOccurences);
	}
	
	@Data
	@RequiredArgsConstructor
	public static class Token {
		private final String word;
		private Multiset<Integer> occurences = HashMultiset.create();
	}


	
}
