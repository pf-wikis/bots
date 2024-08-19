package io.github.pfwikis.bots.common.model;

import static io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping.FACT_TYPE;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.StdConverter;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.facts.model.SDIConcept;
import io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
public class SemanticSubject implements SemanticObject {
	
	private PageRef subject;
	private List<Property> data;
	@JsonProperty("sobj")
	private List<SemanticSubject> subObjects;

	
	public boolean hasConcept(SDIConcept concept) {
		if(!has(SDIPropertyTypeMapping.FACT_TYPE)) return false;
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
					yield switch(parts.length) {
						case 2 -> Year.of(parts[1]);
						case 3 -> YearMonth.of(parts[1], parts[2]);
						case 4 -> LocalDate.of(parts[1], parts[2], parts[3]);
						case 8 -> LocalDateTime.of(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
						default -> throw new IllegalStateException("Unhandled date format for "+item);
					};
				}
				default -> throw new IllegalStateException("Unhandled subject property type "+type);
			};
		}
	}
	
	@Getter
	@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
	public static class PageRef {
		private final String interwiki;
		private final int ns;
		private final String title;
		
		public static PageRef of(JsonNode item) {
			var txt = item.textValue();
			return of(txt);
		}
		
		@JsonCreator
		public static PageRef of(String txt) {
			var parts = StringUtils.splitPreserveAllTokens(txt, '#');
			return new PageRef(
				parts[2],
				Integer.parseInt(parts[1]),
				parts[0].replace('_', ' '));
		}
		
		public static PageRef of(int ns, String title) {
			return new PageRef("", ns, title.replace('_', ' '));
		}
		
		public String toFullTitle() {
			return
				(interwiki.isBlank()?"":(interwiki+":"))
				+ switch(ns) {
					case 0 -> "";
					case 1 -> "Talk:";
					case 4 -> "Project:";
					case 6 -> "File:";
					case 10 -> "Template:";
					case 14 -> "Category:";
					case 120 -> "Featured:";
					case 128 -> "Facts:";
					default -> throw new IllegalStateException("Unhandled namespace "+ns);
				}
				+ title;
		}
		
		@Override
		public String toString() {
			return toFullTitle();
		}

		public String toWikiLink(WikiAPI wiki) {
			return wiki.toWikiLink(this.toFullTitle());
		}
	}
}
