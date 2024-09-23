package io.github.pfwikis.bots.facts.model;

import java.util.List;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.model.subject.SemanticSubject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SInfoboxProperty<JType> {
	private final SProperty<JType> property;
	private String label;
	private SProperty<JType> fallback;

	public String toInfoboxDisplay(WikiAPI wiki, SemanticSubject s) {
		return property.getFactType().toInfoboxDisplay(wiki, getValue(s));
	}
	
	private JType getValue(SemanticSubject s) {
		var v = s.getOr(property, null);
		if(v == null && fallback != null) {
			v = s.getOr(fallback, null);
		}
		if(v == null)
			throw new IllegalStateException(s+" does hot have "+this);
		return v;
	}

	public static <JType> SInfoboxPropertyBuilder<JType> from(SProperty<JType> prop) {
		return new SInfoboxPropertyBuilder<JType>()
			.property(prop);
	}

	public boolean isPresentIn(SemanticSubject s) {
		if(s.has(property))
			return true;
		if(fallback != null && s.has(fallback))
			return true;
		return false;
	}

	public String toInfoboxLabel(SemanticSubject s) {
		var v = getValue(s);
		
		var result = property.getName();
		if(label != null)
			result = label;
		
		if(v instanceof List l && l.size() > 1 && result.charAt(result.length()-1) != 's')
			result+="s";
		return result;
	}
}
