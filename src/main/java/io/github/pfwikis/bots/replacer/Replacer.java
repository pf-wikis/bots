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
		var map = new EnumMap<Wiki, Map<String, String>>(Wiki.class);
		map.put(Wiki.SF, ImmutableMap.<String, String>builder()
			.put("Year of Scoured Stars", "Season 1 scenarios")
			.put("Year of a Thousand Bites", "Season 2 scenarios")
			.put("Year of Exploration's Edge", "Season 3 scenarios")
			.put("Year of the Data Scourge", "Season 4 scenarios")
			.put("Year of Redemption's Rise", "Season 5 scenarios")
			.put("Year of Fortune's Fall", "Season 6 scenarios")
			.put("Year of Era's End", "Season 7 scenarios")
			.build());
		map.put(Wiki.PF, ImmutableMap.<String, String>builder()
			.put("Season 0", "Season 0 scenarios")
			.put("Season 1", "Season 1 scenarios")
			.put("Year of the Shadow Lodge", "Season 2 scenarios")
			.put("Year of the Ruby Phoenix", "Season 3 scenarios")
			.put("Year of the Risen Rune", "Season 4 scenarios")
			.put("Year of the Demon", "Season 5 scenarios")
			.put("Year of the Sky Key", "Season 6 scenarios")
			.put("Year of the Serpent", "Season 7 scenarios")
			.put("Year of the Stolen Storm", "Season 8 scenarios")
			.put("Year of Factions' Favor", "Season 9 scenarios")
			.put("Season of the Ten", "Season 10 scenarios")
			.put("Year of the Open Road", "Season 1 (2E) scenarios")
			.put("Year of Corruption's Reach", "Season 2 (2E) scenarios")
			.put("Year of Shattered Sanctuaries", "Season 3 (2E) scenarios")
			.put("Year of Boundless Wonder", "Season 4 (2E) scenarios")
			.put("Year of Unfettered Exploration", "Season 5 (2E) scenarios")
			.put("Year of Immortal Influence", "Season 6 (2E) scenarios")
			.put("Year of Battle's Spark", "Season 7 (2E) scenarios")
			.build());
				
		for(var e:map.get(run.getServer()).entrySet()) {
			run.getWiki().editIfChange(
					"Facts:"+e.getKey(),
					"""
					{{Facts/Series
					|Name=Year of Shattered Sanctuaries
					|Member category=Category:%s
					}}
					""".formatted(e.getValue()),
					"Create facts for Series"
			);
		}
	}
	
	public static record Debug(List<String> debug) {}
	public static record Out(String blurbText, String blurbExtras) {}
}
