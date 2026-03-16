package io.github.pfwikis.bots.assistant;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.responses.SemanticAsk.Result;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class AssistantTaskGiver extends SimpleBot {
	
	private boolean taskDone = false;

	public AssistantTaskGiver() {
		super("assistant-task-giver", "Assistant");
	}
	
	@Override
	public String getDescription() {
		return new Assistant().getDescription();
	}

	@Override
	public void run(RunContext ctx) throws Exception {
		var cats = run.getWiki().semanticAsk(Object.class, "[[:Category:+]][[Subcategory of::Category:Pages with errors]]")
			.stream()
			.map(c->c.getPage())
			.distinct()
			.collect(Collectors.toSet());
		
		
		var res = run.getWiki().semanticAsk(Printouts.class, "[[Category:Pages with errors]]|?Category=categories");
		res.removeIf(r->r.getPage().getTitle().getNs().equals(NS.USER));
		if(res.isEmpty()) {
			return;
		}
		
		var sb = new StringBuilder()
			.append("I have a new task for you human people. There are a number of pages that need fixing. Please and thank you!\n");
		for(var p:res.subList(0, Math.min(res.size(), 10))) {
			var errors = new HashSet<>(cats);
			errors.retainAll(p.getPrintouts().getCategories().stream().map(c->c.getPage()).toList());
			sb
				.append("* ")
				.append(Discord.wikiLink(run.getServer(), p.getPage().getTitle().toString(), p.getFullurl()))
				.append(" for ");
			if(errors.isEmpty()) {
				sb.append("an uncategorized error, please check the page");
			}
			else {
				sb.append(errors.stream()
						.map(c->c.getTitle().getName())
						.map(c->StringUtils.removeStart(c, "Pages "))
						.map(c->StringUtils.removeStart(c, "with "))
						.collect(Collectors.joining(", ")));
			}
			sb.append("\n");
		}
		if(res.size()>10) {
			sb.append("... and ")
				.append(res.size()-10)
				.append(" more in ")
				.append(Discord.wikiLink(run.getServer(), "Pages with errors", "/wiki/Category:Pages_with_errors"));
		}
		log.info("New human task:\n{}", sb);
		discord.reportToTalkChannel(run.getServer(), this, sb.toString(), true);
	}
	
	@Data
	public static class Printouts {
		private List<Result<?>> categories;
	}
}
