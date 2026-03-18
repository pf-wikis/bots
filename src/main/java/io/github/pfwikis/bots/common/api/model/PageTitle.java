package io.github.pfwikis.bots.common.api.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.api.generated.params.Interwiki;
import io.github.pfwikis.bots.common.api.generated.params.NS;
import kotlin.NotImplementedError;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.JsonNode;

@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PageTitle implements Comparable<PageTitle> {
	private final Interwiki interwiki;
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
		//internal SMW form
		var parts = StringUtils.splitPreserveAllTokens(txt, '#');
		if(parts.length==4) {
			return new PageTitle(
				Interwiki.fromPrefixOrNull(StringUtils.trimToNull(parts[2])),
				NS.fromId(Integer.parseInt(parts[1])),
				parts[0].replace('_', ' '),
				StringUtils.trimToNull(parts[3])
			);
		}
		
		//typical wiki form
		NS ns = NS.MAIN;
		String name = txt;
		String hash = null;
		
		int ind = txt.indexOf(':');
		if(ind != -1) {
			ns = NS.fromName(name.substring(0, ind));
			name = name.substring(ind+1);
		}
		ind = txt.indexOf('#');
		if(ind != -1) {
			hash = name.substring(ind+1);
			name = name.substring(0, ind);
		}
		return new PageTitle(null, ns, name, hash);
	}

	public static PageTitle of(NS ns, String title) {
		return new PageTitle(null, ns, title.replace('_', ' '), null);
	}

	public String toFullTitle() {
		var sb = new StringBuilder();
		if(interwiki!=null) sb.append(interwiki.getPrefix()).append(":");
		sb.append(ns.getPrefix()).append(name);
		if(hash!=null) sb.append("#").append(hash);
		return sb.toString();
	}

	@Override
	public String toString() {
		return toFullTitle();
	}

	/**Creates a wikilink with resolved displayname*/
	public String toWikiLink(WikiAPI wiki) {
		throw new NotImplementedError();
		//return wiki.toWikiLink(this.toFullTitle());
	}
	
	public String toWikitextLink(boolean withNamespacePrefix) {
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
		if(res != 0) return res;
		res = name.compareTo(o.name);
		if(res != 0) return res;
		
		if(hash == null) {
			if(o.hash == null)
				return 0;
			else
				return -1;
		}
		else {
			if(o.hash == null)
				return 1;
			else
				return hash.compareTo(o.hash);
		}
	}

	public PageTitle withoutHash() {
		if(hash == null) return this;
		return new PageTitle(interwiki, ns, name, null);
	}
}