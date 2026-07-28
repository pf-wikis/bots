package io.github.pfwikis.bots.rest.endpoints.infobox;

import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.model.PageTitle;
import io.github.pfwikis.bots.common.api.responses.SemanticSubject;
import lombok.Data;

@Data
public class RPInfoboxParam {
	private PageTitle factsPage;
	private SemanticSubject.Container semanticSubject;

	public boolean validate() {
		return factsPage != null && factsPage.getNs().equals(NS.FACTS) && semanticSubject != null;
	}
}