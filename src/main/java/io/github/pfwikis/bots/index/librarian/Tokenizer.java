package io.github.pfwikis.bots.index.librarian;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.google.api.services.drive.model.File;

import io.github.pfwikis.bots.index.bookreader.BookIndex;
import io.github.pfwikis.bots.index.bookreader.BookReadingJob;
import io.github.pfwikis.bots.index.common.GDrive;
import io.github.pfwikis.bots.utils.Jackson;

public class Tokenizer {

	public static TokenizedBook tokenize(List<SpecialToken> specialTokens, File yaml) throws IOException {
		var bytes = GDrive.INSTANCE.downloadFile(yaml.getId());
		BookIndex index = Jackson.YAML.readValue(bytes, BookIndex.class);
		
		var result = new TokenizedBook(index);
		for(int i=0;i<index.getPageTexts().size();i++) {
			String text = index.getPageTexts().get(i);
			int pageNumber = i+index.getPageOffset();
			
			for(var st:specialTokens) {
				int count = StringUtils.countMatches(text, st.getWord());
				if(count > 0) {
					text = text.replaceAll(" *"+Pattern.quote(st.getWord())+" *", " ");
					result.addWord(st.getWord(), pageNumber, count);
				}
			}
			
			for(String word : text.split("\\s+")) {
				word = StringUtils.trimToNull(word);
				if(word == null) continue;
				
				result.addWord(word, pageNumber);
			}
		}

		return result;
	}

	public static List<SpecialToken> createSpecialTokens(LibrarianConfig config) {
		return config.getPages()
			.stream()
			.map(p->new SpecialToken(BookReadingJob.normalize(p.getTitle()), p))
			.sorted(Comparator.<SpecialToken, Integer>comparing(st->st.getWord().length()).reversed())
			.toList();
	}

}
