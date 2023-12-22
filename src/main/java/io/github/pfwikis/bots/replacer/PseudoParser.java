package io.github.pfwikis.bots.replacer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.common.collect.HashMultiset;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PseudoParser {

	private static final Map<String, Pattern> CACHE = new HashMap<>();
	@NonNull
	private String txt;

	public String goTo(String rawPattern) {
		var pattern = CACHE.computeIfAbsent(rawPattern, Pattern::compile);
		var m = pattern.matcher(txt);
		if(m.find()) {
			var res = txt.substring(0, m.start());
			txt = txt.substring(m.end());
			return res;
		}
		return null;
	}
	
	public boolean hasMore() {
		return !txt.isEmpty();
	}

	public PseudoParser subParserTo(int initBalance, OC oc) {
		int balance = initBalance;
		for(int i=0;i<txt.length();i++) {
			if(balance == 0) {
				var res = new PseudoParser(txt.substring(0, i-initBalance).strip());
				txt = txt.substring(i);
				return res;
			}
			if(txt.charAt(i) == oc.open)
				balance++;
			else if(txt.charAt(i) == oc.close)
				balance--;
		}
		return null;
	}

	public record OC(char open, char close) {}
	public String goToFirstOutside(char toSearch, OC...ocs) {
		var open = Arrays.stream(ocs).collect(Collectors.toMap(oc->oc.open, Function.identity()));
		var close = Arrays.stream(ocs).collect(Collectors.toMap(oc->oc.close, Function.identity()));
		var balance = HashMultiset.<OC>create();
		for(int i=0;i<txt.length();i++) {
			char cur = txt.charAt(i);
			if(cur == toSearch && balance.isEmpty()) {
				var res = txt.substring(0, i);
				txt = txt.substring(i+1);
				return res;
			}
			else if(open.containsKey(cur)) {
				balance.add(open.get(cur));
			}
			else if(close.containsKey(cur)) {
				balance.remove(close.get(cur));
			}
		}
		var res = txt;
		txt = "";
		return res;
	}

	public void trim() {
		txt = txt.strip();
	}
}
