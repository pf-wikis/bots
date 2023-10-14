package io.github.pfwikis.bots.common;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.fastily.jwiki.core.Wiki;
import io.github.pfwikis.bots.common.model.QueryListUsers;
import io.github.pfwikis.bots.common.model.QueryResponse;
import io.github.pfwikis.bots.common.model.QueryTokens;
import okhttp3.HttpUrl;

public class WikiAPI {

	private Wiki wiki;

	public WikiAPI(boolean starfinder, String name, String password) {
		String url = starfinder?"https://starfinderwiki.com":"https://pathfinderwiki.com";
		wiki = new Wiki.Builder()
	            .withDomain(url)
	            .withApiEndpoint(HttpUrl.get(url+"/w/api.php"))
	            .withLogin(name, password)
	            .build();
	}

	public void editIfChange(String page, String content, String reason) {
		var oldText = wiki.getPageText(page);
		if(!content.equals(oldText)) {
			wiki.edit(page, content, reason);
		}
	}

	public boolean accountExists(String botName) throws IOException {
		var resp = query(
			QueryListUsers.class,
			"list", "users",
			"ususers", botName
		);
		
		return !Boolean.TRUE.equals(resp.users()[0].missing());
	}

	public void createAccount(String botName, String password) throws IOException {
		String token = requestToken("createaccount");
		
		var resp = wiki.basicPOST(
			"createaccount", new HashMap<>(Map.of(
			"format", "json",
			"utf8", "1",
			"formatversion", "2",
			"createreturnurl", "https://localhost",
			"createtoken", token,
			"username", botName,
			"password", password,
			"retype", password,
			"reason", "VirenerusBot creates sub bots for better maintainability"
		)));
		var json = resp.body().string();
		if(!json.contains("\"PASS\"")) {
			throw new IllegalStateException("Could not create user "+botName+". Response was:\n"+json);
		}
	}
	
	public void addRight(String botName, String rights, String expiry) throws IOException {
		
		String token = requestToken("userrights");
		
		var resp = wiki.basicPOST(
			"userrights", new HashMap<>(Map.of(
			"format", "json",
			"utf8", "1",
			"formatversion", "2",
			"user", botName,
			"add", rights,
			"expiry", expiry,
			"reason", "VirenerusBot sub bots get temporary permissions",
			"token", token
		)));
		var json = resp.body().string();
		if(json.contains("\"error\"")) {
			throw new IllegalStateException("Could not create user "+botName+". Response was:\n"+json);
		}
		
		
	}

	private String requestToken(String token) {
		return Objects.requireNonNull(query(QueryTokens.class,
			"meta", "tokens",
			"type", token
		).tokens().get(token+"token"));
	}
	
	private static final ObjectMapper JACKSON = new ObjectMapper()
		.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	private <T> T query(Class<T> type, String... params) {
		params = ArrayUtils.addAll(params,
			"format", "json",
			"utf8", "1",
			"formatversion", "2"
		);
		var response = wiki.basicGET("query", params);
		try (var in = response.body().byteStream()) {
			QueryResponse<T> resp = JACKSON.readValue(in, JACKSON.getTypeFactory().constructParametricType(QueryResponse.class, type));
			if(resp.batchcomplete())
				return resp.query();
			else
				throw new IllegalStateException();
		} catch (Exception e) {
			throw new RuntimeException("Failed wiki query with "+Arrays.toString(params), e);
		}
	}
}
