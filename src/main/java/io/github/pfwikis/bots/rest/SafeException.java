package io.github.pfwikis.bots.rest;

import lombok.Getter;

@Getter
public class SafeException extends RuntimeException {

	private final String factsPage;
	
	public SafeException(String factsPage, String msg) {
		super(msg);
		this.factsPage = factsPage;
	}
}
