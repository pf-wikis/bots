package io.github.pfwikis.bots.rest.endpoints.citetemplate;

import org.apache.commons.lang3.StringUtils;

import com.google.common.primitives.Ints;

import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.model.PageTitle;
import io.github.pfwikis.bots.common.api.responses.SemanticSubject;
import lombok.Data;

@Data
public class RPCiteParam {
	private PageTitle factsPage;
	private Integer intLocation;
	private String location;
	private String endPage;
	private String showAs;
	private SemanticSubject.Container semanticSubject;

	public boolean validate() {
		if(location!=null)
			intLocation=Ints.tryParse(location);
		return factsPage != null && factsPage.getNs().equals(NS.FACTS) && semanticSubject != null;
	}

	public boolean hasLocation() {
		return !StringUtils.isBlank(location);
	}

	public boolean hasShowAs() {
		return !StringUtils.isBlank(showAs);
	}

	public boolean hasEndPage() {
		return !StringUtils.isBlank(endPage);
	}
}
