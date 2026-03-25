package io.github.pfwikis.bots.paizoretriever;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import lombok.Data;

@Data
public class PZCategoryResponse {

	private PZData data;
	
	@Data
	public static class PZData {
		private Site site;
	}
	
	@Data
	public static class Site {
		private List<PZCategory> categoryTree;
	}
	
	@Data
	public static class PZCategory {
		private int entityId;
		private String name;
		private List<PZCategory> children;
		private List<PZCategory> resolvedChildren = new ArrayList<>();
		
		public void forEachRaw(Consumer<PZCategory> c) {
			c.accept(this);
			if(children != null)
				children.forEach(child->child.forEachRaw(c));
		}
		
		public Stream<PZCategory> streamResolved() {
			if(resolvedChildren == null) {
				return Stream.of(this);
			}
			//root
			if(entityId == 0) {
				return resolvedChildren.stream().flatMap(PZCategory::streamResolved);
			}
			return Stream.concat(Stream.of(this), resolvedChildren.stream().flatMap(PZCategory::streamResolved));
		}
	}
}
