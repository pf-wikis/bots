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
	DATE(
			"Date",
			"{{#if:{{{$1|}}}|{{{$1|}}} ''(precision of "+datePrecision()+")''}}",
			"{{#set:$1={{{$1|}}}}}",
			"|$1={{{$1|}}}|$1 precision="+datePrecision()
	) {
		@Override
		public String formAfterSet(PropertyDefinition prop) {
			return ("{{#set:$1 precision="+datePrecision()+"}}").replace("$1", prop.getName());
		}
	},
	PAGE_LIST(
			"Page list",
			"{{#arraymap:{{{$1|}}}|;|~|[[~]]}}",
			"{{#set:$1={{{$1|}}}|+sep=;}}",
			"|$1={{{$1|}}}|+sep=;"
	) {
		@Override
		public String infoboxCode(String propName) {
			return "{{#arraymap:{{{$1|}}}|;|~|[[~]]|,\\s|and}}".replace("$1", propName);
		}
	},
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
		public String formAfterSet(PropertyDefinition prop) {
			if(!"Author".equals(prop.getName())) {
				return "{{#set:Author={{{$1|}}}|+sep=;}}".replace("$1", prop.getName());
			}
			return "";
		}
		
		@Override
		public String subFormAtEnd(PropertyDefinition prop) {
			return "{{#set:|Author={{{$1|}}}|+sep=;}}".replace("$1", prop.getName());
		}
	},
	REPRESENTED_BY_PAGE(
			"Represented by page",
			"{{#if:{{{$1|}}}|[[{{{$1}}}]]|{{#ifexist:{{ROOTPAGENAME}}|''default value:'' [[{{ROOTPAGENAME}}]]}}}}",
			"{{#if:{{{$1|}}}|{{#set:$1={{{$1}}}}}|{{#ifexist:{{ROOTPAGENAME}}|{{#set:$1={{ROOTPAGENAME}}}}}}}}",
			"{{#if:{{{$1|}}}|{{#set:$1={{{$1}}}}}|{{#ifexist:{{ROOTPAGENAME}}|{{#set:$1={{ROOTPAGENAME}}}}}}}}"
	);
	
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
	
	public String infoboxCode(String propName) {
		return displayFactCode.replace("$1", propName);
	}

	public String storeFactCode(String propName) {
		return storeFactCode.replace("$1", propName);
	}
	
	public String subFormAtEnd(PropertyDefinition prop) {
		return "";
	}
	
	public String formAfterSet(PropertyDefinition prop) {
		return "";
	}
	
	public String storeSubFactCode(String propName) {
		return storeSubFactCode.replace("$1", propName);
	}
	
	private final static String datePrecision() { 
		return
			"{{#rmatch:{{{$1|}}}|^\\d{4}-\\d{1,2}-\\d{1,2}$|date|"
				+ "{{#rmatch:{{{$1|}}}|^\\d{4}-\\d{1,2}$|month|"
					+ "{{#rmatch:{{{$1|}}}|^\\d{4}$|year|unknown}}"
				+ "}}"
			+ "}}";
	}
}
