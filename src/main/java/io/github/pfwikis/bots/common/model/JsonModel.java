package io.github.pfwikis.bots.common.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public abstract class JsonModel {
	private Map<String, Object> jsonFields = new LinkedHashMap<>();

    @JsonAnySetter
    void setJsonField(String key, Object value) {
    	jsonFields.put(key, value);
    }
}
