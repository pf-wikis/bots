package io.github.pfwikis.bots.rest;

import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;

public class RestProviderBot extends SimpleBot {

	public RestProviderBot() {
		super("rest-provider", "Bot Rest Provider");
	}

	@Override
	protected void run(RunContext ctx) throws Exception {
	}

	@Override
	public String getDescription() {
		return "This bot is responsible for answering our custom parse functions.";
	}
}
