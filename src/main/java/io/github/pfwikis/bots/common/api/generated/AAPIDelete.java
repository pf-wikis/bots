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

import io.github.pfwikis.bots.common.api.generated.params.AAPIDeleteWatchlist;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Delete a page.
 */
public class AAPIDelete implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIDelete create(@NonNull ContainsPageRef title) {

		AAPIDelete v = new AAPIDelete();

		v.title = title;

		return v;
	}

	private AAPIDelete() {}

	private String reason;

	private List<String> tags;

	private Boolean deletetalk;

	private AAPIDeleteWatchlist watchlist;

	private String oldimage;

	private String token;

	private ContainsPageRef title;

	/**Reason for the deletion. If not set, an automatically generated reason will be used.
	 */
	public AAPIDelete reason(String reason) {
		this.reason = reason;

		return this;
	}

	/**Reason for the deletion. If not set, an automatically generated reason will be used.
	 */
	public String getReason() {
		return this.reason;
	}

	/**Change tags to apply to the entry in the deletion log.
	 */
	public AAPIDelete tags(String tags) {
		this.tags = List.of(tags);

		return this;
	}

	/**Change tags to apply to the entry in the deletion log.
	 */
	public AAPIDelete tags(String... tags) {
		this.tags = List.of(tags);
		return this;
	}

	/**Change tags to apply to the entry in the deletion log.
	 */
	public List<String> getTags() {
		return this.tags;
	}

	/**Delete the talk page, if it exists.
	 */
	public AAPIDelete deletetalk(Boolean deletetalk) {
		this.deletetalk = deletetalk;

		return this;
	}

	/**Delete the talk page, if it exists.
	 */
	public Boolean getDeletetalk() {
		return this.deletetalk;
	}

	/**Unconditionally add or remove the page from the current user's watchlist, use preferences (ignored for bot users) or do not change watch.
	 */
	public AAPIDelete watchlist(AAPIDeleteWatchlist watchlist) {
		this.watchlist = watchlist;

		return this;
	}

	/**Unconditionally add or remove the page from the current user's watchlist, use preferences (ignored for bot users) or do not change watch.
	 */
	public AAPIDeleteWatchlist getWatchlist() {
		return this.watchlist;
	}

	/**The name of the old image to delete as provided by <a href="/wiki/Special:ApiHelp/query%2Bimageinfo" title="Special:ApiHelp/query+imageinfo">action=query&amp;prop=imageinfo&amp;iiprop=archivename</a>.
	 */
	public AAPIDelete oldimage(String oldimage) {
		this.oldimage = oldimage;

		return this;
	}

	/**The name of the old image to delete as provided by <a href="/wiki/Special:ApiHelp/query%2Bimageinfo" title="Special:ApiHelp/query+imageinfo">action=query&amp;prop=imageinfo&amp;iiprop=archivename</a>.
	 */
	public String getOldimage() {
		return this.oldimage;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIDelete token(String token) {
		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public ContainsPageRef getTitle() {
		return this.title;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIDelete(");

		if (title != null) {
			sb.append("title=").append(title).append(", ");
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

		if (deletetalk != null) {

			sb.append("deletetalk").append("=").append(deletetalk.toString());

			sb.append(", ");
		}

		if (watchlist != null) {

			sb.append("watchlist").append("=").append(watchlist.getJsonValue());

			sb.append(", ");
		}

		if (oldimage != null) {

			sb.append("oldimage").append("=").append(oldimage);

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

			if (title.toPageRef().hasId()) {
				req.addParameter(
						paramPrefix + "pageid", Integer.toString(title.toPageRef().getId()));
			} else {
				req.addParameter(paramPrefix + "title", title.toPageTitle().toFullTitle());
			}
		}

		if (reason != null) {

			req.addParameter(paramPrefix + "reason", reason);
		}

		if (tags != null) {

			req.addParameter(
					paramPrefix + "tags",
					tags.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (deletetalk != null) {

			req.addParameter(paramPrefix + "deletetalk", deletetalk.toString());
		}

		if (watchlist != null) {

			req.addParameter(paramPrefix + "watchlist", watchlist.getJsonValue());
		}

		if (oldimage != null) {

			req.addParameter(paramPrefix + "oldimage", oldimage);
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
			c.accept(AAPIDelete.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIDelete.this);

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
