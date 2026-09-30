package io.github.pfwikis.bots.paizoretriever;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import io.github.pfwikis.bots.common.api.generated.params.NS;
import io.github.pfwikis.bots.common.api.model.PageRef;
import io.github.pfwikis.bots.common.bots.DualBot;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.utils.Jackson;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DriveThru extends DualBot {
	
	
	public DriveThru() {
		super("drivethru-retriever", "Drivethru Retriever");
	}
	
	@Override
	public String getDescription() {
		return "This bot keeps a list of drivethru links up to date.";
	}
	
	@Override
	protected void run(RunContext ctx) throws Exception {
		var req = ClassicRequestBuilder
			.get("https://api.drivethrurpg.com/api/vBeta/products")
			.addParameter("page", "1")
			.addParameter("pageSize", "50")
			.addParameter("publisherId[require]", "5549")
			.addParameter("properties[]", "productId")
			.addParameter("properties[]", "isbn")
			.addParameter("properties[]", "sku")
			.addParameter("isCommunityContent", "false");
		
		List<DTProduct> products = new ArrayList<>();
		
		try(var client = HttpClients.custom().build()) {
			while(req != null) {
				log.info("Reading page {}", req.getPath());
				var resp = client
					.execute(req
						.addHeader("Host", "api.drivethrurpg.com")
						.addHeader("Accept", "application/vnd.api+json")
						.build(),
						r -> {
							String content = EntityUtils.toString(r.getEntity(), StandardCharsets.UTF_8);
							if(r.getCode()<300)
								return content;
							else
								throw new RuntimeException("Status "+r.getCode()+", Content:\n"+content);
						}
					);
				
				var parsed = Jackson.JSON_LENIENT.readValue(resp, DTResponse.class);
				if(parsed.links.next != null) {
					req = ClassicRequestBuilder.get("https://api.drivethrurpg.com"+parsed.links.next);
				}
				else {
					req = null;
				}
				
				for(var p:parsed.getData()) {
					if(StringUtils.isNoneBlank(p.attributes.isbn) || StringUtils.isNoneBlank(p.attributes.sku)) {
						products.add(p.getAttributes());
					}
				}
			}
		}
		
		var res = Jackson.JSON.createObjectNode();
		products.stream()
			.flatMap(p->Arrays.asList(p.isbn, p.sku)
				.stream()
				.filter(Objects::nonNull)
				.map(v->v.toUpperCase().replaceAll("[^A-Z0-9]+", ""))
				.map(r->Pair.of(r, p.productId))
			)
			.sorted(Comparator.comparing(Pair::getKey))
			.forEach(p->res.put(p.getKey(), p.getValue()));
		
		run.withOwnUser(w-> {
	
			w.editJsonIfChange(
				PageRef.of(NS.TEMPLATE, "Drivethru store/URL"),
				res,
				"Automatic update from store"
			);
		});	
	}
	
	@Data
	public static class DTResponse {
		private DTLinks links;
		private List<DTData> data;
	}
	
	@Data
	public static class DTLinks {
		private String next;
		private String self;
	}
	
	@Data
	public static class DTData {
		private DTProduct attributes;
		private String self;
	}
	
	@Data
	public static class DTProduct {
		private long productId;
		private String isbn;
		private String sku;
	}
	
	
	/*
	public static SortedSet<Book> collectOwnedBooks(DTAccessToken token) throws LoginException {
		try(var timer = Metrics.INSTANCE.driveThruConnection()) {
			SortedSet<Book> owned = new TreeSet<>();
			owned.addAll(CodexUser.NOT_LOGGED_IN.getOwnedBooks());
			boolean done = false;
			int page = 1;
			boolean noError = true;
			while(!done) {
				var resp = Unirest
						.get("https://api.drivethrurpg.com/api/vBeta/order_products")
						.queryString("page", page++)
						.queryString("pageSize", PAGE_SIZE)
						.queryString("archived", 0)
						.queryString("library", true)
						.queryString("getChecksum", 1)
						.queryString("getFilters", 1)
						.queryString("fields", "products_id")
						.header("Authorization", token.getToken())
						.header("Host", "api.drivethrurpg.com")
						.accept("application/json")
						.asObject(new GenericType<List<DTProduct>>() {});
				
				if(!resp.isSuccess()) {
					if(resp.getStatus() == 401 && noError) {
						DTAccessToken newToken = getAccessInfo(token.getApplicationKey());
						token.setToken(newToken.getToken());
						noError = false; //to prevent infinite loop
						page--;
						continue;
					}
					else
						throw new LoginException("Failed to get books");
				}
				
				var list = resp.getBody();
				if(list.size() < PAGE_SIZE) {
					done = true;
				}
				
				for(var product : list) {
					//is it a supported book?
					Book b = Book.fromId(product.getProductId());
					if(b != null) {
						owned.add(b);
						b.impliesOwnership(owned);
					}
					//otherwise it could be a different language
					else {
						b = GERMAN_MAPPING.get(product.getProductId());
						if(b != null) {
							owned.add(b);
							b.impliesOwnership(owned);
						}
					}
				}
			}
			return ImmutableSortedSet.copyOf(owned);
		}
	}
	
	@StandardException
	public static class LoginException extends RuntimeException {}
	
	public static DTAccessToken getAccessInfo(CloseableHttpClient client, String appKey) throws LoginException {
		try {
			var resp = client
				.execute(ClassicRequestBuilder
					.post("https://api.drivethrurpg.com/api/vBeta/auth_key")
					.addParameter("applicationKey", appKey)
					.addHeader("Host", "api.drivethrurpg.com")
					.addHeader("Accept", "application/json")
					.build(),
					r -> {
						String content = EntityUtils.toString(r.getEntity(), StandardCharsets.UTF_8);
						if(r.getCode()<300)
							return content;
						else
							throw new LoginException("Status "+r.getCode()+", Content:\n"+content);
					}
				);
			var result = Jackson.JSON.readValue(resp, DTAccessToken.class);
			
			result.setApplicationKey(appKey);
			return result;
		}
		catch(Exception e) {
			throw new LoginException("Failed to get access token ", e);
		}
	}*/
}