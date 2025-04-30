package io.github.pfwikis.bots.rest.endpoints.citetemplate.model;

import java.util.ArrayList;
import java.util.List;

import io.github.pfwikis.bots.common.model.subject.PageRef;
import io.github.pfwikis.bots.common.model.subject.SemanticSubject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SectionDef extends BookPart {
	private BookPart parent;
	private SemanticSubject subject;
	private Integer endPage;
	private List<PageRef> authors = new ArrayList<>();
	private boolean isSubsection;
	private List<SectionDef> subSections = new ArrayList<>();
	
	@Override
	protected String makeAuthors() {
		if(authors != null && !authors.isEmpty())
			return super.makeAuthors();
		return parent.makeAuthors();
	}
}