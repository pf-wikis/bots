package io.github.pfwikis.bots.templatestyles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameters;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.bots.RunContext;
import io.github.pfwikis.bots.common.bots.SimpleBot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Parameters
public class TemplateStyles extends SimpleBot {

	public TemplateStyles() {
		super("template-styles", "Bot Template Styles");
	}
	
	
	private static record Entry(String page, String className) {}

	@Override
	public void run(RunContext ctx) throws IOException {
		var workDir = new File("outputs/"+run.getServer().getCode()+"/less");
		workDir.mkdirs();
		
		var styles = run.getWiki().getPagesInNamespace("Style")
			.stream()
			.map(p-> new Entry(
				p.getTitle(),
				toCssName(p.getTitle())
			))
			.toList();
		
		//delete files no longer existing
		Arrays.asList(workDir.listFiles())
			.forEach(f->{
				if(styles.stream().noneMatch(s->f.getName().equals(s.className()+".less"))) {
					FileUtils.deleteQuietly(f);
				}
			});
		
		//collect files we want to regenerate
		var toRegenerate = new HashSet<Entry>();
		if(ctx.getPage() != null) {
			toRegenerate.add(new Entry(ctx.getPage(), toCssName(ctx.getPage())));
		}
		else {
			toRegenerate.addAll(styles);
		}
		for(var style:styles) {
			if(!new File(workDir, style.className+".less").isFile()) {
				toRegenerate.add(style);
			}
		}
		
		//generate styles
		log.info("Regenerating styles for {}", toRegenerate);
		for(var style:toRegenerate) {
			var txt = run.getWiki().getPageText(style.page());
			var code = "/*From "+style.page()+"*/\n"+CONSTANTS.get(getWiki())+"."+style.className()+"{\n"+txt+"\n}";
			var result = compile(code, style.page());
			if(result != null) {
				move(result, new File(workDir, style.className+".less"));
				
			};
		}
		
		//merge
		var result = compile(
			styles.stream().map(s->"@import (optional) \""+workDir.getAbsolutePath()+"/"+s.className+".less\";").collect(Collectors.joining("\n")),
			"root"
		);
		var finalResult = new File("outputs/"+run.getServer().getCode()+"/templatestyles.css");
		move(result, finalResult);
	}

	private static void move(File src, File target) throws IOException {
		FileUtils.deleteQuietly(target);
		FileUtils.moveFile(src, target);
	}

	private File compile(String less, String source) {
		try {
			var in = File.createTempFile("paizowikis-in-", ".less");
			Files.writeString(in.toPath(), less);
			var out = File.createTempFile("paizowikis-out-", ".less");
			int status = new ProcessBuilder(
					"npx"+(System.getProperty("os.name").contains("Windows")?".cmd":""),
					"lessc",
					in.getAbsolutePath(),
					out.getAbsolutePath()
				)
				.inheritIO()
				.start().waitFor();
			FileUtils.deleteQuietly(in);
			if(status != 0) {
				reportException("Failed to compile less from %s/wiki/%s with status %s".formatted(run.getServer().getUrl(), source, status));
				FileUtils.deleteQuietly(out);
				return null;
			}
			return out;
			
		} catch (InterruptedException | IOException e) {
			throw new RuntimeException(e);
		}
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
	
	private String toCssName(String name) {
		var className = StringUtils.removeStart(name, "Style:")
			.toLowerCase()
			.replaceAll("[^a-z0-9]", "-");
		if(Character.isDigit(className.charAt(0))) className = "template-"+className;
		return className;
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

	.mixin-floating-box() {
			background-color: @article-color;
			box-shadow: 0 0 .3rem .1rem rgba(0,0,0,0.3);
			border: 1px solid @menu-border-color-thick;
			font-size: 0.9em;
			color: @black;
	}
	
	.clearfix() {
		&::after {
			clear: both;
			content: '';
			display: block;
		}
	}

	@full-width: ~"(min-width: 1000px)";
	@min-small-width: ~"(min-width:720px)";
	@min-tiny-width: ~"(min-width:480px)";
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
