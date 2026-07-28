package io.github.pfwikis.bots.paizoretriever;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import tools.jackson.databind.JsonNode;

@Data
public class RatingsModel {
	private Status status;
	private Response response;
	
	@Data
	public static class Status {
		private int code;
		private String message;
	}
	
	@Data
	public static class Response {
		private JsonNode pagination;
		private List<Bottomline> bottomlines;
	}
	
	@Data
	public static class Bottomline {
		private String productId;
		private int totalReviews;
		private BigDecimal averageScore;
		private int totalOrganicReviews;
		private BigDecimal organicAverageScore;
	}
}
