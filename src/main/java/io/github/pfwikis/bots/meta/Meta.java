package io.github.pfwikis.bots.meta;

import java.util.Objects;
import java.util.stream.Collectors;

import com.beust.jcommander.Parameters;
import com.google.common.collect.MoreCollectors;

import io.github.pfwikis.bots.Runner;
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
	public String getBotPassword() {
		return rootPassword;
	}
	
	@Override
	public String getDescription() {
		var bots = Runner.getAllBots()
			.stream()
			.map(b->"{{User:"+b.getBotName()+"/Status}}")
			.sorted()
			.collect(Collectors.joining("\n"));
				
		return """
		This bot is owned by [[User:Virenerus]]. It is a manager bot that is responsible for managing multiple thematic sub bots, you might know.

		==Sub bots==
		{| class="wikitable" style="margin:auto"
		|-
		! Bot Name !! Status
		%s
		|}
		""".formatted(bots);
	}

	@Override
	public void run(RunContext ctx) throws Exception {
		var allBots = Runner.getAllBots()
				.stream()
				.collect(Collectors.groupingBy(b->b.getBotName()))
				.entrySet();
		for(var e:allBots) {
			var name = e.getKey();
			var bots = e.getValue();
			
			log.info("Checking if bot {} needs an account", name);
			run.withMaster(wiki->{
				try {
					if(!wiki.accountExists(name)) {
						bots.getFirst().setRootPassword(this.getRootPassword());
						log.info("Creating account for {}", name);
						wiki.createAccount(name, bots.getFirst().getBotPassword());
						wiki.addRight(name, "bot|sysop|techadmin", "never");
					}
				} catch(Exception ex) {
					log.error("Failed to check account {}", name, ex);
					System.exit(-1);
				}
			});
			
			bots.forEach(b-> {
				if(b instanceof SimpleBot sb)
					sb.setRun(run);
			});
			
			var descr = bots.stream().map(Bot::getDescription).filter(Objects::nonNull).findAny().get();
			log.info("Updating info for bot {}", name);
			var userPage = """
			{{Bot|Virenerus}}
			==Description==
			%s
			
			==Status==
			
			This bot is a sub bot of [[User:VirenerusBot|VirenerusBot]].
			
			The code for this bot can be found %s.
			
			{| class="wikitable" style="margin:auto"
			|-
			! Bot Name !! Status
			{{User:%s/Status}}
			|}
			""".formatted(
				descr,
				bots.stream().map(b->"["+urlTo(b.getClass())+" here]").collect(Collectors.joining(" and ")),
				name
			);
			
			run.withMaster(wiki->wiki.editIfChange("User:"+name, userPage, "Update "+name+" description"));
		}
	}

	public static String urlTo(Class<?> cl) {
		return "https://github.com/pf-wikis/bots/tree/main/src/main/java/"
				+cl.getName().replace(".", "/")+".java";
	}
}
