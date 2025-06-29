package io.github.pfwikis.bots.meta;

import java.util.stream.Collectors;

import com.beust.jcommander.Parameters;

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
		Runner.getAllBots().forEach(bot-> {
			log.info("Checking if bot {} needs an account", bot.getBotName());
			run.withMaster(wiki->{
				try {
					if(!wiki.accountExists(bot.getBotName())) {
						bot.setRootPassword(this.getRootPassword());
						log.info("Creating account for {}", bot.getBotName());
						wiki.createAccount(bot.getBotName(), bot.getBotPassword());
						wiki.addRight(bot.getBotName(), "bot|sysop|techadmin", "never");
					}
				} catch(Exception e) {
					log.error("Failed to check account {}", bot.getBotName(), e);
					System.exit(-1);
				}
			});
			
			
			if(bot instanceof SimpleBot sb)
				sb.setRun(run);
			var descr = bot.getDescription();
			if(descr == null) return;
			log.info("Updating info for bot {}", bot.getBotName());
			var userPage = """
			{{Bot|Virenerus}}
			==Description==
			%s
			
			==Status==
			
			This bot is a sub bot of [[User:VirenerusBot|VirenerusBot]].
			
			The code for this bot can be found [%s here].
			
			{| class="wikitable" style="margin:auto"
			|-
			! Bot Name !! Status
			{{User:%s/Status}}
			|}
			""".formatted(
				descr,
				urlTo(bot.getClass()),
				bot.getBotName()
			);
			
			run.withMaster(wiki->wiki.editIfChange("User:"+bot.getBotName(), userPage, "Update "+bot.getBotName()+" description"));
		});
	}

	public static String urlTo(Class<?> cl) {
		return "https://github.com/pf-wikis/bots/tree/main/src/main/java/"
				+cl.getName().replace(".", "/")+".java";
	}
}
