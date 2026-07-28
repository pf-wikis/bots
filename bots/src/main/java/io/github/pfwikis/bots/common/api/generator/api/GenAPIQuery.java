package io.github.pfwikis.bots.common.api.generator.api;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class GenAPIQuery {
	private Map<Integer, GenAPINamespace> namespaces;
	private List<GenAPIInterwiki> interwikimap;
	private List<UserGroup> usergroups;
	
	@Getter
	@Setter
	public static class UserGroup {
		private String name;
	}
}
