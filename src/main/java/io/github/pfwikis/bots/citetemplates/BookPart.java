package io.github.pfwikis.bots.citetemplates;

import java.util.List;

public interface BookPart {
	
	List<String> getAuthors();

	default String makeAuthors() {
		var result = formatAuthors(getAuthors());
		if(result == null)
			return "Unknown author";
		return result;
	}

	default String formatAuthors(List<String> authors) {
		if(authors == null || authors.isEmpty())
			return null;
		if(authors.size() == 1) {
			return formatAuthor(authors.get(0));
		}
		if(authors.size() == 2) {
			return formatAuthor(authors.get(0))+" & "+formatAuthor(authors.get(1));
		}
		return formatAuthor(authors.get(0))+", et al";
	}

	private static String formatAuthor(String author) {
		return "{{a|"+author+"}}";
	}
}