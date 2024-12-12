package io.github.pfwikis.bots.index.downloader;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.common.util.concurrent.Uninterruptibles;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.model.SemanticAsk.Result;
import io.github.pfwikis.bots.utils.Jackson;
import io.github.pfwikis.bots.utils.Retry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.With;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class Downloader {
	
	private final String token;
	private final WikiAPI wiki;
	
	public Document jsoup(String url) throws IOException {
		return Jsoup.newSession()
			.cookie("sessionId", token)
			.url(url)
			.maxBodySize(0)
			.timeout(500000)
			.get();
	}
	
	public static void main(String... args) throws IOException {
		new Downloader(args[0], WikiAPI.create(Wiki.PF, null, null)).start();
		new Downloader(args[0], WikiAPI.create(Wiki.SF, null, null)).start();
	}
	
	private static final Set<String> SKIP_LIST = Set.of(
		"Facts:Advanced Class Guide Playtest",
		"Facts:Mythic Adventures Playtest",
		"Facts:Necropolis",
		"Facts:Occult Adventures Playtest",
		"Facts:The Tome of Horrors Complete"
	);
	
	public void start() throws IOException {
		var books = wiki.semanticAsk("[[Facts:+]][[Fact type::Template:Facts/Book]]|?Website");
		for(var book:books) {
			log.info("Checking {}", book.getPage());
			if(book.getPrintouts() == null || book.getPrintouts().getWebsite() == null) continue;
			if(SKIP_LIST.contains(book.getPage())) continue;
			
			var article = StringUtils.removeStart(book.getPage(), "Facts:");
			var dirName = URLEncoder.encode(article.replace(' ', '_'), StandardCharsets.UTF_8);
			var dir = new File("files/downloads", dirName);
			
			if(dir.isDirectory()) continue;
			
			Retry.times(()->{this.checkDownload(book, article, dir); return null;}, 5, 5);
		}
	}
	
	private void checkDownload(Result book, String article, File dir) throws IOException {
		var tmpDir = Files.createTempDirectory("paizo-downloader-").toFile();
		var info = checkDownloadPage(book, article, tmpDir);
		Jackson.YAML.writeValue(new File(tmpDir, "info.yaml"), info);
		FileUtils.moveDirectory(tmpDir, dir);
	}
	
	private DownloadInfo checkDownloadPage(Result book, String article, File tmpDir) throws IOException {
		DownloadInfo info = new DownloadInfo(article);
		
		String url = book.getPrintouts().getWebsite();
		if(!url.startsWith("https://paizo.com/products/")) return info.withNoFileReason("not paizo url: "+url);
		
		var doc = jsoup(url);
		
		var text = doc.text();
		if( //exclude future or unabailable products
			text.contains("Product Availability Unavailable")
			|| text.contains("Will be available for purchase")
		) return info.withNoFileReason("no product available");
		
		if(text.contains("Sign In")) {
			log.error("Logged out");
			System.exit(-1);
		}
		var bigTables = doc.select("table.alternate-rows.alternate-rows-small.table-spacing-large");
		if(bigTables.isEmpty()) return info.withNoFileReason("no download link");
		
		for(var c:bigTables.getFirst().children()) {
			var txt = c.text();
			if(
				c.tagName().equals("tbody")
				&& !txt.isEmpty()
				&& !(txt.contains("Serial Number") && txt.contains("no data"))
				&& !txt.toLowerCase().contains("file per chapter")
				&& !txt.contains("JPGs")
			) {
				var lastUpdated = getLastUpdated(c.children().getFirst());
				info = info.withLastUpdated(lastUpdated);
				var e = c.children().getFirst();
				log.info("  Downloading {}", lastUpdated);
				
				download(tmpDir, e);
				
				unpack(tmpDir);
			}
		}
		
		return info;
	}

	private void unpack(File tmpDir) throws IOException {
		AtomicInteger counter = new AtomicInteger(1);
		Files.walk(tmpDir.toPath())
			.map(Path::toFile)
			.filter(f->f.getName().endsWith(".zip"))
			.forEach(f->{
				try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(f.toPath()))) {
					
					
					ZipEntry zipEntry = zis.getNextEntry();
					while (zipEntry != null) {
						if(!zipEntry.isDirectory()) {
							Files.write(
								tmpDir.toPath().resolve(
										"%03d".formatted(counter.getAndIncrement())
										+"."
										+FilenameUtils.getExtension(zipEntry.getName())
								), 
								zis.readAllBytes()
							);
						}
						
						zis.closeEntry();
						zipEntry = zis.getNextEntry();
					}
					zis.close();
					f.delete();
				} catch(Exception e) {
					log.error("Failed to unpack {}", tmpDir, e);
				}
			});
	}

	@Getter @With
	@RequiredArgsConstructor
	@AllArgsConstructor
	public static class DownloadInfo {
		private final String article;
		private OffsetDateTime lastUpdated;
		private String noFileReason;
	}

	private static OffsetDateTime getLastUpdated(Element e) {
		var updated =e.children().get(3);
		if("never".equals(updated.text())) {
			return null;
		}
		else {
			return OffsetDateTime.parse(
				updated.children().getFirst().attr("datetime")
			);
		}
	}
	
	private void download(File tmpDir, Element e) {
		var updateLink = e.getElementsByAttributeValueStarting(
			"href",
			"https://paizo.com/cgi-bin/WebObjects/Store.woa/wa/Personalizer/"
		).getFirst().attr("href");
		
		boolean first = true;
		while(true) {
			try {
				try {
					var downloadLink = e.getElementsByAttributeValueStarting("href", "https://downloads.paizo.com/");
					if(!downloadLink.isEmpty()) {
						var link = downloadLink.getFirst().attr("href");
						var f = new File(tmpDir, link.substring(link.lastIndexOf('/')));
						FileUtils.copyInputStreamToFile(URI.create(link).toURL().openStream(), f);
						return;
					}
					
				} catch(Exception iex) {}
				//if no downloadlink we have to update
				if(!first)
					Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
				if(updateLink.startsWith("https://downloads.paizo.com/"))
					System.out.println();
				e = jsoup(updateLink);
				first = false;
			} catch(Exception ex) {
				Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
				ex.printStackTrace();
			}
		}
	}
}
