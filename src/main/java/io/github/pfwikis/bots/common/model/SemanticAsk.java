package io.github.pfwikis.bots.common.model;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.google.common.collect.Lists;

import io.github.pfwikis.bots.facts.model.SMWPropertyType;
import io.github.pfwikis.bots.facts.model.SFactType;
import io.github.pfwikis.bots.utils.Jackson;
import io.github.pfwikis.bots.utils.MWJsonHelper;
import lombok.Data;

@Data
public class SemanticAsk {

	@JsonProperty("query-continue-offset")
	private Integer queryContinueOffset;
	private Query query;
	
	@Data
	public static class Query {
		private List<Map<String, Result>> results;
	}
	
	@Data
	public static class Result {
		@JsonProperty("fulltext")
		private String page;
        private String fullurl;
        private int namespace;
        @JsonDeserialize(using = PrintoutsDeserializer.class)
        private Printouts printouts;
        private String exists;
	}
	
	@Data
	public static class Printouts {
		@JsonProperty("Website")
		private String website;
		@JsonProperty("Has type")
		private SMWPropertyType hasType;
		@JsonProperty("Has fact type")
		private SFactType hasFactType;
		@JsonProperty("Has fact display format")
		private String hasFactDisplayFormat;
		@JsonProperty("Has fact note")
		private String hasFactNote;
		@JsonProperty("Suggest values from")
		private String suggestValuesFrom;
		@JsonProperty("Has infobox label")
		private String hasInfoboxLabel;
		@JsonProperty("Name")
		private String name;
		@JsonProperty("Represented by page")
		private Result representedByPage;
		@JsonProperty("Release year")
		private String releaseYear;
		@JsonProperty("On page")
		private String onPage;
		@JsonProperty("To page")
		private String toPage;
		@JsonProperty("Artist")
		private List<Result> artists = Collections.emptyList();
		@JsonProperty("Author")
		private List<Result> authors = Collections.emptyList();
		@JsonProperty("Author all")
		private List<Result> allAuthors = Collections.emptyList();
		@JsonProperty("Primary author")
		private List<Result> primaryAuthors = Collections.emptyList();
		@JsonProperty("Author ordered")
		private List<Ordered> authorsOrdered = Collections.emptyList();
		@JsonProperty("Primary author ordered")
		private List<Ordered> primaryAuthorsOrdered = Collections.emptyList();
		@JsonProperty("Full title")
		private String fullTitle;
		@JsonProperty("Isbn")
		private String isbn;
		@JsonProperty("Publisher")
		private List<Result> publisher = Collections.emptyList();
		@JsonProperty("Is subsection")
		@JsonDeserialize(converter = MWJsonHelper.BooleanConverter.class)
		private Boolean isSubsection;
		@JsonProperty("Disable autocomplete")
		@JsonDeserialize(converter = MWJsonHelper.BooleanConverter.class)
		private Boolean disableAutocomplete;
		@JsonProperty("Created from")
		private Result createdFrom;
		private List<JsonNode> value = Collections.emptyList();
		
		@JsonProperty("Web author")
		private String webAuthor;
		@JsonProperty("Web date")
		private String webDate;
		@JsonProperty("Web url")
		private String webUrl;
		@JsonProperty("Page")
		private Result page;
	}
	
	@Data
	public static class Ordered implements Comparable<Ordered> {
		@JsonProperty("Author")
		private Labeled<Result> author;
		@JsonProperty("Primary author")
		private Labeled<Result> primaryAuthor;
		@JsonProperty("Order")
		private Labeled<Integer> order;
		
		@Override
		public int compareTo(Ordered o) {
			return Objects.compare(order.getValue(), o.order.getValue(), Integer::compare);
		}
	}
	
	@Data
	public static class Labeled<T> {
		private List<T> item;
		
		public T getValue() {
			if(item == null || item.isEmpty())
				return null;
			return item.get(0);
		}
	}
	
	public static class PrintoutsDeserializer extends StdDeserializer<Printouts> {

		protected PrintoutsDeserializer() {
			super(Jackson.JSON.getTypeFactory().constructSimpleType(Printouts.class, new JavaType[0]));
		}

		@Override
		public Printouts deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
			JsonNode node = p.readValueAsTree();
			if(node.isArray() && node.size() == 0)
				return new Printouts();
			var n = (ObjectNode) node;
			var fields = Lists.newArrayList(n.fieldNames());
			fields.forEach(f->{
				var v = n.get(f);
				if(v.isArray()) {
					var arr = (ArrayNode)v;
					if(arr.isEmpty())
						n.remove(f);
					else if(arr.size()==1)
						n.replace(f, arr.get(0));
				}
			});
			var subParser = new TreeTraversingParser(node);
			subParser.nextToken();
			return (Printouts)ctxt.findRootValueDeserializer(this.getValueType()).deserialize(subParser, ctxt);
		}
		
	}
}
