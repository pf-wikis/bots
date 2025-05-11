package io.github.pfwikis.bots.replacer;

import java.io.IOException;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.common.model.SemanticAsk.Result;

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
		var pages = run.getWiki().getPagesInCategory("Category:Pages_using_deprecated_template_parameters");
		
		for(var p:pages) {
			var txt = run.getWiki().getPageText(p.getTitle());
			var ntxt = txt.replaceFirst(
				"(\n *\\| *lat) *= *(\\S+) *\n *\\| *long *= *(\\S+).*",
				"$1long = $2, $3"
			);

			if(!txt.equals(ntxt)) {
				run.getWiki().edit(p.getTitle(), ntxt, "Replace lat and long with latlong.");
			}
		}
	}
	
	private static record P(Result<?> image, String website) {}
}
