package io.github.pfwikis.bots.paizoretriever;

import java.time.Instant;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import io.github.pfwikis.bots.paizoretriever.GraphQLResponse.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class State {
	private TreeMap<String, Entry> values = new TreeMap<>();

	public void addEntries(Product p) {
		
		for(var variantEdge:p.getVariants().getEdges()) {
			var v = variantEdge.getNode();
			var n = new Props();
			n.sku = v.getSku()==null?p.getSku():v.getSku();
			if(v.getPrices() != null) {
				if(v.getPrices().getRetailPrice()!=null)
					n.price = v.getPrices().getRetailPrice().getFormattedV2();
				else
					n.price = v.getPrices().getPrice().getFormattedV2();
			}
			n.name = p.getName();
			n.url = p.getPath();
			n.upc = v.getUpc()==null?p.getUpc():v.getUpc();
			
			var old = values.get(n.sku);
			var lastChanged = Instant.now();
			var created = lastChanged;
			if(old != null) {
				if(old.getProperties().equals(n))
					continue;
				created = old.created;
				n.mergeOld(old.properties);
			}
			
			var e = new Entry(lastChanged, created, n);
			values.put(n.sku, e);
		}
	}

	@Data
	@AllArgsConstructor
	public static class Entry {
		private Instant lastChanged;
		private Instant created;
		private Props properties;
	}
	
	@Data
	public static class Props {
		private String sku;
		private String name;
		private String price;
		private String upc;
		private String url;
		
		public void mergeOld(Props old) {
			if(StringUtils.isAllBlank(name)) name = old.name;
			if(StringUtils.isAllBlank(price)) price = old.price;
			if(StringUtils.isAllBlank(upc)) upc = old.upc;
			if(StringUtils.isAllBlank(url)) url = old.url;
		}
	}
}
