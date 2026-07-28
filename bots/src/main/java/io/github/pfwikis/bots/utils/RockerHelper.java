package io.github.pfwikis.bots.utils;

import com.fizzed.rocker.RockerModel;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.api.model.ContainsPageRef;

public class RockerHelper {
	public static void make(WikiAPI wiki, ContainsPageRef page, RockerModel template) {
		var txt = makeWikitext(template);
		wiki.editIfChange(page, txt, "Automatic regeneration of template");
	}
	
	public static String makeWikitext(RockerModel template) {
		var txt = template.render().toString();
		var trimmed = txt.strip();
		return trimmed;
	}
}
