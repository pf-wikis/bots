package io.github.pfwikis.bots.utils;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.ScalarStyle;
import org.yaml.snakeyaml.LoaderOptions;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Jackson {

	public static final ObjectMapper JSON;
	public static final ObjectMapper JSON_LENIENT;
	public static final ObjectMapper YAML;
	static {
		var dumper = new DumperOptions();
		dumper.setIndicatorIndent(2);
		dumper.setIndentWithIndicator(true);
		dumper.setSplitLines(false);
		dumper.setDefaultScalarStyle(ScalarStyle.FOLDED);
		var loader = new LoaderOptions();
		loader.setCodePointLimit(Integer.MAX_VALUE);
		YAML = new ObjectMapper(YAMLFactory
			.builder()
			.dumperOptions(dumper)
			.loaderOptions(loader)
			.build()
			.enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
			.disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
		)
		.setDefaultPropertyInclusion(Include.NON_EMPTY)
		.registerModule(new GuavaModule())
		.registerModule(new JavaTimeModule());
		
		JSON = new ObjectMapper()
			.setDefaultPropertyInclusion(Include.NON_EMPTY)
			.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
			.registerModule(new JavaTimeModule())
			.findAndRegisterModules();
		
		JSON_LENIENT = JSON
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
}
