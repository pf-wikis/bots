package io.github.pfwikis.bots.common;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.github.fastily.jwiki.core.Wiki;
import io.github.fastily.jwiki.dwrap.Revision;
import io.github.pfwikis.bots.common.model.AllusersQuery;
import io.github.pfwikis.bots.common.model.AllusersQuery.WUser;
import io.github.pfwikis.bots.common.model.ImageUsageQuery;
import io.github.pfwikis.bots.common.model.Page;
import io.github.pfwikis.bots.common.model.PageQuery;
import io.github.pfwikis.bots.common.model.ParseResponse;
import io.github.pfwikis.bots.common.model.QueryListUsers;
import io.github.pfwikis.bots.common.model.QueryResponse;
import io.github.pfwikis.bots.common.model.QueryTokens;
import io.github.pfwikis.bots.common.model.RecentChanges;
import io.github.pfwikis.bots.common.model.RecentChanges.RecentChange;
import io.github.pfwikis.bots.common.model.SemanticAsk;
import io.github.pfwikis.bots.common.model.SemanticAsk.Result;
import io.github.pfwikis.bots.common.model.LogEventsQuery;
import okhttp3.HttpUrl;

public class WikiAPI {

	private Wiki wiki;

	public WikiAPI(boolean starfinder, String name, String password) {
		String url = starfinder?"https://starfinderwiki.com":"https://pathfinderwiki.com";
		var b = new Wiki.Builder()
	            .withDomain(url)
	            .withApiEndpoint(HttpUrl.get(url+"/w/api.php"));
		if(name != null) {
			b = b.withLogin(name, password);
		}
		wiki = b.build();
	}
	
	public boolean upload(Path p, String title, String desc, String summary) {
		return wiki.upload(p, title, desc, summary);
	}

	public List<RecentChange> getRecentChanges(Duration timeRange) {
		return Arrays.asList(query(
			RecentChanges.class,
			"list", "recentchanges",
			"rcend", Instant.now().minus(timeRange).truncatedTo(ChronoUnit.SECONDS).toString(),
			"rcnamespace", "0",
			"rclimit", "5000",
			"rcshow", "!minor|!bot",
			"rctype", "edit",
			"rcprop", "title|timestamp|ids|user|sizes"
		).recentchanges());
	}
	
	public ArrayList<Revision> getRevisions(String title, Duration timeRange) {
		return wiki.getRevisions(title, 0, false, Instant.now().minus(timeRange).truncatedTo(ChronoUnit.SECONDS), null);
	}
	
	public void edit(String page, String content, String reason) {
		if(!wiki.edit(page, content, reason)) {
			throw new RuntimeException("Failed to edit page "+page);
		}
	}

