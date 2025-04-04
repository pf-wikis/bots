package io.github.pfwikis.bots.rest.endpoints.infobox;

import java.util.Arrays;

import io.github.pfwikis.bots.facts.SModel;
import io.github.pfwikis.bots.rest.RPEndpoint;
import io.github.pfwikis.bots.rest.RestProviderBot;
import io.github.pfwikis.bots.utils.RockerHelper;

public class RPInfobox extends RPEndpoint<RPInfoboxParam> {

	public RPInfobox() {
		super(RPInfoboxParam.class, "infobox");
	}

	@Override
	public RPResult handle(RestProviderBot bot, RPInfoboxParam param) throws Exception {
		if(!param.validate()) {
			return error(param.getFactsPage(), "Invalid arguments to infobox");
		}
		
		var subject = param.getData().postProcess();
			
		var concepts = Arrays.stream(SModel.CONCEPTS)
			.filter(c->c.getInfoboxProperties()!=null && !c.getInfoboxProperties().isEmpty())
			.filter(subject::hasConcept)
			.toList();
			
		
		if(concepts.isEmpty()) return error(param.getFactsPage(), "No infobox available based on the facts.");
		
		return RPResult.builder()
			.block(new RPBlock(RPBlockType.WIKITEXT, RockerHelper.makeWikitext(MakeInfoboxTemplate.template(bot.getRun(), concepts, subject))))
			.dependency(param.getFactsPage())
			.build();
	}
}
