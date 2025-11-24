package io.github.pfwikis.bots.paizoretriever;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.util.concurrent.Uninterruptibles;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.bots.DualBot;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.paizoretriever.LdJson.Product;
import io.github.pfwikis.bots.paizoretriever.State.Page;
import io.github.pfwikis.bots.utils.Jackson;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter @Setter
@Parameters
public class PaizoRetriever extends DualBot {
	
	@Parameter(names = "--firefox")
	private String firefoxBin; 
	
	private static final long SLEEP = Duration.ofMillis(250).toMillis();
	private static final File STATE_FILE = new File("outputs/crawl/state.yaml");
	private static final String[] ENTRY_SHOP_URLS =  {
			 "https://store.paizo.com/pathfinder/",
			"https://store.paizo.com/pathfinder/pathfinder-second-edition/coming-soon/",
			"https://store.paizo.com/pathfinder/pathfinder-society/",
			"https://store.paizo.com/starfinder/",
			"https://store.paizo.com/starfinder/starfinder-second-edition/coming-soon/",
			"https://store.paizo.com/starfinder/starfinder-society/"
		};
	
	
	public PaizoRetriever() {
		super("paizo-retriever", "Bot Paizo Retriever");
	}
	
	@Override
	public String getDescription() {
		return "This bot keeps an up to date list of Paizo SKU->store page.";
	}

	@Override
	public void run(RunContext ctx) throws IOException {
		STATE_FILE.getParentFile().mkdirs();
		State state;
		if(STATE_FILE.exists())
			state = Jackson.YAML.readValue(STATE_FILE, State.class);
		else
			state = new State();
		var service = GeckoDriverService.createDefaultService();
		if(firefoxBin != null) {
			service.setExecutable(firefoxBin);
		}
		var driver = new FirefoxDriver(
				service,
				new FirefoxOptions()
					.addArguments(this.localMode?List.of():List.of("-headless"))
		);
		
		try {
			crawlLoop(driver, state);
		} catch(Exception e) {
			log.error("Failed paizo crawling", e);
		} finally {
			driver.close();
			save(state);
			
		}
	}

	private static final ObjectWriter WRITER = Jackson.YAML
			.writerFor(State.class);
	private void save(State state) throws IOException {
		File tmp = File.createTempFile("paizo_", ".yaml");
		WRITER.writeValue(tmp, state);
		FileUtils.deleteQuietly(STATE_FILE);
		FileUtils.moveFile(tmp, STATE_FILE, StandardCopyOption.REPLACE_EXISTING);
	}

	private void crawlLoop(WebDriver driver, State state) throws IOException {
		for(var url:ENTRY_SHOP_URLS) {
			checkOpenList(driver, state, url);
		}
		addRechecks(state);
		
		log.info("Checking {} pages", state.getOpenList().size());
		while(!state.getOpenList().isEmpty()) {
			var url = state.getOpenList().getFirst();
			log.info("  Checking {}", url);
			try {
				crawl(driver, state, url);
				state.getOpenList().removeFirst();
				save(state);
				Uninterruptibles.sleepUninterruptibly(SLEEP, TimeUnit.MILLISECONDS);
			} catch(Exception e) {
				log.error("Failed crawling {}", url, e);
			}
		}
		
		log.info("Openlist empty");
		
		createPages(state);
	}

	private void createPages(State state) {
		var pages = state.getPages().stream()
				.filter(p->p.getStructuredContent()!=null)
				.map(Page::getStructuredContent)
				.filter(p->p.getSku()!=null)
				.filter(p->p.getSku().startsWith("PZO"))
				.sorted(Comparator.comparing(Product::getSku))
				.toList();
		var intro = "<noinclude>{{Bot created|Bot Paizo Retriever|This template is automatically created and update from the paizo webstore.}}</noinclude><includeonly>";
		var outro = "</includeonly><noinclude>[[Category:Data templates]]</noinclude>";
		
		
		run.withOwnUser(api -> {
			var sb1 = new StringBuilder();
			sb1.append(intro)
				.append("{{#switch:{{{1}}}")
				.append("|URL={{Paizo store/URL|{{{2}}}}}")
				.append("|name={{Paizo store/name|{{{2}}}}}")
				.append("}}")
				.append(outro)
				.append("<noinclude>\n")
				.append("{{Documentation|content=\n")
				.append("<wikitext doctable>")
				.append("<wikitext-row>{{Paizo store|name|PZO9205}}</wikitext-row>")
				.append("<wikitext-row>{{Paizo store|URL|PZO9205}}</wikitext-row>")
				.append("<wikitext-row>{{Paizo store|URL|not existing}}</wikitext-row>")
				.append("<wikitext-row>{{Paizo store|not existing}}</wikitext-row>")
				.append("</wikitext>")
				.append("}}</noinclude>");
			api.editIfChange("Template:Paizo store", sb1.toString(), "Automatic update from store");
		
			createPage(api, pages, "URL", Product::getUrl, intro, outro);
			createPage(api, pages, "name", Product::getName, intro, outro);
		});
	}

