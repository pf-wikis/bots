package io.github.pfwikis.bots.common.model.subject;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonNode;

import io.github.pfwikis.bots.common.WikiAPI;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PageRef {
	private final String interwiki;
	private final int ns;
	private final String title;
	private final String hash;

	public static PageRef of(JsonNode item) {
		var txt = item.textValue();
		return of(txt);
	}

	@JsonCreator
	public static PageRef of(String txt) {
		var parts = StringUtils.splitPreserveAllTokens(txt, '#');
		return new PageRef(parts[2], Integer.parseInt(parts[1]), parts[0].replace('_', ' '), parts[3]);
	}

	public static PageRef of(int ns, String title) {
		return new PageRef("", ns, title.replace('_', ' '), "");
	}

	public String toFullTitle() {
		return (interwiki.isBlank() ? "" : (interwiki + ":")) + switch (ns) {
		case 0 -> "";
		case 1 -> "Talk:";
		case 4 -> "Project:";
		case 6 -> "File:";
		case 10 -> "Template:";
		case 14 -> "Category:";
		case 102 -> "Property:";
		case 120 -> "Featured:";
		case 128 -> "Facts:";
		default -> throw new IllegalStateException("Unhandled namespace " + ns);
		} + title + (hash.isBlank() ? "" : ("#" + hash));
	}

	@Override
	public String toString() {
		return toFullTitle();
	}

	/**Creates a wikilink with resolved displayname*/
	public String toWikiLink(WikiAPI wiki) {
		return wiki.toWikiLink(this.toFullTitle());
	}
	
	public String toWikiLink(boolean withNamespacePrefix) {
		var sb = new StringBuilder()
				.append("[[");
		if(ns==14)
			sb.append(":"); //for Category links
		sb.append(this.toFullTitle());
		if(!withNamespacePrefix) {
			sb.append("|");
			sb.append(this.getTitle());
		}
		sb.append("]]");
		return sb.toString();
	}
	
	public String toDisplayTitleWikitext() {
		return "{{#getdisplaytitle:"+this.toFullTitle()+"}}";
	}
}