package io.github.pfwikis.bots.facts.model;

import java.lang.reflect.ParameterizedType;

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
	private final String id;
	@Getter(lazy = true)
	private final JavaType javaType = findType();
	@Getter
	private final SMWPropertyType propertyType;
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
		var res = displayFactWikitext.replace("$v", v);
		if(p.getDefaultValue() == null ) {
			return res;
		}
		return 
			"{{#if:{{{"+p.getName()+"|}}}|"
			+ res
			+ "|''default:'' "
			+ displayFactWikitext.replace("$v", p.getDefaultValue())
			+ "}}";
	}
	
	public String wikitextToStoreFact(SProperty<JType> p, String v) {
		var result = changeStoreFactWikitext(v);
		if(p.getDefaultValue() == null)
			return result;
		return "{{#if:"+v+"|"+result+"|"+changeStoreFactWikitext(p.getDefaultValue())+"}}";
	}
	
	protected String changeStoreFactWikitext(String wt) {
		return wt;
	}

	public String wikitextToInfoboxDisplay(WikiAPI wiki, Object object) {
		return object.toString();
	}

	public String wikitextToQuery(SProperty<JType> prop) {
		return "";
	}
}