package io.github.pfwikis.bots.common.api;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
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
import io.github.pfwikis.bots.utils.Retry;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.exc.MismatchedInputException;

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
		var res = jsonRequest(action, null, null);
		try {	
			var result = Jackson.JSON.treeToValue(res.result, model);
			result.validate();
			
			if(action.builder().requiresPagination()) {
				var continueMap = res.response.getContinueMap();
				while(continueMap != null && !continueMap.isEmpty()) {
					var moreResults = this.jsonRequest(action, null, res.response.getContinueMap());
					var more = Jackson.JSON.treeToValue(moreResults.result, model);
					more.validate();
					result.addContinuedResults(more);
					continueMap = moreResults.response.getContinueMap();
				}
			}
			
			return result;
		} catch(MismatchedInputException e) {
			throw new AAPIException("Failed to parse JSON:\n"+res.result, e);
		}
	}
	
	@SneakyThrows
	public <T> RequestResult<T> complexRun(AAPIMainActionModule action, String mappedField, JavaType model) {
		
		var res = jsonRequest(action, mappedField, null);
		try {	
			T result = Jackson.JSON.treeToValue(res.result, model);
			return new RequestResult<T>(res.response, result);
		} catch(MismatchedInputException e) {
			throw new AAPIException("Failed to parse JSON:\n"+res.result, e);
		}
	}
	
	@SneakyThrows
	private RawRequestResult jsonRequest(AAPIMainActionModule action, String mappedField, Map<String, String> continueMap) {
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
			
			var completeRequest = request.build(); 
			
			String json = requestWithRetry(completeRequest);
			
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
			var result = wrapper.getOtherFields().get(mappedField==null?main.getAction().getKey().getJsonValue():mappedField);
			
			return new RawRequestResult(wrapper, result);
		}
	}
	
	private static final Duration RETRY_DURATION = Duration.ofSeconds(30);
	private String requestWithRetry(ClassicHttpRequest request) {
		Callable<String> act = () -> client.<String>execute(request, resp->{
			String content = EntityUtils.toString(resp.getEntity(), StandardCharsets.UTF_8);
			if(resp.getCode()<300)
				return content;
			else
				throw new AAPIException("Status "+resp.getCode()+", Content:\n"+content);
		});
		try {
			return act.call();
		} catch(Exception first) {
			try {
				return Retry.forDuration(act, RETRY_DURATION, 5);
			} catch(Exception e) {
				var res = new RuntimeException("Failed after retrying", first);
				res.addSuppressed(e);
				throw res;
			}
		}
	}

	private static record RawRequestResult(AAPIWrappedResponse response, JsonNode result) {}
	public static record RequestResult<T>(AAPIWrappedResponse response, T result) {}
	
	public String requestToken(AAPIQueryTokensType token) {
		synchronized(client) {
			return tokens.computeIfAbsent(token, t -> {
				try {
					var resp = jsonRequest(
							AAPIQuery.create()
								.meta(AAPIQueryTokens.create()
										.type(token)
								),
							null,
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
