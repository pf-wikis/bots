package io.github.pfwikis.bots.common;

import java.io.Closeable;
import java.io.IOException;

import org.apache.commons.lang3.exception.ExceptionUtils;

import io.github.pfwikis.bots.common.bots.Bot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.utils.MarkdownUtil;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;

@Slf4j
@RequiredArgsConstructor
public class Discord implements Closeable {
	
	private static long CHANNEL_BOT_ACTIVITY = 1176658356551811105L;
	private static long CHANNEL_ADMINS = 886300705281941514L;
	
	private JDA jda;
	private boolean init = false;
	private final String discordToken;
	

	public synchronized void init() {
		if(!init) {
			try {
				jda = JDABuilder.createDefault(discordToken).build();
				log.info("To add this bot to a server: {}", jda.getInviteUrl(Permission.MESSAGE_EXT_EMOJI, Permission.MESSAGE_SEND));
				jda.awaitReady();
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
			jda.getPresence().setStatus(OnlineStatus.OFFLINE);
			jda.shutdown();
		}
	}
	
	private StringBuilder messageHeader(Bot<?> bot) {
		return new StringBuilder()
			//append Icon
			.append(bot.getWiki()==Wiki.PF?"<:pf:1176801824620154951> ":"<:sf:1176801894086221824> ")
			//append link to bot
			.append(wikiLink(bot.getWiki(), bot.getBotName(), "/wiki/User:"+bot.getBotName()))
			.append(": ");
	}
	
	public static String wikiLink(Wiki wiki, String linkText, String wikiRelativeURL) {
		return MarkdownUtil.maskedLink(linkText, wiki.getUrl()+wikiRelativeURL.replace(' ', '_'));
	}

	public void reportException(Bot<?> bot, Exception e) {
		reportException(bot, ExceptionUtils.getStackTrace(e));
	}
	
	public void reportException(Bot<?> bot, String txt) {
		try {
			var msg = messageHeader(bot)
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

	public void report(Bot<?> bot, String msg) {
		try {
			init();
			jda.getTextChannelById(CHANNEL_BOT_ACTIVITY)
				.sendMessage(messageHeader(bot).append(msg).toString())
				.queue();
		} catch(Exception e) {
			reportException(bot, e);
		}
	}
	
	public void reportToAdmins(Bot<?> bot, String msg) {
		try {
			init();
			jda.getTextChannelById(CHANNEL_ADMINS)
				.sendMessage(new MessageCreateBuilder()
					.setContent(messageHeader(bot).append(msg).toString())
					.build()
				)
				.queue();
		} catch(Exception e) {
			reportException(bot, e);
		}
	}
}
