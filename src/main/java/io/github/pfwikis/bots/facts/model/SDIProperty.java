package io.github.pfwikis.bots.facts.model;

import io.github.pfwikis.bots.factshelper.PropertyType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class SDIProperty {
	private final String name;
	private final PropertyType type;
	private final SDIFactType factType;
	private final String factDisplayFormat;
	private final String note;
	private final String suggestValuesFrom;
	private final String infoboxLabel;
	private final boolean autocompleteDisabled;
	
	public Generated withGenerateCode(String code) {
		return new Generated(name, type, factType, factDisplayFormat, note, suggestValuesFrom, infoboxLabel, autocompleteDisabled, code);
	}
	
	
	@Getter
	public static class Generated extends SDIProperty {
		public Generated(String name, PropertyType type, SDIFactType factType, String factDisplayFormat, String note,
				String suggestValuesFrom, String infoboxLabel, boolean autocompleteDisabled, String generateCode) {
			super(name, type, factType, factDisplayFormat, note, suggestValuesFrom, infoboxLabel, autocompleteDisabled);
			this.generateCode = generateCode;
		}

		private final String generateCode;
	}
}