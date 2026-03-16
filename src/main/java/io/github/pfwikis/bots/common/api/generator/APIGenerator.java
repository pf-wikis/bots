package io.github.pfwikis.bots.common.api.generator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import com.fizzed.rocker.RockerModel;
import com.fizzed.rocker.runtime.RockerRuntime;
import com.fizzed.rocker.runtime.StringBuilderOutput;
import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import com.google.googlejavaformat.java.JavaFormatterOptions;

import io.github.pfwikis.bots.common.api.generator.api.GenAPIModule;
import io.github.pfwikis.bots.common.api.generator.api.GenAPIResult;
import io.github.pfwikis.bots.common.api.generator.model.APIModule;
import io.github.pfwikis.bots.common.api.responses.AAPIWrappedResponse;
import io.github.pfwikis.bots.utils.Jackson;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.node.ArrayNode;

@Slf4j
public class APIGenerator {
	public static void main(String... args) throws IOException {
		new APIGenerator().generate();
	}
	
	private final File root = new File("src/main/java/io/github/pfwikis/bots/common/api/generated");
	private Set<String> workList = new HashSet<>();
	private Map<String, APIModule> modules = new HashMap<String, APIModule>();

	public void generate() throws IOException {
		RockerRuntime.getInstance().setReloading(true);
		//FileUtils.deleteQuietly(root);
		
		getInfo(null, "main");
		for(var m:modules.values()) {
			render(GenModule.template(m), new File(root, m.getJavaName()+".java"));
			for(var p:m.getParameters()) {
				if(p.requiresEnum()) {
					render(GenParam.template(p), new File(root, "params/"+p.getJavaType()+".java"));
				}
			}
		}
		generateNamespaceEnum();
	}

	private void generateNamespaceEnum() throws IOException {
		var res = parseJson("https://pathfinderwiki.com/w/api.php?format=json&action=query&meta=siteinfo&siprop=namespaces&utf8=1&formatversion=2", GenAPIResult.class);
		render(GenNS.template(List.copyOf(res.getQuery().getNamespaces().values())), new File(root, "params/NS.java"));
	}

	public APIModule getInfo(GenAPIModule parent, String path) {
		log.info(path);
		if(!workList.add(path)) {
			return Objects.requireNonNull(modules.get(path));
		}
		String url = "https://pathfinderwiki.com/w/api.php?action=paraminfo&format=json&modules="
			+ path
			+ "&helpformat=html&utf8=1&formatversion=2";
		var info = parseJson(url, GenAPIResult.class).getParaminfo();
		
		//clean deprecated and internal things away
		for(var m:info.getModules()) {
			m.getParameters().removeIf(p->p.isDeprecated());
			m.getParameters().removeIf(p->p.getModule().isInternal());
		}
		if(info.getModules().getFirst().isDeprecated() || info.getModules().getFirst().isInternal()) {
			return null;
		}
		var mod = info.getModules().getFirst();
		
		//handle special cases
		if("query+pageprops".equals(path)) {
			url = "https://pathfinderwiki.com/w/api.php?action=query&list=pagepropnames&ppnlimit=max&format=json&utf8=1&formatversion=2";
			var props = parseJson(url, AAPIWrappedResponse.class).getOtherFields().get("query").get("pagepropnames").asArray();
			var arr = new ArrayNode(Jackson.JSON.getNodeFactory());
			props.valueStream()
				.map(n->n.asObject().get("propname").asString())
				.forEach(arr::add);
			mod.getParameters().forEach(p-> {
				if(p.getName().equals("prop")) {
					p.setType(arr);
				}
			});
		}
		
		var res = APIModule.create(this, mod);
		modules.put(path, res);
		return res;
	}

	public <T> T parseJson(String url, Class<T> type) {
		try {
			return Jackson.JSON.readValue(getJson(url), type);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getJson(String url) throws IOException {
		log.info("Downloading metadata from {}", url);
		try (var httpClient = HttpClients.createDefault()) {
			//TODO remove this cache
			var cacheDir = new File("cache");
			cacheDir.mkdirs();
			File cache = new File(cacheDir, Math.abs(url.hashCode())+".json");
			if(cache.exists()) {
				String json = Files.readString(cache.toPath());
				return json;
			}
			ClassicHttpRequest httpGet = ClassicRequestBuilder.get(url).build();
			return httpClient.execute(httpGet, resp -> {
				var json = EntityUtils.toString(resp.getEntity());
				Files.writeString(cache.toPath(), json);
				return json;
			});
		}
	}
	
	private void render(RockerModel template, File file) {
		try {
			var result = template.render(StringBuilderOutput.FACTORY).toString();
			
			try {
				result = new Formatter(
						new JavaFormatterOptions(false, true, JavaFormatterOptions.Style.AOSP)
				).formatSource(result).replace("    ", "\t");
			} catch(FormatterException e) {
				e.printStackTrace();
				//still write the unformatted to code
			}
			
			file.getParentFile().mkdirs();
			FileUtils.writeStringToFile(file, result, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
