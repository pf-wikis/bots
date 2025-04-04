package io.github.pfwikis.bots.rest.endpoints.timeline;

import java.util.Arrays;
import java.util.List;

import io.github.pfwikis.bots.facts.SModel;
import io.github.pfwikis.bots.rest.DefaultRPParam;
import io.github.pfwikis.bots.rest.RPEndpoint;
import io.github.pfwikis.bots.rest.RestProviderBot;
import io.github.pfwikis.bots.utils.RockerHelper;

public class RPTimeline extends RPEndpoint<RPTimelineParam> {

	public RPTimeline() {
		super(RPTimelineParam.class, "timeline");
	}

	@Override
	public RPResult handle(RestProviderBot bot, RPTimelineParam param) throws Exception {
		String cnt = "{{Error|Empty concepts}}";
		if(param.validate()) {
			var subject = bot.getRun().getWiki().semanticSubject(param.getFactsPage());
			
			var concepts = Arrays.stream(SModel.CONCEPTS)
				.filter(c->c.getInfoboxProperties()!=null && !c.getInfoboxProperties().isEmpty())
				.filter(subject::hasConcept)
				.toList();
			
			
			if(!concepts.isEmpty()) {
				cnt = RockerHelper.makeWikitext(MakeInfoboxTemplate.template(bot.getRun(), concepts, subject));
			}
		}
		
		return new RPResult(
			List.of(new RPBlock(RPBlockType.WIKITEXT, cnt)),
			List.of(param.getFactsPage())
		);
	}
}
