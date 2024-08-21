package io.github.pfwikis.bots.common;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.Uninterruptibles;

import io.github.pfwikis.bots.common.bots.Bot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
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
				log.info("To add this bot to a server: {}", jda
					.getInviteUrl(
						Permission.MESSAGE_EXT_EMOJI,
						Permission.MESSAGE_SEND,
						Permission.MESSAGE_MANAGE
				));
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
				.append("\n```\n")
				.toString();
			
			init();
			
			var channel = jda.getTextChannelById(CHANNEL_BOT_ACTIVITY);
			
			var last100 = channel.getIterableHistory()
				.takeAsync(30)
				.get();
			
			var counter = new AtomicInteger(1);
			last100.forEach(old -> {
				if(old.getContentRaw().startsWith(msg) && old.getReactions().isEmpty()) {
					counter.addAndGet(Optional.ofNullable(
							Ints.tryParse(old.getContentRaw().replaceAll("(?s)^.*x (\\d+)$", "$1"))
					).orElse(1));
				}
				old.delete().queue();
			});
			
			jda.getTextChannelById(CHANNEL_BOT_ACTIVITY)
				.sendMessage(msg+(counter.get()>1?"x "+counter.get():""))
				.queue();
		} catch(Exception e2) {
			log.error("Failed to report error to discord", e2);
		}
	}
	
	public void report(String msg) {
		init();
		jda.getTextChannelById(CHANNEL_BOT_ACTIVITY)
			.sendMessage(msg)
			.queue();
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

	public void removeTooManyErrors() {
		init();
		var channel = jda.getTextChannelById(CHANNEL_BOT_ACTIVITY);
		var lastMsg = channel.getLatestMessageId();
		boolean repeat = true;
		while(repeat) {
			repeat = false;
			var lastFew = channel.getHistoryBefore(lastMsg, 100).complete().getRetrievedHistory();
			var toDelete = new ArrayList<Message>();
			for(int i=1;i<lastFew.size()-1;i++) {
				if(
					lastFew.get(i-1).getContentRaw().contains("**Error**")
					&& lastFew.get(i+0).getContentRaw().contains("**Error**")
					&& lastFew.get(i+1).getContentRaw().contains("**Error**")
				)  {
					toDelete.add(lastFew.get(i));
				}
			}
			if(!toDelete.isEmpty()) {
				log.info("Deleting {} messages", toDelete.size());
				channel.purgeMessages(toDelete);
				repeat = true;
			}
		}
	}
}
