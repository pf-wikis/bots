package io.github.pfwikis.bots.index.librarian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.beust.jcommander.Parameters;
import com.google.common.collect.HashMultiset;

import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.index.common.GDrive;
import io.github.pfwikis.bots.utils.Jackson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class Librarian extends SimpleBot {

	public Librarian() {
		super("librarian", "Bot Librarian");
	}
	
	@Override
	public void run(RunContext ctx) throws Exception {
		var config = loadConfig(LibrarianConfig.class);
		collectPages(config);
		Collections.sort(config.getPages(), Comparator.comparing(LibrarianConfig.Page::getTitle));
		saveConfig(config, "Updated searched pages");
		
		var yamls = GDrive.INSTANCE.listFiles("fileExtension='yaml' AND "+GDrive.str(GDrive.DIR_INDICES)+" in parents");
		Collections.sort(yamls, Comparator.comparing(y->y.getName()));
		var specialTokens = Tokenizer.createSpecialTokens(config);
		
		var books = new ArrayList<TokenizedBook>();
		var allTokens = HashMultiset.<String>create();
		for(var yaml:yamls) {
			try {
				var tokenized = Tokenizer.tokenize(specialTokens, yaml);
				allTokens.addAll(tokenized.getTokens().keySet());
				books.add(tokenized);
			} catch(Exception e) {
				log.error("Failed to tokenize "+yaml.getName());
				reportException(e);
			}
		}
		
		
		for(var book:books) {
			double mostOccurences = book.getTokens().entrySet().stream()
					.mapToInt(t->t.getValue().getOccurences().size())
					.max().orElse(0);
			var tokens = new ArrayList<MappedBook.Token>();
			for(var token:book.getTokens().entrySet()) {
				var word = token.getKey();
				var tf = 0.5+0.5*token.getValue().getOccurences().size()/mostOccurences;
				var idf = Math.log((double)books.size()/allTokens.count(word));
				tokens.add(new MappedBook.Token(token.getKey(), tf*idf, token.getValue().getOccurences()));
			}
			Collections.sort(tokens, Comparator.comparing(t->-t.getTfidf()));
			GDrive.INSTANCE.createOrUpdateFile(
				GDrive.DIR_MAPPED,
				book.getBook().getName()+".yaml",
				"application/yaml",
				Jackson.YAML.writeValueAsBytes(new MappedBook(
					book.getBook().getName(),
					book.getBook().getBookId(),
					book.getBook().getBookModifiedTime(),
					book.getBook().isChapterBased(),
					book.getBook().getPageOffset(),
					tokens
				))
			);
		}
		log.info("Done");
	}

	private static final String[] IB_TYPES = {
			"Abyssal realm",
			"City",
			"City district",
			"Cosmos",
			"Creature",
			"Deity",
			"Hellknight Orders",
			"Historic city",
			"Magic item",
			"Magical vehicle",
			"Mindscape",
			"Nation",
			"Organization",
			"Person",
			"Planar vehicle",
			"Plane",
			"Ship",
			"Spell",
			"Vehicle"};
	private void collectPages(LibrarianConfig config) {
		for(var type:IB_TYPES) {
			var pages = run.getWiki().semanticAsk("[[:+]][[Has infobox type::"+type+"]]");
			
			for(var page:pages) {
				if(config.getPages().stream().anyMatch(p->p.getTitle().equals(page.getPage()))) {
					continue;
				}
				
				var newPage = new LibrarianConfig.Page();
				newPage.setTitle(page.getPage());
				newPage.setType(type);
				config.getPages().add(newPage);
			}
		}
	}

	@Override
	public String getDescription() {
		return
		"""
		This bot reads the documents created by [[User:Bot Book Reader]] and creates different indices from them.
		""";
	}


}
