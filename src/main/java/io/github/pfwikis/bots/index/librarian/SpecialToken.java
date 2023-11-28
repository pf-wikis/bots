package io.github.pfwikis.bots.index.librarian;

import io.github.pfwikis.bots.index.librarian.LibrarianConfig.Page;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter @Setter
public class SpecialToken {

	private final String word;
	private final Page wikiPage;
}
