package io.github.pfwikis.bots.facts.model;

import java.time.temporal.Temporal;
import java.util.List;

import io.github.pfwikis.bots.common.model.SemanticSubject.PageRef;

public class SFactTypes {
	public static final SFactType<String> STRING = new SFactType<>(
			SMWPropertyType.TEXT,
			"$v"
	) {};
	public static final SFactType<PageRef> PAGE = new SFactType<>(
			SMWPropertyType.PAGE,
			"$v"
	) {};
	public static final SFactType<List<PageRef>> PAGE_LIST = new SFactType<>(
			SMWPropertyType.PAGE,
			"{{#arraymap:$v|;|~|{{a|~}}}}"
	) {
		@Override
		protected String changeStoreFactWikitext(String wt) {
			return wt+"|+sep=;";
		}
	};
	public static final SFactType<List<PageRef>> PAGE_LIST_ORDERED = new SFactType<>(
			SMWPropertyType.PAGE,
			"TODO"
			//<span class=\"hidden\">{{#counter: $1-order-counter|set=0}}</span>{{#set:$1=$v|+sep=;}}{{#set:$1 ordered={{#arraymap:$v|;|~|~;{{#counter: $1-order-counter}}|§}}|+sep=§}}
	) {};
	public static final SFactType<String> MULTILINE_WIKITEXT = new SFactType<>(
			SMWPropertyType.TEXT,
			"$v"
	) {};
	public static final SFactType<PageRef> IMAGE = new SFactType<>(
			SMWPropertyType.PAGE,
			"[[File:{{{1|}}}]]"
	) {
		@Override
		protected String changeStoreFactWikitext(String wt) {
			return "File:"+wt;
		}
	};
	public static final SFactType<Integer> INTEGER = new SFactType<>(
			SMWPropertyType.NUMBER,
			"$v"
	) {};
	public static final SFactType<Boolean> BOOLEAN = new SFactType<>(
			SMWPropertyType.NUMBER,
			"$v"
	) {};
	public static final SFactType<Temporal> DATE = new SFactType<>(
			SMWPropertyType.DATE,
			"$v"
	) {};
}