	private void createPage(WikiAPI api, List<Product> pages, String name, Function<Product, String> value, String intro, String outro) {
		var sb2 = new StringBuilder().append(intro).append("{{#switch:{{{1}}}");
		pages.stream()
			.filter(p->p.getUrl() != null)
			.collect(Collectors.groupingBy(p->value.apply(p)))
			.entrySet()
			.stream()
			.sorted(Comparator.comparing(Entry::getKey))
			
			.forEach(e->sb2
					.append("|")
					.append(e.getValue().stream()
						.map(Product::getSku)
						.sorted()
						.collect(Collectors.joining("|"))
						)
					.append("=")
					.append(e.getKey()));
		sb2.append("}}").append(outro);
		
		api.editIfChange("Template:Paizo store/"+name, sb2.toString(), "Automatic update from store");
	}

	private void crawl(WebDriver driver, State state, String url) {
		driver.get(url);
		var now = Instant.now();
		var p = state.findPage(url, now);
		p.setLastChecked(now);
		
		var structured = driver.findElements(By.cssSelector("script[type=\"application/ld+json\"]"))
				.stream()
				.map(e->e.getAttribute("innerHTML"))
				.filter(StringUtils::isNoneBlank)
				.map(json -> {
					try {
						return Jackson.JSON_LENIENT.readValue(json, LdJson.class);
					} catch (JsonProcessingException e) {
						log.warn("Failed to parse json for {}", url, e);
						return null;
					}
				})
				.filter(Objects::nonNull)
				.filter(LdJson.Product.class::isInstance)
				.map(LdJson.Product.class::cast)
				.toList();
		if(structured.size()==1) {
			var cnt = structured.getFirst();
			if(!Objects.equals(p.getStructuredContent(), cnt)) {
				p.setStructuredContent(structured.getFirst());
				p.setLastChanged(now);
				p.setBackOffCounter(0);
			}
			else {
				p.setBackOffCounter(p.getBackOffCounter()+1);
			}
		}
		else if(structured.size()>1) {
			throw new UnsupportedOperationException(url+" has multiple ld+json");
		}
	}

	private void addRechecks(State state) throws IOException {
		var now = Instant.now();
		int added = 0;
		for(var p:state.getPages()) {
			if(
				Duration.between(p.getLastChecked(), now).toHours()>24*(1+Math.min(p.getBackOffCounter(), 30)+(Math.abs(p.getUrl().hashCode())%12))
				&& !state.getOpenList().contains(p.getUrl())
			) {
				state.getOpenList().add(p.getUrl());
				added++;
			}
		}
		if(added > 0) {
			log.info("Added {} pages for rechecking", added);
			save(state);
		}
	}

	private void checkOpenList(WebDriver driver, State state, String url) throws IOException {
		for(int page=1;true;page++) {
			log.info("Reading page {} of {}", page, url);
			driver.get(url+"?sort=newest&page="+page);
			//check if we are at the end of the pages
			if(driver.findElements(By.className("productGrid")).isEmpty()) {
				return;
			}
			
			boolean changes = false;
			
			for(var product : driver.findElements(By.className("product"))) {
				var target = product.findElement(By.tagName("a")).getAttribute("href");
				if(target != null) {
					changes|=state.addLink(target);
				}
			}
			if(!changes) {
				log.info("Nothing new, not checking other pages");
				return;
			}
			save(state);
			
			Uninterruptibles.sleepUninterruptibly(SLEEP, TimeUnit.MILLISECONDS);
		}
		
	}

	
}
