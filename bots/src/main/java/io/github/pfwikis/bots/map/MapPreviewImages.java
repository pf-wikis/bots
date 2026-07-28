package io.github.pfwikis.bots.map;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.beust.jcommander.Parameter;
import com.google.common.io.Files;
import com.google.common.primitives.Longs;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapPreviewImages extends SimpleBot {
	
	private static final File DIR = new File("outputs/pf/map_previews");
	private static final int MAX_SIZE = 1000;
	
	@Parameter(names = "--selenium")
	private String selenium;

	public MapPreviewImages() {
		super("map-preview-images", "Map Preview Images");
	}

	public static record Info(String filename, double[] bbox, int size, boolean sizeIsWidth, Double zoom) {}
	private static record TargetSize(int width, int height) {}
	
	@Override
	public void run(RunContext ctx) throws IOException, InterruptedException {
		if(run.getServer() == Wiki.SF) return;
		
		var profile = new FirefoxProfile();
		profile.setPreference("layout.css.devPixelsPerPx", "2.0");
		var selOptions = new FirefoxOptions()
			.setProfile(profile);
		RemoteWebDriver driver;
		if(selenium == null) {
			driver = new FirefoxDriver(selOptions);
		}
		else {
			driver = new RemoteWebDriver(URI.create("http://"+selenium).toURL(), selOptions
				.addArguments("--headless"));
		}
		try {
			driver.manage().window().setSize(new Dimension(MAX_SIZE+100, MAX_SIZE+500));
			
			driver.get("http://map.pathfinderwiki.com");
			var mapVersion = (String)driver.executeScript("return (typeof MAP_VERSION === 'undefined')?\"1\":MAP_VERSION;");
			long mapLastMod = TimeUnit.MINUTES.toMillis(Optional.ofNullable(Longs.tryParse(mapVersion)).orElse(1L).longValue());
			var infos = List.of(new Info(
				"Map preview 24.768519 -1.4654507.webp",
				new double[] {-2.13971285,30.38844261,0.95900448,32.40772493},
				1000,
				true,
				null
			));
			
			for(var info:infos) {
				handleInfo(info, driver, mapLastMod);
			}
		} finally {
			driver.close();
		}
	}

	private void handleInfo(Info info, RemoteWebDriver driver, long mapLastMod) throws IOException {
		if(info.bbox.length == 4) {
			double left = WebMercator.longitudeToX(info.bbox[0]);
			double right = WebMercator.longitudeToX(info.bbox[2]);
			double bottom = WebMercator.latitudeToY(info.bbox[1]);
			double top = WebMercator.latitudeToY(info.bbox[3]);
			
			double height = top - bottom;
			double width = right - left;
			double aspect = width/height;
			var targetSize = targetSize(info, aspect);
			String script = "map.jumpTo(map.cameraForBounds([%f, %f, %f, %f]));"
				.formatted(info.bbox[0],info.bbox[1],info.bbox[2],info.bbox[3]);
			generateImage(driver, mapLastMod, info.filename, targetSize, script);
		}
		else if(info.bbox.length == 2) {
			double aspect = 16/9;
			var targetSize = targetSize(info, aspect);
			String script = "map.jumpTo({center:[%f, %f], zoom: %f});"
					.formatted(info.bbox[0],info.bbox[1],info.zoom==null?7:info.zoom);
			generateImage(driver, mapLastMod, info.filename, targetSize, script);
		}
		else {
			log.error("Failed to generate map preview for {}", info);
		}
	}

	private TargetSize targetSize(Info info, double aspect) {
		aspect = new BigDecimal(aspect).setScale(2,RoundingMode.HALF_UP).doubleValue();
		int targetWidth = (int) Math.round(info.sizeIsWidth?info.size:info.size*aspect)/2;
		int targetHeight = (int) Math.round(info.sizeIsWidth?info.size/aspect:info.size)/2;
		
		if(targetWidth > MAX_SIZE) {
			double factor = ((double)MAX_SIZE)/targetWidth;
			targetWidth = MAX_SIZE;
			targetHeight = (int) Math.round(targetHeight*factor);
		}
		if(targetHeight > MAX_SIZE) {
			double factor = ((double)MAX_SIZE)/targetHeight;
			targetHeight = MAX_SIZE;
			targetWidth = (int) Math.round(targetWidth*factor);
		}
		return new TargetSize(targetWidth, targetHeight);
	}
	
	private void generateImage(RemoteWebDriver driver, long mapLastMod, String filename, TargetSize targetSize, String script) throws IOException {
		File result = new File(DIR, filename);
		long lastMod = result.lastModified();
		if(lastMod>=mapLastMod && false) {
			log.info("No change: {}",filename);
			return;
		}
		result.getParentFile().mkdirs();
		var img = (String)driver.executeAsyncScript("""
			var callback = arguments[arguments.length - 1];
			(async ()=>{
				console.log("Run");
				map.getContainer().style.width = '%dpx';
				map.getContainer().style.height = '%dpx';
				map.resize();
				%s
				var subscription = map.on('idle', () => {
					console.log("Idle");
					subscription.unsubscribe();
					callback(map.getCanvas().toDataURL('image/webp'));
				});
			})();
			""".formatted(
				targetSize.width,
				targetSize.height,
				script
			)
		);
		if(!img.startsWith("data:image/webp;base64,"))
			throw new IllegalStateException("Result started with "+img.substring(0, Math.min(50, img.length())));
		var data = Base64.getDecoder().decode(img.substring(23));
		Files.write(data, result);
		result.setLastModified(mapLastMod);
		log.info("Created: {}",filename);
	}

	@Override
	public String getDescription() {
		return "Generates preview images for pages using maps";
	}
}
