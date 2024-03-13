package io.github.pfwikis.bots.scheduler;

import java.util.stream.Collectors;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.Runner;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class Scheduler extends SimpleBot {

	@Parameter(names = "--matomoToken")
	protected String matomoToken;
	@Parameter(names = "--hostPfWiki")
	protected String hostPfWiki;
	@Parameter(names = "--hostSfWiki")
	protected String hostSfWiki;
	@Parameter(names = "--hostMatomo")
	protected String hostMatomo;

	public Scheduler() {
		super("scheduler", "VirenerusBot");
	}
	
	@Override
	protected String getBotPassword() {
		return rootPassword;
	}

	@Override
	public void run() throws Exception {
		log.info("Host of pf wiki {}", hostPfWiki);
		Thread.sleep(1000000);
	}

	@Override
	protected String getDescription() {
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
}
