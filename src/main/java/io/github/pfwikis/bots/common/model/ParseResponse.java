package io.github.pfwikis.bots.common.model;

import java.util.List;

import lombok.Data;

public record ParseResponse(
		Content parse
) {
	
	public static record Content(
			String title,
			int pageid,
			long revid,
			String text,
			Properties properties,
			List<Category> categories,
			List<Link> links
	) {}
	
	public static record Link(
			int ns,
			String title,
			boolean exists
	) {}
	
	public static record Category(
			String sortkey,
			String category
	) {}
	
	@Data
	public static class Properties {
		private String page_image_free;
	}
}
