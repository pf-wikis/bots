package io.github.pfwikis.bots.replacer;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.beust.jcommander.Parameters;
import com.google.common.collect.Maps;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.model.PageRef;
import io.github.pfwikis.bots.common.api.model.PageTitle;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class Replacer extends SimpleBot {

	public Replacer() {
		super("replacer", "Manual Bulk Operations");
	}
	
	@Override
	public String getDescription() {
		return "This bot is only started by hand for manual bulk changes to the wiki.";
	}

	@Override
	public void run(RunContext ctx) throws IOException {
		if(run.getServer() == Wiki.SF) return;
		
		var mapping = Map.ofEntries(
				Map.entry("Thorp", "[[:Category:Thorps|Thorp]]"),
				Map.entry("Hamlet", "[[:Category:Hamlets|Hamlet]]"),
				Map.entry("Village", "[[:Category:Villages|Village]]"),
				Map.entry("Town", "[[:Category:Towns|Town]]"),
				Map.entry("City", "[[:Category:Cities|City]]"),
				Map.entry("Large town", "[[:Category:Large towns|Large town]]"),
				Map.entry("Large city", "[[:Category:Large cities|Large city]]"),
				Map.entry("Small town", "[[:Category:Small towns|Small town]]"),
				Map.entry("Small city", "[[:Category:Small cities|Small city]]"),
				Map.entry("Metropolis", "[[:Category:Metropolises|Metropolis]]"),
				Map.entry("Abandoned", "[[:Category:Abandoned settlements|Abandoned]]"),
				Map.entry("Ruins", "[[:Category:Ruins|Ruins]]")
		);
		
		var pages = run.getWiki().getPagesTranscluding(PageRef.of(NS.TEMPLATE, "City"));
		var pat = Pattern.compile("(\\| *size *= *)(.*?)( *\n)");
		for(var p:pages) {
			log.info("{}", p);
			var otxt = run.getWiki().getWikitext(p);
			var m = pat.matcher(otxt);
			var txt = m.replaceAll(mr->"$1"+mapping.getOrDefault(mr.group(2), mr.group(2))+"$3");
			if(!txt.equals(otxt)) {
				run.getWiki().edit(p, txt, "Standardize city sizes");
			}
		}
	}
	
	private boolean priceExists(String prices, String code) {
		return Pattern.compile("\\|"+Pattern.quote(code)+"[\\|=]").matcher(prices).find();
	}
	
	public static record Book(String website, String pubcode) {}
	public static record Release(String price, String pubcode, String releaseType, String note) {}
}
