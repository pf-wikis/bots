package io.github.pfwikis.bots.common.api.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.api.generated.params.NS;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.JsonNode;

@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PageTitle implements Comparable<PageTitle> {
	private final String interwiki;
	@Getter
	private final NS ns;
	@Getter
	private final String name;
	private final String hash;

	public static PageTitle of(JsonNode item) {
		var txt = item.stringValue();
		return of(txt);
	}

	@JsonCreator
	public static PageTitle of(String txt) {
		var parts = StringUtils.splitPreserveAllTokens(txt, '#');
		//internal SMW form
		if(parts.length==4) {
			return new PageTitle(parts[2], NS.fromId(Integer.parseInt(parts[1])), parts[0].replace('_', ' '), parts[3]);
		}
		parts = StringUtils.splitPreserveAllTokens(txt, ':');
		if(parts.length==1)
			return new PageTitle("", NS.MAIN, txt, "");
		if(parts.length==2)
			return new PageTitle("", NS.fromName(parts[0]), parts[1], "");
		else
			throw new UnsupportedOperationException("Unsupported page title format: "+txt);
	}

	public static PageTitle of(NS ns, String title) {
		return new PageTitle("", ns, title.replace('_', ' '), "");
	}

	public String toFullTitle() {
		return (interwiki.isBlank() ? "" : (interwiki + ":"))
				+ ns.getPrefix()
				+ name
				+ (hash.isBlank() ? "" : ("#" + hash));
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
		if(ns==NS.CATEGORY)
			sb.append(":"); //for Category links
		sb.append(this.toFullTitle());
		if(!withNamespacePrefix) {
			sb.append("|");
			sb.append(name);
		}
		sb.append("]]");
		return sb.toString();
	}
	
	public String toDisplayTitleWikitext() {
		return "{{#getdisplaytitle:"+this.toFullTitle()+"}}";
	}

	@Override
	public int compareTo(PageTitle o) {
		int res = ns.compareTo(o.ns);
		if(res != 0)
			return res;
		return name.compareTo(o.name);
	}
}