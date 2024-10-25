package io.github.pfwikis.bots.facts.model;

import java.util.Collections;
import java.util.List;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class SPropertyGroup {
	private final String name;
	@Builder.Default
	private List<SProperty<?>> properties = Collections.emptyList();
	
	public static class SPropertyGroupBuilder {
		public SPropertyGroupBuilder properties(SProperty<?>... properties) {
			this.properties$value = List.of(properties);
			this.properties$set = true;
			return this;
		}
	}
}
