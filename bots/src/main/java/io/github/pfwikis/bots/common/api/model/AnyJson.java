package io.github.pfwikis.bots.common.api.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import tools.jackson.databind.JsonNode;

@EqualsAndHashCode
@ToString
public abstract class AnyJson {
	private Map<String, JsonNode> jsonFields = new LinkedHashMap<>();

    @JsonAnySetter
    void setJsonField(String key, JsonNode value) {
    	jsonFields.put(key, value);
    }
}
