package io.github.pfwikis.bots.paizoretriever;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class GraphQLResponse {

	private GQLData data;
	
	@Data
	public static class GQLData {
		private Site site;
	}
	
	@Data
	public static class Site {
		private Category category;
	}
	
	@Data
	public static class Category {
		private Edges<Product> products;
	}
	
	@Data
	public static class Edges<T> {
		private PageInfo pageInfo;
		private List<Edge<T>> edges;
	}
	
	@Data
	public static class PageInfo {
		private String endCursor;
		private boolean hasNextPage;
	}
	
	@Data
	public static class Edge<T> {
		private T node;
	}
	
	@Data
	public static class Product {
		private long entityId;
		private String name;
		private String sku;
		private String description;
		private String upc;
		private String path;
		private Image defaultImage;
		private Edges<Variant> variants;
	}
	
	@Data
	public static class Variant {
		private long entiotyId;
		private String sku;
		private String upc;
		private Prices prices;
		private Image defaultImage;
	}
	
	@Data
	public static class Image {
		private String urlOriginal;
	}
	
	@Data
	public static class Prices {
		private Price price;
		private Price retailPrice;
	}
	
	@Data
	public static class Price {
		private BigDecimal value;
		private String formattedV2;
	}
}
