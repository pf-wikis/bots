package io.github.pfwikis.bots.common.model;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.MoreCollectors;

import io.github.pfwikis.bots.common.model.subject.SemanticSubject.Property;
import io.github.pfwikis.bots.facts.model.SProperty;

public interface SemanticObject {
	
	List<Property> getData();
	
	default boolean has(SProperty<?> prop) {
		return getData().stream().anyMatch(p->p.getProperty().equals(prop.getName().replace(" ", "_")));
	}
	
	default <T> T get(SProperty<T> prop) {
		var result = getOr(prop, null);
		if(result == null)
			throw new IllegalStateException(prop.getName()+ " has no value");
		return result;
	}
	
	default <T> T getOr(SProperty<T> prop, T defaultValue) {
		var values = getData().stream()
				.filter(p->p.getProperty().equals(prop.getName().replace(" ", "_")))
				.collect(MoreCollectors.toOptional())
				.map(op->op.getDataitem())
				.orElse(Collections.emptyList());
		
		if(values.size()==0) {
			return defaultValue;
		}
		return prop.getFactType().convertToJava(values);
	}
}
