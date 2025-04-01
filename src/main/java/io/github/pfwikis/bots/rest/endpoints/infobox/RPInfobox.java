package io.github.pfwikis.bots.rest.endpoints.infobox;

import java.util.Arrays;
import java.util.List;

import io.github.pfwikis.bots.facts.SModel;
import io.github.pfwikis.bots.rest.DefaultRPParam;
import io.github.pfwikis.bots.rest.RPEndpoint;
import io.github.pfwikis.bots.rest.RestProviderBot;
import io.github.pfwikis.bots.utils.RockerHelper;

public class RPInfobox extends RPEndpoint<DefaultRPParam> {

	public RPInfobox() {
		super(DefaultRPParam.class, "infobox");
	}

	@Override
	public RPResult handle(RestProviderBot bot, DefaultRPParam param) throws Exception {
		var subject = bot.getRun().getWiki().semanticSubject(param.getFactsPage());
		
		var concepts = Arrays.stream(SModel.CONCEPTS)
			.filter(c->c.getInfoboxProperties()!=null && !c.getInfoboxProperties().isEmpty())
			.filter(subject::hasConcept)
			.toList();
		
		String cnt = "{{Error|Empty concepts}}";
		if(!concepts.isEmpty()) {
			cnt = RockerHelper.makeWikitext(MakeInfoboxTemplate.template(bot.getRun(), concepts, subject));
		}
		
		return new RPResult(
			List.of(new RPBlock(RPBlockType.WIKITEXT, cnt)),
			List.of("Facts:The Burning of Greensteeples")
		);
	}
}
