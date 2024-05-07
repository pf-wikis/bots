package io.github.pfwikis.bots.templatestyles;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.EnumMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.Wiki;
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
		var fullStyle = new StringBuilder();
		
		for(var style:styles) {
			var className = StringUtils.removeStart(style.getTitle(), "Style:")
					.toLowerCase()
					.replaceAll("[^a-z0-9]", "-");
			if(Character.isDigit(className.charAt(0))) className = "template-"+className;
			var txt = run.getWiki().getPageText(style.getTitle());
			var result = CONSTANTS.get(run.getServer())+"\n."+className+"{"+txt+"}";
			
			var in = File.createTempFile("paizowikis-in-", ".less");
			Files.writeString(in.toPath(), result);
			var out = File.createTempFile("paizowikis-out-", ".less");
			try {
				int status = new ProcessBuilder(
						"npx"+(System.getProperty("os.name").contains("Windows")?".cmd":""),
						"lessc",
						in.getAbsolutePath(),
						out.getAbsolutePath()
					)
					.inheritIO()
					.start().waitFor();
				if(status != 0) {
					reportException("Failed to compile less from %s/wiki/%s with status %s".formatted(run.getServer().getUrl(), style.getTitle(), status));
					continue;
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			
			var content = Files.readString(out.toPath());
			fullStyle
				.append("\n\n")
				.append("/*From ")
				.append(style.getTitle())
				.append("*/\n")
				.append(content);
			log.info("Added {} to styles", style.getTitle());
		}
		
		var result = new File("outputs/"+run.getServer().getCode()+"/templatestyles.css");
		result.getParentFile().mkdirs();
		Files.writeString(result.toPath(), fullStyle, StandardCharsets.UTF_8);
	}

	@Override
	public String getDescription() {
		return
			"""
			This bot collects less code from the Style namespace and compiles them hourly for use in the wiki. If any style page is invalid it is skipped.
			The following variables are provided:
			<syntaxhighlight lang="less">
			%s
			</syntaxhighlight>
			""".formatted(CONSTANTS.get(run.getServer()));
	}
	
	private static final String SHARED = """
	.mixin-hlist() {
	    margin: 0;
	    display: flex;
	    flex-wrap: wrap;
	    justify-content: center;
	    column-gap: 1.2em;
	    > li {
	        display: block;
	        position: relative;
	
	        &+li::before {
	            content: "·";
	            position: absolute;
	            left: -.6em;
	            font-weight: bold;
	            color: @black;
	            transform: translateX(-50%);
	        }
	    }
	}
	
	.clearfix() {
	    &::after {
	        clear: both;
	        content: '';
	        display: block;
	    }
	}
	""";
	
	private static final Map<Wiki,String> CONSTANTS = Map.of(
		Wiki.PF, """
		@black: rgb(32, 33, 34);
		@article-color: #f5f5dc;
		@article-color-contrast: saturate(@article-color, 25%);
		@background-color: rgb(87,22,13);
		@menu-border-color-thick: #a9a9a9;
		@menu-border-color-thin: #d3d3d3;
		@menu-heading-background-color: #d2b48c;
		@menu-background-hover-color: #f5deb3;
		
		@subheading-color: #112a61;
		@link-color: average(#3366cc, @subheading-color);	
		"""+SHARED,
		Wiki.SF, """
		@black: rgb(32, 33, 34);
		@article-color: #dafafc;
		@article-color-contrast: saturate(@article-color, 20%);
		@background-color: #003030;
		@menu-border-color-thick: #a9a9a9;
		@menu-border-color-thin: #d3d3d3;
		@menu-heading-background-color: #48d1cc;
		@menu-background-hover-color: #48d1cc;
		
		@subheading-color: #112a61;
		@link-color: average(#3366cc, @subheading-color);
		"""+SHARED);
}
