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

/** Merge page histories.
 */
public class AAPIMergehistory implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIMergehistory create() {

		AAPIMergehistory v = new AAPIMergehistory();

		return v;
	}

	private AAPIMergehistory() {}

	private String from;

	private Long fromid;

	private String to;

	private Long toid;

	private java.time.Instant timestamp;

	private String reason;

	private String token;

	/**Title of the page from which history will be merged. Cannot be used together with <var>fromid</var>.
	 */
	public AAPIMergehistory from(String from) {

		this.from = from;

		return this;
	}

	/**Title of the page from which history will be merged. Cannot be used together with <var>fromid</var>.
	 */
	public String getFrom() {
		return this.from;
	}

	/**Page ID of the page from which history will be merged. Cannot be used together with <var>from</var>.
	 */
	public AAPIMergehistory fromid(Long fromid) {

		this.fromid = fromid;

		return this;
	}

	/**Page ID of the page from which history will be merged. Cannot be used together with <var>from</var>.
	 */
	public Long getFromid() {
		return this.fromid;
	}

	/**Title of the page to which history will be merged. Cannot be used together with <var>toid</var>.
	 */
	public AAPIMergehistory to(String to) {

		this.to = to;

		return this;
	}

	/**Title of the page to which history will be merged. Cannot be used together with <var>toid</var>.
	 */
	public String getTo() {
		return this.to;
	}

	/**Page ID of the page to which history will be merged. Cannot be used together with <var>to</var>.
	 */
	public AAPIMergehistory toid(Long toid) {

		this.toid = toid;

		return this;
	}

	/**Page ID of the page to which history will be merged. Cannot be used together with <var>to</var>.
	 */
	public Long getToid() {
		return this.toid;
	}

	/**Timestamp up to which revisions will be moved from the source page's history to the destination page's history. If omitted, the entire page history of the source page will be merged into the destination page.
	 */
	public AAPIMergehistory timestamp(java.time.Instant timestamp) {

		this.timestamp = timestamp;

		return this;
	}

	/**Timestamp up to which revisions will be moved from the source page's history to the destination page's history. If omitted, the entire page history of the source page will be merged into the destination page.
	 */
	public java.time.Instant getTimestamp() {
		return this.timestamp;
	}

	/**Reason for the history merge.
	 */
	public AAPIMergehistory reason(String reason) {

		this.reason = reason;

		return this;
	}

	/**Reason for the history merge.
	 */
	public String getReason() {
		return this.reason;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIMergehistory token(String token) {

		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIMergehistory(");

		if (from != null) {

			sb.append("from").append("=").append(from);

			sb.append(", ");
		}

		if (fromid != null) {

			sb.append("fromid").append("=").append(fromid.toString());

			sb.append(", ");
		}

		if (to != null) {

			sb.append("to").append("=").append(to);

			sb.append(", ");
		}

		if (toid != null) {

			sb.append("toid").append("=").append(toid.toString());

			sb.append(", ");
		}

		if (timestamp != null) {

			sb.append("timestamp")
					.append("=")
					.append(
							timestamp
									.truncatedTo(java.time.temporal.ChronoUnit.SECONDS)
									.toString());

			sb.append(", ");
		}

		if (reason != null) {

			sb.append("reason").append("=").append(reason);

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

		if (from != null) {

			req.addParameter(paramPrefix + "from", from);
		}

		if (fromid != null) {

			req.addParameter(paramPrefix + "fromid", fromid.toString());
		}

		if (to != null) {

			req.addParameter(paramPrefix + "to", to);
		}

		if (toid != null) {

			req.addParameter(paramPrefix + "toid", toid.toString());
		}

		if (timestamp != null) {

			req.addParameter(
					paramPrefix + "timestamp",
					timestamp.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (reason != null) {

			req.addParameter(paramPrefix + "reason", reason);
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
			c.accept(AAPIMergehistory.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIMergehistory.this);

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
