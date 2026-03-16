package io.github.pfwikis.bots.common.api.generated;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.NonNull;

import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import io.github.pfwikis.bots.common.api.model.AAPIModule;
import io.github.pfwikis.bots.common.api.model.AAPISubmodule;
import io.github.pfwikis.bots.common.api.model.AAPITokenModule;
import io.github.pfwikis.bots.common.api.model.ContainsPageRef;
import io.github.pfwikis.bots.common.api.AAPI;
import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryTokensType;
import io.github.pfwikis.bots.common.api.generated.params.NS;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Change authentication data for the current user.
 */
public class AAPIChangeauthenticationdata
		implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIChangeauthenticationdata create(@NonNull String request) {

		AAPIChangeauthenticationdata v = new AAPIChangeauthenticationdata();

		v.request = request;

		return v;
	}

	private AAPIChangeauthenticationdata() {}

	private String request;

	private String token;

	/**Use this authentication request, by the <samp>id</samp> returned from <kbd><a href="/wiki/Special:ApiHelp/query%2Bauthmanagerinfo" title="Special:ApiHelp/query+authmanagerinfo">action=query&amp;meta=authmanagerinfo</a></kbd> with <kbd>amirequestsfor=change</kbd>.
	 */
	public String getRequest() {
		return this.request;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIChangeauthenticationdata token(String token) {

		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIChangeauthenticationdata(");

		if (request != null) {

			sb.append("changeauthrequest").append("=").append(request);

			sb.append(", ");
		}

		if (token != null) {

			sb.append("changeauthtoken").append("=").append(token);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (request != null) {

			req.addParameter(paramPrefix + "changeauthrequest", request);
		}

		token = api.requestToken(AAPIQueryTokensType.CSRF);

		if (token != null) {

			req.addParameter(paramPrefix + "changeauthtoken", token);
		}
	}

	private final Builder builder = new Builder();

	@Override
	public Builder builder() {
		return builder;
	}

	private class Builder extends AAPIModule.Builder {
		@Override
		public void forEachModule(Consumer<AAPIModule> c) {
			c.accept(AAPIChangeauthenticationdata.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIChangeauthenticationdata.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return true;
		}
	}
}
