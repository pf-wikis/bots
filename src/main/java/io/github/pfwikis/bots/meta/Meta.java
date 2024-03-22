package io.github.pfwikis.bots.meta;

import java.util.stream.Collectors;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.Runner;
import io.github.pfwikis.bots.common.bots.SimpleBot;

@Parameters
public class Meta extends SimpleBot {

	public Meta() {
		super("meta", "VirenerusBot");
	}
	
	@Override
	protected String getBotPassword() {
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
		! Bot Name !! Last run !! Status
		%s
		|}
		""".formatted(bots);
	}

	@Override
	public void run() throws Exception {
		Runner.getAllBots().forEach(bot-> {
			var userPage = """
			{{Bot|Virenerus}}
			==Description==
			%s
			
			==Status==
			
			This bot is a sub bot of [[User:VirenerusBot|VirenerusBot]].
			
			The code for this bot can be found [%s here].
			
			{| class="wikitable" style="margin:auto"
			|-
			! Bot Name !! Last run !! Status
			{{User:%s/Status}}
			|}
			""".formatted(
				bot.getDescription(),
				"https://github.com/pf-wikis/bots/tree/main/src/main/java/"+bot.getClass().getName().replace(".", "/")+".java",
				bot.getBotName()
			);
			
			run.withMaster(wiki->wiki.editIfChange("User:"+bot.getBotName(), userPage, "Update "+bot.getBotName()+" description"));
		});
	}
}