	public void editIfChange(String page, String content, String reason) {
		var oldText = wiki.getPageText(page);
		if(!content.equals(oldText)) {
			edit(page, content, reason);
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
			"reason", "VirenerusBot sub bots need permissions",
			"token", token
		)));
		var json = resp.body().string();
		if(json.contains("\"error\"")) {
			throw new IllegalStateException("Could not create user "+botName+". Response was:\n"+json);
		}
		
		
	}

	public String requestToken(String token) {
		return Objects.requireNonNull(query(QueryTokens.class,
			"meta", "tokens",
			"type", token
		).tokens().get(token+"token"));
	}
	
	private static final ObjectMapper JACKSON = new ObjectMapper()
		.findAndRegisterModules()
		.registerModule(new JavaTimeModule())
		.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	private <T> T query(Class<T> type, String... params) {
		var resp = this.<QueryResponse<T>>get(
			JACKSON.getTypeFactory()
				.constructParametricType(QueryResponse.class, type),
			"query",
			params
		);
		if(!resp.batchcomplete()) {
			throw new IllegalStateException("continue now supported but required");
		}
		var query = resp
				.query();
		if(query != null) {
			return query;
		}
		try {
			return type.getConstructor().newInstance();
		} catch(Exception e) {
			throw new IllegalStateException("No no-argument constructor for "+type);
		}
	}
	
	private <T> T get(JavaType type, String action, String... params) {
		params = ArrayUtils.addAll(params,
			"format", "json",
			"utf8", "1",
			"formatversion", "2"
		);
		var response = wiki.basicGET(action, params);
		try (var in = response.body().byteStream()) {
			return JACKSON.readValue(in, type);
		} catch (Exception e) {
			throw new RuntimeException("Failed wiki "+action+" with "+Arrays.toString(params), e);
		}
	}

	private <T> T get(Class<T> type, String action, String... params) {
		return get(JACKSON.getTypeFactory().constructType(type), action, params);
	}
	
	public boolean pageExists(String title) {
		return wiki.exists(title);
	}
	
	public String getPageText(String title) {
		return wiki.getPageText(title);
	}

	public ParseResponse.Content getParsed(String title) {
		return get(ParseResponse.class, "parse",
			"page", title,
			"disablelimitreport", "true",
			"disableeditsection", "true",
			"disabletoc", "true"
		).parse();
	}
	
	public ParseResponse.Content getParsed(long oldid) {
		return get(ParseResponse.class, "parse",
			"oldid", Long.toString(oldid),
			"disablelimitreport", "true",
			"disableeditsection", "true",
			"disabletoc", "true"
		).parse();
	}
	
	public List<Page> getPagesInCategory(String category) {
		return query(PageQuery.class,
			"generator", "categorymembers",
			"gcmtitle", category,
			"gcmprop", "ids",
			"gcmlimit", "5000"
		).getPages();
	}
	
	public List<Page> getPagesTranscluding(String template) {
		return Arrays.asList(query(PageQuery.class,
			"prop", "transcludedin",
			"titles", template,
			"tilimit", "5000"
		).getPages().get(0).getTranscludedin());
	}
	
	public List<Page> getImageUsage(String page) {
		return query(ImageUsageQuery.class, 
			"list", "imageusage",
			"iutitle", page
		).getImageusage();
	}
	
	public List<WUser> getAdmins() {
		return query(AllusersQuery.class, 
			"list", "allusers",
			"augroup", "sysop",
			"aulimit", "5000"
		).getAllusers();
	}
	
	public List<JsonNode> getLogEvents(ZonedDateTime end, String user) {
		return query(LogEventsQuery.class, 
			"list", "logevents",
			"lelimit", "1",
			"leend", end.toInstant().truncatedTo(ChronoUnit.SECONDS).toString(),
			"leuser", user
		).getLogevents();
	}
	
	public boolean delete(String page, String reason) {
		return wiki.delete(page, reason);
	}

	public List<Page> getPagesInCategory(String category, String namespace) {
		return query(PageQuery.class,
			"generator", "categorymembers",
			"gcmtitle", category,
			"gcmprop", "ids",
			"gcmnamespace", namespace,
			"gcmlimit", "5000"
		).getPages();
	}
	
	public List<Page> getCategories(String page) {
		var result = query(PageQuery.class,
				"prop", "categories",
				"titles", page
			).getPages().get(0).getCategories();
		if(result == null) {
			return Collections.emptyList();
		}
		return Arrays.asList(result);
	}

	public void protect(String title, String protections, String reason, String token) {
		wiki.basicPOST("protect", new HashMap<>(Map.of(
				"format", "json",
				"title", title,
				"protections", protections,
				"reason", reason,
				"token", token,
				"utf8", "1",
				"formatversion", "2"
		)));
	}

	public void setContentModel(String title, String model) throws IOException {
		String token = requestToken("csrf");
		
		var resp = wiki.basicPOST(
			"changecontentmodel", new HashMap<>(Map.of(
			"format", "json",
			"utf8", "1",
			"formatversion", "2",
			"title", title,
			"model", model,
			"token", token,
			"summary", "Change contentmodel to JSON"
		)));
		var json = resp.body().string();
		if(json.contains("\"error\"")) {
			throw new IllegalStateException("Could not set contentmodel of "+title+". Response was:\n"+json);
		}
	}

	public ArrayList<Result> semanticAsk(String query) {
		var results = new ArrayList<SemanticAsk.Result>();
		var response = get(SemanticAsk.class, "ask", "query", query+"|limit=1000");
		results.addAll(response.getQuery().getResults().values());
		while(response.getQueryContinueOffset() != null) {
			response = get(SemanticAsk.class, "ask", "query", query+"|limit=1000|offset="+response.getQueryContinueOffset());
			results.addAll(response.getQuery().getResults().values());
		}
		return results;
	}

	public void rename(String title, String newTitle, boolean leaveRedirect, String reaseon) {
		wiki.move(title, newTitle, true, true, !leaveRedirect, reaseon);
	}
}
