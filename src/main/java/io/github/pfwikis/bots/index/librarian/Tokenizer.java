package io.github.pfwikis.bots.index.librarian;

import java.io.IOException;
import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;

import com.google.api.services.drive.model.File;

import io.github.pfwikis.bots.index.bookreader.BookIndex;
import io.github.pfwikis.bots.index.common.GDrive;
import io.github.pfwikis.bots.index.common.IJackson;

public class Tokenizer {

	public static TokenizedBook tokenize(File yaml) throws IOException {
		var bytes = GDrive.INSTANCE.downloadFile(yaml.getId());
		BookIndex index = IJackson.YAML.readValue(bytes, BookIndex.class);
		
		var result = new TokenizedBook(index);
		for(int i=0;i<index.getPageTexts().size();i++) {
			String text = index.getPageTexts().get(i);
			int pageNumber = i+index.getPageOffset();
			
			//TODO find and remove pagenames first
			
			for(String word : text.split("\\s+")) {
				word = StringUtils.trimToNull(word);
				if(word == null) continue;
				
				result.addWord(word, pageNumber);
			}
		}
		
		result.getTokens().entrySet()
			.stream()
			.sorted(Comparator.comparing(e->e.getValue().getOccurences().size()))
			.forEach(e->System.out.println(e.getKey()+" x"+e.getValue().getOccurences().size()));
		
		return result;
	}

}
