package io.github.pfwikis.bots.facts.model;

import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;

import io.github.pfwikis.bots.common.model.SemanticSubject.PageRef;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SPropertyTypeMapping<TYPE> {

	public static SPropertyTypeMapping<String> HAS_FACT_TYPE = prop(String.class, "Has fact type");
	public static SPropertyTypeMapping<String> HAS_INFOBOX_TYPE = prop(String.class, "Has infobox type");
	public static SPropertyTypeMapping<String> HAS_LOCATION_TYPE = prop(String.class, "Has location type");
	public static SPropertyTypeMapping<String> HAS_META_TYPE = prop(String.class, "Has meta type");		
			
	private final Class<TYPE> type;
	private final String property;
	
	public static <T> SPropertyTypeMapping<T> prop(Class<T> type, String property) {
		return new SPropertyTypeMapping<>(type, property);
	}
}
