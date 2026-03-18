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

/** Send a public thank-you notification for a comment.
 */
public class AAPIDiscussiontoolsthank implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIDiscussiontoolsthank create(@NonNull String page, @NonNull String commentid) {

		AAPIDiscussiontoolsthank v = new AAPIDiscussiontoolsthank();

		v.page = page;

		v.commentid = commentid;

		return v;
	}

	private AAPIDiscussiontoolsthank() {}

	private String page;

	private String commentid;

	private String token;

	/**The page to perform actions on.
	 */
	public String getPage() {
		return this.page;
	}

	/**ID of the comment to thank.
	 */
	public String getCommentid() {
		return this.commentid;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIDiscussiontoolsthank token(String token) {

		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIDiscussiontoolsthank(");

		if (page != null) {

			sb.append("page").append("=").append(page);

			sb.append(", ");
		}

		if (commentid != null) {

			sb.append("commentid").append("=").append(commentid);

			sb.append(", ");
		}

		if (token != null) {

			sb.append("token").append("=").append(token);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (page != null) {

			req.addParameter(paramPrefix + "page", page);
		}

		if (commentid != null) {

			req.addParameter(paramPrefix + "commentid", commentid);
		}

		token = api.requestToken(AAPIQueryTokensType.CSRF);

		if (token != null) {

			req.addParameter(paramPrefix + "token", token);
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
			c.accept(AAPIDiscussiontoolsthank.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIDiscussiontoolsthank.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return true;
		}

		@Override
		protected boolean internalRequiresPagination() {
			return true;
		}
	}
}
