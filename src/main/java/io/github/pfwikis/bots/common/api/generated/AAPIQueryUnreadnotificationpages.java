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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryMeta.AAPIQueryMetaModule;

/** Get pages for which there are unread notifications for the current user.
 */
public class AAPIQueryUnreadnotificationpages implements AAPIModule, AAPIQueryMetaModule {

	public static AAPIQueryUnreadnotificationpages create() {

		AAPIQueryUnreadnotificationpages v = new AAPIQueryUnreadnotificationpages();

		return v;
	}

	private AAPIQueryUnreadnotificationpages() {}

	private Boolean grouppages;

	private Integer limit;

	/**Group talk pages together with their subject page, and group notifications not associated with a page together with the current user's user page.
	 */
	public AAPIQueryUnreadnotificationpages grouppages(Boolean grouppages) {

		this.grouppages = grouppages;

		return this;
	}

	/**Group talk pages together with their subject page, and group notifications not associated with a page together with the current user's user page.
	 */
	public Boolean getGrouppages() {
		return this.grouppages;
	}

	/**The maximum number of pages to return.
	 */
	public AAPIQueryUnreadnotificationpages limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**The maximum number of pages to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryUnreadnotificationpages(");

		if (grouppages != null) {

			sb.append("unpgrouppages").append("=").append(grouppages.toString());

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("unplimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (grouppages != null) {

			req.addParameter(paramPrefix + "unpgrouppages", grouppages.toString());
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "unplimit", limit.toString());
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
			c.accept(AAPIQueryUnreadnotificationpages.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryUnreadnotificationpages.this);

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
