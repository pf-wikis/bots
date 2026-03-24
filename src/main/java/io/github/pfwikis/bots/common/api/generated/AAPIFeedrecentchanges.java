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

import io.github.pfwikis.bots.common.api.generated.params.AAPIFeedrecentchangesFeedformat;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Returns a recent changes feed.
 */
public class AAPIFeedrecentchanges implements AAPIModule, AAPIMainActionModule {

	public static AAPIFeedrecentchanges create() {

		AAPIFeedrecentchanges v = new AAPIFeedrecentchanges();

		return v;
	}

	private AAPIFeedrecentchanges() {}

	private AAPIFeedrecentchangesFeedformat feedformat;

	private NS namespace;

	private Boolean invert;

	private Boolean associated;

	private Long days;

	private Long limit;

	private Boolean hideminor;

	private Boolean hidebots;

	private Boolean hideanons;

	private Boolean hideliu;

	private Boolean hidepatrolled;

	private Boolean hidemyself;

	private Boolean hidecategorization;

	private String tagfilter;

	private Boolean inverttags;

	private String target;

	private Boolean showlinkedto;

	/**The format of the feed.
	 */
	public AAPIFeedrecentchanges feedformat(AAPIFeedrecentchangesFeedformat feedformat) {
		this.feedformat = feedformat;

		return this;
	}

	/**The format of the feed.
	 */
	public AAPIFeedrecentchangesFeedformat getFeedformat() {
		return this.feedformat;
	}

	/**Namespace to limit the results to.
	 */
	public AAPIFeedrecentchanges namespace(NS namespace) {
		this.namespace = namespace;

		return this;
	}

	/**Namespace to limit the results to.
	 */
	public NS getNamespace() {
		return this.namespace;
	}

	/**All namespaces but the selected one.
	 */
	public AAPIFeedrecentchanges invert(Boolean invert) {
		this.invert = invert;

		return this;
	}

	/**All namespaces but the selected one.
	 */
	public Boolean getInvert() {
		return this.invert;
	}

	/**Include associated (talk or main) namespace.
	 */
	public AAPIFeedrecentchanges associated(Boolean associated) {
		this.associated = associated;

		return this;
	}

	/**Include associated (talk or main) namespace.
	 */
	public Boolean getAssociated() {
		return this.associated;
	}

	/**Days to limit the results to.
	 */
	public AAPIFeedrecentchanges days(Long days) {
		this.days = days;

		return this;
	}

	/**Days to limit the results to.
	 */
	public Long getDays() {
		return this.days;
	}

	/**Maximum number of results to return.
	 */
	public AAPIFeedrecentchanges limit(Long limit) {
		this.limit = limit;

		return this;
	}

	/**Maximum number of results to return.
	 */
	public Long getLimit() {
		return this.limit;
	}

	/**Hide minor changes.
	 */
	public AAPIFeedrecentchanges hideminor(Boolean hideminor) {
		this.hideminor = hideminor;

		return this;
	}

	/**Hide minor changes.
	 */
	public Boolean getHideminor() {
		return this.hideminor;
	}

	/**Hide changes made by bots.
	 */
	public AAPIFeedrecentchanges hidebots(Boolean hidebots) {
		this.hidebots = hidebots;

		return this;
	}

	/**Hide changes made by bots.
	 */
	public Boolean getHidebots() {
		return this.hidebots;
	}

	/**Hide changes made by anonymous users.
	 */
	public AAPIFeedrecentchanges hideanons(Boolean hideanons) {
		this.hideanons = hideanons;

		return this;
	}

	/**Hide changes made by anonymous users.
	 */
	public Boolean getHideanons() {
		return this.hideanons;
	}

	/**Hide changes made by registered users.
	 */
	public AAPIFeedrecentchanges hideliu(Boolean hideliu) {
		this.hideliu = hideliu;

		return this;
	}

	/**Hide changes made by registered users.
	 */
	public Boolean getHideliu() {
		return this.hideliu;
	}

	/**Hide patrolled changes.
	 */
	public AAPIFeedrecentchanges hidepatrolled(Boolean hidepatrolled) {
		this.hidepatrolled = hidepatrolled;

		return this;
	}

	/**Hide patrolled changes.
	 */
	public Boolean getHidepatrolled() {
		return this.hidepatrolled;
	}

	/**Hide changes made by the current user.
	 */
	public AAPIFeedrecentchanges hidemyself(Boolean hidemyself) {
		this.hidemyself = hidemyself;

		return this;
	}

	/**Hide changes made by the current user.
	 */
	public Boolean getHidemyself() {
		return this.hidemyself;
	}

	/**Hide category membership changes.
	 */
	public AAPIFeedrecentchanges hidecategorization(Boolean hidecategorization) {
		this.hidecategorization = hidecategorization;

		return this;
	}

	/**Hide category membership changes.
	 */
	public Boolean getHidecategorization() {
		return this.hidecategorization;
	}

	/**Filter by tag.
	 */
	public AAPIFeedrecentchanges tagfilter(String tagfilter) {
		this.tagfilter = tagfilter;

		return this;
	}

