package io.github.pfwikis.bots.common.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ImageUsageQuery {
	private List<Page> imageusage = new ArrayList<>();
}