package io.github.pfwikis.bots.common.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import io.github.pfwikis.bots.factshelper.FactType;
import io.github.pfwikis.bots.factshelper.PropertyType;
import io.github.pfwikis.bots.utils.Jackson;
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
		private String[] website;
		@JsonProperty("Has type")
		private PropertyType[] hasType;
		@JsonProperty("Has fact type")
		private FactType[] hasFactType;
		@JsonProperty("Has fact display format")
		private String[] hasFactDisplayFormat;
		@JsonProperty("Has fact note")
		private String[] hasFactNote;
		@JsonProperty("Suggest values from")
		private String[] suggestValuesFrom;
		@JsonProperty("Copy to parent")
		private boolean[] copyToParent;
		@JsonProperty("Has infobox label")
		private String[] hasInfoboxLabel;
	}
	
	public static class PrintoutsDeserializer extends StdDeserializer<Printouts> {

		protected PrintoutsDeserializer() {
			super(Jackson.JSON.getTypeFactory().constructSimpleType(Printouts.class, new JavaType[0]));
		}

		@Override
		public Printouts deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
			if(p.currentToken() == JsonToken.START_ARRAY) {
				p.nextToken();
				return new Printouts();
			}
			return (Printouts)ctxt.findRootValueDeserializer(this.getValueType()).deserialize(p, ctxt);
		}
		
	}
}
