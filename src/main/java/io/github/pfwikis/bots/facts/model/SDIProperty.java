package io.github.pfwikis.bots.facts.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fizzed.rocker.RockerContent;

import io.github.pfwikis.bots.common.WikiAPI;
import io.github.pfwikis.bots.common.bots.Run.SingleRun;
import io.github.pfwikis.bots.common.model.SemanticAsk.Result;
import io.github.pfwikis.bots.factshelper.FactsHelper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SDIProperty {
	private final String name;
	private final SMWPropertyType smwType;
	private final SDIFactType factType;
	private final String factDisplayFormat;
	private final String note;
	private final String suggestValuesFrom;
	private final String infoboxLabel;
	private final boolean autocompleteDisabled;
	
	public static Map<String, SDIProperty> load(SingleRun run) {
		return run.cache("propertyDefinitions", "", b->
			run.getWiki().semanticAsk("[[Has type::+]]"
				+ "|?Has type"
				+ "|?Has fact type"
				+ "|?Has fact display format"
				+ "|?Has fact note"
				+ "|?Suggest values from"
				+ "|?Has infobox label"
				+ "|?Disable autocomplete"
			)
			.stream()
			.map(p->new SDIProperty(
					p.getFulltext().substring(9),
					p.getPrintouts().getHasType(),
					p.getPrintouts().getHasFactType(),
					p.getPrintouts().getHasFactDisplayFormat(),
					p.getPrintouts().getHasFactNote(),
					p.getPrintouts().getSuggestValuesFrom(),
					p.getPrintouts().getHasInfoboxLabel(),
					Boolean.TRUE.equals(p.getPrintouts().getDisableAutocomplete())
				))
			.collect(Collectors.toMap(d->d.getName(), d->d))
		);
	}
	
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
}