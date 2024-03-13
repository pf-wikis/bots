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

	public void start() throws Exception {
		try {

		} catch(Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(1000000);
	}

}
