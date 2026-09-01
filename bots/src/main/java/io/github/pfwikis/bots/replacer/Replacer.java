package io.github.pfwikis.bots.replacer;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.api.generated.params.NS;
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
		var pages = run.getWiki().getPagesInNamespace(NS.FACTS);
		for(var p:pages) {
			log.info("{}", p.getTitle());
			var otxt = run.getWiki().getWikitext(p);
			var txt = otxt.replaceAll(
				"(\\| *)Quantity( *=)",
				"$1Item quantity$2"
			);
			if(!txt.equals(otxt)) {
				run.getWiki().edit(p, txt, "Renamed Quantity to Item quantity");
			}
		}
	}
	
	private boolean priceExists(String prices, String code) {
		return Pattern.compile("\\|"+Pattern.quote(code)+"[\\|=]").matcher(prices).find();
	}
	
	public static record Book(String website, String pubcode) {}
	public static record Release(String price, String pubcode, String releaseType, String note) {}
}
