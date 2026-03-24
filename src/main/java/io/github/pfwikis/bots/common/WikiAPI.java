package io.github.pfwikis.bots.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import io.github.pfwikis.bots.common.api.MWApi;
import io.github.pfwikis.bots.common.api.MWApiCache;
import io.github.pfwikis.bots.common.api.generated.AAPIAsk;
import io.github.pfwikis.bots.common.api.generated.AAPIDelete;
import io.github.pfwikis.bots.common.api.generated.AAPIParse;
import io.github.pfwikis.bots.common.api.generated.AAPIProtect;
import io.github.pfwikis.bots.common.api.generated.AAPIQuery;
import io.github.pfwikis.bots.common.api.generated.AAPIQueryAllpages;
import io.github.pfwikis.bots.common.api.generated.AAPIQueryAllusers;
import io.github.pfwikis.bots.common.api.generated.AAPIQueryCategories;
import io.github.pfwikis.bots.common.api.generated.AAPIQueryCategorymembers;
import io.github.pfwikis.bots.common.api.generated.AAPIQueryImageusage;
import io.github.pfwikis.bots.common.api.generated.AAPIQueryInfo;
import io.github.pfwikis.bots.common.api.generated.AAPIQueryLinkshere;
import io.github.pfwikis.bots.common.api.generated.AAPIQueryLogevents;
import io.github.pfwikis.bots.common.api.generated.AAPIQueryQuerypage;
import io.github.pfwikis.bots.common.api.generated.AAPIQueryRecentchanges;
import io.github.pfwikis.bots.common.api.generated.AAPIQueryTranscludedin;
import io.github.pfwikis.bots.common.api.generated.AAPIUpload;
import io.github.pfwikis.bots.common.api.generated.params.AAPIAskApi_version;
import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllusersGroup;
import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryCategorymembersProp;
import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryInfoProp;
import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryLogeventsProp;
import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryLogeventsType;
import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryQuerypagePage;
import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryRecentchangesProp;
import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryRecentchangesShow;
import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryRecentchangesType;
import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.model.AAPIExceptions.AAPIException;
import io.github.pfwikis.bots.common.api.model.AAPIExceptions.AAPIMissingPageException;
import io.github.pfwikis.bots.common.api.model.ContainsPageRef;
import io.github.pfwikis.bots.common.api.model.PageRef;
import io.github.pfwikis.bots.common.api.model.PageTitle;
import io.github.pfwikis.bots.common.api.responses.DeleteResponse;
import io.github.pfwikis.bots.common.api.responses.ParseResponse;
import io.github.pfwikis.bots.common.api.responses.QueryResponse;
import io.github.pfwikis.bots.common.api.responses.QueryResponse.Category;
import io.github.pfwikis.bots.common.api.responses.QueryResponse.LogEvent;
import io.github.pfwikis.bots.common.api.responses.QueryResponse.MWUser;
import io.github.pfwikis.bots.common.api.responses.QueryResponse.QRPage;
import io.github.pfwikis.bots.common.api.responses.QueryResponse.RecentChange;
import io.github.pfwikis.bots.common.api.responses.QueryResponse.ResponsePageRef;
import io.github.pfwikis.bots.common.api.responses.SemanticAsk;
import io.github.pfwikis.bots.common.api.responses.SemanticAsk.Result;
import io.github.pfwikis.bots.common.api.responses.UploadResponse;
import io.github.pfwikis.bots.common.bots.Bot;
import io.github.pfwikis.bots.utils.Jackson;
import io.github.pfwikis.bots.utils.SimpleCache.CacheId;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.JavaType;

@Slf4j
public class WikiAPI {

	public static synchronized WikiAPI create(io.github.pfwikis.bots.common.Wiki wiki, String name, String password, String antiProtectionSecret) {
		return new WikiAPI(wiki, name, password, antiProtectionSecret);
	}

	private io.github.pfwikis.bots.common.Wiki server;
	private MWApi wiki;

	private WikiAPI(io.github.pfwikis.bots.common.Wiki wiki, String name, String password, String antiProtectionSecret) {
		this.server = wiki;
		this.wiki = MWApiCache.get(wiki, name, password, antiProtectionSecret);
	}
	
