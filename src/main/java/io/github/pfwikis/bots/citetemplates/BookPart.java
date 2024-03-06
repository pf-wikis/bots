package io.github.pfwikis.bots.citetemplates;

import java.util.List;

public interface BookPart {
	
	List<String> getAuthors();

	default String makeAuthors() {
		var authors = getAuthors();
		if(authors == null || authors.isEmpty())
			return "Unknown author";
		if(authors.size() == 1) {
			return makeAuthor(authors.get(0));
		}
		if(authors.size() == 2) {
			return makeAuthor(authors.get(0))+" & "+makeAuthor(authors.get(1));
		}
		return makeAuthor(authors.get(0))+", et al";
	}

	private static String makeAuthor(String author) {
		return "{{a|"+author+"}}";
	}
}