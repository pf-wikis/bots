package io.github.pfwikis.bots.activitychecker;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.Runner;
import io.github.pfwikis.bots.common.Discord;
import io.github.pfwikis.bots.common.bots.Bot;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.common.model.AllusersQuery.WUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class ActivityChecker extends SimpleBot {

	public ActivityChecker() {
		super("activity-checker", "Bot Activity Checker");
	}

	@Override
	public void run() throws IOException, InterruptedException {
		var admins = run.getWiki().getAdmins();
		var oldAdmins = new ArrayList<WUser>();
		log.info("Found {} admins", admins.size());
		for(var user:admins) {
			var logEvents = run.getWiki().getLogEvents(ZonedDateTime.now().minus(6, ChronoUnit.MONTHS), user.getName());
			if(logEvents.isEmpty()) {
				oldAdmins.add(user);
			}
		}
		
		var botNames = Runner.getAllBots().stream().map(Bot::getBotName).collect(Collectors.toSet());
		oldAdmins.removeIf(u->botNames.contains(u.getName()));
		
		if(oldAdmins.size() > 1) {
			var list = oldAdmins.stream().map(u->Discord.wikiLink(run.getServer(), u.getName(), "/wiki/User:"+u.getName())).collect(Collectors.joining(", "));
			discord.reportToAdmins(this, "<@171724557248364544> the admins "+list
			+" have been inactive for more than 6 months and should lose their privileges.");
		}
		else if(oldAdmins.size() == 1) {
			var u = oldAdmins.get(0);
			discord.reportToAdmins(this, "<@171724557248364544> the admin "+Discord.wikiLink(run.getServer(), u.getName(), "/wiki/User:"+u.getName())
			+" has been inactive for more than 6 months and should lose their privileges.");
		}
	}
	
	@Override
	public String getDescription() {
		return """
		This bot checks the activity of administrators regularly. If an adiministrator is inactive for more
		than 6 months this bot will post a message about it into the discord admin channel.
		""";
	}

}
