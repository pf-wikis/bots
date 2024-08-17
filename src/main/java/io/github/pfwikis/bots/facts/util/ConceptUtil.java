package io.github.pfwikis.bots.facts.util;

import io.github.pfwikis.bots.facts.model.SConcept;

public class ConceptUtil {

	public static String cssName(SConcept c) {
		return c.getName().toLowerCase().replaceAll("[^a-z0-9]+", "-");
	}
}
