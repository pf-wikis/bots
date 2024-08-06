package io.github.pfwikis.bots.common.model;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.MoreCollectors;

import io.github.pfwikis.bots.common.model.SemanticSubject.Property;
import io.github.pfwikis.bots.facts.model.SDIProperty;
import io.github.pfwikis.bots.facts.model.SDIPropertyTypeMapping;

public interface SemanticObject {
	
	List<Property> getData();
	
	default boolean has(String prop) {
		return getData().stream().anyMatch(p->p.getProperty().equals(prop));
	}
	
	default boolean has(SDIPropertyTypeMapping<?> prop) {
		return has(prop.getProperty());
	}
	
	default boolean has(SDIProperty prop) {
		return has(prop.getName().replace(" ", "_"));
	}
	
	@SuppressWarnings("unchecked")
	default <T> List<T> getAll(String prop) {
		return (List<T>) getData().stream()
			.filter(p->p.getProperty().equals(prop))
			.collect(MoreCollectors.toOptional())
			.map(op->op.getDataitem())
			.orElse(Collections.emptyList());
	}
	
	default <T> List<T> getAll(SDIPropertyTypeMapping<T> prop) {
		return getAll(prop.getProperty());
	}
	
	default <T> List<T> getAll(SDIProperty prop) {
		return getAll(prop.getName().replace(" ", "_"));
	}
	
	default <T> T get(String prop) {
		var values = getAll(prop);
		if(values.size()==1) {
			return (T) values.getFirst();
		}
		else {
			throw new IllegalStateException("Property "+prop+" has "+values.size()+" values");
		}
	}
	
	default <T> T get(SDIPropertyTypeMapping<T> prop) {
		return get(prop.getProperty());
	}
	
	default <T> T getOr(SDIPropertyTypeMapping<T> prop, T defaultValue) {
		var values = getAll(prop);
		if(values.size()==0) {
			return defaultValue;
		}
		else if(values.size()==1) {
			return (T) values.getFirst();
		}
		else {
			throw new IllegalStateException("Property "+prop.getProperty()+" has "+values.size()+" values");
		}
	}
}
