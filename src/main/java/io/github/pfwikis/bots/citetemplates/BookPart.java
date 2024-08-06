package io.github.pfwikis.bots.citetemplates;

import java.util.List;

import io.github.pfwikis.bots.common.WikiAPI;

public interface BookPart {
	
	List<String> getAuthors();

	default String makeAuthors(WikiAPI wiki) {
		var result = formatAuthors(wiki, getAuthors());
		if(result == null)
			return "Unknown author";
		return result;
	}

	default String formatAuthors(WikiAPI wiki, List<String> authors) {
		if(authors == null || authors.isEmpty())
			return null;
		if(authors.size() == 1) {
			return formatAuthor(wiki, authors.get(0));
		}
		if(authors.size() == 2) {
			return formatAuthor(wiki, authors.get(0))+" & "+formatAuthor(wiki, authors.get(1));
		}
		return formatAuthor(wiki, authors.get(0))+", et al";
	}

	private static String formatAuthor(WikiAPI wiki, String author) {
		return wiki.toWikiLink(author);
	}
}