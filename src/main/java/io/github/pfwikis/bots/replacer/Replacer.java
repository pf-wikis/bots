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
			"Images of half-orcs", "Images of dromaars",
			"Images of half-elves", "Images of aiuvarins"
		);
		/*
		rename categories
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
		*/
		
		for(var e : todo.entrySet()) {
			for(var p:run.getWiki().getPagesInCategory("Category:"+e.getKey())) {
				if(!p.getTitle().startsWith("File:")) continue;
				
				var text = run.getWiki().getPageText(p.getTitle());
				var newText = text.replaceAll("(\\| *keyword\\d+ *= *)(half-elves)(\\s*[\\|\n])", "$1aiuvarins$3")
						          .replaceAll("(\\| *keyword\\d+ *= *)(half-orcs)(\\s*[\\|\n])", "$1dromaars$3");
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
