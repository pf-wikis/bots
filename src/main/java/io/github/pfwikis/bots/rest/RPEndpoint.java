package io.github.pfwikis.bots.rest;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.scheduler.Scheduler;
import io.github.pfwikis.bots.utils.Jackson;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import spark.Request;
import spark.Response;
import spark.Route;

@Slf4j
@Setter
@Getter
@RequiredArgsConstructor
public abstract class RPEndpoint<T> implements Route {

	private static final Map<Class<? extends Route>, Instant> DISCORD_LOCKS = new HashMap<>();
	private final Class<T> parameterType;
	private final String endpoint;
	protected Wiki wiki;
	protected Scheduler scheduler;
	
	public abstract RPResult handle(RestProviderBot bot, T param) throws Exception;
	
	public String handle(Request request, Response response) throws Exception {
		var bot = new RestProviderBot();
		try {
			scheduler.initBot(wiki, scheduler.getDiscord(), bot);
			T param = Jackson.JSON.readValue(request.bodyAsBytes(), parameterType);
			log.info("Requesting {} with param {}", endpoint, param);
			var res = handle(bot, param);
			return Jackson.JSON.writeValueAsString(res);
		} catch(Exception e) {
			response.status(500);
			log.error("Failed to execute {}", endpoint, e);
			if(bot.getDiscord() == null) return null;
			Instant nextLog = DISCORD_LOCKS.computeIfAbsent(this.getClass(), c->Instant.MIN);
			if(nextLog.isBefore(Instant.now())) {
				DISCORD_LOCKS.put(this.getClass(), Instant.now());
				bot.getDiscord().reportException(bot, e);
			}
			return null;
		}
		
	}
	
	public static enum RPBlockType {
		WIKITEXT,
		HTML;
	}
	public static record RPBlock(RPBlockType type, String value) {}
	public static record RPResult(List<RPBlock> blocks, List<String> dependsOn) {}
}
