package io.github.pfwikis.bots.facts.model;

import java.time.temporal.Temporal;
import java.util.List;

import io.github.pfwikis.bots.common.model.SemanticSubject.PageRef;

public class SFactTypes {
	public static final SFactType<String> STRING = new SFactType<>(
			SMWPropertyType.TEXT,
			"{{{$1|}}}",
			"{{#set:$1={{{$1|}}}}}",
			"|$1={{{$1|}}}"
	) {};
	public static final SFactType<PageRef> PAGE = new SFactType<>(
			SMWPropertyType.PAGE,
			"{{{$1|}}}",
			"{{#set:$1={{{$1|}}}}}",
			"|$1={{{$1|}}}"
	) {};
	public static final SFactType<List<PageRef>> PAGE_LIST = new SFactType<>(
			SMWPropertyType.PAGE,
			"{{#arraymap:{{{$1|}}}|;|~|{{a|~}}}}",
			"{{#set:$1={{{$1|}}}|+sep=;}}",
			"|$1={{{$1|}}}|+sep=;"
	) {};
	public static final SFactType<List<PageRef>> PAGE_LIST_ORDERED = new SFactType<>(
			SMWPropertyType.PAGE,
			"TODO",
			"TODO",
			"TODO"
	) {};
	public static final SFactType<String> MULTILINE_WIKITEXT = new SFactType<>(
			SMWPropertyType.TEXT,
			"{{{$1|}}}",
			"{{#set:$1={{{$1|}}}}}",
			"|$1={{{$1|}}}"
	) {};
	public static final SFactType<PageRef> IMAGE = new SFactType<>(
			SMWPropertyType.PAGE,
			"{zhj7utgf",
			"{{#set:$1=File:{{{$1|}}}}}",
			"|$1=File:{{{$1|}}}"
	) {};
	public static final SFactType<Integer> INTEGER = new SFactType<>(
			SMWPropertyType.NUMBER,
			"{{{$1|}}}",
			"{{#set:$1={{{$1|}}}}}",
			"|$1={{{$1|}}}"
	) {};
	public static final SFactType<Boolean> BOOLEAN = new SFactType<>(
			SMWPropertyType.NUMBER,
			"{{{$1|}}}",
			"{{#set:$1={{{$1|}}}}}",
			"|$1={{{$1|}}}"
	) {};
	private final static String DATE_PRECISION =
		"{{#if:{{{$1|}}}|"
			+"{{#rmatch:{{{$1|}}}|^\\d{4}-\\d{1,2}-\\d{1,2}$|date|"
				+ "{{#rmatch:{{{$1|}}}|^\\d{4}-\\d{1,2}$|month|"
					+ "{{#rmatch:{{{$1|}}}|^\\d{4}$|year|unknown}}"
				+ "}}"
			+ "}}"
		+ "|empty}}";
	public static final SFactType<Temporal> DATE = new SFactType<>(
			SMWPropertyType.DATE,
			"{{#if:{{{$1|}}}|{{{$1|}}} ''(precision of "+DATE_PRECISION+")''}}",
			"{{#set:$1={{{$1|}}}}}",
			"|$1={{{$1|}}}|$1 precision="+DATE_PRECISION
	) {/*
		
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
		}*/
	};
	/*,
	
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
	);*/
}
