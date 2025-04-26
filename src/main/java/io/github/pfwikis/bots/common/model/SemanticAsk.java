package io.github.pfwikis.bots.common.model;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.util.StdConverter;
import com.google.common.collect.Lists;

import io.github.pfwikis.bots.facts.model.SMWPropertyType;
import io.github.pfwikis.bots.facts.model.SFactType;
import io.github.pfwikis.bots.utils.Jackson;
import io.github.pfwikis.bots.utils.MWJsonHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SemanticAsk<T> {

	@JsonProperty("query-continue-offset")
	private Integer queryContinueOffset;
	private Query<T> query;
	
	@Data
	public static class Query<T> {
		private List<Map<String, Result<T>>> results;
	}
	
	@Data
	public static class Result<T> {
		@JsonProperty("fulltext")
		private String page;
        private String fullurl;
        private int namespace;
        @JsonDeserialize(using = PrintoutsDeserializer.class)
        private T printouts;
        private String exists;

		public String toPage() {
			return page.replaceFirst(" *#.*", "");
		}
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
		private List<Result<Printouts>> artists = Collections.emptyList();
		@JsonProperty("Author")
		private List<Result<Printouts>> authors = Collections.emptyList();
		@JsonProperty("Author all")
		private List<Result<Printouts>> allAuthors = Collections.emptyList();
		@JsonProperty("Primary author")
		private List<Result<Printouts>> primaryAuthors = Collections.emptyList();
		@JsonProperty("Author ordered")
		private List<Ordered> authorsOrdered = Collections.emptyList();
		@JsonProperty("Primary author ordered")
		private List<Ordered> primaryAuthorsOrdered = Collections.emptyList();
		@JsonProperty("Full title")
		private String fullTitle;
		@JsonProperty("Isbn")
		private String isbn;
		@JsonProperty("Publisher")
		private List<Result<Printouts>> publisher = Collections.emptyList();
		@JsonProperty("Is subsection")
		@JsonDeserialize(converter = MWJsonHelper.BooleanConverter.class)
		private Boolean isSubsection;
		@JsonProperty("Disable autocomplete")
		@JsonDeserialize(converter = MWJsonHelper.BooleanConverter.class)
		private Boolean disableAutocomplete;
		@JsonProperty("Created from")
		private Result<Printouts> createdFrom;
		private List<JsonNode> value = Collections.emptyList();
		
		@JsonProperty("Web author")
		private String webAuthor;
		@JsonProperty("Web date")
		private String webDate;
		@JsonProperty("Web url")
		private String webUrl;
		@JsonProperty("Page")
		private Result<Printouts> page;
	}
	
	@Data
	public static class Ordered implements Comparable<Ordered> {
		@JsonProperty("Author")
		private Labeled<Result<Printouts>> author;
		@JsonProperty("Primary author")
		private Labeled<Result<Printouts>> primaryAuthor;
		@JsonProperty("Order")
		private Labeled<Integer> order;
		
		@Override
		public int compareTo(Ordered o) {
			return Objects.compare(order.getValue(), o.order.getValue(), Integer::compare);
		}
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class WikiDate {
		private JsonNode timestamp;
		@JsonDeserialize(converter = WikiRawDateToTemporalConverter.class)
		private Temporal raw;

		public static Temporal parseRaw(String v) {
			var parts = Arrays.stream(v.split("/")).mapToInt(Integer::parseInt).toArray();
			return switch(parts.length) {
				case 2 -> Year.of(parts[1]);
				case 3 -> YearMonth.of(parts[1], parts[2]);
				case 4 -> LocalDate.of(parts[1], parts[2], parts[3]);
				case 8 -> LocalDateTime.of(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
				default -> throw new IllegalStateException("Unhandled date format for "+v);
			};
		}
	}
	
	public static class WikiRawDateToTemporalConverter extends StdConverter<String, Temporal> {
		@Override
		public Temporal convert(String value) {
			return WikiDate.parseRaw(value);
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
	
	public static class PrintoutsDeserializer<T> extends StdDeserializer<T> implements ContextualDeserializer {

		protected PrintoutsDeserializer(JavaType wrapperType) {
			super(wrapperType);
		}
		
		protected PrintoutsDeserializer() {
			super(Object.class);
		}
		
		@Override
	    public PrintoutsDeserializer<T> createContextual(DeserializationContext ctxt, BeanProperty property) {
	        return new PrintoutsDeserializer<T>(property.getType());
	    }
		
		@Override
		public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
			JsonNode node = p.readValueAsTree();
			if(node.isArray() && node.size() == 0) {
				node = ctxt.getNodeFactory().objectNode();
			}
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
			return (T)ctxt.findRootValueDeserializer(this.getValueType()).deserialize(subParser, ctxt);
		}
		
	}
}
