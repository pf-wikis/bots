package io.github.pfwikis.bots.facts.util;

import io.github.pfwikis.bots.facts.model.SConcept;
import io.github.pfwikis.bots.facts.model.SProperty;
import io.github.pfwikis.bots.facts.model.SPropertyGroup;

public class ConceptUtil {

	public static String cssName(SConcept c) {
		return cssName(c.getName());
	}
	
	public static String cssName(SPropertyGroup g) {
		return cssName(g.getName());
	}
	
	public static String cssName(String txt) {
		return txt.toLowerCase().replaceAll("[^a-z0-9]+", "-");
	}

	public static String cssName(SProperty<?> prop) {
		return cssName(prop.getName());
	}
}
