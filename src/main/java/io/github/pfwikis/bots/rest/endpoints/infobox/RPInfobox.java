package io.github.pfwikis.bots.rest.endpoints.infobox;

import java.util.EnumMap;
import java.util.List;

import com.google.common.collect.Lists;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.facts.SModel;
import io.github.pfwikis.bots.facts.model.SConcept;
import io.github.pfwikis.bots.rest.RPEndpoint;
import io.github.pfwikis.bots.rest.RestProviderBot;
import io.github.pfwikis.bots.utils.Jackson;
import io.github.pfwikis.bots.utils.RockerHelper;
import lombok.Getter;

@Getter
public class RPInfobox extends RPEndpoint<RPInfoboxParam> {

	private EnumMap<Wiki, List<SConcept>> concepts = new EnumMap<>(Wiki.class);
	
	public RPInfobox() {
		super(RPInfoboxParam.class, "infobox");
		
		for(var wiki:Wiki.values()) {
			concepts.put(
					wiki,
					SModel.getConcepts(wiki).stream()
						.filter(c->c.getInfoboxProperties()!=null && !c.getInfoboxProperties().isEmpty())
						.toList());
		}
	}
	
	@Override
	public RPResult handle(RestProviderBot bot, RPInfoboxParam param) throws Exception {
		if(!param.validate()) {
			return error(param.getFactsPage(), "Invalid arguments to infobox");
		}
		
		var result = generateResult(bot, param);
		
		return RPResult.builder()
			.block(new RPBlock(
					RPBlockType.WIKITEXT,
					result
					
			))
			.dependency(param.getFactsPage())
			.build();
	}

	public String generateResult(RestProviderBot bot, RPInfoboxParam param) {
		var subject = param.getSemanticSubject().postProcess();
		var matchingConcepts = Lists.newArrayList(concepts.get(bot.getRun().getServer()));
		matchingConcepts.removeIf(c->!subject.hasConcept(c));
		return RockerHelper.makeWikitext(MakeInfoboxTemplate.template(
				bot.getRun().getServer(),
				matchingConcepts,
				subject,
				bot.getRun().getWiki().getSeries2Category()
		));
	}
}
