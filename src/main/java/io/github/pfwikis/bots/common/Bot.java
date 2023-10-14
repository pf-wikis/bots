package io.github.pfwikis.bots.common;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.beust.jcommander.Parameter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter @Setter
@RequiredArgsConstructor
public abstract class Bot {
	
	private final String id;
	private final String botName;

	@Parameter(names = "--password")
	private String rootPassword;
	
	protected Run run;

	public abstract void run() throws IOException;

	public synchronized void start() {
		
		for(boolean starfinder : new boolean[] {false, true}) {
			run = new Run();
			try {
				run.masterWiki = new WikiAPI(starfinder, "VirenerusBot", rootPassword);
			} catch(Exception e) {
				log.error("Failed to log in as {}", botName, e);
				System.exit(-1);
			}
			
			checkAccount();
			
			try {
				run.wiki = new WikiAPI(starfinder, botName, rootPassword+botName);
			} catch(Exception e) {
				log.error("Failed to log in as {}", botName, e);
				System.exit(-1);
			}
			
			try {
				run();
			} catch (IOException e) {
				run.getExceptions().add(e);
			}
			
			
			//create bot report
			try {
				var report = """
				This account is a bot.
				
				%s
				
				This bot was created by [[User:Virenerus|]] and is a sub bot of [[User:VirenerusBot|]].
				
				The code for this bot can be found [%s here].
				
				{| class="wikitable" style="margin:auto"
				|-
				! Bot Name !! Last run !! Status !! notes
				{{User:%s/Status}}
				|}
				""".formatted(
					getDescription(),
					"https://github.com/pf-wikis/bots/tree/main/src/main/java/"+this.getClass().getName().replace(".", "/")+".java",
					botName);
				
				run.wiki.editIfChange("User:"+botName, report, "Update "+botName+" description");
				
				report = """
				|-
				| [[User:%s|]] || %s || %s || %s
				""".formatted(
					botName,
					run.getTimestamp().truncatedTo(ChronoUnit.SECONDS).toInstant().toString(),
					run.exceptions.isEmpty()?"<span style=\"color:ForestGreen\">OK</span>":"<span style=\"color:Crimson\">ERROR</span>",
					run.exceptions.isEmpty()?"":(
						run.exceptions.stream()
							.map(ExceptionUtils::getStackTrace)
							.map(v->"<pre><nowiki>"+v+"</nowiki></pre>")
							.collect(Collectors.joining())
						+"[[Category:Articles with errors]]"
					)
				);
				
				run.wiki.editIfChange("User:"+botName+"/Status", report, "Update "+botName+" status");
			} catch(Exception e) {
				log.error("Failed to create bot report for {}", botName, e);
				System.exit(-1);
			}
		}
	}
	
	private void checkAccount() {
		try {
			if(!run.masterWiki.accountExists(botName)) {
				run.masterWiki.createAccount(botName, rootPassword+botName);
			}
			run.masterWiki.addTemporaryRight(botName);
		} catch(Exception e) {
			log.error("Failed to check account {}", botName, e);
			System.exit(-1);
		}
		
	}

	protected abstract String getDescription();

	@Getter
	public static class Run {
		private WikiAPI masterWiki;
		private OffsetDateTime timestamp = OffsetDateTime.now();
		private List<Exception> exceptions = new ArrayList<>();
		private WikiAPI wiki;
	}
	
}
