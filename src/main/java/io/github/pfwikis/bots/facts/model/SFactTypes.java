package io.github.pfwikis.bots.facts.model;

import java.time.temporal.Temporal;
import java.util.List;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.model.SemanticSubject.PageRef;

public class SFactTypes {
	public static final SFactType<String> STRING = new SFactType<>(
			"STRING",
			SMWPropertyType.TEXT,
			"$v"
	) {};
	public static final SFactType<PageRef> PAGE = new SFactType<>(
			"PAGE",
			SMWPropertyType.PAGE,
			"[[$v]]"
	) {
		public String wikitextToInfoboxDisplay(WikiAPI wiki, Object object) {
			if(object instanceof PageRef page) {
				return page.toWikiLink(wiki);
			}
			return object.toString();
		};
	};
	public static final SFactType<List<PageRef>> PAGE_LIST = new SFactType<>(
			"PAGE_LIST",
			SMWPropertyType.PAGE,
			"{{#arraymap:$v|;|~|{{a|~}}}}"
	) {
		@Override
		protected String changeStoreFactWikitext(String wt) {
			return wt+"|+sep=;";
		}
		
		public String wikitextToInfoboxDisplay(WikiAPI wiki, Object object) {
			if(object instanceof PageRef page) {
				return page.toWikiLink(wiki);
			}
			return object.toString();
		};
	};
	public static final SFactType<List<PageRef>> PAGE_LIST_ORDERED = new SFactType<>(
			"PAGE_LIST_ORDERED",
			SMWPropertyType.PAGE,
			"TODO"
			//<span class=\"hidden\">{{#counter: $1-order-counter|set=0}}</span>{{#set:$1=$v|+sep=;}}{{#set:$1 ordered={{#arraymap:$v|;|~|~;{{#counter: $1-order-counter}}|§}}|+sep=§}}
	) {
		@Override
		public String wikitextToInfoboxDisplay(WikiAPI wiki, Object object) {
			if(object instanceof PageRef page) {
				return page.toWikiLink(wiki);
			}
			return object.toString();
		}
		
		@Override
		public String wikitextToQuery(SProperty<List<PageRef>> prop) {
			return ".Ordered_page";
		}
	};
	public static final SFactType<String> MULTILINE_WIKITEXT = new SFactType<>(
			"MULTILINE_WIKITEXT",
			SMWPropertyType.TEXT,
			"$v"
	) {};
	public static final SFactType<PageRef> IMAGE = new SFactType<>(
			"IMAGE",
			SMWPropertyType.PAGE,
			"[[File:{{{$v|}}}|250px]]"
	) {
		@Override
		protected String changeStoreFactWikitext(String wt) {
			return "File:"+wt;
		}
	};
	public static final SFactType<Integer> INTEGER = new SFactType<>(
			"INTEGER",
			SMWPropertyType.NUMBER,
			"$v"
	) {};
	public static final SFactType<Boolean> BOOLEAN = new SFactType<>(
			"BOOLEAN",
			SMWPropertyType.NUMBER,
			"$v"
	) {};
	public static final SFactType<Temporal> DATE = new SFactType<>(
			"DATE",
			SMWPropertyType.DATE,
			"$v"
	) {
		@Override
		public String wikitextToQuery(SProperty<Temporal> prop) {
			return "#LOCL";
		}
	};
}
