package io.github.pfwikis.bots.paizoretriever;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.google.common.math.LongMath;

import io.github.pfwikis.bots.paizoretriever.GraphQLResponse.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter @Setter
public class State {
	private TreeMap<String, Entry> values = new TreeMap<>();

	
	public void addEntries(Product p, RatingsModel.Bottomline ratings, Instant now) {
		var variants = new ArrayList<Props>();
		for(var variantEdge:p.getVariants().getEdges()) {
			var v = variantEdge.getNode();
			variants.add(Props.builder()
				.sku(v.getSku()==null?p.getSku():v.getSku())
				.name(p.getName())
				.url(p.getPath())
				.price(Optional.ofNullable(v.getPrices())
					.map(pr->Optional.ofNullable(pr.getRetailPrice()).orElse(pr.getPrice()))
					.map(pr->pr.getFormattedV2())
					.orElse(null)
				)
				.upc(v.getUpc()==null?p.getUpc():v.getUpc())
				.storeImage(Optional.ofNullable(v.getDefaultImage()).orElse(p.getDefaultImage()).getUrlOriginal())
				.build()
			);
		}
		variants.add(Props.builder()
			.sku(p.getSku())
			.name(p.getName())
			.url(p.getPath())
			.price(null)
			.upc(p.getUpc())
			.storeImage(p.getDefaultImage().getUrlOriginal())
			.build());
		variants.forEach(v->addEntry(v, ratings, now));
	}
	public void addEntry(Props v, RatingsModel.Bottomline ratings, Instant now) {
		var n = new Props();
		n.sku = v.getSku();
		n.price = v.getPrice();
		n.name = v.getName();
		n.url = v.getUrl();
		n.upc = v.getUpc();
		n.storeImage = v.getStoreImage();
		if(ratings != null && ratings.getTotalReviews() > 0) {
			n.ratings = new Ratings(
				ratings.getTotalReviews(),
				ratings.getAverageScore()
			);
		}
		
		var old = values.get(n.sku);
		var lastChanged = now;
		var created = lastChanged;
		if(old != null) {
			if(old.getProperties().equals(n))
				return;
			created = old.created;
			if(!n.mergeOld(old.properties)) {
				lastChanged = old.lastChanged;
			}
			else {
				log.info("{} changed", n.getSku());
			}
		}
		
		var e = new Entry(lastChanged, created, n);
		values.put(n.sku, e);
	}
	
	@Data
	@AllArgsConstructor
	public static class Entry {
		private Instant lastChanged;
		private Instant created;
		private Props properties;
	}
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Props {
		private String sku;
		private String name;
		private String price;
		private String upc;
		private String url;
		private String storeImage;
		private Ratings ratings;
		
		public boolean mergeOld(Props old) {
			if(StringUtils.isAllBlank(name)) name = old.name;
			if(StringUtils.isAllBlank(price)) price = old.price;
			if(StringUtils.isAllBlank(upc)) upc = old.upc;
			if(StringUtils.isAllBlank(url)) url = old.url;
			if(StringUtils.isAllBlank(storeImage)) storeImage = old.storeImage;
			if(ratings == null && old.ratings!=null && old.ratings.getTotalReviews() > 0) ratings = old.ratings;
			
			return !Objects.equals(name, old.name)
				|| !Objects.equals(price, old.price)
				|| !Objects.equals(upc, old.upc)
				|| !Objects.equals(url, old.url)
				|| !Objects.equals(ratings, old.ratings);
		}
	}
	
	@Data
	@AllArgsConstructor
	public static class Ratings {
		private int totalReviews;
		private BigDecimal averageScore;
		
		public boolean equals(Object o) {
			if(o instanceof Ratings or) {
				return totalReviews == or.totalReviews
					&& averageScore.doubleValue() == or.averageScore.doubleValue();
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return LongMath.mod(
				Double.doubleToLongBits(averageScore.doubleValue()*totalReviews),
				Integer.MAX_VALUE
			);
		}
	}
}
