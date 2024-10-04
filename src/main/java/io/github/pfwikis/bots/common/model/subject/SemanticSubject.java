package io.github.pfwikis.bots.common.model.subject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.StdConverter;

import io.github.pfwikis.bots.common.model.SemanticObject;
import static io.github.pfwikis.bots.facts.SFactsProperties.Fact_type;
import io.github.pfwikis.bots.facts.model.SConcept;
import io.github.pfwikis.bots.facts.model.SMWPropertyType;
import lombok.Data;
import lombok.Setter;

@Data
public class SemanticSubject implements SemanticObject, Comparable<SemanticSubject> {
	
	private PageRef subject;
	private List<Property> data;
	private List<SemanticSubject> subObjects = Collections.emptyList();
	private String sortKey;
	
	
	public boolean hasConcept(SConcept concept) {
		if(!has(Fact_type)) return false;
		return ("Facts/"+concept.getName()).equals(get(Fact_type).getTitle());
	}
	
	public List<SemanticSubject> getSubObjects(String factType) {
		var res = subObjects.stream()
			.filter(e->e.has(Fact_type))
			.filter(e->
				e.get(Fact_type).getTitle()
					.equals(factType))
			.sorted()
			.toList();
		return res;
	}
	
	public static class Root extends SemanticSubject {
		@JsonProperty("sobj")
		private List<SemanticSubject> replacingObject = new ArrayList<>();
	}
	
	@Setter
	public static class Container {
		private Root query;

		public SemanticSubject postProcess() {
			var replacements = query.replacingObject.stream()
				.collect(Collectors.toMap(so->so.getSubject(), so->so));
			
			replacements.values().forEach(s->replace(s, replacements));
			replace(query, replacements);
			
			var res = new SemanticSubject();
			res.data=query.getData();
			res.subject=query.getSubject();
			
			extractKeys(res);
			return res;
		}

		private void extractKeys(SemanticSubject s) {
			var it = s.data.iterator();
			while(it.hasNext()) {
				var p = it.next();
				
				for(var v:p.dataitem) {
					if(v instanceof SemanticSubject child)
						extractKeys(child);
				}
				
				if(p.property.equals("_SOBJ")) {
					s.subObjects = p.dataitem.stream()
						.map(SemanticSubject.class::cast)
						.toList();
					it.remove();
				}
				else if(p.property.equals("_SKEY")) {
					s.sortKey = p.dataitem.stream()
						.map(String.class::cast)
						.collect(Collectors.joining());
					it.remove();
				}
			}
		}

		private void replace(SemanticSubject s, Map<PageRef, SemanticSubject> replacements) {
			s.getData().forEach(p->{
				for(int i=0;i<p.dataitem.size();i++) {
					if(p.dataitem.get(i) instanceof SemanticSubject child) {
						replace(child, replacements);
					}
					else if(replacements.containsKey(p.dataitem.get(i))) {
						p.dataitem.set(i, replacements.get(p.dataitem.get(i)));
					}
				}
			});
		}
	}
	
	@Data
	public static class Property {
		private String property;
		@JsonDeserialize(contentConverter = DataItemConverter.class)
		private List<Object> dataitem;
	}
	
	@Data
	private static class Value {
		private SMWPropertyType type;
		private JsonNode item;
	}
	
	public static class DataItemConverter extends StdConverter<Value, Object> {

		@Override
		public Object convert(Value value) {
			if(value.item.isNull())
				return null;
			return value.type.convertToJava(value.item);
		}
	}

	private static final Comparator<SemanticSubject> COMPARATOR = Comparator
		.nullsLast(Comparator.comparing(SemanticSubject::getSortKey))
		.thenComparing(s->s.getSubject().toFullTitle());
	@Override
	public int compareTo(SemanticSubject o) {
		return COMPARATOR.compare(this, o);
	}

	@Override
	public String internalName() {
		return subject.toFullTitle();
	}
	
	
}
