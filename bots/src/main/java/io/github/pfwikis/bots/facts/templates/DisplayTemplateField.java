package io.github.pfwikis.bots.facts.templates;

import io.github.pfwikis.bots.facts.model.SProperty;
import io.github.pfwikis.bots.facts.util.ConceptUtil;
import io.github.pfwikis.bots.utils.WikitextHelper;

public class DisplayTemplateField {

	public static String render(SProperty<?> prop, boolean storeProperty) {
		String disp = prop.wikitextToDisplayFact();
		String contentSuffix = "";
		String content = disp;
		if(storeProperty) {
			contentSuffix=prop.wikitextBeforeStoringFact()
				+ "{{#set:"+prop.getName()+"="+prop.wikitextToStoreFact()+"}}";
			content+=contentSuffix;
		}
		
		
		String prefix = prefix(prop);
		String suffix = "</div>";
		String test = prop.wikitextToTestIfValue(true);
		
		if(!prop.isRequired()) {
			return WikitextHelper.ifElse(test, disp, prefix, contentSuffix+suffix, null);
		}
		
		var sb = new StringBuilder();
		sb.append(prefix);
		if(prop.isGenerateWikitextDynamic()) {
		
			if(test.equals(disp)) {
				sb.append("{{#ifContent:%s||%s|{{Error|<code>%s</code> is a required parameter}}}}"
						.formatted(disp, contentSuffix, prop.getName()));
			}
			else {
				sb.append("{{#if:%s|%s|{{Error|<code>%s</code> is a required parameter}}}}"
					    .formatted(test, content, prop.getName()));

			}
		}
		else {
			sb.append(content);
		}
		sb.append(suffix);
		return sb.toString();
	}
	
	private static String prefix(SProperty<?> prop) {
		var css = ConceptUtil.cssName(prop);
		var sb = new StringBuilder();
		//show the property name
		sb.append("<div class=\"facts-display-key prop-")
			.append(css)
			.append("\">{{#property_link:")
			.append(prop.getName())
			.append("}}</div>");
		
		sb.append("<div class=\"facts-display-value prop-")
			.append(css)
			.append("\">");
		return sb.toString();
	}
}
