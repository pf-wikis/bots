package io.github.pfwikis.bots.index.bookreader;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameters;
import com.google.common.util.concurrent.MoreExecutors;

import io.github.pfwikis.bots.common.Style;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.index.common.GDrive;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class BookReader extends SimpleBot {

	private boolean localMode = false;
	
	public BookReader() {
		super("book-reader", "Bot Book Reader");
	}
	
	private StringBuilder report = new StringBuilder();
	
	@Override
	public void beforeRuns() throws Exception {
		var books = GDrive.INSTANCE.listFiles("mimeType='application/pdf'");
		var yamls = GDrive.INSTANCE.listFiles("fileExtension='yaml'");
		var pool = localMode
				?MoreExecutors.newDirectExecutorService()
				:Executors.newVirtualThreadPerTaskExecutor();
		
		
		Collections.sort(books, Comparator.comparing(b->b.getName()));
		report.append("I have read:");

		for(var book : books) {
			report
				.append("\n* [[")
				.append(StringUtils.removeEnd(book.getName(), ".pdf").replace('_', ' '))
				.append("|]] on ")
				.append(Style.DATE_FORMAT.format(Instant.ofEpochMilli(book.getModifiedTime().getValue()).atOffset(ZoneOffset.UTC)));

			var matchingYamls = yamls.stream().filter(y->y.getName().equals(book.getName()+".yaml")).toList();
			yamls.removeAll(matchingYamls);
			if(matchingYamls.size() == 0) {
				pool.execute(new BookReadingJob(this, book, null));
			}
			else if(matchingYamls.size() == 1) {
				pool.execute(new BookReadingJob(this, book, matchingYamls.get(0)));
			}
			else {
				reportException(new RuntimeException("Multiple matching yamls for book "+book));
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
	public void run() throws Exception {
		discord.report(report.toString());
	}


	@Override
	protected String getDescription() {
		return
		"""
		This bot reads the PDFs and transforms them into word lists.
		All of this happens offsite, but the results are documented here for transparency.
		[[User:Bot Librarian]] is the bot that creates indices from that.
		""";
	}


}
