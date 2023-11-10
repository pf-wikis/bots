package io.github.pfwikis.bots.index.common;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.ScalarStyle;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

public class IJackson {

	public static final ObjectMapper JSON;
	public static final ObjectMapper YAML;
	static {
		var dumper = new DumperOptions();
		dumper.setIndicatorIndent(2);
		dumper.setIndentWithIndicator(true);
		dumper.setSplitLines(false);
		dumper.setDefaultScalarStyle(ScalarStyle.FOLDED);
		YAML = new ObjectMapper(YAMLFactory
			.builder()
			.dumperOptions(dumper)
			.build()
			.enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
		)
		.setDefaultPropertyInclusion(Include.NON_EMPTY);
		
		JSON = new ObjectMapper()
			.setDefaultPropertyInclusion(Include.NON_EMPTY)
			.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}
}
