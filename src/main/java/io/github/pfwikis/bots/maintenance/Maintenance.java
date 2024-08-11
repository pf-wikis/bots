package io.github.pfwikis.bots.maintenance;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class Maintenance extends SimpleBot {


	public Maintenance() {
		super("maintenance", "Bot Maintenance");
	}

	@Override
	protected void run() throws Exception {
		var entries = run.getWiki().semanticAsk("[[Created from::+]]|?Created from");
		entries.forEach(r-> {
			var generated = r.getPage();
			var from = r.getPrintouts().getCreatedFrom();
			
			if(from.getExists().equals("") && !run.getWiki().pageExists(from.getPage())) {
				run.getWiki().delete(generated, "Source [["+from.getPage()+"]] was deleted.");
			}
		});
	}

	@Override
	public String getDescription() {
		return """
			This bot executes regular maintenance tasks. Currently these are:
			* remove cite templates that were generated from a no longer existing facts page
			* remove infobox templates that were generated from a no longer existing facts page
		""";
	}

}
