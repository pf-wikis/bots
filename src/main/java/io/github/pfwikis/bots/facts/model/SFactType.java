package io.github.pfwikis.bots.facts.model;

import java.lang.reflect.ParameterizedType;

import com.fasterxml.jackson.databind.JavaType;

import io.github.pfwikis.bots.utils.Jackson;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class SFactType<JType> {
	
	@Getter(lazy = true)
	private final JavaType javaType = findType();
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
/*
	
	
	
	public String subFormAtEnd(SProperty<JType> prop) {
		return "";
	}
	
	public String formAfterSet(SProperty<JType> prop) {
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