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
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.MarkdownUtil;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;

@Slf4j
public class Discord implements Closeable {
	
	private static long CHANNEL_BOT_ACTIVITY = 1176658356551811105L;
	private static long CHANNEL_ADMINS = 886300705281941514L;
	
	private JDA jda;
	@Setter
	private Run run;
	private Bot<?> bot;
	private boolean init = false;

	public Discord(Bot<?> bot) {
		this.bot = bot;
	}
	
	public synchronized void init() {
		if(!init) {
			try {
				jda = JDABuilder.createDefault(bot.getDiscordToken()).build();
				log.info("To add this bot to a server: {}", jda.getInviteUrl(Permission.MESSAGE_EXT_EMOJI, Permission.MESSAGE_SEND));
				jda.awaitReady();
				jda.getPresence().setActivity(Activity.customStatus("Running "+bot.getBotName()+(bot.isLocalMode()?" from a personal machine":"")));
				jda.getPresence().setStatus(OnlineStatus.ONLINE);
				init = true;
			} catch(Exception e) {
				log.error("Failed to init discord", e);
			}
		}
	}

	@Override
	public synchronized void close() throws IOException {
		if(init) {
			jda.getPresence().setActivity(Activity.customStatus("sleeping"));
			jda.getPresence().setStatus(OnlineStatus.OFFLINE);
			jda.shutdown();
		}
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
			.append(wikiLink(bot.getBotName(), "/wiki/User:"+bot.getBotName()))
			.append(": ");
	}
	
	public String wikiLink(String linkText, String wikiRelativeURL) {
		return MarkdownUtil.maskedLink(linkText, "https://"+(isPathfinder()?"path":"star")+"finderwiki.com"+wikiRelativeURL.replace(' ', '_'));
	}

	public void reportException(Exception e) {
		reportException(ExceptionUtils.getStackTrace(e));
	}
	
	public void reportException(String txt) {
		try {
			var msg = messageHeader()
				.append("**Error**\n")
				//append error
				.append("```java\n")
				.append(txt)
				.append("\n```");
			
			init();
			jda.getTextChannelById(CHANNEL_BOT_ACTIVITY)
				.sendMessage(msg.toString())
				.queue();
		} catch(Exception e2) {
			log.error("Failed to report error to discord", e2);
		}
	}

	public void report(String msg) {
		try {
			init();
			jda.getTextChannelById(CHANNEL_BOT_ACTIVITY)
				.sendMessage(messageHeader().append(msg).toString())
				.queue();
		} catch(Exception e) {
			reportException(e);
		}
	}
	
	public void reportToAdmins(String msg) {
		try {
			init();
			jda.getTextChannelById(CHANNEL_ADMINS)
				.sendMessage(new MessageCreateBuilder()
					.setContent(messageHeader().append(msg).toString())
					.build()
				)
				.queue();
		} catch(Exception e) {
			reportException(e);
		}
	}
}
