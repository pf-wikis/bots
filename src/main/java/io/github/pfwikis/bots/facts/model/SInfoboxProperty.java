package io.github.pfwikis.bots.facts.model;

import java.util.List;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.model.SemanticSubject.PageRef;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SInfoboxProperty {
	private final SProperty<?> property;
	private String label;
	private SProperty<?> fallback;

	public String wikitextToInfoboxLabel(int size) {
		var result = property.getName();
		if(label != null)
			result = label;
		
		if(size > 1 && result.charAt(result.length()-1) != 's')
			result+="s";
		return result;
	}
	
	public String wikitextToInfoboxDisplay(WikiAPI wiki, List<?> values) {
		if(values.size() == 0)
			return "";
		if(values.size() == 1)
			return infoboxValue(wiki, values.getFirst());
		if(values.size() == 2)
			return infoboxValue(wiki, values.getFirst())
				+ " and "
				+ infoboxValue(wiki, values.get(1));
		var sb = new StringBuilder();
		for(int i=0;i<values.size()-1;i++) {
			if(i>0) sb.append(", ");
			sb.append(infoboxValue(wiki, values.get(i)));
		}
		sb.append(", and ");
		sb.append(infoboxValue(wiki, values.getLast()));
		return sb.toString();
	}

	private String infoboxValue(WikiAPI wiki, Object object) {
		return property.getFactType().wikitextToInfoboxDisplay(wiki, object);
	}
}
