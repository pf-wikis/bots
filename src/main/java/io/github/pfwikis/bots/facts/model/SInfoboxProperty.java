package io.github.pfwikis.bots.facts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

public interface SInfoboxProperty {

	@Builder
	@RequiredArgsConstructor
	@AllArgsConstructor
	public static class Simple implements SInfoboxProperty {
		private final SProperty<?> prop;
		private String label;
		private SProperty<?> fallback;
	}
	
}
