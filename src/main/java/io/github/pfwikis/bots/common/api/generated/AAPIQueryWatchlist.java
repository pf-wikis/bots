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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryWatchlistDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryWatchlistProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryWatchlistShow;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryWatchlistType;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** Get recent changes to pages in the current user's watchlist.
 */
public class AAPIQueryWatchlist
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryWatchlist create() {

		AAPIQueryWatchlist v = new AAPIQueryWatchlist();

		return v;
	}

	private AAPIQueryWatchlist() {}

	private Boolean allrev;

	private java.time.Instant start;

	private java.time.Instant end;

	private List<NS> namespace;

	private String user;

	private String excludeuser;

	private AAPIQueryWatchlistDir dir;

	private Integer limit = 5000;

	private List<AAPIQueryWatchlistProp> prop;

	private List<AAPIQueryWatchlistShow> show;

	private List<AAPIQueryWatchlistType> type;

	private String owner;

	private String token;

	/**Include multiple revisions of the same page within given timeframe.
	 */
	public AAPIQueryWatchlist allrev(Boolean allrev) {

		this.allrev = allrev;

		return this;
	}

	/**Include multiple revisions of the same page within given timeframe.
	 */
	public Boolean getAllrev() {
		return this.allrev;
	}

	/**The timestamp to start enumerating from.
	 */
	public AAPIQueryWatchlist start(java.time.Instant start) {

		this.start = start;

		return this;
	}

	/**The timestamp to start enumerating from.
	 */
	public java.time.Instant getStart() {
		return this.start;
	}

	/**The timestamp to end enumerating.
	 */
	public AAPIQueryWatchlist end(java.time.Instant end) {

		this.end = end;

		return this;
	}

	/**The timestamp to end enumerating.
	 */
	public java.time.Instant getEnd() {
		return this.end;
	}

	/**Filter changes to only the given namespaces.
	 */
	public AAPIQueryWatchlist namespace(NS... namespace) {

		this.namespace = List.of(namespace);

		return this;
	}

	/**Filter changes to only the given namespaces.
	 */
	public List<NS> getNamespace() {
		return this.namespace;
	}

	/**Only list changes by this user.
	 */
	public AAPIQueryWatchlist user(String user) {

		this.user = user;

		return this;
	}

	/**Only list changes by this user.
	 */
	public String getUser() {
		return this.user;
	}

	/**Don't list changes by this user.
	 */
	public AAPIQueryWatchlist excludeuser(String excludeuser) {

		this.excludeuser = excludeuser;

		return this;
	}

	/**Don't list changes by this user.
	 */
	public String getExcludeuser() {
		return this.excludeuser;
	}

	/**<p>In which direction to enumerate:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryWatchlist dir(AAPIQueryWatchlistDir dir) {

		this.dir = dir;

		return this;
	}

	/**<p>In which direction to enumerate:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryWatchlistDir getDir() {
		return this.dir;
	}

	/**How many total results to return per request.
	 */
	public AAPIQueryWatchlist limit(Integer limit) {

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
	public AAPIQueryWatchlist prop(AAPIQueryWatchlistProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which additional properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryWatchlistProp> getProp() {
		return this.prop;
	}

	/**Show only items that meet these criteria. For example, to see only minor edits done by logged-in users, set wlshow=minor|!anon.
	 */
	public AAPIQueryWatchlist show(AAPIQueryWatchlistShow... show) {

		this.show = List.of(show);

		return this;
	}

	/**Show only items that meet these criteria. For example, to see only minor edits done by logged-in users, set wlshow=minor|!anon.
	 */
	public List<AAPIQueryWatchlistShow> getShow() {
		return this.show;
	}

	/**<p>Which types of changes to show:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryWatchlist type(AAPIQueryWatchlistType... type) {

		this.type = List.of(type);

		return this;
	}

	/**<p>Which types of changes to show:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryWatchlistType> getType() {
		return this.type;
	}

	/**Used along with wltoken to access a different user's watchlist.
	 */
	public AAPIQueryWatchlist owner(String owner) {

		this.owner = owner;

		return this;
	}

	/**Used along with wltoken to access a different user's watchlist.
	 */
	public String getOwner() {
		return this.owner;
	}

	/**A security token (available in the user's <a href="/wiki/Special:Preferences#mw-prefsection-watchlist" title="Special:Preferences">preferences</a>) to allow access to another user's watchlist.
	 */
	public AAPIQueryWatchlist token(String token) {

		this.token = token;

		return this;
	}

	/**A security token (available in the user's <a href="/wiki/Special:Preferences#mw-prefsection-watchlist" title="Special:Preferences">preferences</a>) to allow access to another user's watchlist.
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryWatchlist(");

		if (allrev != null) {

			sb.append("wlallrev").append("=").append(allrev.toString());

			sb.append(", ");
		}

		if (start != null) {

			sb.append("wlstart")
					.append("=")
					.append(start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (end != null) {

			sb.append("wlend")
					.append("=")
					.append(end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("wlnamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (user != null) {

			sb.append("wluser").append("=").append(user);

			sb.append(", ");
		}

		if (excludeuser != null) {

			sb.append("wlexcludeuser").append("=").append(excludeuser);

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("wldir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("wllimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("wlprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (show != null) {

			sb.append("wlshow")
					.append("=")
					.append(
							show.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (type != null) {

			sb.append("wltype")
					.append("=")
					.append(
							type.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (owner != null) {

			sb.append("wlowner").append("=").append(owner);

			sb.append(", ");
		}

		if (token != null) {

			sb.append("wltoken").append("=").append(token);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (allrev != null) {

			req.addParameter(paramPrefix + "wlallrev", allrev.toString());
		}

		if (start != null) {

			req.addParameter(
					paramPrefix + "wlstart",
					start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (end != null) {

			req.addParameter(
					paramPrefix + "wlend",
					end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "wlnamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (user != null) {

			req.addParameter(paramPrefix + "wluser", user);
		}

		if (excludeuser != null) {

			req.addParameter(paramPrefix + "wlexcludeuser", excludeuser);
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "wldir", dir.getJsonValue());
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "wllimit", limit.toString());
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "wlprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (show != null) {

			req.addParameter(
					paramPrefix + "wlshow",
					show.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (type != null) {

			req.addParameter(
					paramPrefix + "wltype",
					type.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (owner != null) {

			req.addParameter(paramPrefix + "wlowner", owner);
		}

		if (token != null) {

			req.addParameter(paramPrefix + "wltoken", token);
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
			c.accept(AAPIQueryWatchlist.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryWatchlist.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
