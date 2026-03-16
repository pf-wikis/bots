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

/** Subscribe (or unsubscribe) to receive notifications about a topic.
 */
public class AAPIDiscussiontoolssubscribe
		implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIDiscussiontoolssubscribe create(
			@NonNull String page, @NonNull String commentname, @NonNull Boolean subscribe) {

		AAPIDiscussiontoolssubscribe v = new AAPIDiscussiontoolssubscribe();

		v.page = page;

		v.commentname = commentname;

		v.subscribe = subscribe;

		return v;
	}

	private AAPIDiscussiontoolssubscribe() {}

	private String page;

	private String token;

	private String commentname;

	private Boolean subscribe;

	/**A page on which the topic appears
	 */
	public String getPage() {
		return this.page;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIDiscussiontoolssubscribe token(String token) {

		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	/**Name of the topic to subscribe to (or unsubscribe from)
	 */
	public String getCommentname() {
		return this.commentname;
	}

	/**True to subscribe, false to unsubscribe
	 */
	public Boolean getSubscribe() {
		return this.subscribe;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIDiscussiontoolssubscribe(");

		if (page != null) {

			sb.append("page").append("=").append(page);

			sb.append(", ");
		}

		if (token != null) {

			sb.append("token").append("=").append(token);

			sb.append(", ");
		}

		if (commentname != null) {

			sb.append("commentname").append("=").append(commentname);

			sb.append(", ");
		}

		if (subscribe != null) {

			sb.append("subscribe").append("=").append(subscribe.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (page != null) {

			req.addParameter(paramPrefix + "page", page);
		}

		token = api.requestToken(AAPIQueryTokensType.CSRF);

		if (token != null) {

			req.addParameter(paramPrefix + "token", token);
		}

		if (commentname != null) {

			req.addParameter(paramPrefix + "commentname", commentname);
		}

		if (subscribe != null) {

			req.addParameter(paramPrefix + "subscribe", subscribe.toString());
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
			c.accept(AAPIDiscussiontoolssubscribe.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIDiscussiontoolssubscribe.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return true;
		}
	}
}
