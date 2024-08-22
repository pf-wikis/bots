package io.github.pfwikis.bots.common.model;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.MoreCollectors;

import io.github.pfwikis.bots.common.model.SemanticSubject.PageRef;
import io.github.pfwikis.bots.common.model.SemanticSubject.Property;
import io.github.pfwikis.bots.facts.model.SProperty;
import io.github.pfwikis.bots.facts.model.SPropertyTypeMapping;

public interface SemanticObject {
	
	List<Property> getData();
	
	default boolean has(String prop) {
		return getData().stream().anyMatch(p->p.getProperty().equals(prop.replace(" ", "_")));
	}
	
	default boolean has(SPropertyTypeMapping<?> prop) {
		return has(prop.getProperty());
	}
	
	default boolean has(SProperty<?> prop) {
		return has(prop.getName());
	}
	
	@SuppressWarnings("unchecked")
	default <T> List<T> getAll(String prop) {
		return (List<T>) getData().stream()
			.filter(p->p.getProperty().equals(prop.replace(" ", "_")))
			.collect(MoreCollectors.toOptional())
			.map(op->op.getDataitem())
			.orElse(Collections.emptyList());
	}
	
	default <T> List<T> getAll(SPropertyTypeMapping<T> prop) {
		return getAll(prop.getProperty());
	}
	
	default <T> List<T> getAll(SProperty<T> prop) {
		return getAll(prop.getName());
	}
	
	default <T> T get(String prop) {
		List<T> values = getAll(prop);
		if(values.size()==1) {
			return values.getFirst();
		}
		else {
			throw new IllegalStateException("Property "+prop+" has "+values.size()+" values");
		}
	}
	
	default <T> T get(SProperty<T> prop) {
		T result = get(prop.getName());
		return result;
	}
	
	default <T> T get(SPropertyTypeMapping<T> prop) {
		return get(prop.getProperty());
	}
	
	default <T> T getOr(String prop, T defaultValue) {
		List<T> values = getAll(prop);
		if(values.size()==0) {
			return defaultValue;
		}
		else if(values.size()==1) {
			return values.getFirst();
		}
		else {
			throw new IllegalStateException("Property "+prop+" has "+values.size()+" values");
		}
	}
	
	default <T> T getOr(SPropertyTypeMapping<T> prop, T defaultValue) {
		return getOr(prop.getProperty(), defaultValue);
	}
	
	default <T> T getOr(SProperty<T> prop, T defaultValue) {
		return getOr(prop.getName(), defaultValue);
	}
}
