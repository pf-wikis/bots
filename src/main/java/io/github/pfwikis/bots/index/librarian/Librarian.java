package io.github.pfwikis.bots.index.librarian;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.SimpleBot;
import io.github.pfwikis.bots.index.bookreader.BookIndex;
import io.github.pfwikis.bots.index.common.GDrive;
import io.github.pfwikis.bots.index.common.IJackson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters(commandDescription = "Creates indices")
public class Librarian extends SimpleBot {

	public Librarian() {
		super("librarian", "Bot Librarian");
	}
	
	@Override
	public void run() throws Exception {
		var config = loadConfig(LibrarianConfig.class);
		collectPages(config);
		Collections.sort(config.getPages(), Comparator.comparing(LibrarianConfig.Page::getTitle));
		saveConfig(config, "Updated searched pages");
		
		var yamls = GDrive.INSTANCE.listFiles("fileExtension='yaml'");
		Collections.sort(yamls, Comparator.comparing(y->y.getName()));
		
		var books = new ArrayList<TokenizedBook>();
		for(var yaml:yamls) {
			try {
				books.add(Tokenizer.tokenize(yaml));
			} catch(Exception e) {
				log.error("Failed to tokenize "+yaml.getName());
				addException(e);
			}
		}
		System.out.println();
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
				if(config.getPages().stream().anyMatch(p->p.getTitle().equals(page.getFulltext()))) {
					continue;
				}
				
				var newPage = new LibrarianConfig.Page();
				newPage.setTitle(page.getFulltext());
				newPage.setType(type);
				config.getPages().add(newPage);
			}
		}
	}

	@Override
	protected String getDescription() {
		return
		"""
		This bot reads the documents created by [[User:Bot Book Reader]] and creates different indices from them.
		""";
	}


}
