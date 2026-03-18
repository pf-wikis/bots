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

import io.github.pfwikis.bots.common.api.generated.params.AAPIMoveWatchlist;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Move a page.
 */
public class AAPIMove implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIMove create(@NonNull String to) {

		AAPIMove v = new AAPIMove();

		v.to = to;

		return v;
	}

	private AAPIMove() {}

	private String from;

	private Long fromid;

	private String to;

	private String reason;

	private Boolean movetalk;

	private Boolean movesubpages;

	private Boolean noredirect;

	private AAPIMoveWatchlist watchlist;

	private Boolean ignorewarnings;

	private List<String> tags;

	private String token;

	/**Title of the page to rename. Cannot be used together with <var>fromid</var>.
	 */
	public AAPIMove from(String from) {

		this.from = from;

		return this;
	}

	/**Title of the page to rename. Cannot be used together with <var>fromid</var>.
	 */
	public String getFrom() {
		return this.from;
	}

	/**Page ID of the page to rename. Cannot be used together with <var>from</var>.
	 */
	public AAPIMove fromid(Long fromid) {

		this.fromid = fromid;

		return this;
	}

	/**Page ID of the page to rename. Cannot be used together with <var>from</var>.
	 */
	public Long getFromid() {
		return this.fromid;
	}

	/**Title to rename the page to.
	 */
	public String getTo() {
		return this.to;
	}

	/**Reason for the rename.
	 */
	public AAPIMove reason(String reason) {

		this.reason = reason;

		return this;
	}

	/**Reason for the rename.
	 */
	public String getReason() {
		return this.reason;
	}

	/**Rename the talk page, if it exists.
	 */
	public AAPIMove movetalk(Boolean movetalk) {

		this.movetalk = movetalk;

		return this;
	}

	/**Rename the talk page, if it exists.
	 */
	public Boolean getMovetalk() {
		return this.movetalk;
	}

	/**Rename subpages, if applicable.
	 */
	public AAPIMove movesubpages(Boolean movesubpages) {

		this.movesubpages = movesubpages;

		return this;
	}

	/**Rename subpages, if applicable.
	 */
	public Boolean getMovesubpages() {
		return this.movesubpages;
	}

	/**Don't create a redirect.
	 */
	public AAPIMove noredirect(Boolean noredirect) {

		this.noredirect = noredirect;

		return this;
	}

	/**Don't create a redirect.
	 */
	public Boolean getNoredirect() {
		return this.noredirect;
	}

	/**Unconditionally add or remove the page from the current user's watchlist, use preferences (ignored for bot users) or do not change watch.
	 */
	public AAPIMove watchlist(AAPIMoveWatchlist watchlist) {

		this.watchlist = watchlist;

		return this;
	}

	/**Unconditionally add or remove the page from the current user's watchlist, use preferences (ignored for bot users) or do not change watch.
	 */
	public AAPIMoveWatchlist getWatchlist() {
		return this.watchlist;
	}

	/**Ignore any warnings.
	 */
	public AAPIMove ignorewarnings(Boolean ignorewarnings) {

		this.ignorewarnings = ignorewarnings;

		return this;
	}

	/**Ignore any warnings.
	 */
	public Boolean getIgnorewarnings() {
		return this.ignorewarnings;
	}

	/**Change tags to apply to the entry in the move log and to the null revision on the destination page.
	 */
	public AAPIMove tags(String... tags) {

		this.tags = List.of(tags);

		return this;
	}

	/**Change tags to apply to the entry in the move log and to the null revision on the destination page.
	 */
	public List<String> getTags() {
		return this.tags;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIMove token(String token) {

		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIMove(");

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

		if (reason != null) {

			sb.append("reason").append("=").append(reason);

			sb.append(", ");
		}

		if (movetalk != null) {

			sb.append("movetalk").append("=").append(movetalk.toString());

			sb.append(", ");
		}

		if (movesubpages != null) {

			sb.append("movesubpages").append("=").append(movesubpages.toString());

			sb.append(", ");
		}

		if (noredirect != null) {

			sb.append("noredirect").append("=").append(noredirect.toString());

			sb.append(", ");
		}

		if (watchlist != null) {

			sb.append("watchlist").append("=").append(watchlist.getJsonValue());

			sb.append(", ");
		}

		if (ignorewarnings != null) {

			sb.append("ignorewarnings").append("=").append(ignorewarnings.toString());

			sb.append(", ");
		}

		if (tags != null) {

			sb.append("tags")
					.append("=")
					.append(tags.stream().map(v -> v).collect(Collectors.joining("|")));

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

		if (reason != null) {

			req.addParameter(paramPrefix + "reason", reason);
		}

		if (movetalk != null) {

			req.addParameter(paramPrefix + "movetalk", movetalk.toString());
		}

		if (movesubpages != null) {

			req.addParameter(paramPrefix + "movesubpages", movesubpages.toString());
		}

		if (noredirect != null) {

			req.addParameter(paramPrefix + "noredirect", noredirect.toString());
		}

		if (watchlist != null) {

			req.addParameter(paramPrefix + "watchlist", watchlist.getJsonValue());
		}

		if (ignorewarnings != null) {

			req.addParameter(paramPrefix + "ignorewarnings", ignorewarnings.toString());
		}

		if (tags != null) {

			req.addParameter(
					paramPrefix + "tags",
					tags.stream().map(v -> v).collect(Collectors.joining("|")));
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
			c.accept(AAPIMove.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIMove.this);

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
