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

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Patrol a page or revision.
 */
public class AAPIPatrol implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIPatrol create() {

		AAPIPatrol v = new AAPIPatrol();

		return v;
	}

	private AAPIPatrol() {}

	private Long rcid;

	private Long revid;

	private List<String> tags;

	private String token;

	/**Recentchanges ID to patrol.
	 */
	public AAPIPatrol rcid(Long rcid) {
		this.rcid = rcid;

		return this;
	}

	/**Recentchanges ID to patrol.
	 */
	public Long getRcid() {
		return this.rcid;
	}

	/**Revision ID to patrol.
	 */
	public AAPIPatrol revid(Long revid) {
		this.revid = revid;

		return this;
	}

	/**Revision ID to patrol.
	 */
	public Long getRevid() {
		return this.revid;
	}

	/**Change tags to apply to the entry in the patrol log.
	 */
	public AAPIPatrol tags(String tags) {
		this.tags = List.of(tags);

		return this;
	}

	/**Change tags to apply to the entry in the patrol log.
	 */
	public AAPIPatrol tags(String... tags) {
		this.tags = List.of(tags);
		return this;
	}

	/**Change tags to apply to the entry in the patrol log.
	 */
	public List<String> getTags() {
		return this.tags;
	}

	/**A "patrol" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIPatrol token(String token) {
		this.token = token;

		return this;
	}

	/**A "patrol" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIPatrol(");

		if (rcid != null) {

			sb.append("rcid").append("=").append(rcid.toString());

			sb.append(", ");
		}

		if (revid != null) {

			sb.append("revid").append("=").append(revid.toString());

			sb.append(", ");
		}

		if (tags != null) {

			sb.append("tags")
					.append("=")
					.append(tags.stream().map(v -> v).collect(Collectors.joining("|")));

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

		if (rcid != null) {

			req.addParameter(paramPrefix + "rcid", rcid.toString());
		}

		if (revid != null) {

			req.addParameter(paramPrefix + "revid", revid.toString());
		}

		if (tags != null) {

			req.addParameter(
					paramPrefix + "tags",
					tags.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		token = api.requestToken(AAPIQueryTokensType.PATROL);

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
			c.accept(AAPIPatrol.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIPatrol.this);

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
