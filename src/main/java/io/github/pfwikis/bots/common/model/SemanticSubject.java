package io.github.pfwikis.bots.common.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.StdConverter;
import com.fizzed.rocker.RockerContent;
import com.google.common.collect.MoreCollectors;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.facts.model.SDIConcept;
import io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.FACT_TYPE;

@Data
public class SemanticSubject implements SemanticObject {
	
	private PageRef subject;
	private List<Property> data;
	@JsonProperty("sobj")
	private List<SemanticSubject> subObjects;

	
	public boolean hasConcept(SDIConcept concept) {
		return ("Facts/"+concept.getName()).equals(get(SDIPropertyTypeMapping.FACT_TYPE).getTitle());
	}
	
	public List<SemanticSubject> getSubObjects(String factType) {
		var res = subObjects.stream()
			.filter(e->e.has(FACT_TYPE))
			.filter(e->
				e.get(FACT_TYPE).getTitle()
					.equals(factType))
			.toList();
		return res;
	}
	
	@Data
	public static class Container {
		private SemanticSubject query;
	}
	
	@Data
	public static class Property {
		private String property;
		@JsonDeserialize(contentConverter = DataItemConverter.class)
		private List<Object> dataitem;
	}
	
	public static class DataItemConverter extends StdConverter<ObjectNode, Object> {

		@Override
		public Object convert(ObjectNode value) {
			int type = value.get("type").intValue();
			var item = value.get("item");
			return switch(type) {
				case 4 -> "t".equals(item.textValue());
				case 2,5 -> item.textValue();
				case 1 -> item.asInt();
				case 9 -> PageRef.of(item);
				case 6 -> {
					var parts = Arrays.stream(item.textValue().split("/")).mapToInt(Integer::parseInt).toArray();
					if(parts.length==4)
						yield LocalDate.of(parts[1], parts[2], parts[3]);
					if(parts.length==8)
						yield LocalDateTime.of(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
					throw new IllegalStateException("Unhandled date format for "+item);
				}
				default -> throw new IllegalStateException("Unhandled subject property type "+type);
			};
		}
	}
	
	@Getter
	@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
	public static class PageRef {
		private final int ns;
		private final String title;
		
		public static PageRef of(JsonNode item) {
			var txt = item.textValue();
			return of(txt);
		}
		
		@JsonCreator
		public static PageRef of(String txt) {
			int split = txt.lastIndexOf("##");
			txt = txt.substring(0,split);
			split = txt.lastIndexOf('#');
			return new PageRef(
				Integer.parseInt(txt.substring(split+1)),
				txt.substring(0, split).replace('_', ' '));
		}

		public String toFullTitle() {
			return switch(ns) {
				case 0 -> "";
				case 6 -> "File:";
				case 14 -> "Category:";
				case 128 -> "Facts:";
				default -> throw new IllegalStateException("Unhandled namespace "+ns);
			}+title;
		}

		public String toWikiLink(WikiAPI wiki) {
			return wiki.toWikiLink(this.toFullTitle());
		}
	}
}
