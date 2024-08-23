package io.github.pfwikis.bots.utils;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharSequenceNodeFactory;
import com.googlecode.concurrenttrees.solver.LCSubstringSolver;

public class StringHelper {
	
	public static String findLongestCommonSubstring(String... values) {
		LCSubstringSolver solver = new LCSubstringSolver(new DefaultCharSequenceNodeFactory());
	    for (String s: values) {
	        solver.add(s);
	    }
		return solver.getLongestCommonSubstring().toString();
	}
	
	public static String titleToPattern(String title, boolean ignoreFirstLetterCase) {
		var prefix = "";
		if(ignoreFirstLetterCase) {
			prefix = "["
				+Character.toLowerCase(title.charAt(0))
				+Character.toUpperCase(title.charAt(0))
				+"]";
			title = title.substring(1);
		}
		return prefix
			+Arrays.stream(title.split("[ _]"))
			.map(Pattern::quote)
			.collect(Collectors.joining("[ _]"));
	}

}
