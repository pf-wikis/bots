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

/** Send a thank-you notification to an editor.
 */
public class AAPIThank implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIThank create() {

		AAPIThank v = new AAPIThank();

		return v;
	}

	private AAPIThank() {}

	private Long rev;

	private Long log;

	private String token;

	private String source;

	/**Revision ID to thank someone for. This or 'log' must be provided.
	 */
	public AAPIThank rev(Long rev) {

		this.rev = rev;

		return this;
	}

	/**Revision ID to thank someone for. This or 'log' must be provided.
	 */
	public Long getRev() {
		return this.rev;
	}

	/**Log ID to thank someone for. This or 'rev' must be provided.
	 */
	public AAPIThank log(Long log) {

		this.log = log;

		return this;
	}

	/**Log ID to thank someone for. This or 'rev' must be provided.
	 */
	public Long getLog() {
		return this.log;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIThank token(String token) {

		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	/**A short string describing the source of the request, for example <kbd>diff</kbd> or <kbd>history</kbd>.
	 */
	public AAPIThank source(String source) {

		this.source = source;

		return this;
	}

	/**A short string describing the source of the request, for example <kbd>diff</kbd> or <kbd>history</kbd>.
	 */
	public String getSource() {
		return this.source;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIThank(");

		if (rev != null) {

			sb.append("rev").append("=").append(rev.toString());

			sb.append(", ");
		}

		if (log != null) {

			sb.append("log").append("=").append(log.toString());

			sb.append(", ");
		}

		if (token != null) {

			sb.append("token").append("=").append(token);

			sb.append(", ");
		}

		if (source != null) {

			sb.append("source").append("=").append(source);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (rev != null) {

			req.addParameter(paramPrefix + "rev", rev.toString());
		}

		if (log != null) {

			req.addParameter(paramPrefix + "log", log.toString());
		}

		token = api.requestToken(AAPIQueryTokensType.CSRF);

		if (token != null) {

			req.addParameter(paramPrefix + "token", token);
		}

		if (source != null) {

			req.addParameter(paramPrefix + "source", source);
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
			c.accept(AAPIThank.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIThank.this);

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
