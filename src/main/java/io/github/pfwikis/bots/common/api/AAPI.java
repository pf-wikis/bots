package io.github.pfwikis.bots.common.api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;


import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.api.generated.AAPIJson;
import io.github.pfwikis.bots.common.api.generated.AAPIMain;
import io.github.pfwikis.bots.common.api.generated.AAPIQuery;
import io.github.pfwikis.bots.common.api.generated.AAPIQueryTokens;
import io.github.pfwikis.bots.common.api.generated.params.AAPIJsonFormatversion;
import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;
import io.github.pfwikis.bots.common.api.generated.params.AAPIMainErrorformat;
import io.github.pfwikis.bots.common.api.generated.params.AAPIMainFormat.AAPIMainFormatModule;
import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryTokensType;
import io.github.pfwikis.bots.common.api.model.AAPIExceptions;
import io.github.pfwikis.bots.common.api.model.AAPIExceptions.AAPIException;
import io.github.pfwikis.bots.common.api.model.AAPIExceptions.AAPIRuntimeException;
import io.github.pfwikis.bots.common.api.responses.AAPIWrappedResponse;
import io.github.pfwikis.bots.common.api.responses.IResponse;
import io.github.pfwikis.bots.common.api.responses.QueryResponse;
import io.github.pfwikis.bots.utils.Jackson;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.JsonNode;

@Slf4j
@RequiredArgsConstructor
public class AAPI {
	
	private final Wiki wiki;
	private final String password;
	private final String secret;
	
	private final static CloseableHttpClient client = HttpClients.custom().build();
	private final EnumMap<AAPIQueryTokensType, String> tokens = new EnumMap<>(AAPIQueryTokensType.class);
	

	private static final AAPIMainFormatModule FORMAT = AAPIJson.create()
			.utf8(true)
			.formatversion(AAPIJsonFormatversion.V_2);
	
	@SneakyThrows
	public <T extends IResponse<T>> T run(AAPIMainActionModule action, Class<T> model) {
		var res = jsonRequest(action, null);
		
		var result = Jackson.JSON.treeToValue(res.result, model);
		result.validate();
		
		var continueMap = res.response.getContinueMap();
		while(continueMap != null && !continueMap.isEmpty()) {
			var moreResults = this.jsonRequest(action, res.response.getContinueMap());
			var more = Jackson.JSON.treeToValue(moreResults.result, model);
			more.validate();
			result.addContinuedResults(more);
			continueMap = moreResults.response.getContinueMap();
		}
		
		return result;
	}
	
	@SneakyThrows
	private RequestResult jsonRequest(AAPIMainActionModule action, Map<String, String> continueMap) {
		synchronized(client) {
			if(log.isDebugEnabled()) {
				log.debug(action.toString());
			}
			var request = (action.builder().requiresPost()?ClassicRequestBuilder.post():ClassicRequestBuilder.get())
					.setUri(wiki.getApiURL())
					.setCharset(StandardCharsets.UTF_8)
					.setHeader("anti-protection", secret)
					.setHeader("Authorization", "Bearer "+password);
			var main =AAPIMain.create().action(action)
				.format(FORMAT)
				.errorformat(AAPIMainErrorformat.PLAINTEXT);
			
			main.buildRequest(this, request, "");
			
			if(continueMap != null) {
				continueMap.forEach(request::addParameter);
			}
			
			
			String json = null;
			try {
				json = client.<String>execute(request.build(), resp->{
					return EntityUtils.toString(resp.getEntity(), StandardCharsets.UTF_8);
				});
			} catch(IOException e) {
				throw new AAPIException("Failed to connect to wiki", e);
			}
			
			var wrapper = Jackson.JSON.readValue(json, AAPIWrappedResponse.class);
			//print warnings
			if(wrapper.getWarnings() != null) {
				for(var w:wrapper.getWarnings()) {
					log.warn(w.getCode()+": "+w.getText());
				}
			}
			//if there are errors we fail
			if(wrapper.getErrors() != null && !wrapper.getErrors().isEmpty()) {
				if(wrapper.getErrors().size() == 1) {
					throw AAPIExceptions.from(wrapper.getErrors().getFirst());
				}
				else {
					throw new AAPIExceptions.AAPIMultipleExceptions(wrapper.getErrors().stream().map(AAPIExceptions::from).toList());
				}
			}
			var result = wrapper.getOtherFields().get(main.getAction().getKey().getJsonValue());
			
			return new RequestResult(wrapper, result);
		}
	}
	
	private record RequestResult(AAPIWrappedResponse response, JsonNode result) {}
	
	public String requestToken(AAPIQueryTokensType token) {
		synchronized(client) {
			return tokens.computeIfAbsent(token, t -> {
				try {
					var resp = jsonRequest(
							AAPIQuery.create()
								.meta(AAPIQueryTokens.create()
										.type(token)
								),
							null
						);
					
					return Objects.requireNonNull(Jackson.JSON.treeToValue(resp.result, QueryResponse.class)
							.getTokens()
							.get(token.getJsonValue()+"token"));
				} catch(Exception e) {
					throw new AAPIRuntimeException(e);
				}
			});
		}
	}
}
