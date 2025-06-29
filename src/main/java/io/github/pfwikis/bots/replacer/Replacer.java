package io.github.pfwikis.bots.replacer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import com.beust.jcommander.Parameters;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.common.model.Page;
import io.github.pfwikis.bots.common.model.SemanticAsk.Result;
import io.github.pfwikis.bots.common.model.subject.PageRef;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class Replacer extends SimpleBot {

	public Replacer() {
		super("replacer", "Bot Manual Bulk Operations");
	}
	
	@Override
	public String getDescription() {
		return "This bot is only started by hand for manual bulk changes to the wiki.";
	}

	@Override
	public void run(RunContext ctx) throws IOException {
		var fileCats = run.getWiki().semanticAsk(P.class, "[[File:+]]|?Category=category")
				.stream()
				.flatMap(r->Optional.ofNullable(r.getPrintouts().category).orElse(Collections.emptyList()).stream())
				.map(c->c.getPage())
				.distinct()
				.toList();
		
		var mainCats = run.getWiki().semanticAsk(P.class, "[[:+]]|?Category=category")
				.stream()
				.flatMap(r->Optional.ofNullable(r.getPrintouts().category).orElse(Collections.emptyList()).stream())
				.map(c->c.getPage())
				.distinct()
				.toList();
		
		var matches = new HashSet<String>();
		matches.addAll(fileCats);
		matches.retainAll(mainCats);
		
		for(var m:matches) {
			log.info(m);
		}
		
	}
	
	public static record Debug(List<String> debug) {}
	public static record P(List<Result<?>> category) {}
}
