package io.github.pfwikis.bots.templatestyles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class TemplateStyles extends SimpleBot {

	public TemplateStyles() {
		super("template-styles", "Bot Template Styles");
	}

	@Override
	public void run() throws IOException {
		var styles = run.getWiki().getPagesInNamespace("Style");
		
		for(var style:styles) {
			var className = StringUtils.removeStart(style.getTitle(), "Style:")
					.toLowerCase()
					.replaceAll("[^a-z]", "-");
			var txt = run.getWiki().getPageText(style.getTitle());
			var result = "."+className+"{"+txt+"}";
			
			log.info(result);
			var in = File.createTempFile("paizowikis-in-", ".less");
			Files.writeString(in.toPath(), result);
			var out = File.createTempFile("paizowikis-out-", ".css");
			try {
				int status = new ProcessBuilder("lessc", in.getAbsolutePath(), out.getAbsolutePath())
					.inheritIO()
					.start().waitFor();
				log.info("Exited with {}", status);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			
			
			var content = Files.readString(out.toPath());
			log.info(content);
		}
	}

	@Override
	protected String getDescription() {
		return
			"""
			This bot collects less code from the namespace and compiles them for use in the wiki.
			""";
	}


}