	public boolean editIfChange(ContainsPageRef page, String content, String reason) {
		content = content.trim()+"\n";
		String oldText;
		try {
			oldText = wiki.getWikitext(page);
			if(oldText != null) oldText = oldText.trim()+"\n";
		} catch(AAPIMissingPageException e) {
			oldText = "";
		}

		if(!content.equals(oldText)) {
			if(Bot.globalLocalMode) {
				try {
					new File("debug").mkdir();
					var diffOld = oldText.replaceAll("((?=\\[\\[)|<div|\\{\\{#if)", "\n$1");
					Files.writeString(Path.of("debug/old.html"), diffOld);
					Files.writeString(Path.of("debug/newForTesting.html"), content);
					var diffNew = content.replaceAll("((?=\\[\\[)|<div|\\{\\{#if)", "\n$1");
					Files.writeString(Path.of("debug/new.html"), diffNew);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			wiki.edit(page, content, reason);
			server.storeInCache(CacheId.PAGE_EXISTS, page.toPageRef(), true);
			return true;
		}
		server.storeInCache(CacheId.PAGE_EXISTS, page.toPageRef(), true);
		return false;
	}
	
	public void edit(ContainsPageRef page, String content, String reason) {
		content = content.trim()+"\n";
		wiki.edit(page, content, reason);
		server.storeInCache(CacheId.PAGE_EXISTS, page.toPageRef(), true);
	}

	public List<PageRef> getPagesInNamespace(NS namespace) {
		return wiki.run(AAPIQuery.create().list(AAPIQueryAllpages.create()
					.namespace(namespace)
				), QueryResponse.class).getAllpages().stream().map(QRPage::getPage).toList();
	}
	
	public List<PageRef> getPagesInCategory(ContainsPageRef category) {
		return Lists.transform(wiki.run(AAPIQuery
				.create()
				.generator(AAPIQueryCategorymembers.create(category)
						.prop(AAPIQueryCategorymembersProp.IDS)),
				QueryResponse.class
		).getPages(), QRPage::getPage);
	}
	
	public List<QRPage> getPagesInCategory(ContainsPageRef category, NS[] namespaces) {
		return wiki.run(AAPIQuery
				.create()
				.generator(AAPIQueryCategorymembers.create(category)
						.prop(AAPIQueryCategorymembersProp.IDS)
						.namespace(namespaces)),
				QueryResponse.class
		).getPages();
	}

	public String getWikitext(ContainsPageRef page) throws AAPIMissingPageException {
		return wiki.getWikitext(page);
	}

	public boolean exists(ContainsPageRef page) {
		return wiki.exists(page);
	}

	public List<PageRef> getCategories(ContainsPageRef page) {
		var categories = wiki.getPageProperties(page, AAPIQueryCategories.create()).getCategories();
		if(categories == null) return Collections.emptyList();
		return categories.stream().map(Category::getPage).toList();
	}
	
	public List<MWUser> getAdmins() {
		return wiki.run(AAPIQuery.create()
			.list(AAPIQueryAllusers.create()
					.group(AAPIQueryAllusersGroup.SYSOP)
			),
			QueryResponse.class
		).getAllusers();
	}
	
	public List<LogEvent> getLogEvents(Instant end, String user) {
		return wiki.run(
			AAPIQuery.create()
				.list(AAPIQueryLogevents.create()
					.end(end)
					.user(user)
				),
			QueryResponse.class
		).getLogevents();
	}
	
	public List<RecentChange> getRecentChanges(Instant changesSince, NS namespace, AAPIQueryRecentchangesShow[] show) {
		var rc = AAPIQueryRecentchanges.create()
			.end(changesSince)
			.type(
				AAPIQueryRecentchangesType.EDIT,
				AAPIQueryRecentchangesType.NEW
			)
			.prop(
				AAPIQueryRecentchangesProp.TITLE,
				AAPIQueryRecentchangesProp.TIMESTAMP,
				AAPIQueryRecentchangesProp.IDS,
				AAPIQueryRecentchangesProp.USER,
				AAPIQueryRecentchangesProp.SIZES
			);
		
		if(namespace != null) {
			rc.namespace(namespace);
		}
		
		if(show != null) {
			rc.show(show);
		}
		
		return wiki.run(AAPIQuery.create().list(rc), QueryResponse.class).getRecentchanges();
	}
	
	public List<LogEvent> getRecentLogEvents(AAPIQueryLogeventsType type, Instant changesSince) {
		var rle = AAPIQueryLogevents.create()
			.end(changesSince)
			.prop(
				AAPIQueryLogeventsProp.DETAILS,
				AAPIQueryLogeventsProp.IDS,
				AAPIQueryLogeventsProp.TIMESTAMP,
				AAPIQueryLogeventsProp.TITLE,
				AAPIQueryLogeventsProp.TYPE,
				AAPIQueryLogeventsProp.USER,
				AAPIQueryLogeventsProp.USERID
			)
			.type(type);
		return wiki.run(AAPIQuery.create().list(rle), QueryResponse.class).getLogevents();
	}
	
	private static final Map<Class<?>, JavaType> PRINTOUT_TYPES = new HashMap<>();
	private static synchronized JavaType printoutType(Class<?>cl) {
		return PRINTOUT_TYPES.computeIfAbsent(cl, 
				t->Jackson.JSON.getTypeFactory().constructSimpleType(
						SemanticAsk.Query.class,
						new JavaType[] {Jackson.JSON.getTypeFactory().constructType(t)}
				)
		);
	}
	
	public <T> List<Result<T>> semanticAsk(Class<T> printoutType, String query) {
		var result = new ArrayList<Result<T>>();
		Integer offset = 0;
		int limit = 1000;
		
		while(offset != null) {
			var resp = wiki.<SemanticAsk.Query<T>>complexRun(
					AAPIAsk.create(query+"|limit="+limit+"|offset="+offset)
						.api_version(AAPIAskApi_version.V_3),
					"query",
					printoutType(printoutType)
			);
			offset = resp.response().getQueryContinueOffset();
			resp.result().getResults().stream().flatMap(e->e.values().stream()).forEach(result::add);
		}
		
		return result;
	}
	
	public <T> List<Result<T>> semanticAsk(Class<T> printoutType, String query, int limit) {
		var resp = wiki.<SemanticAsk.Query<T>>complexRun(
				AAPIAsk.create(query+"|limit="+limit)
					.api_version(AAPIAskApi_version.V_3),
				"query",
				printoutType(printoutType)
		);
		
		return resp.result().getResults().stream().flatMap(e->e.values().stream()).toList();
	}

	public ParseResponse getHTML(long revid) {
		return wiki.run(AAPIParse.create()
				.oldid(revid)
				.disablelimitreport(true)
				.disableeditsection(true)
				.disabletoc(true),
				ParseResponse.class
			);
	}
	
	public ParseResponse getHTML(ContainsPageRef page) {
		return wiki.run(AAPIParse.create()
			.page(page)
			.disablelimitreport(true)
			.disableeditsection(true)
			.disabletoc(true),
			
			ParseResponse.class
		);
	}

	public void upload(String url, ContainsPageRef title, String desc, String summary) throws IOException {
		wiki.run(AAPIUpload.create()
			.url(url)
			.filename(title.toPageTitle().getName())
			.text(desc)
			.comment(summary),
			UploadResponse.class);
	}
	
	private record Series2CategoryPrintouts(Result<?> series, Result<?> category) {}
	public Map<PageTitle, PageTitle> getSeries2Category() {
		return server
			.cache(CacheId.SERIES_2_CATEGORY, "", () -> {
				var res = semanticAsk(Series2CategoryPrintouts.class, "[[Fact type::Template:Facts/Series]][[Member category::+]]|?Represented by page=series|?Member category=category");
				var b = ImmutableMap.<PageTitle, PageTitle>builder();
				for(var r:res) {
					b.put(
							r.getPrintouts().series().getPage().getTitle(),
							r.getPrintouts().category().getPage().getTitle()
					);
				}
				return b.build();
			});
	}
	
	public List<ResponsePageRef> getPagesTranscluding(ContainsPageRef template) {
		return wiki.run(AAPIQuery.create()
				.prop(AAPIQueryTranscludedin.create())
				.titles(template),
			QueryResponse.class
		).getPages()
			.stream()
			.flatMap(p->p.getTranscludedin()==null
				?Stream.empty()
				:p.getTranscludedin().stream()
			)
			.toList();
	}
	
	public boolean isProtected(ContainsPageRef page) {
		var p = wiki.run(AAPIQuery.create()
				.titles(page)
				.prop(AAPIQueryInfo.create()
					.prop(AAPIQueryInfoProp.PROTECTION)
				),
				QueryResponse.class
			)
			.getPages().getFirst();
		
		return p.getProtection() != null && !p.getProtection().isEmpty();
	}

	public void protect(ContainsPageRef page, String protections, String reason) {
		wiki.run(AAPIProtect.create(page, protections)
				.reason(wiki.getBotname()+": "+reason),
				QueryResponse.class
			)
			.getPages().getFirst();
	}

	public List<QRPage> getAllRedirects() {
		return wiki.run(AAPIQuery.create()
				.list(AAPIQueryQuerypage.create(AAPIQueryQuerypagePage.LISTREDIRECTS)),
			QueryResponse.class
		).getQuerypage().getResults();
	}
	
	public List<PageRef> getImageUsage(ContainsPageRef page) {
		if(!page.toPageTitle().getNs().equals(NS.FILE))
			return Collections.emptyList();
		
		return wiki.run(AAPIQuery.create()
				.list(AAPIQueryImageusage.create(page)),
			QueryResponse.class
		).getImageusage().stream().map(p->p.getPage()).toList();
	}
	
	public List<PageRef> getPagesLinkingTo(ContainsPageRef page) {
		return wiki.run(AAPIQuery.create()
					.prop(AAPIQueryLinkshere.create())
					.titles(page),
				QueryResponse.class
			).getPages()
			.stream()
			.flatMap(p->p.getLinkshere()==null
				?Stream.empty()
				:p.getLinkshere().stream()
			)
			.map(p->p.toPageRef())
			.toList();
	}

	public void delete(ContainsPageRef page, String reason) {
		wiki.run(AAPIDelete.create(page)
			.reason(wiki.getBotname()+": "+reason),
			DeleteResponse.class
		);
		server.storeInCache(CacheId.PAGE_EXISTS, page.toPageRef(), false);
	}
	
	
	/*
	public ArrayList<Revision> getRevisions(String title, Duration timeRange) {
		return wiki.getRevisions(title, 0, false, Instant.now().minus(timeRange).truncatedTo(ChronoUnit.SECONDS), null);
	}
	
	public void purge(List<String> pages) {
		wiki.purge(pages.toArray(String[]::new));
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
	
	private static final ObjectMapper JACKSON = new ObjectMapper(JsonFactory
			.builder()
			.enable(StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION)
			.build())
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
		var fParams = ArrayUtils.addAll(params,
			"format", "json",
			"utf8", "1",
			"formatversion", "2"
		);
		var result = basicRequest(()->wiki.basicGET(action, fParams));
		try {
			return JACKSON.treeToValue(result, type);
		} catch (JsonProcessingException | IllegalArgumentException e) {
			throw new RuntimeException("Failed to parse JSON response. Response:\n"+result, e);
		}
	}
	
	private JsonNode basicRequest(Supplier<Response> r) {
		try {
			var resp = r.get();
			
			while(resp.code() == 503) {
				log.info("Sleeping due to rate limits");
				Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
				resp = r.get();
			}
			
			var body = resp.body().bytes();
			
			if(!resp.isSuccessful()) {
				throw new RuntimeException("HTTP status code '"+resp.code()+"' was thrown. Response:\n"+new String(body));
			}
			
			try {
				var res = JACKSON.readTree(body);
				if(res.has("errors")) {
					throw new RuntimeException("Mediawiki returned error. Response:\n"+res.toPrettyString());
				}
				return res;
			} catch(Exception e) {
				throw new RuntimeException("Failed to parse JSON response. Response:\n"+new String(body), e);
			}
		} catch(Exception e) {
			throw new RuntimeException(e);
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
	
	public boolean pageExists(PageTitle page) {
		return pageExists(page.toFullTitle());
	}
	
	public String getPageText(String title) {
		return wiki.getPageText(title);
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
					Query.PAGE_INFO,
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
	
	
	public int getNamespaceId(String page) {
		return wiki.whichNS(page).v;
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
	
	public void setContentModel(String title, String model) throws IOException {
		String token = requestToken("csrf");
		
		basicRequest(()->wiki.basicPOST(
			"changecontentmodel", new HashMap<>(Map.of(
			"format", "json",
			"utf8", "1",
			"formatversion", "2",
			"title", title,
			"model", model,
			"token", token,
			"summary", "Change contentmodel to JSON"
		))));
	}
	
	
	

	public List<Result<Printouts>> semanticAsk(String query) {
		return semanticAsk(Printouts.class, query);
	}

	public <T> List<Result<T>> semanticAsk(Class<T> printoutType, String query) {
		var jt = printoutType(printoutType);
		var results = new ArrayList<SemanticAsk.Result<T>>();
		var response = this.<SemanticAsk<T>>get(jt,
				"ask", "api_version", "3", "query", query+"|limit=1000");
		response.getQuery().getResults().stream().flatMap(e->e.values().stream()).forEach(results::add);
		int lastOffset = Integer.MIN_VALUE;
		while(response.getQueryContinueOffset() != null) {
			int nextOffset = response.getQueryContinueOffset();
			if(nextOffset < lastOffset) {
				throw new IllegalStateException("Offset changed from "+lastOffset+" to "+response.getQueryContinueOffset());
			}
			lastOffset = response.getQueryContinueOffset();
			response = this.<SemanticAsk<T>>get(jt, "ask", "api_version", "3", "query", query+"|limit=1000|offset="+response.getQueryContinueOffset());
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

	

	public String getPageImage(String page) {
		return query(Query.PAGE_IMAGES, "titles", page).getPages().get(0).getPageimage();
	}
	
	
	
	public static final Query<RecentChanges> LIST_RECENT_CHANGES = new Query.WithContinue<>(
		RecentChanges.class,
		"list",
		"recentchanges",
		"rccontinue"
	) {
		public RecentChanges mergeResults(RecentChanges a, RecentChanges b) {
			var l = new ArrayList<RecentChange>();
			l.addAll(List.of(a.recentchanges()));
			l.addAll(List.of(b.recentchanges()));
			return new RecentChanges(l.toArray(RecentChange[]::new));
		};
	};

	public static final Query<AllpagesQuery> LIST_ALL_PAGES = new Query.WithContinue<>(
		AllpagesQuery.class,
		"list",
		"allpages",
		"apcontinue"
	) {
		public AllpagesQuery mergeResults(AllpagesQuery a, AllpagesQuery b) {
			a.getAllpages().addAll(b.getAllpages());
			return a;
		};
	};

	public static final Query<QueryListUsers> LIST_USERS = new Query<>(
		QueryListUsers.class,
		"list",
		"users"
	);

	public static final Query<QueryTokens> META_TOKENS = new Query<>(
		QueryTokens.class,
		"meta",
		"tokens"
	);

	public static final Query<PageQuery> GENERATOR_CATEGORY_MEMBERS = new Query<>(
		PageQuery.class,
		"generator",
		"categorymembers"
	);
	
	public static final Query<PageQuery> PROP_TRANSCLUDED_IN = new Query.WithContinue<>(
		PageQuery.class,
		"prop",
		"transcludedin",
		"ticontinue"
	) {
		public PageQuery mergeResults(PageQuery a, PageQuery b) {
			a.getPages().addAll(b.getPages());
			return a;
		};
	};
	
	public static final Query<PageQuery> PROP_CATEGORIES = new Query.WithContinue<>(
		PageQuery.class,
		"prop",
		"categories",
		"clcontinue"
	) {
		@Override
		public PageQuery mergeResults(PageQuery a, PageQuery b) {
			a.getPages().addAll(b.getPages());
			return a;
		}
	};
			
	public static final Query<QueryPageQuery> LIST_QUERY_PAGE = new Query.WithContinue<>(
		QueryPageQuery.class, 
		"list",
		"querypage",
		"qpoffset"
	) {
		@Override
		public QueryPageQuery mergeResults(QueryPageQuery a, QueryPageQuery b) {
			a.getQuerypage().getResults().addAll(b.getQuerypage().getResults());
			return a;
		}
	};
	
	public static final Query<LogEventsQuery> LIST_LOG_EVENTS = new Query.WithContinue<>(
		LogEventsQuery.class, 
		"list",
		"logevents",
		"lecontinue"
	) {
		@Override
		public LogEventsQuery mergeResults(LogEventsQuery a, LogEventsQuery b) {
			a.getLogevents().addAll(b.getLogevents());
			return a;
		}
	};
	
	public static final Query<ImageUsageQuery> LIST_IMAGE_USAGE = new Query.WithContinue<>(
		ImageUsageQuery.class, 
		"list",
		"imageusage",
		"iucontinue"
	) {
		@Override
		public ImageUsageQuery mergeResults(ImageUsageQuery a, ImageUsageQuery b) {
			a.getImageusage().addAll(b.getImageusage());
			return a;
		}
	};
	
	public static final Query<PageQuery> PAGE_IMAGES = new Query.WithContinue<>(
		PageQuery.class, 
		"prop",
		"pageimages",
		"picontinue"
	) {
		@Override
		public PageQuery mergeResults(PageQuery a, PageQuery b) {
			a.getPages().addAll(b.getPages());
			return a;
		}
	};
	
	public static final Query<PageQuery> LIST_PAGES_LINKING_TO = new Query.WithContinue<>(
		PageQuery.class, 
		"prop",
		"linkshere",
		"lhcontinue"
	) {
		@Override
		public PageQuery mergeResults(PageQuery a, PageQuery b) {
			a.getPages().addAll(b.getPages());
			return a;
		}
	};
	
	public static final Query<PageInfoQuery> PAGE_INFO = new Query<>(
		PageInfoQuery.class, 
		"prop",
		"info"
	);
	
	public static final Query<AllusersQuery> LIST_ALL_USERS = new Query.WithContinue<>(
			AllusersQuery.class, 
			"list",
			"allusers",
			"aufrom"
	) {
		@Override
		public AllusersQuery mergeResults(AllusersQuery a, AllusersQuery b) {
			a.getAllusers().addAll(b.getAllusers());
			return a;
		}
	};
	*/
}
