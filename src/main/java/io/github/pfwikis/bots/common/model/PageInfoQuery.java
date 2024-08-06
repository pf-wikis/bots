package io.github.pfwikis.bots.common.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PageInfoQuery {
	private List<Page> pages = new ArrayList<>();
}