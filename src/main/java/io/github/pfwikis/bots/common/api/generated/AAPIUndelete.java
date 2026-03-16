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

import io.github.pfwikis.bots.common.api.generated.params.AAPIUndeleteWatchlist;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** <p>Undelete revisions of a deleted page.
 * </p>
 * <p>A list of deleted revisions (including timestamps) can be retrieved through <a href="/wiki/Special:ApiHelp/query%2Bdeletedrevisions" title="Special:ApiHelp/query+deletedrevisions">prop=deletedrevisions</a>, and a list of deleted file IDs can be retrieved through <a href="/wiki/Special:ApiHelp/query%2Bfilearchive" title="Special:ApiHelp/query+filearchive">list=filearchive</a>.
 * </p>
 */
public class AAPIUndelete implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIUndelete create(@NonNull String title) {

		AAPIUndelete v = new AAPIUndelete();

		v.title = title;

		return v;
	}

	private AAPIUndelete() {}

	private String title;

	private String reason;

	private List<String> tags;

	private List<java.time.Instant> timestamps;

	private List<Integer> fileids;

	private Boolean undeletetalk;

	private AAPIUndeleteWatchlist watchlist;

	private String token;

	/**Title of the page to undelete.
	 */
	public String getTitle() {
		return this.title;
	}

	/**Reason for restoring.
	 */
	public AAPIUndelete reason(String reason) {

		this.reason = reason;

		return this;
	}

	/**Reason for restoring.
	 */
	public String getReason() {
		return this.reason;
	}

	/**Change tags to apply to the entry in the deletion log.
	 */
	public AAPIUndelete tags(String... tags) {

		this.tags = List.of(tags);

		return this;
	}

	/**Change tags to apply to the entry in the deletion log.
	 */
	public List<String> getTags() {
		return this.tags;
	}

	/**Timestamps of the revisions to undelete. If both <var>timestamps</var> and <var>fileids</var> are empty, all will be undeleted.
	 */
	public AAPIUndelete timestamps(java.time.Instant... timestamps) {

		this.timestamps = List.of(timestamps);

		return this;
	}

	/**Timestamps of the revisions to undelete. If both <var>timestamps</var> and <var>fileids</var> are empty, all will be undeleted.
	 */
	public List<java.time.Instant> getTimestamps() {
		return this.timestamps;
	}

	/**IDs of the file revisions to restore. If both <var>timestamps</var> and <var>fileids</var> are empty, all will be restored.
	 */
	public AAPIUndelete fileids(Integer... fileids) {

		this.fileids = List.of(fileids);

		return this;
	}

	/**IDs of the file revisions to restore. If both <var>timestamps</var> and <var>fileids</var> are empty, all will be restored.
	 */
	public List<Integer> getFileids() {
		return this.fileids;
	}

	/**Undelete all revisions of the associated talk page, if any.
	 */
	public AAPIUndelete undeletetalk(Boolean undeletetalk) {

		this.undeletetalk = undeletetalk;

		return this;
	}

	/**Undelete all revisions of the associated talk page, if any.
	 */
	public Boolean getUndeletetalk() {
		return this.undeletetalk;
	}

	/**Unconditionally add or remove the page from the current user's watchlist, use preferences (ignored for bot users) or do not change watch.
	 */
	public AAPIUndelete watchlist(AAPIUndeleteWatchlist watchlist) {

		this.watchlist = watchlist;

		return this;
	}

	/**Unconditionally add or remove the page from the current user's watchlist, use preferences (ignored for bot users) or do not change watch.
	 */
	public AAPIUndeleteWatchlist getWatchlist() {
		return this.watchlist;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIUndelete token(String token) {

		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIUndelete(");

		if (title != null) {

			sb.append("title").append("=").append(title);

			sb.append(", ");
		}

		if (reason != null) {

			sb.append("reason").append("=").append(reason);

			sb.append(", ");
		}

		if (tags != null) {

			sb.append("tags")
					.append("=")
					.append(tags.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (timestamps != null) {

			sb.append("timestamps")
					.append("=")
					.append(
							timestamps.stream()
									.map(
											v ->
													v.truncatedTo(
																	java.time.temporal.ChronoUnit
																			.SECONDS)
															.toString())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (fileids != null) {

			sb.append("fileids")
					.append("=")
					.append(
							fileids.stream()
									.map(v -> v.toString())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (undeletetalk != null) {

			sb.append("undeletetalk").append("=").append(undeletetalk.toString());

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

		if (reason != null) {

			req.addParameter(paramPrefix + "reason", reason);
		}

		if (tags != null) {

			req.addParameter(
					paramPrefix + "tags",
					tags.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (timestamps != null) {

			req.addParameter(
					paramPrefix + "timestamps",
					timestamps.stream()
							.map(
									v ->
											v.truncatedTo(java.time.temporal.ChronoUnit.SECONDS)
													.toString())
							.collect(Collectors.joining("|")));
		}

		if (fileids != null) {

			req.addParameter(
					paramPrefix + "fileids",
					fileids.stream().map(v -> v.toString()).collect(Collectors.joining("|")));
		}

		if (undeletetalk != null) {

			req.addParameter(paramPrefix + "undeletetalk", undeletetalk.toString());
		}

		if (watchlist != null) {

			req.addParameter(paramPrefix + "watchlist", watchlist.getJsonValue());
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
			c.accept(AAPIUndelete.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIUndelete.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return true;
		}
	}
}
