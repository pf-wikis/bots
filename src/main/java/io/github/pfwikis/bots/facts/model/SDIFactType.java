package io.github.pfwikis.bots.facts.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.model.SemanticSubject.PageRef;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public enum SDIFactType {

	PLAIN(
			"Plain",
			"{{{$1|}}}",
			"{{#set:$1={{{$1|}}}}}",
			"|$1={{{$1|}}}"
	),
	MULTILINE_WIKITEXT(
			"Multiline wikitext",
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
		
		private static final DateTimeFormatter US_FORMAT = DateTimeFormatter
			.ofPattern("[MMMM [dd, ]]yyyy")
			.localizedBy(Locale.ENGLISH);
		@Override
		public String formAfterSet(SDIProperty prop) {
			return ("{{#set:$1 precision="+datePrecision()+"}}").replace("$1", prop.getName());
		}
		
		@Override
		public String infoboxValue(WikiAPI wiki, SDIProperty prop, Object object) {
			if(object instanceof TemporalAccessor date) {
				return "<time datetime=\""+object+"\">"+US_FORMAT.format(date)+"</time>";
			}
			return super.infoboxValue(wiki, prop, object);
		}
	},
	PAGE_LIST(
			"Page list",
			"{{#arraymap:{{{$1|}}}|;|~|{{a|~}}}}",
			"{{#set:$1={{{$1|}}}|+sep=;}}",
			"|$1={{{$1|}}}|+sep=;"
	),
	IMAGE(
			"Image",
			"{{#if:{{{$1|}}}|[[File:{{{$1|}}}|250px|{{{Name|}}}]]}}",
			"{{#set:Image=File:{{{$1|}}}}}",
			"|$1=File:{{{$1|}}}"
	),
	AUTHOR(
			"Author",
			"{{#arraymap:{{{$1|}}}|;|~|{{a|~}}}}",
			"<span class=\"hidden\">{{#counter: $1-order-counter|set=0}}</span>{{#set:$1={{{$1|}}}|+sep=;}}{{#set:$1 ordered={{#arraymap:{{{$1|}}}|;|~|~;{{#counter: $1-order-counter}}|§}}|+sep=§}}",
			"|$1={{{$1|}}}|+sep=;|$1 ordered={{#arraymap:{{{$1|}}}|;|~|~;{{#counter: $1-order-counter}}|§}}|+sep=§"
	) {
		@Override
		public String formAfterSet(SDIProperty prop) {
			return "{{#set:Author all={{{$1|}}}|+sep=;}}".replace("$1", prop.getName());
		}
		
		@Override
		public String formBeforeSubformTransclude(SDIProperty prop) {
			return "{{#counter: $1-order-counter|set=0}}";
		}
		
		@Override
		public String subFormAtEnd(SDIProperty prop) {
			return "{{#set:|Author all={{{$1|}}}|+sep=;}}".replace("$1", prop.getName());
		}
	},
	REPRESENTED_BY_PAGE(
			"Represented by page",
			"{{#if:{{{$1|}}}|[[{{{$1}}}]]|{{#ifexist:{{ROOTPAGENAME}}|''default value:'' [[{{ROOTPAGENAME}}]]}}}}",
			"{{#if:{{{$1|}}}|{{#set:$1={{{$1}}}}}|{{#ifexist:{{ROOTPAGENAME}}|{{#set:$1={{ROOTPAGENAME}}}}}}}}",
			"{{#if:{{{$1|}}}|{{#set:$1={{{$1}}}}}|{{#ifexist:{{ROOTPAGENAME}}|{{#set:$1={{ROOTPAGENAME}}}}}}}}"
	);
	
	private final static Map<String, SDIFactType> MAP = Arrays.stream(SDIFactType.values()).collect(Collectors.toMap(f->f.id, f->f));
	
	private final String id;
	private final String displayFactCode;
	private final String storeFactCode;
	private final String storeSubFactCode;

	@JsonCreator
	public static SDIFactType of(String id) {
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
	
	public String subFormAtEnd(SDIProperty prop) {
		return "";
	}
	
	public String formAfterSet(SDIProperty prop) {
		return "";
	}
	
	public String formBeforeSubformTransclude(SDIProperty prop) {
		return "";
	}
	
	public String storeSubFactCode(String propName) {
		return storeSubFactCode.replace("$1", propName);
	}
	
	private final static String datePrecision() { 
		return
			"{{#if:{{{$1|}}}|"
				+"{{#rmatch:{{{$1|}}}|^\\d{4}-\\d{1,2}-\\d{1,2}$|date|"
					+ "{{#rmatch:{{{$1|}}}|^\\d{4}-\\d{1,2}$|month|"
						+ "{{#rmatch:{{{$1|}}}|^\\d{4}$|year|unknown}}"
					+ "}}"
				+ "}}"
			+ "|empty}}";
	}

	public String infoboxLabel(SDIProperty prop, List<Object> values) {
		var label = prop.getInfoboxLabel()==null?prop.getName():prop.getInfoboxLabel();
		if(values.size()>1 && label.charAt(label.length()-1)!='s')
			label += "s";
		return label;
	}
	
	public String infoboxValue(WikiAPI wiki, SDIProperty prop, List<Object> values) {
		if(values.size() == 0)
			return "";
		if(values.size() == 1)
			return infoboxValue(wiki, prop, values.getFirst());
		if(values.size() == 2)
			return infoboxValue(wiki, prop, values.getFirst())
				+ " and "
				+ infoboxValue(wiki, prop, values.get(1));
		var sb = new StringBuilder();
		for(int i=0;i<values.size()-1;i++) {
			if(i>0) sb.append(", ");
			sb.append(infoboxValue(wiki, prop, values.get(i)));
		}
		sb.append(", and ");
		sb.append(infoboxValue(wiki, prop, values.getLast()));
		return sb.toString();
	}

	public String infoboxValue(WikiAPI wiki, SDIProperty prop, Object object) {
		if(object instanceof PageRef page) {
			return page.toWikiLink(wiki);
		}
		return object.toString();
	}
}