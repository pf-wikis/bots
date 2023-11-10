package io.github.pfwikis.bots.index.librarian;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class LibrarianConfig {

	private List<LibrarianConfig.Page> pages = new ArrayList<>();
	
	@Data
	public static class Page {
		
		private String title;
		private String type;
		
	}
}
