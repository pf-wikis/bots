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

import io.github.pfwikis.bots.common.api.generated.params.AAPIFeedwatchlistFeedformat;

import io.github.pfwikis.bots.common.api.generated.params.AAPIFeedwatchlistWlshow;

import io.github.pfwikis.bots.common.api.generated.params.AAPIFeedwatchlistWltype;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Returns a watchlist feed.
 */
public class AAPIFeedwatchlist implements AAPIModule, AAPIMainActionModule {

	public static AAPIFeedwatchlist create() {

		AAPIFeedwatchlist v = new AAPIFeedwatchlist();

		return v;
	}

	private AAPIFeedwatchlist() {}

	private AAPIFeedwatchlistFeedformat feedformat;

	private Long hours;

	private Boolean linktosections;

	private Boolean allrev;

	private String wlowner;

	private String wltoken;

	private List<AAPIFeedwatchlistWlshow> wlshow;

	private List<AAPIFeedwatchlistWltype> wltype;

	private String wlexcludeuser;

	/**The format of the feed.
	 */
	public AAPIFeedwatchlist feedformat(AAPIFeedwatchlistFeedformat feedformat) {

		this.feedformat = feedformat;

		return this;
	}

	/**The format of the feed.
	 */
	public AAPIFeedwatchlistFeedformat getFeedformat() {
		return this.feedformat;
	}

	/**List pages modified within this many hours from now.
	 */
	public AAPIFeedwatchlist hours(Long hours) {

		this.hours = hours;

		return this;
	}

	/**List pages modified within this many hours from now.
	 */
	public Long getHours() {
		return this.hours;
	}

	/**Link directly to changed sections if possible.
	 */
	public AAPIFeedwatchlist linktosections(Boolean linktosections) {

		this.linktosections = linktosections;

		return this;
	}

	/**Link directly to changed sections if possible.
	 */
	public Boolean getLinktosections() {
		return this.linktosections;
	}

	/**Include multiple revisions of the same page within given timeframe.
	 */
	public AAPIFeedwatchlist allrev(Boolean allrev) {

		this.allrev = allrev;

		return this;
	}

	/**Include multiple revisions of the same page within given timeframe.
	 */
	public Boolean getAllrev() {
		return this.allrev;
	}

	/**Used along with token to access a different user's watchlist.
	 */
	public AAPIFeedwatchlist wlowner(String wlowner) {

		this.wlowner = wlowner;

		return this;
	}

	/**Used along with token to access a different user's watchlist.
	 */
	public String getWlowner() {
		return this.wlowner;
	}

	/**A security token (available in the user's <a href="/wiki/Special:Preferences#mw-prefsection-watchlist" title="Special:Preferences">preferences</a>) to allow access to another user's watchlist.
	 */
	public AAPIFeedwatchlist wltoken(String wltoken) {

		this.wltoken = wltoken;

		return this;
	}

	/**A security token (available in the user's <a href="/wiki/Special:Preferences#mw-prefsection-watchlist" title="Special:Preferences">preferences</a>) to allow access to another user's watchlist.
	 */
	public String getWltoken() {
		return this.wltoken;
	}

	/**Show only items that meet these criteria. For example, to see only minor edits done by logged-in users, set show=minor|!anon.
	 */
	public AAPIFeedwatchlist wlshow(AAPIFeedwatchlistWlshow... wlshow) {

		this.wlshow = List.of(wlshow);

		return this;
	}

	/**Show only items that meet these criteria. For example, to see only minor edits done by logged-in users, set show=minor|!anon.
	 */
	public List<AAPIFeedwatchlistWlshow> getWlshow() {
		return this.wlshow;
	}

	/**<p>Which types of changes to show:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIFeedwatchlist wltype(AAPIFeedwatchlistWltype... wltype) {

		this.wltype = List.of(wltype);

		return this;
	}

	/**<p>Which types of changes to show:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIFeedwatchlistWltype> getWltype() {
		return this.wltype;
	}

	/**Don't list changes by this user.
	 */
	public AAPIFeedwatchlist wlexcludeuser(String wlexcludeuser) {

		this.wlexcludeuser = wlexcludeuser;

		return this;
	}

	/**Don't list changes by this user.
	 */
	public String getWlexcludeuser() {
		return this.wlexcludeuser;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIFeedwatchlist(");

		if (feedformat != null) {

			sb.append("feedformat").append("=").append(feedformat.getJsonValue());

			sb.append(", ");
		}

		if (hours != null) {

			sb.append("hours").append("=").append(hours.toString());

			sb.append(", ");
		}

		if (linktosections != null) {

			sb.append("linktosections").append("=").append(linktosections.toString());

			sb.append(", ");
		}

		if (allrev != null) {

			sb.append("allrev").append("=").append(allrev.toString());

			sb.append(", ");
		}

		if (wlowner != null) {

			sb.append("wlowner").append("=").append(wlowner);

			sb.append(", ");
		}

		if (wltoken != null) {

			sb.append("wltoken").append("=").append(wltoken);

			sb.append(", ");
		}

		if (wlshow != null) {

			sb.append("wlshow")
					.append("=")
					.append(
							wlshow.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (wltype != null) {

			sb.append("wltype")
					.append("=")
					.append(
							wltype.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (wlexcludeuser != null) {

			sb.append("wlexcludeuser").append("=").append(wlexcludeuser);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (feedformat != null) {

			req.addParameter(paramPrefix + "feedformat", feedformat.getJsonValue());
		}

		if (hours != null) {

			req.addParameter(paramPrefix + "hours", hours.toString());
		}

		if (linktosections != null) {

			req.addParameter(paramPrefix + "linktosections", linktosections.toString());
		}

		if (allrev != null) {

			req.addParameter(paramPrefix + "allrev", allrev.toString());
		}

		if (wlowner != null) {

			req.addParameter(paramPrefix + "wlowner", wlowner);
		}

		if (wltoken != null) {

			req.addParameter(paramPrefix + "wltoken", wltoken);
		}

		if (wlshow != null) {

			req.addParameter(
					paramPrefix + "wlshow",
					wlshow.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (wltype != null) {

			req.addParameter(
					paramPrefix + "wltype",
					wltype.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (wlexcludeuser != null) {

			req.addParameter(paramPrefix + "wlexcludeuser", wlexcludeuser);
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
			c.accept(AAPIFeedwatchlist.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIFeedwatchlist.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}

		@Override
		protected boolean internalRequiresPagination() {
			return true;
		}
	}
}
