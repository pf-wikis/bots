package io.github.pfwikis.bots.scheduler;

import java.net.HttpURLConnection;
import java.net.URI;

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
	protected URI hostPfWiki = URI.create("https://pathfinderwiki.com");
	@Parameter(names = "--hostSfWiki")
	protected URI hostSfWiki = URI.create("https://starfinderwiki.com");
	@Parameter(names = "--hostMatomo")
	protected URI hostMatomo = URI.create("https://matomo.pathfinderwiki.com");

	public void start() throws Exception {
        var connection = (HttpURLConnection) hostPfWiki.toURL().openConnection();
        connection.setRequestMethod("HEAD");
        int code = connection.getResponseCode();
        log.info("PF Wiki returned " + code);
		Thread.sleep(1000000);
	}

}
