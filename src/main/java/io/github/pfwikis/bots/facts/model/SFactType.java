package io.github.pfwikis.bots.facts.model;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.fasterxml.jackson.databind.JavaType;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.utils.Jackson;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor
public abstract class SFactType<JType> {
	@Getter
	private final String id;
	@Getter(lazy = true)
	private final JavaType javaType = findType();
	@Getter
	private final SMWPropertyType propertyType;
	private final String templateDataType;
	private final String displayFactWikitext;
	
	private JavaType findType() {
		Class<?> t = this.getClass();
		while(!t.getSuperclass().equals(SFactType.class)) {
			t=t.getSuperclass();
		}
		ParameterizedType sup = (ParameterizedType)t.getGenericSuperclass();
		return Jackson.JSON.constructType(sup.getActualTypeArguments()[0]);
	}

	public String wikitextToDisplayFact(SProperty<JType> p, String v) {
		return displayFactWikitext.replace("$v", v);
	}
	
	public String wikitextToStoreFact(SProperty<JType> p, String v) {
		return v;
	}
	
	public String toInfoboxDisplay(WikiAPI wiki, JType v) {
		return v.toString();
	}

	public String wikitextToQuery(SProperty<JType> prop) {
		return "";
	}

	public String wikitextToTestIfValue(SProperty<JType> p, String v) {
		return v;
	}

	public String wikitextBeforeStoringFact(SProperty<JType> p) {
		return "";
	}

	@SuppressWarnings("unchecked")
	public JType convertToJava(List<Object> values) {
		return switch(values.size()) {
			case 0 -> null;
			case 1 -> (JType)values.getFirst();
			default -> throw new IllegalStateException("There are "+values.size()+" values");
		};
	}

	protected abstract String configureFormField(SProperty<?> prop);

	public String toTemplateDataType() {
		return templateDataType;
	}
}