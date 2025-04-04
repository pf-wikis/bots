package io.github.pfwikis.bots.rest.endpoints.citetemplate;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class RPCiteParam {
	private String factsPage;
	private String location;
	private String endPage;
	private String showAs;

	public boolean validate() {
		return factsPage != null && factsPage.startsWith("Facts:");
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
