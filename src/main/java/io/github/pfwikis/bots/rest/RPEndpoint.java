package io.github.pfwikis.bots.rest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import org.apache.commons.lang3.StringUtils;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.rest.RPEndpoint.RPBlock;
import io.github.pfwikis.bots.rest.RPEndpoint.RPBlockType;
import io.github.pfwikis.bots.rest.RPEndpoint.RPResult;
import io.github.pfwikis.bots.scheduler.Scheduler;
import io.github.pfwikis.bots.utils.Jackson;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Singular;
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
		T param = null;
		try {
			try {
				scheduler.initBot(wiki, scheduler.getDiscord(), bot);
				param = Jackson.JSON.readValue(request.bodyAsBytes(), parameterType);
				log.info("Requesting {} with param {}", endpoint, param);
				var res = handle(bot, param);
				return Jackson.JSON.writeValueAsString(res);
			} catch(Exception e) {
				Throwable potentiallySafe = e;
				//see if there is a safe messag ein the cause stack to return
				while(potentiallySafe!=null) {
					if(potentiallySafe instanceof SafeException se) {
						return Jackson.JSON.writeValueAsString(error(
							se.getFactsPage(),
							StringUtils.removeStart(e.getMessage(), SafeException.class.getName()+": ")
						));
					}
					potentiallySafe = potentiallySafe.getCause();
				}
				throw e;
			}
		} catch(Exception e) {
			response.status(500);
			log.error("Failed to execute {} on {}", endpoint, param, e);
			if(bot.getDiscord() == null || scheduler.isLocalMode()) return null;
			Instant nextLog = DISCORD_LOCKS.computeIfAbsent(this.getClass(), c->Instant.MIN);
			if(nextLog.isBefore(Instant.now())) {
				DISCORD_LOCKS.put(this.getClass(), Instant.now());
				bot.getDiscord().reportException(bot, e);
			}
			return null;
		}
		
	}
	
	protected RPResult error(String factsPage, String cause, String... extraCategories) {
		var sb = new StringBuilder()
				.append("{{Error|")
				.append(cause)
				.append("}}");
		for(var cat:extraCategories) {
			sb.append("[[").append(StringUtils.prependIfMissing(cat, "Category")).append("]]");
		}
		return RPResult.builder()
			.block(new RPBlock(RPBlockType.WIKITEXT, sb.toString()))
			.dependsOn(factsPage!=null?List.of(factsPage):List.of())
			.build();
	}
	
	public static enum RPBlockType {
		WIKITEXT,
		HTML;
	}
	public static record RPBlock(RPBlockType type, String value) {}
	
	@Builder
	@Getter @Setter
	public static class RPResult {
		@Builder.Default
		private UUID uuid = UUID.randomUUID();
		@Singular
		private List<RPBlock> blocks;
		@Singular("dependency")
		private List<String> dependsOn;
		private String headItem;
		private Object data;
	}
}
