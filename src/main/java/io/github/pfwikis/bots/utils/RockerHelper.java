package io.github.pfwikis.bots.utils;

import com.fizzed.rocker.RockerModel;

import io.github.pfwikis.bots.common.WikiAPI;

public class RockerHelper {
	public static void make(WikiAPI wiki, String page, RockerModel template) {
		var txt = template.render().toString();
		var trimmed = txt.strip().replace("\t", "").replaceAll("\\s*\n\\s*", "");
		wiki.editIfChange(page, trimmed, "Automatic regeneration of template");
	}
}
