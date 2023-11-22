package io.github.pfwikis.bots.replacer;

import java.io.IOException;

import com.beust.jcommander.Parameters;
import com.beust.jcommander.internal.Maps;

import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
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
		
		
		var todo = Maps.newHashMap(
			"Positive Energy Plane", "Creation's Forge",
			"Positive Energy Plane/Inhabitants", "Creation's Forge/Inhabitants",
			"Positive Energy Plane/Locations", "Creation's Forge/Locations",
			"Positive Energy Plane/Nations", "Creation's Forge/Nations",
			"Positive Energy Plane/Settlements", "Creation's Forge/Settlements",
			"Negative Energy Plane", "Void",
			"Negative Energy Plane/Inhabitants", "Void/Inhabitants",
			"Negative Energy Plane/Locations", "Void/Locations",
			"Negative Energy Plane/Settlements", "Void/Settlements",
			"Shadow Plane", "Netherworld",
			"Shadow Plane/Inhabitants", "Netherworld/Inhabitants",
			"Shadow Plane/Locations", "Netherworld/Locations",
			"Shadow Plane/Nations", "Netherworld/Nations",
			"Shadow Plane/Organizations", "Netherworld/Organizations",
			"Shadow Plane/Settlements", "Netherworld/Settlements",
			"Material Plane", "Universe"
		);

		//rename categories
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
		for(var e : todo.entrySet()) {
			if(run.getWiki().pageExists("Category:"+e.getKey()))
				run.getWiki().rename("Category:"+e.getKey(), "Category:"+e.getValue(), false, "Renamed category "+e.getKey()+" to "+e.getValue());
		}
		
		
		/*
		//search and replace
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
		}*/
	}

}
