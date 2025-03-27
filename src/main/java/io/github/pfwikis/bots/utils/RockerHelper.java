package io.github.pfwikis.bots.utils;

import com.fizzed.rocker.RockerModel;

import io.github.pfwikis.bots.common.WikiAPI;

public class RockerHelper {
	public static void make(WikiAPI wiki, String page, RockerModel template) {
		var txt = template.render().toString();
		var trimmed = txt.strip()
				.replaceAll("(?m)^\t+", "")
				.replace("\n", "")
				.replaceAll("  +", " ")
				.replace("§§§n§§§", "\n");
		wiki.editIfChange(page, trimmed, "Automatic regeneration of template");
	}
	
	/**
	 * @return a rocker linebreak that will not be removed by make
	 */
	public static String n() {
		return n(1);
	}
	
	public static String n(int number) {
		return "§§§n§§§".repeat(number);
	}
}
