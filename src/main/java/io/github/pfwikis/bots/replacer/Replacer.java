package io.github.pfwikis.bots.replacer;

import java.io.IOException;
import java.util.regex.Pattern;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
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
			
				);
				if(!txt.equals(ntxt)) {
					run.getWiki().edit(using.getTitle(), ntxt, "Replace Cite template with new usage");
				}
			}*/
		}
	}
}
