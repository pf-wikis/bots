package io.github.pfwikis.bots.facts.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class SDIProperty<JType> {
	private String name;
	private SDIFactType<JType> factType;
	private String description;
	/*
	private final String factDisplayFormat;
	private final String note;
	private final String suggestValuesFrom;
	private final String infoboxLabel;
	private final boolean autocompleteDisabled;
	*/
	
	public Generated withGenerateCode(String code) {
		return new Generated(name, smwType, factType, factDisplayFormat, note, suggestValuesFrom, infoboxLabel, autocompleteDisabled, code);
	}
	
	
	@Getter
	public static class Generated extends SDIProperty {
		public Generated(String name, SMWPropertyType type, SDIFactType factType, String factDisplayFormat, String note,
				String suggestValuesFrom, String infoboxLabel, boolean autocompleteDisabled, String generateCode) {
			super(name, type, factType, factDisplayFormat, note, suggestValuesFrom, infoboxLabel, autocompleteDisabled);
			this.generateCode = generateCode;
		}

		private final String generateCode;
	}

	public String infoboxLabel(List<Object> values) {
		return factType.infoboxLabel(this, values);
	}

	public String infoboxValue(WikiAPI wiki, List<Object> values) {
		return factType.infoboxValue(wiki, this, values);
	}
	
	public String infoboxValue(WikiAPI wiki, Object value) {
		return factType.infoboxValue(wiki, this, value);
	}
}