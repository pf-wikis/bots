package io.github.pfwikis.bots.rest;

import lombok.Data;

@Data
public class DefaultRPParam {
	private String factsPage;

	public boolean validate() {
		return factsPage != null && factsPage.startsWith("Facts:");
	}
}