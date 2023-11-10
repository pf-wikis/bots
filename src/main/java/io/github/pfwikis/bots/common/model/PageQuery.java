package io.github.pfwikis.bots.common.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PageQuery {
	private List<Page> pages = new ArrayList<>();
}