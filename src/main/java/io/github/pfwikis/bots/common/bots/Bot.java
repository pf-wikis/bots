package io.github.pfwikis.bots.common.bots;

import java.time.temporal.ChronoUnit;
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
public abstract class Bot<RUN extends Run> {
	
	protected final String id;
	protected final String botName;

	@Parameter(names = "--password")
	protected String rootPassword;
	
	protected RUN run;

	public abstract void run() throws Exception;

	public synchronized void start() {
		
		var runs = createRuns();
		
		
		for(var currentRun : runs) {
			this.run = currentRun;
			try {
				run();
			} catch (Exception e) {
				run.addException(e);
			}
			
			
			//create bot report
			try {
				var userPage = """
				{{Bot|Virenerus}}
				
				%s
				
				This bot is a sub bot of [[User:VirenerusBot|]].
				
				The code for this bot can be found [%s here].
				
				{| class="wikitable" style="margin:auto"
				|-
				! Bot Name !! Last run !! Status !! notes
				{{User:%s/Status}}
				|}
				
				
				%s
				""".formatted(
					getDescription(),
					"https://github.com/pf-wikis/bots/tree/main/src/main/java/"+this.getClass().getName().replace(".", "/")+".java",
					botName,
					run.hasReport()?("==Report==\n{{User:"+botName+"/Report}}"):""
				);
				
				run.withMaster(wiki->wiki.editIfChange("User:"+botName, userPage, "Update "+botName+" description"));
				
				var status = """
				|-
				| [[User:%s|]] || %s || %s || %s
				""".formatted(
					botName,
					run.getTimestamp().truncatedTo(ChronoUnit.SECONDS).toInstant().toString(),
					run.getExceptions().isEmpty()?"<span style=\"color:ForestGreen\">OK</span>":"<span style=\"color:Crimson\">ERROR</span>",
					run.getExceptions().isEmpty()?"":(
						run.getExceptions().stream()
							.map(ExceptionUtils::getStackTrace)
							.map(v->"<pre><nowiki>"+v+"</nowiki></pre>")
							.collect(Collectors.joining())
						+"[[Category:Articles with errors]]"
					)
				);
				
				run.withMaster(wiki->wiki.editIfChange("User:"+botName+"/Status", status, "Update "+botName+" status"));
				
				if(run.hasReport()) {
					run.withMaster(wiki->wiki.editIfChange("User:"+botName+"/Report", run.getReport().toString(), "Update "+botName+" report"));
				}
			} catch(Exception e) {
				log.error("Failed to create bot report for {}", botName, e);
				System.exit(-1);
			}
		}
	}

	protected abstract List<RUN> createRuns();

	protected void checkAccount(Run run) {
		run.withMaster(wiki->{
			try {
				if(!wiki.accountExists(botName)) {
					wiki.createAccount(botName, rootPassword+botName);
					wiki.addRight(botName, "bot|sysop|techadmin", "never");
				}
			} catch(Exception e) {
				log.error("Failed to check account {}", botName, e);
				System.exit(-1);
			}
		});
	}

	protected abstract String getDescription();

}
