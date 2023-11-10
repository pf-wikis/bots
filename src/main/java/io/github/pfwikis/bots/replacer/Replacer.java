package io.github.pfwikis.bots.replacer;

import java.io.IOException;
import java.util.Map;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters(commandDescription = "For manual bulk operations")
public class Replacer extends SimpleBot {

	public Replacer() {
		super("replacer", "Bot Manual Bulk Operations");
	}
	
	@Override
	protected String getDescription() {
		return "This bot is only started by hand for manual bulk changes to the wiki.";
	}

	@Override
	public void run() throws IOException {
		if(run.isStarfinder())
			return;
		var todo = Map.of(
			"Half-elf creatures", "Aiuvarin creatures",
			"Aquatic half-elf/Inhabitants", "Aquatic Aiuvarin/Inhabitants"
		);
	
		for(var e : todo.entrySet()) {
			for(var p:run.getWiki().getPagesInCategory("Category:"+e.getKey())) {
				var text = run.getWiki().getPageText(p.getTitle());
				var newText = text.replaceAll("\\[\\[Category: *"+e.getKey()+" *((\\|[^\\]]*)?\\]\\])", "[[Category:"+e.getValue()+"$1");
				if(!newText.equals(text)) {
					run.getWiki().edit(p.getTitle(), newText, "Renamed category "+e.getKey()+" to "+e.getValue());
				}
				else {
					log.warn("No luck on {}", p.getTitle());
				}
			}
		}
	}

}
