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
			"{{#set:$1={{{$1|}}}}}"
	),
	PAGE_LIST(
			"Page list",
			"{{#arraymap:{{{$1|}}}|;|~|[[~]]}}",
			"{{#arraymap:{{{$1|}}}|;|~|{{#set:$1=~}}}}"
	),
	IMAGE(
			"Image",
			"[[File:{{{Image|}}}|250px|{{{Name|}}}]]",
			"{{#set:Image=File:{{{Image|}}}}}"
	);
	
	private final static Map<String, FactType> MAP = Arrays.stream(FactType.values()).collect(Collectors.toMap(f->f.id, f->f));
	
	private final String id;
	private final String displayFactCode;
	private final String storeFactCode;

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
}
