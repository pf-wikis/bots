package io.github.pfwikis.bots.common.api.generator.model;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.api.generator.api.GenAPIInterwiki;
import io.github.pfwikis.bots.common.api.generator.api.GenAPINamespace;
import io.github.pfwikis.bots.common.api.generator.api.GenAPIResult;
import lombok.Getter;

@Getter
public class APIInfo {
	
	private List<APINamespace> namespaces;
	private List<APIInterwiki> interwikis;

	public static APIInfo create(GenAPIResult... infos) {
		var res = new APIInfo();
		
		res.namespaces = Arrays.stream(infos)
			.flatMap(i->i.getQuery().getNamespaces().values().stream())
			.collect(Collectors.groupingBy(
				ns->ns.getId(),
				Collectors.toList()
			))
			.entrySet()
			.stream()
			.map(e->new APINamespace(e.getKey(), e.getValue()))
			.toList();
		
		res.interwikis = Arrays.stream(infos)
			.flatMap(i->i.getQuery().getInterwikimap().stream())
			.map(APIInterwiki::new)
			.toList();
		
		return res;
	}
	
	@Getter
	public static class APIInterwiki {
		
		private String javaName;
		private String url;
		private String prefix;
		private Wiki wiki;
		
		public APIInterwiki(GenAPIInterwiki iw) {
			javaName = iw.getPrefix().toUpperCase();
			url = iw.getUrl();
			prefix = iw.getPrefix();
			this.wiki = switch(iw.getPrefix()) {
				case "pfw" -> Wiki.PF;
				case "sfw" -> Wiki.SF;
				default -> throw new IllegalStateException();
			};
		}
	}
	
	@Getter
	public static class APINamespace {
		
		private String javaName;
		private int id;
		private String prefix;
		private String label;
		private EnumMap<Wiki, String> alt;
		

		public APINamespace(int id, List<GenAPINamespace> sources) {
			this.id = id;
			if(id == 0) {
				javaName = "MAIN";
				prefix = "";
				label = "Main";
			}
			else {
				javaName = sources.getFirst().getCanonical().toUpperCase().replaceAll("[^A-Z]+", "_");
				prefix = sources.getFirst().getCanonical()+":";
				label = sources.getFirst().getCanonical();
				
				if(!sources.getFirst().getName().equals(sources.getFirst().getCanonical())) {
					alt = new EnumMap<Wiki, String>(Wiki.class);
					alt.put(Wiki.PF, sources.getFirst().getName());
					alt.put(Wiki.SF, sources.getLast().getName());
				}
			}
		}
		
	}
}
