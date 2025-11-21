package io.github.pfwikis.bots.paizoretriever;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import io.github.pfwikis.bots.paizoretriever.LdJson.Product;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class State {
	private List<String> openList = new ArrayList<>();
	private List<Page> pages = new ArrayList<>();

	public boolean addLink(String url) {
		if(openList.contains(url))
			return false;
		if(pages.stream().anyMatch(p->p.url.equals(url)))
			return false;
		openList.add(url);
		return true;
	}

	@Getter @Setter
	public static class Page {
		private String url;
		private Instant lastChecked;
		private Instant lastChanged;
		private Instant created;
		private int backOffCounter = 0;
		private LdJson.Product structuredContent;
	}

	public Page findPage(String url, Instant now) {
		return pages.stream()
			.filter(p->p.getUrl().equals(url))
			.findAny()
			.orElseGet(()-> {
				var p = new Page();
				p.setLastChanged(now);
				p.setLastChecked(now);
				p.setCreated(now);
				p.setUrl(url);
				pages.add(p);
				return p;
			});
	}
}
