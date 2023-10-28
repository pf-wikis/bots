package io.github.pfwikis.bots.index.librarian;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.Style;
import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.index.common.GDrive;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters(commandDescription = "Creates indices")
public class Librarian extends SimpleBot {

	public Librarian() {
		super("librarian", "Bot Librarian");
	}

	public static void main(String[] args) throws Exception {
		var br = new Librarian();
		br.run=new SingleRun(false);
		br.run();
		for(var e:br.run.getExceptions()) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() throws Exception {
		var yamls = GDrive.INSTANCE.listFiles("fileExtension='yaml'");
		var pool = Executors.newVirtualThreadPerTaskExecutor();
		
		
		Collections.sort(yamls, Comparator.comparing(y->y.getName()));
		run.report("I have read:");

		for(var book : books) {
			run
				.report("\n* [[")
				.report(StringUtils.removeEnd(book.getName(), ".pdf").replace('_', ' '))
				.report("|]] on ")
				.report(Style.DATE_FORMAT.format(Instant.ofEpochMilli(book.getModifiedTime().getValue()).atOffset(ZoneOffset.UTC)));

			var matchingYamls = yamls.stream().filter(y->y.getName().equals(book.getName()+".yaml")).toList();
			yamls.removeAll(matchingYamls);
			if(matchingYamls.size() == 0) {
				pool.execute(new BookReadingJob(run, book, null));
			}
			else if(matchingYamls.size() == 1) {
				pool.execute(new BookReadingJob(run, book, matchingYamls.get(0)));
			}
			else {
				run.addException(new RuntimeException("Multiple matching yamls for book "+book));
			}
		}
		
		
		//delete remaining yamls
		for(var yaml:yamls) {
			GDrive.INSTANCE.deleteFile(yaml.getId());
		}
		
		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.DAYS);
	}


	@Override
	protected String getDescription() {
		return
		"""
		This bot reads the documents created by [[User:Bot Book Reader]] and creates different indices from them.
		""";
	}


}
