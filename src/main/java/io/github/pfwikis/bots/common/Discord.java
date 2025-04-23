package io.github.pfwikis.bots.common;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
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
import net.dv8tion.jda.api.utils.FileUpload;
import net.dv8tion.jda.api.utils.MarkdownUtil;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;

@Slf4j
@RequiredArgsConstructor
public class Discord implements Closeable {
	
	private static long CHANNEL_BOT_ACTIVITY = 1176658356551811105L;
	private static long CHANNEL_BLOG_WATCH = 1287958801584099380L;
	private static long CHANNEL_ADMINS = 886300705281941514L;
	private static long CHANNEL_PF = 702358341443256361L;
	private static long CHANNEL_SF = 1094469746545672274L;
	
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
		return MarkdownUtil.maskedLink(linkText, StringUtils.prependIfMissing(wikiRelativeURL.replace(' ', '_'), wiki.getUrl()));
	}

	public void reportException(Bot<?> bot, Exception e) {
		var trace = ExceptionUtils.getStackTrace(e);
		String fullTrace = null;
		//limit because discord only allows 2k characters
		if(trace.length()>1000) {
			fullTrace = trace;
			trace=Optional.ofNullable(e.getMessage()).orElse("No message");
		}
		reportException(bot, trace, fullTrace);
	}
	
	public void reportException(Bot<?> bot, String txt) {
		String fullTrace = null;
		//limit because discord only allows 2k characters
		if(txt.length()>1000) {
			fullTrace = txt;
			txt=txt.substring(0,1000)+"\n…";
		}
		reportException(bot, txt, fullTrace);
	}
	
	private void reportException(Bot<?> bot, String txt, String fullTrace) {
		try {
			var msg = messageHeader(bot)
				.append("**Error**\n")
				//append error
				.append("```java\n")
				.append(txt)
				.append("\n```")
				.toString();
			
			init();
			
			var channel = jda.getTextChannelById(CHANNEL_BOT_ACTIVITY);
			
			var last30 = channel.getIterableHistory()
				.takeAsync(30)
				.get();
			
			var counter = new AtomicInteger(1);
			last30.forEach(old -> {
				if(old.getContentRaw().contains("Test"))
					System.out.println("aaa");
				
				if(old.getAuthor().getIdLong()!=jda.getSelfUser().getIdLong()) return;
				if(!old.getContentRaw().startsWith(msg)) return;
				if(!old.getReactions().isEmpty()) return;
				
				counter.addAndGet(Optional.ofNullable(
						Ints.tryParse(old.getContentRaw().replaceAll("(?s)^.*x (\\d+)$", "$1"))
				).orElse(1));
				old.delete().queue();
			});
			
			var msgData = new MessageCreateBuilder()
				.addContent(msg+(counter.get()>1?"\nx "+counter.get():""));
			if(fullTrace != null) {
				msgData.addFiles(FileUpload.fromStreamSupplier("tace.log", ()->new ByteArrayInputStream(fullTrace.getBytes(StandardCharsets.UTF_8))));
			}
			
			jda.getTextChannelById(CHANNEL_BOT_ACTIVITY)
				.sendMessage(msgData.build())
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
	
	public void reportToBlogWatch(Bot<?> bot, String msg, boolean suppressEmbeds) {
		reportTo(CHANNEL_BLOG_WATCH, bot, messageHeader(bot).append(msg).toString(), suppressEmbeds);
	}
	
	public void reportToAdmins(Bot<?> bot, String msg, boolean suppressEmbeds) {
		reportTo(CHANNEL_ADMINS, bot, messageHeader(bot).append(msg).toString(), suppressEmbeds);
	}
	
	public void reportToTalkChannel(Wiki wiki, Bot<?> bot, String msg, boolean suppressEmbeds) {
		reportTo(wiki==Wiki.PF?CHANNEL_PF:CHANNEL_SF, bot, messageHeader(bot).append(msg).toString(), suppressEmbeds);
	}
	
	private void reportTo(long channel, Bot<?> bot, String msg, boolean suppressEmbeds) {
		if(msg.length() > 1500) {
			var parts = msg.split("\n+");
			for(int i=0;i<parts.length;i++) {
				var p = parts[i];
				while(i+1<parts.length && p.length()+parts[i+1].length() < 1400) {
					i++;
					p+="\n"+parts[i];
				}
				reportTo(channel, bot, p, suppressEmbeds);
			}
			return;
		}

		try {
			init();
			jda.getTextChannelById(channel)
				.sendMessage(new MessageCreateBuilder().setContent(msg).build()
				)
				.setSuppressEmbeds(suppressEmbeds)
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
