package io.github.pfwikis.bots.common.api;

import io.github.pfwikis.bots.common.Wiki;
import io.github.pfwikis.bots.common.api.AAPI.RequestResult;
import io.github.pfwikis.bots.common.api.generated.AAPIEdit;
import io.github.pfwikis.bots.common.api.generated.AAPIParse;
import io.github.pfwikis.bots.common.api.generated.AAPIQuery;
import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;
import io.github.pfwikis.bots.common.api.generated.params.AAPIParseProp;
import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;
import io.github.pfwikis.bots.common.api.model.ContainsPageRef;
import io.github.pfwikis.bots.common.api.responses.EditResponse;
import io.github.pfwikis.bots.common.api.responses.IResponse;
import io.github.pfwikis.bots.common.api.responses.ParseResponse;
import io.github.pfwikis.bots.common.api.responses.QueryResponse;
import io.github.pfwikis.bots.common.api.responses.QueryResponse.QRPage;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.JavaType;

@Slf4j
public class MWApi {

	private final String botname;
	private final AAPI api;

	public MWApi(Wiki wiki, String botname, String password, String secret) {
		this.api = new AAPI(wiki, password, secret);
		this.botname = botname;
	}

	public String getWikitext(ContainsPageRef page) {
		var q = AAPIParse.create()
				.prop(AAPIParseProp.WIKITEXT)
				.page(page);
		
		return api.run(q, ParseResponse.class).getWikitext();
	}
	
	public boolean exists(ContainsPageRef page) {
		if(page.toPageRef().hasId()) return true;
		
		var q = AAPIQuery.create()
			.titles(page);
		
		var resp = api.run(q, QueryResponse.class);
		if(resp.getPages().isEmpty()) return false;
		return !Boolean.TRUE.equals(resp.getPages().getFirst().getMissing());
	}
	
	public QRPage getPageProperties(ContainsPageRef page, AAPIQueryPropModule... props) {
		var q = AAPIQuery.create()
				.prop(props)
				.titles(page);
		
		var resp = api.run(q, QueryResponse.class);
		return resp.getPages().getFirst();
	}

	public EditResponse edit(ContainsPageRef page, String content, String reason) {
		log.info("Editing {}", page);
		var q = AAPIEdit.create(page)
				.text(content)
				.bot(true)
				.summary(botname+": "+reason);
		return api.run(q, EditResponse.class);
	}

	public <T extends IResponse<T>> T run(AAPIMainActionModule action, Class<T> type) {
		return api.run(action, type);
	}
	
	public <T> RequestResult<T> complexRun(AAPIMainActionModule action, String mappedField, JavaType type) {
		return api.complexRun(action, mappedField, type);
	}
}
