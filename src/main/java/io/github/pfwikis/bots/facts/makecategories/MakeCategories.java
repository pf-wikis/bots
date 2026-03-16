package io.github.pfwikis.bots.facts.makecategories;

import java.io.IOException;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.api.responses.SemanticAsk.Result;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class MakeCategories extends SimpleBot {
	
	public MakeCategories() {
		super("make-categories", "Facts Master");
	}

	@Override
	public void run(RunContext ctx) throws IOException, InterruptedException {
		var res = run.getWiki().semanticAsk(
				Print.class,
				"[[Gallery page::+]][[Represented by page::+]]|?Fact type=factType|?Gallery page=gallery|?Represented by page=main"
		);
		
		for(var r:res) {
			if(r.getPrintouts().gallery.getExists().equals("1")) continue;
			var entries = run.getWiki().semanticAsk(Printout.class, "[["+r.getPrintouts().gallery.getPage()+"]]").size();
			if(entries == 0) continue;
			
			run.getWiki().edit(
				r.getPrintouts().gallery.getPage(),
				"""
				{{Main|%s}}
				{{DEFAULTSORT:%s}}
				[[Category:Artwork by source]]
				""".formatted(r.getPrintouts().main.getPage(), r.getPrintouts().main.getPage()),
				"Created wanted artwork category"
			);
		}
	}
	
	private static record Print(
		Result<?> factType,
		Result<?> gallery,
		Result<?> main
	) {}
	
	private static record Printout() {}
	
	@Override
	public String getDescription() {
		return null;
	}
}
