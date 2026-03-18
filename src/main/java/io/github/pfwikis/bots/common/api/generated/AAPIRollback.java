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

import io.github.pfwikis.bots.common.api.generated.params.AAPIRollbackWatchlist;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** <p>Undo the last edit to the page.
 * </p>
 * <p>If the last user who edited the page made multiple edits in a row, they will all be rolled back.
 * </p>
 */
public class AAPIRollback implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIRollback create(@NonNull String user) {

		AAPIRollback v = new AAPIRollback();

		v.user = user;

		return v;
	}

	private AAPIRollback() {}

	private String title;

	private Long pageid;

	private List<String> tags;

	private String user;

	private String summary;

	private Boolean markbot;

	private AAPIRollbackWatchlist watchlist;

	private String token;

	/**Title of the page to roll back. Cannot be used together with <var>pageid</var>.
	 */
	public AAPIRollback title(String title) {

		this.title = title;

		return this;
	}

	/**Title of the page to roll back. Cannot be used together with <var>pageid</var>.
	 */
	public String getTitle() {
		return this.title;
	}

	/**Page ID of the page to roll back. Cannot be used together with <var>title</var>.
	 */
	public AAPIRollback pageid(Long pageid) {

		this.pageid = pageid;

		return this;
	}

	/**Page ID of the page to roll back. Cannot be used together with <var>title</var>.
	 */
	public Long getPageid() {
		return this.pageid;
	}

	/**Tags to apply to the rollback.
	 */
	public AAPIRollback tags(String... tags) {

		this.tags = List.of(tags);

		return this;
	}

	/**Tags to apply to the rollback.
	 */
	public List<String> getTags() {
		return this.tags;
	}

	/**Name of the user whose edits are to be rolled back.
	 */
	public String getUser() {
		return this.user;
	}

	/**Custom edit summary. If empty, default summary will be used.
	 */
	public AAPIRollback summary(String summary) {

		this.summary = summary;

		return this;
	}

	/**Custom edit summary. If empty, default summary will be used.
	 */
	public String getSummary() {
		return this.summary;
	}

	/**Mark the reverted edits and the revert as bot edits.
	 */
	public AAPIRollback markbot(Boolean markbot) {

		this.markbot = markbot;

		return this;
	}

	/**Mark the reverted edits and the revert as bot edits.
	 */
	public Boolean getMarkbot() {
		return this.markbot;
	}

	/**Unconditionally add or remove the page from the current user's watchlist, use preferences (ignored for bot users) or do not change watch.
	 */
	public AAPIRollback watchlist(AAPIRollbackWatchlist watchlist) {

		this.watchlist = watchlist;

		return this;
	}

	/**Unconditionally add or remove the page from the current user's watchlist, use preferences (ignored for bot users) or do not change watch.
	 */
	public AAPIRollbackWatchlist getWatchlist() {
		return this.watchlist;
	}

	/**<p>A "rollback" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 * </p>
	 * <p>For compatibility, the token used in the web UI is also accepted.
	 * </p>
	 */
	public AAPIRollback token(String token) {

		this.token = token;

		return this;
	}

	/**<p>A "rollback" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 * </p>
	 * <p>For compatibility, the token used in the web UI is also accepted.
	 * </p>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIRollback(");

		if (title != null) {

			sb.append("title").append("=").append(title);

			sb.append(", ");
		}

		if (pageid != null) {

			sb.append("pageid").append("=").append(pageid.toString());

			sb.append(", ");
		}

		if (tags != null) {

			sb.append("tags")
					.append("=")
					.append(tags.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (user != null) {

			sb.append("user").append("=").append(user);

			sb.append(", ");
		}

		if (summary != null) {

			sb.append("summary").append("=").append(summary);

			sb.append(", ");
		}

		if (markbot != null) {

			sb.append("markbot").append("=").append(markbot.toString());

			sb.append(", ");
		}

		if (watchlist != null) {

			sb.append("watchlist").append("=").append(watchlist.getJsonValue());

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

		if (title != null) {

			req.addParameter(paramPrefix + "title", title);
		}

		if (pageid != null) {

			req.addParameter(paramPrefix + "pageid", pageid.toString());
		}

		if (tags != null) {

			req.addParameter(
					paramPrefix + "tags",
					tags.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (user != null) {

			req.addParameter(paramPrefix + "user", user);
		}

		if (summary != null) {

			req.addParameter(paramPrefix + "summary", summary);
		}

		if (markbot != null) {

			req.addParameter(paramPrefix + "markbot", markbot.toString());
		}

		if (watchlist != null) {

			req.addParameter(paramPrefix + "watchlist", watchlist.getJsonValue());
		}

		token = api.requestToken(AAPIQueryTokensType.ROLLBACK);

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
			c.accept(AAPIRollback.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIRollback.this);

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
