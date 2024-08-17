package io.github.pfwikis.bots.facts.model;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.model.SemanticSubject.PageRef;
import io.github.pfwikis.bots.utils.Jackson;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class SFactType<JType> {
	
	@Getter(lazy = true)
	private final JavaType javaType = findType();
	private final SMWPropertyType propertyType;
	private final String displayFactCode;
	private final String storeFactCode;
	private final String storeSubFactCode;
	
	private JavaType findType() {
		Class<?> t = this.getClass();
		while(!t.getSuperclass().equals(SFactType.class)) {
			t=t.getSuperclass();
		}
		ParameterizedType sup = (ParameterizedType)t.getGenericSuperclass();
		return Jackson.JSON.constructType(sup.getActualTypeArguments()[0]);
	}
/*
	public String displayFactCode(String propName) {
		return displayFactCode.replace("$1", propName);
	}
	
	public String storeFactCode(String propName) {
		return storeFactCode.replace("$1", propName);
	}
	
	public String subFormAtEnd(SProperty<JType> prop) {
		return "";
	}
	
	public String formAfterSet(SProperty<JType> prop) {
		return "";
	}
	
	public String formBeforeSubformTransclude(SProperty<JType> prop) {
		return "";
	}
	
	public String storeSubFactCode(String propName) {
		return storeSubFactCode.replace("$1", propName);
	}
	
	public String infoboxLabel(SProperty<JType> prop, List<Object> values) {
		var label = prop.getInfoboxLabel()==null?prop.getName():prop.getInfoboxLabel();
		if(values.size()>1 && label.charAt(label.length()-1)!='s')
			label += "s";
		return label;
	}
	
	public String infoboxValue(WikiAPI wiki, SProperty<JType> prop, List<Object> values) {
		if(values.size() == 0)
			return "";
		if(values.size() == 1)
			return infoboxValue(wiki, prop, values.getFirst());
		if(values.size() == 2)
			return infoboxValue(wiki, prop, values.getFirst())
				+ " and "
				+ infoboxValue(wiki, prop, values.get(1));
		var sb = new StringBuilder();
		for(int i=0;i<values.size()-1;i++) {
			if(i>0) sb.append(", ");
			sb.append(infoboxValue(wiki, prop, values.get(i)));
		}
		sb.append(", and ");
		sb.append(infoboxValue(wiki, prop, values.getLast()));
		return sb.toString();
	}

	public String infoboxValue(WikiAPI wiki, SProperty<JType> prop, JType object) {
		if(object instanceof PageRef page) {
			return page.toWikiLink(wiki);
		}
		return object.toString();
	}*/
}