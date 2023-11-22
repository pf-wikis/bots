package io.github.pfwikis.bots.common;

import java.io.Closeable;
import java.io.IOException;

import org.apache.commons.lang3.exception.ExceptionUtils;

import io.github.pfwikis.bots.common.bots.Bot;
import io.github.pfwikis.bots.common.bots.Run;
import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.MarkdownUtil;

@Slf4j
public class Discord implements Closeable {
	
	private static long CHANNEL_BOT_ACTIVITY = 1176658356551811105L;
	
	private JDA jda;
	@Setter
	private Run run;
	private Bot<?> bot;

	public Discord(Bot<?> bot) throws InterruptedException {
		this.bot = bot;
		jda = JDABuilder.createDefault(bot.getDiscordToken()).build();
		log.info("To add this bot to a server: {}", jda.getInviteUrl(Permission.MESSAGE_EXT_EMOJI, Permission.MESSAGE_SEND));
		jda.awaitReady();
		jda.getPresence().setActivity(Activity.customStatus("Running "+bot.getBotName()+(bot.isLocalMode()?" from a personal machine":"")));
	}

	@Override
	public void close() throws IOException {
		jda.getPresence().setActivity(null);
		jda.shutdown();
	}
	
	private boolean isPathfinder() {
		if(run != null && run instanceof SingleRun sr && sr.isStarfinder())
			return false;
		return true;
	}
	
	private StringBuilder messageHeader() {
		return new StringBuilder()
			//append Icon
			.append(isPathfinder()?"<:pf:1176801824620154951> ":"<:sf:1176801894086221824> ")
			//append link to bot
			.append(wikiLink(bot.getBotName(), "/wiki/User:"+bot.getBotName().replace(' ', '_')))
			.append(": ");
	}
	
	public String wikiLink(String linkText, String wikiRelativeURL) {
		return MarkdownUtil.maskedLink(linkText, "https://"+(isPathfinder()?"path":"star")+"finderwiki.com"+wikiRelativeURL);
	}

	public void reportException(Exception e) {
		try {
			var msg = messageHeader()
				.append("**Error**\n")
				//append error
				.append("```java\n")
				.append(ExceptionUtils.getStackTrace(e))
				.append("\n```");
			
			jda.getTextChannelById(CHANNEL_BOT_ACTIVITY)
				.sendMessage(msg.toString())
				.queue();
		} catch(Exception e2) {
			log.error("Failed to report error to discord", e2);
		}
	}

	public void report(String msg) {
		try {
			jda.getTextChannelById(CHANNEL_BOT_ACTIVITY)
				.sendMessage(messageHeader().append(msg).toString())
				.queue();
		} catch(Exception e) {
			reportException(e);
		}
	}

	
}
