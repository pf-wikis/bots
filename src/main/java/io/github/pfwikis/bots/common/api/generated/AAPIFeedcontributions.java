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

import io.github.pfwikis.bots.common.api.generated.params.AAPIFeedcontributionsFeedformat;

import io.github.pfwikis.bots.common.api.generated.params.AAPIFeedcontributionsTagfilter;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Returns a user's contributions feed.
 */
public class AAPIFeedcontributions implements AAPIModule, AAPIMainActionModule {

	public static AAPIFeedcontributions create(@NonNull String user) {

		AAPIFeedcontributions v = new AAPIFeedcontributions();

		v.user = user;

		return v;
	}

	private AAPIFeedcontributions() {}

	private AAPIFeedcontributionsFeedformat feedformat;

	private String user;

	private NS namespace;

	private Integer year;

	private Integer month;

	private List<AAPIFeedcontributionsTagfilter> tagfilter;

	private Boolean deletedonly;

	private Boolean toponly;

	private Boolean newonly;

	private Boolean hideminor;

	private Boolean showsizediff;

	/**The format of the feed.
	 */
	public AAPIFeedcontributions feedformat(AAPIFeedcontributionsFeedformat feedformat) {

		this.feedformat = feedformat;

		return this;
	}

	/**The format of the feed.
	 */
	public AAPIFeedcontributionsFeedformat getFeedformat() {
		return this.feedformat;
	}

	/**What users to get the contributions for.
	 */
	public String getUser() {
		return this.user;
	}

	/**Which namespace to filter the contributions by.
	 */
	public AAPIFeedcontributions namespace(NS namespace) {

		this.namespace = namespace;

		return this;
	}

	/**Which namespace to filter the contributions by.
	 */
	public NS getNamespace() {
		return this.namespace;
	}

	/**From year (and earlier).
	 */
	public AAPIFeedcontributions year(Integer year) {

		this.year = year;

		return this;
	}

	/**From year (and earlier).
	 */
	public Integer getYear() {
		return this.year;
	}

	/**From month (and earlier).
	 */
	public AAPIFeedcontributions month(Integer month) {

		this.month = month;

		return this;
	}

	/**From month (and earlier).
	 */
	public Integer getMonth() {
		return this.month;
	}

	/**Filter contributions that have these tags.
	 */
	public AAPIFeedcontributions tagfilter(AAPIFeedcontributionsTagfilter... tagfilter) {

		this.tagfilter = List.of(tagfilter);

		return this;
	}

	/**Filter contributions that have these tags.
	 */
	public List<AAPIFeedcontributionsTagfilter> getTagfilter() {
		return this.tagfilter;
	}

	/**Show only deleted contributions.
	 */
	public AAPIFeedcontributions deletedonly(Boolean deletedonly) {

		this.deletedonly = deletedonly;

		return this;
	}

	/**Show only deleted contributions.
	 */
	public Boolean getDeletedonly() {
		return this.deletedonly;
	}

	/**Only show edits that are the latest revisions.
	 */
	public AAPIFeedcontributions toponly(Boolean toponly) {

		this.toponly = toponly;

		return this;
	}

	/**Only show edits that are the latest revisions.
	 */
	public Boolean getToponly() {
		return this.toponly;
	}

	/**Only show edits that are page creations.
	 */
	public AAPIFeedcontributions newonly(Boolean newonly) {

		this.newonly = newonly;

		return this;
	}

	/**Only show edits that are page creations.
	 */
	public Boolean getNewonly() {
		return this.newonly;
	}

	/**Hide minor edits.
	 */
	public AAPIFeedcontributions hideminor(Boolean hideminor) {

		this.hideminor = hideminor;

		return this;
	}

	/**Hide minor edits.
	 */
	public Boolean getHideminor() {
		return this.hideminor;
	}

	/**Show the size difference between revisions.
	 */
	public AAPIFeedcontributions showsizediff(Boolean showsizediff) {

		this.showsizediff = showsizediff;

		return this;
	}

	/**Show the size difference between revisions.
	 */
	public Boolean getShowsizediff() {
		return this.showsizediff;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIFeedcontributions(");

		if (feedformat != null) {

			sb.append("feedformat").append("=").append(feedformat.getJsonValue());

			sb.append(", ");
		}

		if (user != null) {

			sb.append("user").append("=").append(user);

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("namespace").append("=").append(Integer.toString(namespace.getId()));

			sb.append(", ");
		}

		if (year != null) {

			sb.append("year").append("=").append(year.toString());

			sb.append(", ");
		}

		if (month != null) {

			sb.append("month").append("=").append(month.toString());

			sb.append(", ");
		}

		if (tagfilter != null) {

			sb.append("tagfilter")
					.append("=")
					.append(
							tagfilter.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (deletedonly != null) {

			sb.append("deletedonly").append("=").append(deletedonly.toString());

			sb.append(", ");
		}

		if (toponly != null) {

			sb.append("toponly").append("=").append(toponly.toString());

			sb.append(", ");
		}

		if (newonly != null) {

			sb.append("newonly").append("=").append(newonly.toString());

			sb.append(", ");
		}

		if (hideminor != null) {

			sb.append("hideminor").append("=").append(hideminor.toString());

			sb.append(", ");
		}

		if (showsizediff != null) {

			sb.append("showsizediff").append("=").append(showsizediff.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (feedformat != null) {

			req.addParameter(paramPrefix + "feedformat", feedformat.getJsonValue());
		}

		if (user != null) {

			req.addParameter(paramPrefix + "user", user);
		}

		if (namespace != null) {

			req.addParameter(paramPrefix + "namespace", Integer.toString(namespace.getId()));
		}

		if (year != null) {

			req.addParameter(paramPrefix + "year", year.toString());
		}

		if (month != null) {

			req.addParameter(paramPrefix + "month", month.toString());
		}

		if (tagfilter != null) {

			req.addParameter(
					paramPrefix + "tagfilter",
					tagfilter.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (deletedonly != null) {

			req.addParameter(paramPrefix + "deletedonly", deletedonly.toString());
		}

		if (toponly != null) {

			req.addParameter(paramPrefix + "toponly", toponly.toString());
		}

		if (newonly != null) {

			req.addParameter(paramPrefix + "newonly", newonly.toString());
		}

		if (hideminor != null) {

			req.addParameter(paramPrefix + "hideminor", hideminor.toString());
		}

		if (showsizediff != null) {

			req.addParameter(paramPrefix + "showsizediff", showsizediff.toString());
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
			c.accept(AAPIFeedcontributions.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIFeedcontributions.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
