package io.github.pfwikis.bots.activitychecker;

import java.io.IOException;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class ActivityChecker extends SimpleBot {

	public ActivityChecker() {
		super("activity-checker", "Bot Activity Checker");
	}

	@Override
	public void run() throws IOException, InterruptedException {
		
	}
	
	@Override
	protected String getDescription() {
		return """
		This bot checks the activity of administrators regularly and reports to the official discord.
		""";
	}

}
