package io.github.pfwikis.bots.common.model;

import lombok.Data;

@Data
public class Page {
	private int pageid;
	private String title;
	private Page[] transcludedin;
	private Page[] categories;
}