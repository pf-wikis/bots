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

import io.github.pfwikis.bots.common.api.generated.params.AAPIProtectWatchlist;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Change the protection level of a page.
 */
public class AAPIProtect implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIProtect create(@NonNull ContainsPageRef title, @NonNull String protections) {

		AAPIProtect v = new AAPIProtect();

		v.title = title;

		v.protections = List.of(protections);

		return v;
	}

	private AAPIProtect() {}

	private List<String> protections;

	private List<String> expiry;

	private String reason;

	private List<String> tags;

	private Boolean cascade;

	private AAPIProtectWatchlist watchlist;

	private String token;

	private ContainsPageRef title;

	/**<p>List of protection levels, formatted <kbd>action=level</kbd> (e.g. <kbd>edit=sysop</kbd>). A level of <kbd>all</kbd> means everyone is allowed to take the action, i.e. no restriction.
	 * </p><p><strong>Note:</strong> Any actions not listed will have restrictions removed.
	 * </p>
	 */
	public List<String> getProtections() {
		return this.protections;
	}

	/**Expiry timestamps. If only one timestamp is set, it'll be used for all protections. Use <kbd>infinite</kbd>, <kbd>indefinite</kbd>, <kbd>infinity</kbd>, or <kbd>never</kbd>, for a never-expiring protection.
	 */
	public AAPIProtect expiry(String expiry) {
		this.expiry = List.of(expiry);

		return this;
	}

	/**Expiry timestamps. If only one timestamp is set, it'll be used for all protections. Use <kbd>infinite</kbd>, <kbd>indefinite</kbd>, <kbd>infinity</kbd>, or <kbd>never</kbd>, for a never-expiring protection.
	 */
	public AAPIProtect expiry(String... expiry) {
		this.expiry = List.of(expiry);
		return this;
	}

	/**Expiry timestamps. If only one timestamp is set, it'll be used for all protections. Use <kbd>infinite</kbd>, <kbd>indefinite</kbd>, <kbd>infinity</kbd>, or <kbd>never</kbd>, for a never-expiring protection.
	 */
	public List<String> getExpiry() {
		return this.expiry;
	}

	/**Reason for (un)protecting.
	 */
	public AAPIProtect reason(String reason) {
		this.reason = reason;

		return this;
	}

	/**Reason for (un)protecting.
	 */
	public String getReason() {
		return this.reason;
	}

	/**Change tags to apply to the entry in the protection log.
	 */
	public AAPIProtect tags(String tags) {
		this.tags = List.of(tags);

		return this;
	}

	/**Change tags to apply to the entry in the protection log.
	 */
	public AAPIProtect tags(String... tags) {
		this.tags = List.of(tags);
		return this;
	}

	/**Change tags to apply to the entry in the protection log.
	 */
	public List<String> getTags() {
		return this.tags;
	}

	/**Enable cascading protection (i.e. protect transcluded templates and images used in this page). Ignored if none of the given protection levels support cascading.
	 */
	public AAPIProtect cascade(Boolean cascade) {
		this.cascade = cascade;

		return this;
	}

	/**Enable cascading protection (i.e. protect transcluded templates and images used in this page). Ignored if none of the given protection levels support cascading.
	 */
	public Boolean getCascade() {
		return this.cascade;
	}

	/**Unconditionally add or remove the page from the current user's watchlist, use preferences (ignored for bot users) or do not change watch.
	 */
	public AAPIProtect watchlist(AAPIProtectWatchlist watchlist) {
		this.watchlist = watchlist;

		return this;
	}

	/**Unconditionally add or remove the page from the current user's watchlist, use preferences (ignored for bot users) or do not change watch.
	 */
	public AAPIProtectWatchlist getWatchlist() {
		return this.watchlist;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIProtect token(String token) {
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
		var sb = new StringBuilder().append("AAPIProtect(");

		if (title != null) {
			sb.append("title=").append(title).append(", ");
		}

		if (protections != null) {

			sb.append("protections")
					.append("=")
					.append(protections.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (expiry != null) {

			sb.append("expiry")
					.append("=")
					.append(expiry.stream().map(v -> v).collect(Collectors.joining("|")));

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

		if (cascade != null) {

			sb.append("cascade").append("=").append(cascade.toString());

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

			if (title.toPageRef().hasId()) {
				req.addParameter(
						paramPrefix + "pageid", Integer.toString(title.toPageRef().getId()));
			} else {
				req.addParameter(paramPrefix + "title", title.toPageTitle().toFullTitle());
			}
		}

		if (protections != null) {

			req.addParameter(
					paramPrefix + "protections",
					protections.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (expiry != null) {

			req.addParameter(
					paramPrefix + "expiry",
					expiry.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (reason != null) {

			req.addParameter(paramPrefix + "reason", reason);
		}

		if (tags != null) {

			req.addParameter(
					paramPrefix + "tags",
					tags.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (cascade != null) {

			req.addParameter(paramPrefix + "cascade", cascade.toString());
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
			c.accept(AAPIProtect.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIProtect.this);

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
