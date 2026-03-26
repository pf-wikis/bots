package io.github.pfwikis.bots.paizoretriever;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.openqa.selenium.bidi.module.Network;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.common.util.concurrent.Uninterruptibles;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.api.model.PageRef;
import io.github.pfwikis.bots.common.bots.DualBot;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.paizoretriever.PZCategoryResponse.PZCategory;
import io.github.pfwikis.bots.paizoretriever.State.Props;
import io.github.pfwikis.bots.utils.Jackson;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.creativecouple.validation.isbn.ISBN;
import tools.jackson.databind.ObjectWriter;

@Slf4j
@Getter @Setter
@Parameters
public class PaizoRetriever extends DualBot {
	
	@Parameter(names = "--selenium")
	private String selenium; 
	
	private static final Duration SLEEP = Duration.ofMillis(100);
	private static final File STATE_FILE = new File("outputs/crawl/state.yaml");
	private static final Set<String> WANTED_CATEGORIES = Set.of(
		"Subscriptions",
		"Pathfinder",
		"Starfinder",
		"Virtual Tabletop",
		"Accessories",
		//we do not want all other games so we list some subcategories here
		"Board Games",
		"Card Games",
		"Pathfinder Adventure Card Game"
	);
	
	public PaizoRetriever() {
		super("paizo-retriever", "Paizo Retriever");
	}
	
	public PaizoRetriever(String selenium) {
		this();
		this.selenium = selenium;
	}
	
	@Override
	public String getDescription() {
		return "This bot keeps an up to date list of Paizo SKU->store page.";
	}

	@Override
	public void run(RunContext ctx) throws Exception {
		STATE_FILE.getParentFile().mkdirs();
		State state;
		if(STATE_FILE.exists())
			state = Jackson.YAML.readValue(STATE_FILE, State.class);
		else
			state = new State();

		var driver = createDriver();
		var net = new Network(driver);
		try {
			var authF = new CompletableFuture<String>(); 
					new AtomicReference<String>();
			net.onResponseCompleted(resp -> {
				if(resp.getRequest().getUrl().contains("graphql")) {
					resp.getRequest().getHeaders().forEach(h->{
						if(h.getName().equalsIgnoreCase("Authorization"))
							authF.complete(h.getValue().getValue());
					});
				}
			});
			driver.get("https://store.paizo.com");
			var auth = authF.get(30, TimeUnit.SECONDS);
			net.close();
			driver.quit();
			
			try(var client = HttpClients.custom().build()) {
				var baseRequest = ClassicRequestBuilder.post()
					.setUri("https://store.paizo.com/graphql")
					.setCharset(StandardCharsets.UTF_8)
					.setHeader("Content-Type", "application/json")
					.setHeader("Authorization", auth)
					.build();
				
				
				var categoryRoot = collectCategories(client, baseRequest);
				categoryRoot.streamResolved()
					//only the ones we want
					.filter(c->WANTED_CATEGORIES.contains(c.getName()))
					//but also their children
					.flatMap(c->c.streamResolved())
					.distinct()
					.forEach(c-> {
						log.info("Collecting products from category '{}'", c.getName());
						String page = null;
						do {
							page = collectResponses(client, baseRequest, state, c, page);
						} while(page != null);
					});
			}
		
			//crawlLoop(driver, state);
		} catch(Exception e) {
			log.error("Failed paizo crawling", e);
		} finally {
			net.close();
			driver.quit();
			save(state);
		}
		
		createPages(state);
	}
	
	private static record PageDef(String name, Function<Props, String> getter) {}
	private static final List<PageDef> pageDefs = List.of(
		new PageDef("URL", p->"https://store.paizo.com"+p.getUrl()),
		//new PageDef("name", Props::getName),
		new PageDef("price", Props::getPrice),
		new PageDef("upc", p->checkIsbn(p.getUpc()))
	);
	
	private void createPages(State state) {
		var pages = state.getValues().values().stream()
				.map(p->p.getProperties())
				.filter(p->p.getSku()!=null)
				.filter(p->p.getSku().startsWith("PZ") || p.getSku().startsWith("DYN"))
				.sorted(Comparator.comparing(Props::getSku))
				.toList();
		var intro = "<noinclude>{{Bot created|VirenerusBot#Paizo Retriever|This template is automatically created and update from the paizo webstore.}}</noinclude><includeonly>";
		var outro = "</includeonly><noinclude>[[Category:Data templates]]</noinclude>";
		
		
		run.withOwnUser(api -> {
			var sb = new StringBuilder();
			sb.append(intro)
				.append("{{#switch:{{{1}}}");
			for(var def:pageDefs) {
				sb
					.append("|")
					.append(def.name)
					.append("={{Paizo store/")
					.append(def.name)
					.append("|{{{2}}}}}");
			}
			sb
				.append("}}")
				.append(outro)
				.append("<noinclude>\n")
				.append("{{Documentation|content=\n")
				.append("<wikitext doctable>")
				.append("<wikitext-row>{{ISBN|{{Paizo store|upc|PZO9205}}}}</wikitext-row>")
				.append("<wikitext-row>{{Paizo store|URL|PZO9205}}</wikitext-row>")
				.append("<wikitext-row>{{Paizo store|price|PZO9205}}</wikitext-row>")
				.append("<wikitext-row>{{Paizo store|URL|not existing}}</wikitext-row>")
				.append("<wikitext-row>{{Paizo store|not existing}}</wikitext-row>")
				.append("</wikitext>")
				.append("}}</noinclude>");
			api.editIfChange(PageRef.of("Template:Paizo store"), sb.toString(), "Automatic update from store");
		
			for(var def:pageDefs) {
				createPage(api, pages, def, intro, outro);
			}
		});
	}

