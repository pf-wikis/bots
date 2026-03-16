package io.github.pfwikis.bots.meta;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.Runner;
import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.model.PageRef;
import io.github.pfwikis.bots.common.bots.Bot;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class Meta extends SimpleBot {

	public Meta() {
		super("meta", "VirenerusBot");
	}
	
	@Override
	public String getDescription() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void run(RunContext ctx) throws Exception {
		var allBots = Runner.getAllBots()
				.stream()
				.filter(b->!b.getClass().equals(this.getClass()))
				.collect(Collectors.groupingBy(b->b.getBotName()))
				.entrySet()
				.stream()
				.sorted(Comparator.comparing(e->e.getKey()))
				.toList();
		
		var sb = new StringBuilder();
		sb.append("{{Bot|Virenerus}}")
			.append("This bot is owned by [[User:Virenerus]].\n")
			.append("It is split up into multiple thematic sub bots.\n\n");
		
		for(var e:allBots) {
			var name = e.getKey();
			var bots = e.getValue();
			
			bots.forEach(b-> {
				if(b instanceof SimpleBot simpleBot)
					simpleBot.setRun(run);
			});
			
			var descr = bots.stream().map(Bot::getDescription).filter(Objects::nonNull).findAny().get();

			var userPage = """
			==%s==
			%s<br><br>
			%s
			
			
			""".formatted(
				name,
				bots.stream().map(b->"["+urlTo(b.getClass())+" Code]").collect(Collectors.joining(" and ")),
				descr				
			);
			sb.append(userPage);
		}
		
		run.getWiki().editIfChange(PageRef.of(NS.USER, botName), sb.toString(), "Automatic update of documentation");
	}

	public static String urlTo(Class<?> cl) {
		return "https://github.com/pf-wikis/bots/tree/main/src/main/java/"
				+cl.getName().replace(".", "/")+".java";
	}
}
