package io.github.pfwikis.bots.facts.blog;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.common.model.SemanticAsk.Result;
import io.github.pfwikis.bots.replacer.SemanticDataIniHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class BlogFacts extends SimpleBot {

	public BlogFacts() {
		super("blog-facts", "Bot Blog Facts");
	}
	
	@Getter @Setter
	@AllArgsConstructor
	private static class Cit {
		private String id;
		private String name;
		private Result page;
		private String url;
		private String date;
		private String author;
		private Set<String> pages;
		private boolean error = false;
	}

	@Override
	public void run(RunContext ctx) {
		var citations = run.getWiki().semanticAsk("[[Type::Template:Cite web]]"
				+ "|?Web author"
				+ "|?Web date"
				+ "|?Web url"
				+ "|?Name"
				+ "|?Page"
		).stream()
			.map(c->new Cit(
				null,
				c.getPrintouts().getName(),
				c.getPrintouts().getPage(),
				Optional.ofNullable(c.getPrintouts().getWebUrl()).map(String::trim).orElse(null),
				c.getPrintouts().getWebDate(),
				c.getPrintouts().getWebAuthor(),
				Set.of(c.getFullurl()),
				false
			))
			.toList();
		
		var cits = new HashMap<String, Cit>();
		var pat = Pattern.compile("^https?://(www\\.)?paizo.com/(community|paizo)/blog/(?<id>\\w+?)([\\?#].*)?$");
		for(var cit:citations) {
			if(cit.url==null) {
				log.info(cit.url);
				continue;
			}
			var m = pat.matcher(cit.url);
			if(!m.matches()) {
				log.info(cit.url);
				continue;
			}
			String id = m.group("id");
			if(StringUtils.isEmpty(id)) {
				log.info(cit.url);
				continue;
			}
			
			merge(cits, id, cit);
		}
		
		for(var cit:cits.values()) {
			run.getWiki().editIfChange(
				"Facts:Paizo blog/"+cit.getId().toLowerCase(),
				"""
				{{Facts/Web citation|
				|Name=%s
				|Author=%s
				|Website=https://paizo.com/community/blog/%s
				|Website name=Paizo blog
				|Release date=%s
				}}%s
				""".formatted(
					Optional.ofNullable(cit.getName()).orElse(""),
					Optional.ofNullable(cit.getAuthor()).orElse(""),
					cit.getId().toLowerCase(),
					Optional.ofNullable(cit.getDate()).orElse(""),
					cit.isError()?"[[Category:Pages with errors]]":""
				), 
				"Creating paizo blog facts"
			);
		}
	}
	
	private void merge(Map<String, Cit> citations, String id, Cit c) {
		var merged = citations.computeIfAbsent(id, x->new Cit(x, null, null, null, null, null, new HashSet<>(), false));
		merged.setAuthor(merge(merged, merged.getAuthor(), parseAuthor(c.getAuthor())));
		merged.setDate(merge(merged, merged.getDate(), parseDate(merged, c.getDate())));
		merged.setName(merge(merged, merged.getName(), c.getName()));
		if(c.getPage() != null)
			merged.getPages().add(c.getPage().getPage());
	}

	private String parseDate(Cit merged, String in) {
		if(in == null) return null;
		var res = SemanticDataIniHelper.parseDate(in);
		if(res == null) {
			merged.setError(true);
			return "\""+in+"\"";
		}
		return res;
	}
	
	private String parseAuthor(String authors) {
		if(authors == null) return null;
		return Arrays.stream(authors.split(" *(,|&| and ) *"))
			.sorted()
			.map(v->StringUtils.remove(StringUtils.remove(v, "["), "]"))
			.collect(Collectors.joining("; "));
	}

	private String merge(Cit merged, String v1, String v2) {
		v1 = StringUtils.trimToNull(v1);
		v2 = StringUtils.trimToNull(v2);
		if(v1 == null)
			return v2;
		if(v2 == null)
			return v1;
		if(v1.equals(v2))
			return v1;
		merged.setError(true);
		if(v1.contains("\""+v2+"\""))
			return v1;
		if(v1.startsWith("\""))
			return v1+" or maybe \""+v2+"\"";
		return "\""+v1+"\" or maybe \""+v2+"\"";
	}

	@Override
	public String getDescription() {
		return
			"""
			This bot creates facts pages for blog entries.
			""";
	}
}
