package io.github.pfwikis.bots.utils;

import org.snakeyaml.engine.v2.api.DumpSettings;
import org.snakeyaml.engine.v2.api.LoadSettings;
import org.snakeyaml.engine.v2.common.ScalarStyle;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

import tools.jackson.core.StreamReadFeature;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.cfg.CoercionAction;
import tools.jackson.databind.cfg.CoercionInputShape;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.node.JsonNodeFactory;
import tools.jackson.dataformat.yaml.YAMLFactory;
import tools.jackson.dataformat.yaml.YAMLMapper;
import tools.jackson.dataformat.yaml.YAMLWriteFeature;
import tools.jackson.datatype.guava.GuavaModule;

public class Jackson {

	public static final ObjectMapper JSON;
	public static final ObjectMapper JSON_LENIENT;
	public static final ObjectMapper YAML;
	public static final JsonNodeFactory NODES;
	static {
		var dumper = DumpSettings.builder()
			.setIndicatorIndent(2)
			.setIndentWithIndicator(true)
			.setSplitLines(false)
			.setDefaultScalarStyle(ScalarStyle.FOLDED)
			.build();
		var loader = LoadSettings.builder()
			.setCodePointLimit(Integer.MAX_VALUE)
			.build();
		new ObjectMapper();
		YAML = YAMLMapper.builder(YAMLFactory
			.builder()
			.dumperOptions(dumper)
			.loadSettings(loader)
			.enable(YAMLWriteFeature.MINIMIZE_QUOTES)
			.disable(YAMLWriteFeature.WRITE_DOC_START_MARKER)
			.build()
		)
		.changeDefaultPropertyInclusion(v->v.withValueInclusion(Include.NON_EMPTY))
		.addModule(new GuavaModule())
		.build();
		
		JSON = JsonMapper.builder()
			.changeDefaultPropertyInclusion(v->v.withValueInclusion(Include.NON_EMPTY))
			.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
			.enable(StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION)
			.findAndAddModules()
			.build();
		NODES = JSON.getNodeFactory();
		
		JSON_LENIENT = JSON.rebuild()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.withCoercionConfig(Enum.class, cfg->cfg.setCoercion(CoercionInputShape.EmptyString, CoercionAction.AsNull))
			.build();
	}
	public static JsonNode stringNodeOrNull(String stringOrNull) {
		if(stringOrNull == null)
			return null;
		return NODES.stringNode(stringOrNull);
	}
}
