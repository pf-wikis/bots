package io.github.pfwikis.bots.index.bookreader;

import java.util.List;

import lombok.Data;

@Data
public class Bookmark {

	private String name;
	private int page;
	private int endPageExclusive;
	private List<Bookmark> children;
}
