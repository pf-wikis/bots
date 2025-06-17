package io.github.pfwikis.bots.replacer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
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
		var game = run.getServer().getName();
		var map = ImmutableMap.<String, String>builder()
				.put(game+" Cards", game+" Cards")
				.put(game+" Adventure Card Game", game+" Adventure Card Game")
				.put(game+" Accessories", game+" Accessories")
				.put(game+" Pawns", game+" Pawns")
				.put(game+" Battles", game+" Battles")
				.put(game+" Paper Minis", game+" Paper Minis")
				.put(game+" Campaign Setting", game+" Campaign Setting")
				.put(game+" Terrain", game+" Terrain")
				.put(game+" Book Tabs", game+" Book Tabs")
				.put(game+" Roleplaying Game", game+" RPG")
				.put(game+" Player Companion", game+" Player Companion")
				.put("Lost Omens", game+" Lost Omens")
				.put(game+" Modules", game+" Modules")
				.put(game+" Adventure", game+" Adventure")
				.put(game+" Tales", game+" Tales")
				.put(game+"'s Journal (series)", game+"'s Journal")
				.build();
		
		for(var e:map.entrySet()) {
			var series = e.getKey();
			if(run.getWiki().pageExists(series)) {
				run.getWiki().editIfChange(
						"Facts:"+e.getKey(),
						"""
						{{Facts/Series
						|Name=%s
						|Member category=Category:%s
						}}
						""".formatted(series, e.getValue()),
						"Create facts for Series"
				);
			}
		}
	}
	
	public static record Debug(List<String> debug) {}
	public static record Out(String blurbText, String blurbExtras) {}
}
