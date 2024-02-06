package io.github.pfwikis.bots.common.model;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.google.common.collect.Lists;

import io.github.pfwikis.bots.factshelper.FactType;
import io.github.pfwikis.bots.factshelper.PropertyType;
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
		private String fulltext;
        private String fullurl;
        private int namespace;
        @JsonDeserialize(using = PrintoutsDeserializer.class)
        private Printouts printouts;
	}
	
	@Data
	public static class Printouts {
		@JsonProperty("Website")
		private String website;
		@JsonProperty("Has type")
		private PropertyType hasType;
		@JsonProperty("Has fact type")
		private FactType hasFactType;
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
		@JsonProperty("Author")
		private List<Result> authors = Collections.emptyList();
		@JsonProperty("Primary author")
		private List<Result> primaryAuthors = Collections.emptyList();
		@JsonProperty("Full title")
		private String fullTitle;
		@JsonProperty("Isbn")
		private String isbn;
		@JsonProperty("Publisher")
		private List<Result> publisher = Collections.emptyList();
		@JsonProperty("Is subsection")
		@JsonDeserialize(converter = MWJsonHelper.BooleanConverter.class)
		private Boolean isSubsection;
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
