package io.github.pfwikis.bots.rest.endpoints.infobox;

import io.github.pfwikis.bots.common.model.subject.SemanticSubject;
import lombok.Data;

@Data
public class RPInfoboxParam {
	private String factsPage;
	private SemanticSubject.Container semanticSubject;

	public boolean validate() {
		return factsPage != null && factsPage.startsWith("Facts:") && semanticSubject != null;
	}
}