package io.github.pfwikis.bots.scheduler;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class Scheduler {

	@Parameter(names = "--password")
	protected String rootPassword;
	@Parameter(names = "--discordToken")
	protected String discordToken;
	@Parameter(names = "--localMode")
	protected boolean localMode;
	@Parameter(names = "--matomoToken")
	protected String matomoToken;
	@Parameter(names = "--hostPfWiki")
	protected String hostPfWiki;
	@Parameter(names = "--hostSfWiki")
	protected String hostSfWiki;
	@Parameter(names = "--hostMatomo")
	protected String hostMatomo;

	public void start() throws Exception {
		log.info("Launched scheduler with:\n\thostPfWiki: {}\n\thostSfWiki: {}\n\thostMatomo: {}", hostPfWiki, hostSfWiki, hostMatomo);
		Thread.sleep(1000000);
	}

}
