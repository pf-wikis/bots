package io.github.pfwikis.bots.facts.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import io.github.pfwikis.bots.facts.SFactsProperties;
import io.github.pfwikis.bots.facts.SUtilProperties;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;
import lombok.experimental.Accessors;

@Getter @Setter
@ToString
@Accessors(chain = true)
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
public class SProperty<JType> {
	private final String name;
	private final SFactType<JType> factType;
	private String description;
	private boolean autocompleteDisabled;
	private String suggestValuesFrom;
	private String formNote;
	private String allowsPattern;
	private boolean required;
	@With
	private String defaultValue;
	@With
	private String generateWikitext;

	public boolean isGenerateWikitextDynamic() {
		if(generateWikitext != null && StringUtils.containsNone(generateWikitext, '[', '{')) {
			return false;
		}
		return true;
	}

	public String wikitextToDisplayFact() {
		String v = generateWikitext!=null?generateWikitext:("{{{"+name+"|}}}");
		var res = factType.wikitextToDisplayFact(this, v);
		
		if(defaultValue == null ) {
			return res;
		}
		return 
			"{{#if:"+wikitextToTestIfValue(false)
			+ "|" + res
			+ "|''default:'' " + factType.wikitextToDisplayFact(this, defaultValue)
			+ "}}";
	}

	public String wikitextToStoreFact() {
		String v = generateWikitext!=null?generateWikitext:("{{{"+name+"|}}}");
		if(defaultValue == null)
			return factType.wikitextToStoreFact(this, v);
		
		return factType.wikitextToDisplayFact(this, "{{#if:"+wikitextToTestIfValue(false)+"|"+v+"|"+defaultValue+"}}");
	}

	public List<SProperty<?>> generateProperties(SConcept c, SConcept parent) {
		return Collections.emptyList();
	}

	public String wikitextToQuery() {
		return factType.wikitextToQuery(this);
	}

	public String wikitextToTestIfValue(boolean includeDefault) {
		String v = generateWikitext!=null?generateWikitext:("{{{"+name+"|}}}");
		var res = factType.wikitextToTestIfValue(this, v);
		if(includeDefault && this.getDefaultValue() != null)
			res = "{{#if:"+res+"|1|"+factType.wikitextToTestIfValue(this, defaultValue)+"}}";
		return res;
	}

	public String wikitextBeforeStoringFact() {
		return factType.wikitextBeforeStoringFact(this);
	}

	public String configureFormField() {
		var conf = factType.configureFormField(this);
		if(StringUtils.startsWith(suggestValuesFrom, "Category:")) {
			conf+="|values from category="+suggestValuesFrom.substring(9);
		}
		else if(StringUtils.startsWith(suggestValuesFrom, "Namespace:")) {
			conf+="|values from namespace="+suggestValuesFrom.substring(10);
		}
		else if(StringUtils.startsWith(suggestValuesFrom, "Property:")) {
			conf+="|values from property="+suggestValuesFrom.substring(9);
		}
		else if(StringUtils.startsWith(suggestValuesFrom, "Values:")) {
			conf+="|values="+suggestValuesFrom.substring(7)+"|existing values only";
		}
		
		if(allowsPattern != null) {
			conf = conf.replace("input type", "base type")
				+ "|input type=regexp|regexp="
				//this ! allows for empty inputs
				+ "/!"+allowsPattern.replace("!", "\\!").replace("|", "!")+"/"
				+ "|message=Not a valid value for "+name;
		}
		return conf;
	}
	
	public String toSafeName() {
		return getName().toLowerCase().replaceAll("\\W+", "-");
	}

	public static List<SProperty<?>> getAll() {
		return Stream.concat(
			SFactsProperties.getAll().stream(),
			SUtilProperties.getAll().stream()
		)
		.sorted(Comparator.comparing(p->p.getName()))
		.toList();
	}
}