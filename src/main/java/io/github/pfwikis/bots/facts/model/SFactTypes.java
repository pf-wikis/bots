package io.github.pfwikis.bots.facts.model;

import static io.github.pfwikis.bots.facts.SUtilProperties.Order;
import static io.github.pfwikis.bots.facts.SUtilProperties.Ordered_value;

import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.model.subject.PageRef;
import io.github.pfwikis.bots.common.model.subject.SemanticSubject;

public class SFactTypes {
	public static final SFactType<String> STRING = new SFactType<>(
			"STRING",
			SMWPropertyType.TEXT,
			"string",
			"$v"
	) {

		@Override
		protected String configureFormField(SProperty<?> prop) {
			if(prop.isAutocompleteDisabled())
				return "|input type=text";
			return "|input type=combobox";
		}
	};
	public static final SFactType<String> URL = new SFactType<>(
			"URL",
			SMWPropertyType.URL,
			"url",
			"$v"
	) {

		@Override
		protected String configureFormField(SProperty<?> prop) {
			return "|input type=text";
		}
	};
	public static final SFactType<String> ISBN = new SFactType<>(
			"ISBN",
			SMWPropertyType.TEXT,
			"string",
			"$v"
	) {
		@Override
		public String toInfoboxDisplay(String v) {
			return "[[Special:BookSources/"+v+"|"+v+"]]";
		}

		@Override
		protected String configureFormField(SProperty<?> prop) {
			return "";
		}
	};
	public static final SFactType<PageRef> PAGE = new SFactType<>(
			"PAGE",
			SMWPropertyType.PAGE,
			"wiki-page-name",
			"[[:$v]]"
	) {
		@Override
		public String toInfoboxDisplay(PageRef v) {
			return v.toWikiLink();
		}

		@Override
		protected String configureFormField(SProperty<?> prop) {
			return STRING.configureFormField(prop);
		};
	};
	public static final SFactType<List<PageRef>> PAGE_LIST = new SFactType<>(
			"PAGE_LIST",
			SMWPropertyType.PAGE,
			"string",
			"{{#arraymap:$v|;|~|{{a|~}}}}"
	) {
		@Override
		public String wikitextToStoreFact(SProperty<List<PageRef>> p, String v) {
			return v+"|+sep=;";
		}
		
		@Override
		public String toInfoboxDisplay(List<PageRef> v) {
			var values = Lists.transform(v, e->PAGE.toInfoboxDisplay(e));
			if(values.size() == 0)
				return "";
			if(values.size() == 1)
				return values.getFirst();
			if(values.size() == 2)
				return values.getFirst()
					+ " and "
					+ values.getLast();
			var sb = new StringBuilder();
			for(int i=0;i<values.size()-1;i++) {
				if(i>0) sb.append(", ");
				sb.append(values.get(i));
			}
			sb.append(", and ");
			sb.append(values.getLast());
			return sb.toString();
		}

		@Override
		protected String configureFormField(SProperty<?> prop) {
			return "|input type=tokens|delimiter=;";
		};
		
		public List<PageRef> convertToJava(List<Object> values) {
			return values.stream()
				.map(PageRef.class::cast)
				.toList();
		};
	};
	public static final SFactType<List<PageRef>> PAGE_LIST_ORDERED = new SFactType<>(
			"PAGE_LIST_ORDERED",
			SMWPropertyType.PAGE,
			"string",
			"{{#arraymap:$v|;|~|{{a|~}}}}"
	) {
		@Override
		public String toInfoboxDisplay(List<PageRef> v) {
			return PAGE_LIST.toInfoboxDisplay(v);
		}
		
		@Override
		public String wikitextBeforeStoringFact(SProperty<List<PageRef>> p) {
			return "<span class=\"hidden\">{{#counter: "+p.getName()+"-order-counter|set=0}}</span>";
		}
		
		@Override
		public String wikitextToStoreFact(SProperty<List<PageRef>> p, String v) {
			return "{{#arraymap:"+v+"|;|~|~;{{#counter: "+p.getName()+"-order-counter}}|§}}|+sep=§";
		}
		
		@Override
		public String wikitextToQuery(SProperty<List<PageRef>> prop) {
			return ".Ordered_value";
		}

		@Override
		protected String configureFormField(SProperty<?> prop) {
			return "|input type=tokens|delimiter=;";
		}
		
		public List<PageRef> convertToJava(List<Object> values) {
			return values.stream()
				.map(SemanticSubject.class::cast)
				.map(ss->Pair.of(ss.get(Order), ss.get(Ordered_value)))
				.sorted()
				.map(p->p.getRight())
				.toList();
		};
	};
	public static final SFactType<String> MULTILINE_WIKITEXT = new SFactType<>(
			"MULTILINE_WIKITEXT",
			SMWPropertyType.TEXT,
			"content",
			"$v"
	) {

		@Override
		protected String configureFormField(SProperty<?> prop) {
			return "|input type=textarea|autogrow|editor=visualeditor";
		}
	};
	public static final SFactType<PageRef> IMAGE = new SFactType<>(
			"IMAGE",
			SMWPropertyType.PAGE,
			"wiki-file-name",
			"[[File:$v|250px]]"
	) {
		@Override
		public String wikitextToStoreFact(SProperty<PageRef> p, String v) {
			return "File:"+v;
		}

		@Override
		protected String configureFormField(SProperty<?> prop) {
			return "|input type=combobox|values from namespace=File";
		}
	};
	public static final SFactType<Integer> INTEGER = new SFactType<>(
			"INTEGER",
			SMWPropertyType.NUMBER,
			"number",
			"$v"
	) {

		@Override
		protected String configureFormField(SProperty<?> prop) {
			return "|input type=regexp|regexp=/^[0-9]*$/";
		}
	};
	public static final SFactType<Boolean> BOOLEAN = new SFactType<>(
			"BOOLEAN",
			SMWPropertyType.BOOLEAN,
			"text",
			"$v"
	) {

		@Override
		protected String configureFormField(SProperty<?> prop) {
			return "|input type=checkbox";
		}
	};
	public static final SFactType<Temporal> DATE = new SFactType<>(
			"DATE",
			SMWPropertyType.DATE,
			"date",
			"$v"
	) {
		@Override
		public String wikitextToQuery(SProperty<Temporal> prop) {
			return "#LOCL";
		}

		@Override
		protected String configureFormField(SProperty<?> prop) {
			return "";
		}
		
		
		private static final DateTimeFormatter US_FORMAT = DateTimeFormatter
				.ofPattern("[MMMM [dd, ]]yyyy")
				.localizedBy(Locale.ENGLISH);
		
		public String toInfoboxDisplay(Temporal v) {
			return "<time datetime=\""+v+"\">"+US_FORMAT.format(v)+"</time>";
		}
	};
}