	/**Filter by tag.
	 */
	public String getTagfilter() {
		return this.tagfilter;
	}

	/**All edits except ones tagged with the selected ones.
	 */
	public AAPIFeedrecentchanges inverttags(Boolean inverttags) {
		this.inverttags = inverttags;

		return this;
	}

	/**All edits except ones tagged with the selected ones.
	 */
	public Boolean getInverttags() {
		return this.inverttags;
	}

	/**Show only changes on pages linked from this page.
	 */
	public AAPIFeedrecentchanges target(String target) {
		this.target = target;

		return this;
	}

	/**Show only changes on pages linked from this page.
	 */
	public String getTarget() {
		return this.target;
	}

	/**Show changes on pages linked to the selected page instead.
	 */
	public AAPIFeedrecentchanges showlinkedto(Boolean showlinkedto) {
		this.showlinkedto = showlinkedto;

		return this;
	}

	/**Show changes on pages linked to the selected page instead.
	 */
	public Boolean getShowlinkedto() {
		return this.showlinkedto;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIFeedrecentchanges(");

		if (feedformat != null) {

			sb.append("feedformat").append("=").append(feedformat.getJsonValue());

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("namespace").append("=").append(Integer.toString(namespace.getId()));

			sb.append(", ");
		}

		if (invert != null) {

			sb.append("invert").append("=").append(invert.toString());

			sb.append(", ");
		}

		if (associated != null) {

			sb.append("associated").append("=").append(associated.toString());

			sb.append(", ");
		}

		if (days != null) {

			sb.append("days").append("=").append(days.toString());

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("limit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (hideminor != null) {

			sb.append("hideminor").append("=").append(hideminor.toString());

			sb.append(", ");
		}

		if (hidebots != null) {

			sb.append("hidebots").append("=").append(hidebots.toString());

			sb.append(", ");
		}

		if (hideanons != null) {

			sb.append("hideanons").append("=").append(hideanons.toString());

			sb.append(", ");
		}

		if (hideliu != null) {

			sb.append("hideliu").append("=").append(hideliu.toString());

			sb.append(", ");
		}

		if (hidepatrolled != null) {

			sb.append("hidepatrolled").append("=").append(hidepatrolled.toString());

			sb.append(", ");
		}

		if (hidemyself != null) {

			sb.append("hidemyself").append("=").append(hidemyself.toString());

			sb.append(", ");
		}

		if (hidecategorization != null) {

			sb.append("hidecategorization").append("=").append(hidecategorization.toString());

			sb.append(", ");
		}

		if (tagfilter != null) {

			sb.append("tagfilter").append("=").append(tagfilter);

			sb.append(", ");
		}

		if (inverttags != null) {

			sb.append("inverttags").append("=").append(inverttags.toString());

			sb.append(", ");
		}

		if (target != null) {

			sb.append("target").append("=").append(target);

			sb.append(", ");
		}

		if (showlinkedto != null) {

			sb.append("showlinkedto").append("=").append(showlinkedto.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (feedformat != null) {

			req.addParameter(paramPrefix + "feedformat", feedformat.getJsonValue());
		}

		if (namespace != null) {

			req.addParameter(paramPrefix + "namespace", Integer.toString(namespace.getId()));
		}

		if (invert != null) {

			req.addParameter(paramPrefix + "invert", invert.toString());
		}

		if (associated != null) {

			req.addParameter(paramPrefix + "associated", associated.toString());
		}

		if (days != null) {

			req.addParameter(paramPrefix + "days", days.toString());
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "limit", limit.toString());
		}

		if (hideminor != null) {

			req.addParameter(paramPrefix + "hideminor", hideminor.toString());
		}

		if (hidebots != null) {

			req.addParameter(paramPrefix + "hidebots", hidebots.toString());
		}

		if (hideanons != null) {

			req.addParameter(paramPrefix + "hideanons", hideanons.toString());
		}

		if (hideliu != null) {

			req.addParameter(paramPrefix + "hideliu", hideliu.toString());
		}

		if (hidepatrolled != null) {

			req.addParameter(paramPrefix + "hidepatrolled", hidepatrolled.toString());
		}

		if (hidemyself != null) {

			req.addParameter(paramPrefix + "hidemyself", hidemyself.toString());
		}

		if (hidecategorization != null) {

			req.addParameter(paramPrefix + "hidecategorization", hidecategorization.toString());
		}

		if (tagfilter != null) {

			req.addParameter(paramPrefix + "tagfilter", tagfilter);
		}

		if (inverttags != null) {

			req.addParameter(paramPrefix + "inverttags", inverttags.toString());
		}

		if (target != null) {

			req.addParameter(paramPrefix + "target", target);
		}

		if (showlinkedto != null) {

			req.addParameter(paramPrefix + "showlinkedto", showlinkedto.toString());
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
			c.accept(AAPIFeedrecentchanges.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIFeedrecentchanges.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}

		@Override
		protected boolean internalRequiresPagination() {
			return limit == null;
		}
	}
}
