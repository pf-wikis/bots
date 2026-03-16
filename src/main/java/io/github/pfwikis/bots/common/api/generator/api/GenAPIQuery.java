package io.github.pfwikis.bots.common.api.generator.api;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class GenAPIQuery {
	private Map<Integer, GenAPINamespace> namespaces;
	private List<GenAPIInterwiki> interwikimap;
}
