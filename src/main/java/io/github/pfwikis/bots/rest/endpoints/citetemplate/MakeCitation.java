package io.github.pfwikis.bots.rest.endpoints.citetemplate;

import static io.github.pfwikis.bots.facts.SFactsProperties.Publisher;
import static io.github.pfwikis.bots.facts.SFactsProperties.Represented_by_page;
import static io.github.pfwikis.bots.facts.SFactsProperties.Website;
import static io.github.pfwikis.bots.facts.SFactsProperties.Website_name;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.apache.commons.text.StringEscapeUtils;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import com.google.common.primitives.Ints;

import io.github.pfwikis.bots.rest.endpoints.citetemplate.model.BookDef;
import io.github.pfwikis.bots.utils.StringHelper;
import lombok.Data;

public class MakeCitation {
	
	public static String makeFullRef(BookDef book, RPCiteParam param) {
		var info = makeInfo(book, param);
		var b = new Builder();
		b.add(
			"<ref group=\"",
			StringEscapeUtils.escapeHtml4(info.writeGroup()),
			"\" group-content=\"",
			StringEscapeUtils.escapeHtml4(info.writeGroupContent()),
			"\">",
			info.write(),
			"</ref>"
		);
		return b.toString();
	}

	public static String make(BookDef book, RPCiteParam param) {
		var info = makeInfo(book, param);
		return info.write();
	}
	
	private static CiteInfo makeInfo(BookDef book, RPCiteParam param) {
		var r = new CiteInfo(makeAuthor(book, param), makeTitle(book, param));
		r.setGroupAuthor(makeAuthor(book, RPCiteParam.EMPTY));
		r.setArticle(makeArticle(book, param));
		r.setGroupTitle(makeTitle(book, RPCiteParam.EMPTY));
		r.setLocation(makeLocation(book, param));
		r.setPublisher(makePublisher(book));
		r.setYear(makeYear(book));
		return r;
	}

	private static String makeYear(BookDef book) {
		if(book.getReleaseYear()!=null) {
			return book.getReleaseYear().toString();
		}
		return null;
	}

	private static String makePublisher(BookDef book) {
		if(book.has(Publisher)) {
			var sb = new StringBuilder();
			boolean first = true;
			for(var pub:book.get(Publisher)) {
				if(first)
					first = false;
				else
					sb.append(", ");
				sb.append(pub.toWikiLink(false));
			}
			return sb.toString();
		}
		else if(book.has(Website_name)) {
			return book.get(Website_name);
		}
		return null;
	}

	private static String makeLocation(BookDef book, RPCiteParam param) {
		if(book.isWebCitation()) {
			if(param.hasLocation()) {
				return "#"+param.getLocation();
			}
		}
		else {
			if(param.hasShowAs()) {
				return param.getShowAs();
			} else if(param.hasLocation()) {
				var loc = param.getLocation();
				if(Ints.tryParse(param.getLocation()) != null && param.hasEndPage()) {
					return loc+"&ndash;"+param.getEndPage();
				}
				return loc;
			}
		}
		return null;
	}

	private static String makeTitle(BookDef book, RPCiteParam param) {
		var b = new Builder();
		if(book.has(Website) && !book.has(Represented_by_page)) {
			b.add(
				"[",
				book.get(Website)
			);
			if(book.isWebCitation() && param.hasLocation()) {
				b.add(
					"&#35;",
					URLEncoder.encode(param.getLocation(), StandardCharsets.UTF_8)
				);
			}
			b.add(
				" ",
				book.getName(),
				"]"
			);
		}
		else {
			b.add(
				"[[",
				Optional.ofNullable(book.getOr(Represented_by_page, null)).map(v->v.toString()).orElse(book.getName()),
				"|",
				StringHelper.pageToTitle(book.getName()),
				"]]"
			);
		}
		return b.toString();
	}

	private static @Nullable String makeArticle(BookDef book, RPCiteParam param) {
		var articleRanges = book.getArticlePageRanges();
		var articleCases = book.getArticleSpecialCases();
		if(param.getIntLocation() != null && articleRanges.get(param.getIntLocation())!=null) {
			return articleRanges.get(param.getIntLocation());
		}
		else if(articleCases.containsKey(param.getLocation())) {
			return articleCases.get(param.getLocation());
		}
		return null;
	}

	private static String makeAuthor(BookDef book, RPCiteParam param) {
		var authorRanges = book.getAuthorPageRanges();
		var authorCases = book.getAuthorSpecialCases();
		if(param.getIntLocation() != null) {
			return authorRanges.get(param.getIntLocation());
		}
		else {
			return authorCases.getOrDefault(param.getLocation(), book.getAuthorsString());
		}
	}

	private static class Builder {
		private StringBuilder sb = new StringBuilder();

		public void add(String val) {
			sb.append(val);
		}
		
		public void add(String... vals) {
			for(String val:vals)
				add(val);
		}
		
		@Override
		public String toString() {
			if(sb.isEmpty()) {
				return null;
			}
			return sb.toString();
		}
	}
	
	@Data
	private static class CiteInfo {
		private String groupAuthor;
		private String groupTitle;
		@NonNull
		private String author;
		private String article;
		@NonNull
		private String title;
		private String location;
		private String publisher;
		private String year;
		
		public String write() {
			var b = new Builder();
			b.add(
				"<cite class=\"cite\">",
				"<span class=\"authors\">", author, "</span>",
				". "
			);
			if(article != null) {
				b.add("<span class=\"article\">“", article, "”</span> in ");
			}
			b.add("<span class=\"title\">", title, "</span>");
			if(location != null) {
				b.add(", <span class=\"location\">", location, "</span>");
			}
			if(article != null || title != null || location != null)
				b.add(". ");
			if(publisher != null) {
				b.add("<span class=\"publisher\">", publisher, "</span>");
			}
			if(year != null) {
				if(publisher != null) b.add(", ");
				b.add("<span class=\"year\" title=\"release year\">", year, "</span>");
			}
			b.add("</cite>");
			return b.toString();
		}

		public String writeGroupContent() {
			var b = new Builder();
			b.add("<cite class=\"cite\">");
			if(!author.equals(groupAuthor)) {
				b.add("<span class=\"authors\">", author, "</span>. ");
			}
			if(article != null) {
				b.add("<span class=\"article\">“", article, "”</span>");
			}
			if(location != null) {
				if(article != null) b.add(", ");
				b.add("<span class=\"location\">", location, "</span>");
			}
			b.add("</cite>");
			return b.toString();
		}

		public String writeGroup() {
			var b = new Builder();
			b.add(
				"<cite class=\"cite\">",
				"<span class=\"authors\">", groupAuthor, "</span>. ",
				"<span class=\"title\">", groupTitle, "</span>. "
			);
			if(publisher != null) {
				b.add("<span class=\"publisher\">", publisher, "</span>");
			}
			if(year != null) {
				if(publisher != null) b.add(", ");
				b.add("<span class=\"year\" title=\"release year\">", year, "</span>");
			}
			b.add("</cite>");
			return b.toString();
		}
	}
}
