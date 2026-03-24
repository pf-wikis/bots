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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryWatchlistrawProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryWatchlistrawShow;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryWatchlistrawDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** Get all pages on the current user's watchlist.
 */
public class AAPIQueryWatchlistraw
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryWatchlistraw create() {

		AAPIQueryWatchlistraw v = new AAPIQueryWatchlistraw();

		return v;
	}

	private AAPIQueryWatchlistraw() {}

	private List<NS> namespace;

	private Integer limit;

	private List<AAPIQueryWatchlistrawProp> prop;

	private List<AAPIQueryWatchlistrawShow> show;

	private String owner;

	private String token;

	private AAPIQueryWatchlistrawDir dir;

	private String fromtitle;

	private String totitle;

	/**Only list pages in the given namespaces.
	 */
	public AAPIQueryWatchlistraw namespace(NS namespace) {
		this.namespace = List.of(namespace);

		return this;
	}

	/**Only list pages in the given namespaces.
	 */
	public AAPIQueryWatchlistraw namespace(NS... namespace) {
		this.namespace = List.of(namespace);
		return this;
	}

	/**Only list pages in the given namespaces.
	 */
	public List<NS> getNamespace() {
		return this.namespace;
	}

	/**How many total results to return per request.
	 */
	public AAPIQueryWatchlistraw limit(Integer limit) {
		this.limit = limit;

		return this;
	}

	/**How many total results to return per request.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**<p>Which additional properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryWatchlistraw prop(AAPIQueryWatchlistrawProp prop) {
		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which additional properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryWatchlistraw prop(AAPIQueryWatchlistrawProp... prop) {
		this.prop = List.of(prop);
		return this;
	}

	/**<p>Which additional properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryWatchlistrawProp> getProp() {
		return this.prop;
	}

	/**Only list items that meet these criteria.
	 */
	public AAPIQueryWatchlistraw show(AAPIQueryWatchlistrawShow show) {
		this.show = List.of(show);

		return this;
	}

	/**Only list items that meet these criteria.
	 */
	public AAPIQueryWatchlistraw show(AAPIQueryWatchlistrawShow... show) {
		this.show = List.of(show);
		return this;
	}

	/**Only list items that meet these criteria.
	 */
	public List<AAPIQueryWatchlistrawShow> getShow() {
		return this.show;
	}

	/**Used along with wrtoken to access a different user's watchlist.
	 */
	public AAPIQueryWatchlistraw owner(String owner) {
		this.owner = owner;

		return this;
	}

	/**Used along with wrtoken to access a different user's watchlist.
	 */
	public String getOwner() {
		return this.owner;
	}

	/**A security token (available in the user's <a href="/wiki/Special:Preferences#mw-prefsection-watchlist" title="Special:Preferences">preferences</a>) to allow access to another user's watchlist.
	 */
	public AAPIQueryWatchlistraw token(String token) {
		this.token = token;

		return this;
	}

	/**A security token (available in the user's <a href="/wiki/Special:Preferences#mw-prefsection-watchlist" title="Special:Preferences">preferences</a>) to allow access to another user's watchlist.
	 */
	public String getToken() {
		return this.token;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryWatchlistraw dir(AAPIQueryWatchlistrawDir dir) {
		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryWatchlistrawDir getDir() {
		return this.dir;
	}

	/**Title (with namespace prefix) to begin enumerating from.
	 */
	public AAPIQueryWatchlistraw fromtitle(String fromtitle) {
		this.fromtitle = fromtitle;

		return this;
	}

	/**Title (with namespace prefix) to begin enumerating from.
	 */
	public String getFromtitle() {
		return this.fromtitle;
	}

	/**Title (with namespace prefix) to stop enumerating at.
	 */
	public AAPIQueryWatchlistraw totitle(String totitle) {
		this.totitle = totitle;

		return this;
	}

	/**Title (with namespace prefix) to stop enumerating at.
	 */
	public String getTotitle() {
		return this.totitle;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryWatchlistraw(");

		if (namespace != null) {

			sb.append("wrnamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("wrlimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("wrprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (show != null) {

			sb.append("wrshow")
					.append("=")
					.append(
							show.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (owner != null) {

			sb.append("wrowner").append("=").append(owner);

			sb.append(", ");
		}

		if (token != null) {

			sb.append("wrtoken").append("=").append(token);

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("wrdir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (fromtitle != null) {

			sb.append("wrfromtitle").append("=").append(fromtitle);

			sb.append(", ");
		}

		if (totitle != null) {

			sb.append("wrtotitle").append("=").append(totitle);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "wrnamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "wrlimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "wrlimit", "5000");
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "wrprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (show != null) {

			req.addParameter(
					paramPrefix + "wrshow",
					show.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (owner != null) {

			req.addParameter(paramPrefix + "wrowner", owner);
		}

		if (token != null) {

			req.addParameter(paramPrefix + "wrtoken", token);
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "wrdir", dir.getJsonValue());
		}

		if (fromtitle != null) {

			req.addParameter(paramPrefix + "wrfromtitle", fromtitle);
		}

		if (totitle != null) {

			req.addParameter(paramPrefix + "wrtotitle", totitle);
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
			c.accept(AAPIQueryWatchlistraw.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryWatchlistraw.this);

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
