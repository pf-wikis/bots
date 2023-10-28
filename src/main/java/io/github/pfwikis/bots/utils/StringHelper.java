package io.github.pfwikis.bots.utils;

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

}
