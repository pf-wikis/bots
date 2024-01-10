package io.github.pfwikis.bots.factshelper;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FactType {

	PLAIN(
			"Plain",
			"{{{$1|}}}",
			"{{#set:$1={{{$1|}}}}}",
			"|$1={{{$1|}}}"
	),
	PAGE_LIST(
			"Page list",
			"{{#arraymap:{{{$1|}}}|;|~|[[~]]}}",
			"{{#set:$1={{{$1|}}}|+sep=;}}",
			"|$1={{{$1|}}}|+sep=;"
	),
	IMAGE(
			"Image",
			"[[File:{{{$1|}}}|250px|{{{Name|}}}]]",
			"{{#set:Image=File:{{{$1|}}}}}",
			"|$1=File:{{{$1|}}}"
	),
	AUTHOR(
			"Author",
			"{{#arraymap:{{{$1|}}}|;|~|[[~]]}}",
			"{{#set:$1={{{$1|}}}|+sep=;}}",
			"|$1={{{$1|}}}|+sep=;"
	) {
		
		@Override
		public String formSpecial(String propName) {
			if(!"Author".equals(propName)) {
				return "{{#set:Author={{{$1|}}}|+sep=;}}".replace("$1", propName);
			}
			return "";
		}
		
		@Override
		public String subFormSpecial(String propName) {
			return "{{#set:|Author={{{$1|}}}|+sep=;}}".replace("$1", propName);
		}
	};
	
	private final static Map<String, FactType> MAP = Arrays.stream(FactType.values()).collect(Collectors.toMap(f->f.id, f->f));
	
	private final String id;
	private final String displayFactCode;
	private final String storeFactCode;
	private final String storeSubFactCode;

	@JsonCreator
	public static FactType of(String id) {
		var res = MAP.get(id);
		if(res == null) {
			throw new NoSuchElementException("Unknown fact type '"+id+"'");
		}
		return res;
	}

	public String displayFactCode(String propName) {
		return displayFactCode.replace("$1", propName);
	}

	public String storeFactCode(String propName) {
		return storeFactCode.replace("$1", propName);
	}
	
	public String formSpecial(String propName) {
		return "";
	}
	
	public String subFormSpecial(String propName) {
		return "";
	}
	
	public String storeSubFactCode(String propName) {
		return storeSubFactCode.replace("$1", propName);
	}
}
