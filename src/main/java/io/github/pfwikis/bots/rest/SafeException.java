package io.github.pfwikis.bots.rest;

import io.github.pfwikis.bots.common.api.model.PageTitle;
import lombok.Getter;

@Getter
public class SafeException extends RuntimeException {

	private final PageTitle factsPage;
	
	public SafeException(PageTitle factsPage, String msg) {
		super(msg);
		this.factsPage = factsPage;
	}
}
