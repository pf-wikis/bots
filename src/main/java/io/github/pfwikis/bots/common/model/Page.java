package io.github.pfwikis.bots.common.model;

import java.util.List;

import org.jfree.data.json.impl.JSONObject;

import lombok.Data;

@Data
public class Page {
	private int pageid;
	private String title;
	private String displaytitle;
	private Page[] transcludedin;
	private Page[] categories;
	private Page[] linkshere;
	private List<JSONObject> protection;
	private String pageimage;
}