	private static String checkIsbn(String upc) {
		if(upc == null)
			return upc;
		upc = upc.trim();
		try {
			var isbn = ISBN.valueOf(upc);
			return isbn.toString();
		} catch(Exception e) {
			return upc;
		}
	}

	private void createPage(WikiAPI api, List<Props> pages, PageDef def, String intro, String outro) {
		var sb = new StringBuilder().append(intro).append("{{#switch:{{{1}}}");
		pages.stream()
			.filter(p->StringUtils.isNotBlank(def.getter.apply(p)))
			.collect(Collectors.groupingBy(p->def.getter.apply(p).trim()))
			.entrySet()
			.stream()
			.peek(e->Collections.sort(e.getValue(), Comparator.comparing(v->v.getSku())))
			.sorted(Comparator.comparing(e->e.getValue().getFirst().getSku()))
			.forEach(e->sb
					.append("\n|")
					.append(e.getValue().stream()
						.map(Props::getSku)
						.collect(Collectors.joining("|"))
						)
					.append("=")
					.append(e.getKey()));
		sb.append("}}").append(outro);
		
		api.editIfChange(PageRef.of("Template:Paizo store/"+def.name), sb.toString(), "Automatic update from store");
	}
	
	private PZCategory collectCategories(CloseableHttpClient client, ClassicHttpRequest baseRequest) throws IOException {
		var root = new PZCategory();
		root.setResolvedChildren(collectCategories(client, baseRequest, null, new HashMap<>()));
		return root;
	}
	
	private List<PZCategory> collectCategories(CloseableHttpClient client, ClassicHttpRequest baseRequest, String id, Map<Integer, PZCategory> mapping) throws IOException {
		log.info("Collecting categories for {}", id);
		var query = """
			{
				site {
			      categoryTree(rootEntityId: %s) {
			        entityId
			        name
			        children {
			          entityId
			          name
			          children {
			            entityId
			          }
			        }
			      }
			    }
			}
			""".formatted(id);
		var resp = graphQLRequest(client, baseRequest, query, PZCategoryResponse.class);
		var root = resp.getData().getSite().getCategoryTree();
		
		for(var r:root) {
			r.forEachRaw(c -> {
				if(c.getName() != null) {
					mapping.putIfAbsent(c.getEntityId(), c);
				}
			});
		}
		
		for(var r:root) {
			r.forEachRaw(c -> {
				if(c.getChildren() == null) return;
				for(var cc:c.getChildren()) {
					var existing = mapping.get(cc.getEntityId());
					if(existing == null) {
						try {
							collectCategories(client, baseRequest, Integer.toString(cc.getEntityId()), mapping);
						} catch (IOException e) {
							throw new RuntimeException();
						}
					}
					existing = mapping.get(cc.getEntityId());
					c.getResolvedChildren().add(existing);
				}
			});
		}
		return resp.getData().getSite().getCategoryTree();
	}

	@SneakyThrows
	private String collectResponses(CloseableHttpClient client, ClassicHttpRequest baseRequest, State state, PZCategory category, String page) {
		log.info("Querying paizo category '{}' store page '{}'", category.getName(), page);
		//for the structure see https://developer.bigcommerce.com/graphql-storefront/explorer
		var query = """
				{
					site {
						category (entityId: %s) {
							products (first: 50%s) {
								pageInfo {
									endCursor
									hasNextPage
								}
								edges {
									node {
										entityId
										name
										sku
										upc
										path
										variants {
						                    edges {
						                    	node {
						                    		sku
													upc
						                    		prices {
														price {
															formattedV2
														}
														retailPrice {
															formattedV2
														}
													}
						                   		}
						                    }
						                }
									}
								}
							}
						}
					}
				}
				""".formatted(
					Integer.toString(category.getEntityId()),
					page==null?"":(", after:\""+page+"\"")
				);
			
			var resp = graphQLRequest(client, baseRequest, query, GraphQLResponse.class);
			var products = resp.getData().getSite().getCategory().getProducts();
			
			products.getEdges().forEach(e->state.addEntries(e.getNode()));
			save(state);
			
			if(products.getPageInfo().isHasNextPage()) {
				Uninterruptibles.sleepUninterruptibly(SLEEP);
				return products.getPageInfo().getEndCursor();
			}
			return null;
	}
	
	private <T> T graphQLRequest(CloseableHttpClient client, ClassicHttpRequest baseRequest, String query, Class<T> type) throws IOException {
		var request = ClassicRequestBuilder.copy(baseRequest)
				.setEntity(new StringEntity(Jackson.JSON.writeValueAsString(Map.of("query", query)), ContentType.APPLICATION_JSON))
				.build();
		var json = client.<String>execute(request, resp->{
			String content = EntityUtils.toString(resp.getEntity(), StandardCharsets.UTF_8);
			if(resp.getCode()<300)
				return content;
			else
				throw new IOException("Status "+resp.getCode()+", Content:\n"+content);
		});
		return Jackson.JSON.readValue(json, type);
	}

	private RemoteWebDriver createDriver() throws MalformedURLException {
		if(selenium != null) {
			return new RemoteWebDriver(
					URI.create("http://"+selenium).toURL(),
					new FirefoxOptions()
						.addArguments("-headless")
						.enableBiDi()
			);
		}
		return new FirefoxDriver(new FirefoxOptions().enableBiDi());
	}

	private static final ObjectWriter WRITER = Jackson.YAML
			.writerFor(State.class);
	private void save(State state) throws IOException {
		File tmp = File.createTempFile("paizo_", ".yaml");
		WRITER.writeValue(tmp, state);
		FileUtils.deleteQuietly(STATE_FILE);
		FileUtils.moveFile(tmp, STATE_FILE, StandardCopyOption.REPLACE_EXISTING);
	}
	
}
