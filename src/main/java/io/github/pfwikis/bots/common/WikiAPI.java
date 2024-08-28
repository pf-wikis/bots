package io.github.pfwikis.bots.common;

import java.io.IOException;
import java.nio.file.Files;
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
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.github.fastily.jwiki.core.NS;
import io.github.fastily.jwiki.dwrap.Revision;
import io.github.pfwikis.bots.common.api.MWApi;
import io.github.pfwikis.bots.common.api.MWApiCache;
import io.github.pfwikis.bots.common.bots.Bot;
import io.github.pfwikis.bots.common.model.AllusersQuery.WUser;
import io.github.pfwikis.bots.common.model.LogEventsQuery.LogEvent;
import io.github.pfwikis.bots.common.model.Page;
import io.github.pfwikis.bots.common.model.ParseResponse;
import io.github.pfwikis.bots.common.model.QueryPageQuery.QPPage;
import io.github.pfwikis.bots.common.model.QueryResponse;
import io.github.pfwikis.bots.common.model.RecentChanges.RecentChange;
import io.github.pfwikis.bots.common.model.SemanticAsk;
import io.github.pfwikis.bots.common.model.SemanticAsk.Result;
import io.github.pfwikis.bots.common.model.subject.PageRef;
import io.github.pfwikis.bots.common.model.subject.SemanticSubject;
import io.github.pfwikis.bots.utils.Jackson;
import io.github.pfwikis.bots.utils.SimpleCache.CacheId;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WikiAPI {

	public static synchronized WikiAPI create(io.github.pfwikis.bots.common.Wiki wiki, String name, String password) {
		return new WikiAPI(wiki, name, password);
	}

	private io.github.pfwikis.bots.common.Wiki server;
	private MWApi wiki;

	private WikiAPI(io.github.pfwikis.bots.common.Wiki wiki, String name, String password) {
		this.server = wiki;
		this.wiki = MWApiCache.get(wiki, name, password);
	}

	public boolean upload(Path p, String title, String desc, String summary) {
		return wiki.upload(p, title, desc, summary);
	}

	public List<RecentChange> getRecentChanges(Instant changesSince, String namespace, String show) {
		var query = new String[] {
			"rcend", changesSince.truncatedTo(ChronoUnit.SECONDS).toString(),
			"rclimit", "5000",
			"rctype", "edit|new",
			"rcprop", "title|timestamp|ids|user|sizes"
		};
		if(namespace != null) {
			query = ArrayUtils.addAll(query, "rcnamespace", namespace);
		}
		if(show != null) {
			query = ArrayUtils.addAll(query, "rcshow", show);
		}
		
		return Arrays.asList(query(
			Query.LIST_RECENT_CHANGES,
			query
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

	public boolean editIfChange(String page, String content, String reason) {
		content = content.trim();
		var oldText = wiki.getPageText(page);

		if(Bot.globalLocalMode && !content.equals(oldText)) {
			try {
				var diffOld = oldText.replaceAll("((?=\\[\\[)|<div|\\{\\{#if)", "\n$1");
				Files.writeString(Path.of("debug/old.html"), diffOld);
				Files.writeString(Path.of("debug/newForTesting.html"), content);
				var diffNew = content.replaceAll("((?=\\[\\[)|<div|\\{\\{#if)", "\n$1");
				Files.writeString(Path.of("debug/new.html"), diffNew);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			edit(page, content, reason);
			return true;
		}
		return false;
	}

	public Set<String> getAllSubPages(String namespace, String page) {
		return query(
				Query.LIST_ALL_PAGES,
				"apprefix", page+"/",
				"apnamespace", Integer.toString(wiki.getNS(namespace).v),
				"aplimit", "5000"
			).getAllpages()
			.stream()
			.map(p->p.getTitle())
			.collect(Collectors.toSet());
	}

	public boolean accountExists(String botName) throws IOException {
		var resp = query(
			Query.LIST_USERS,
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
		return Objects.requireNonNull(query(
			Query.META_TOKENS,
			"type", token
		).tokens().get(token+"token"));
	}
	
	private static final ObjectMapper JACKSON = new ObjectMapper()
		.findAndRegisterModules()
		.registerModule(new JavaTimeModule())
		.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
		.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	private <T> T query(Query<T> q, String... params) {
		var realParams = ArrayUtils.addAll(
			new String[] {q.getWhat(), q.getWhich()},
			params
		);
		var resp = this.<QueryResponse<T>>get(
			JACKSON.getTypeFactory()
				.constructParametricType(QueryResponse.class, q.getResponseType()),
			"query",
			realParams
		);
		if(resp.getErrors() != null && !resp.getErrors().isEmpty()) {
			throw new RuntimeException(q+" call with "+Arrays.toString(params)+" failed:\n"+resp.getErrors());
		}
		if(resp.getWarnings() != null && !resp.getWarnings().isEmpty()) {
			log.warn("{} call with {} failed:\n{}", q, Arrays.toString(params), resp.getErrors());
		}
		if(resp.getContinueInfo() != null) {
			var remainingPages = query(q, q.nextPageParams(params, resp));
			return q.mergeResults(resp.getQuery(), remainingPages);
		}
		var query = resp.getQuery();
		if(query != null) {
			return query;
		}
		try {
			return q.getResponseType().getConstructor().newInstance();
		} catch(Exception e) {
			throw new IllegalStateException("No no-argument constructor for "+q.getResponseType());
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
		return server.cache(CacheId.PAGE_EXISTS, title, ()->wiki.exists(title));
	}
	
	public void precacheExistence(List<String> existingPages) {
		existingPages.forEach(p->server.storeInCache(CacheId.PAGE_EXISTS, p, Boolean.TRUE));
	}
	
	public boolean pageExists(PageRef page) {
		return pageExists(page.toFullTitle());
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
	
	public ParseResponse.Content parseText(String text) {
		return get(ParseResponse.class, "parse",
			"text", text,
			"contentmodel", "wikitext",
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
		return query(
			Query.GENERATOR_CATEGORY_MEMBERS,
			"gcmtitle", category,
			"gcmprop", "ids",
			"gcmlimit", "5000"
		).getPages();
	}
	
	public List<Page> getPagesTranscluding(String template) {
		var pages = query(
			Query.PROP_TRANSCLUDED_IN,
			"titles", template,
			"tilimit", "5000"
		).getPages()
			.stream()
			.flatMap(p->p.getTranscludedin()==null
				?Stream.empty()
				:Arrays.stream(p.getTranscludedin())
			)
			.toList();
		return pages;
	}
	
	public List<Page> getImageUsage(String page) {
		if(!page.startsWith("File:"))
			return Collections.emptyList();
		
		return query(
			Query.LIST_IMAGE_USAGE,
			"iutitle", page
		).getImageusage();
	}
	
	public List<Page> getPagesLinkingTo(String page) {
		return query(
			Query.LIST_PAGES_LINKING_TO,
			"titles", page
		).getPages()
			.stream()
			.flatMap(p->p.getLinkshere()==null
				?Stream.empty()
				:Arrays.stream(p.getLinkshere())
			)
			.toList();
	}
	
	public String getDisplayTitle(String page) {
		return server.cache(CacheId.DISPLAY_TITLE, page, () -> {
			var results = query(
					Query.DISPLAY_TITLE,
					"titles", page,
					"inprop", "displaytitle"
				).getPages();
			if(!results.isEmpty())
				return results.getFirst().getDisplaytitle();
			else {
				//this can happen on interwiki links
				return wiki.nss(page).replace('_', ' ');
			}
		});
	}
	
	public List<WUser> getAdmins() {
		return query(
			Query.LIST_ALL_USERS,
			"augroup", "sysop",
			"aulimit", "5000"
		).getAllusers();
	}
	
	public int getNamespaceId(String page) {
		return wiki.whichNS(page).v;
	}
	
	public List<QPPage> getRedirects() {
		return query(
			Query.LIST_QUERY_PAGE,
			"qppage", "Listredirects",
			"qplimit", "5000"
		).getQuerypage()
			.getResults();
	}
	
	public List<LogEvent> getLogEvents(ZonedDateTime end, String user) {
		return query(
			Query.LIST_LOG_EVENTS,
			"lelimit", "5000",
			"leend", end.toInstant().truncatedTo(ChronoUnit.SECONDS).toString(),
			"leuser", user
		).getLogevents();
	}
	
	public List<LogEvent> getRecentLogEvents(String types, Instant changesSince) {
		return query(
			Query.LIST_LOG_EVENTS,
			"lelimit", "5000",
			"leend", changesSince.truncatedTo(ChronoUnit.SECONDS).toString(),
			"leprop", "details|ids|timestamp|title|type|user|userid",
			"letype", types
		).getLogevents();
	}
	
	public boolean delete(String page, String reason) {
		return wiki.delete(page, reason);
	}

	public List<Page> getPagesInCategory(String category, String namespace) {
		return query(
			Query.GENERATOR_CATEGORY_MEMBERS,
			"gcmtitle", category,
			"gcmprop", "ids",
			"gcmnamespace", namespace,
			"gcmlimit", "5000"
		).getPages();
	}
	
	public List<Page> getPagesInNamespace(String namespace) {
		return query(
			Query.LIST_ALL_PAGES,
			"apnamespace", Integer.toString(wiki.getNS(namespace).v),
			"aplimit", "5000"
		).getAllpages();
	}
	
	public List<Page> getCategories(String page) {
		var result = query(
				Query.PROP_CATEGORIES,
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
		var response = get(SemanticAsk.class, "ask", "api_version", "3", "query", query+"|limit=5000");
		response.getQuery().getResults().stream().flatMap(e->e.values().stream()).forEach(results::add);
		int lastOffset = Integer.MIN_VALUE;
		while(response.getQueryContinueOffset() != null) {
			int nextOffset = response.getQueryContinueOffset();
			if(nextOffset < lastOffset) {
				throw new IllegalStateException("Offset changed from "+lastOffset+" to "+response.getQueryContinueOffset());
			}
			lastOffset = response.getQueryContinueOffset();
			response = get(SemanticAsk.class, "ask", "api_version", "3", "query", query+"|limit=5000|offset="+response.getQueryContinueOffset());
			response.getQuery().getResults().stream().flatMap(e->e.values().stream()).forEach(results::add);
		}
		return results;
	}

	public void rename(String title, String newTitle, boolean leaveRedirect, String reaseon) {
		wiki.move(title, newTitle, true, true, !leaveRedirect, reaseon);
	}

	public List<String> search(String query, NS... namespaces) {
		return wiki.search(query, -1, namespaces);
	}

	public void move(String page, String newTitle, boolean redirect, String reason) {
		wiki.move(page, newTitle, true, true, !redirect, reason);
	}

	public ArrayList<String> getWantedTemplates() {
		return wiki.querySpecialPage("Wantedtemplates", -1);
	}

	public String resolveRedirects(String page) {
		return server.cache(CacheId.RESOLVED_REDIRECT, page, ()->wiki.resolveRedirect(page));
	}

	public void undelete(String page, String reason) {
		wiki.undelete(page, reason);
	}

	public SemanticSubject semanticSubject(String page) {
		var ns = wiki.whichNS(page).v;
		var title = wiki.nss(page);
		try {
			var result = this.get(SemanticSubject.Container.class, "smwbrowse",
				"browse", "subject",
				"params", Jackson.JSON.writeValueAsString(Map.of("ns", ns, "subject", title))
			);
			
			return result.postProcess();
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public String toWikiLink(String page) {
		var display = getDisplayTitle(page);
		var usedPage = (wiki.whichNS(page).equals(NS.CATEGORY)?":":"")+page;
		
		if(StringUtils.isEmpty(display)) {
			return "[["+usedPage+"]]";
		}
		
		display = wiki.nss(display);
		
		if(!display.equals(page)) {
			return "[["
				+ usedPage
				+ "|"
				+ display
				+ "]]";
		}
		return "[["+usedPage+"]]";
	}

	public String withoutNamespace(String title) {
		return wiki.nss(title);
	}
}
