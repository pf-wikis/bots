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
	
	private List<String> pages = List.of("Bot Activity Checker",
			"Bot Article of the Week",
			"Bot Assistant",
			"Bot Blog Facts",
			"Bot Facts Master",
			"Bot Maintenance",
			"Bot Manual Bulk Operations",
			"Bot Map Search Page",
			"Bot News Feed Reader",
			"Bot Page Syncer",
			"Bot Paizo Retriever",
			"Bot Rest Provider",
			"Bot Template Styles");
	
	@Override
	public void run(RunContext ctx) throws IOException {
		for(var r:new PageTitle[] {PageTitle.of(NS.USER, "VirenerusBot")}) {
			for(var sub:run.getWiki().getSubPages(r)) {
				if(!sub.getTitle().getName().contains("/Statistics/"))
					run.getWiki().delete(sub, "No longer used");
			}
		}
	}
	
	private boolean priceExists(String prices, String code) {
		return Pattern.compile("\\|"+Pattern.quote(code)+"[\\|=]").matcher(prices).find();
	}
	
	public static record Book(String website, String pubcode) {}
	public static record Release(String price, String pubcode, String releaseType, String note) {}
}
