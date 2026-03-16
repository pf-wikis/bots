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

/** Add or remove change tags from individual revisions or log entries.
 */
public class AAPITag implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPITag create() {

		AAPITag v = new AAPITag();

		return v;
	}

	private AAPITag() {}

	private List<Integer> rcid;

	private List<Integer> revid;

	private List<Integer> logid;

	private List<String> add;

	private List<String> remove;

	private String reason;

	private List<String> tags;

	private String token;

	/**One or more recent changes IDs from which to add or remove the tag.
	 */
	public AAPITag rcid(Integer... rcid) {

		this.rcid = List.of(rcid);

		return this;
	}

	/**One or more recent changes IDs from which to add or remove the tag.
	 */
	public List<Integer> getRcid() {
		return this.rcid;
	}

	/**One or more revision IDs from which to add or remove the tag.
	 */
	public AAPITag revid(Integer... revid) {

		this.revid = List.of(revid);

		return this;
	}

	/**One or more revision IDs from which to add or remove the tag.
	 */
	public List<Integer> getRevid() {
		return this.revid;
	}

	/**One or more log entry IDs from which to add or remove the tag.
	 */
	public AAPITag logid(Integer... logid) {

		this.logid = List.of(logid);

		return this;
	}

	/**One or more log entry IDs from which to add or remove the tag.
	 */
	public List<Integer> getLogid() {
		return this.logid;
	}

	/**Tags to add. Only manually defined tags can be added.
	 */
	public AAPITag add(String... add) {

		this.add = List.of(add);

		return this;
	}

	/**Tags to add. Only manually defined tags can be added.
	 */
	public List<String> getAdd() {
		return this.add;
	}

	/**Tags to remove. Only tags that are either manually defined or completely undefined can be removed.
	 */
	public AAPITag remove(String... remove) {

		this.remove = List.of(remove);

		return this;
	}

	/**Tags to remove. Only tags that are either manually defined or completely undefined can be removed.
	 */
	public List<String> getRemove() {
		return this.remove;
	}

	/**Reason for the change.
	 */
	public AAPITag reason(String reason) {

		this.reason = reason;

		return this;
	}

	/**Reason for the change.
	 */
	public String getReason() {
		return this.reason;
	}

	/**Tags to apply to the log entry that will be created as a result of this action.
	 */
	public AAPITag tags(String... tags) {

		this.tags = List.of(tags);

		return this;
	}

	/**Tags to apply to the log entry that will be created as a result of this action.
	 */
	public List<String> getTags() {
		return this.tags;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPITag token(String token) {

		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPITag(");

		if (rcid != null) {

			sb.append("rcid")
					.append("=")
					.append(rcid.stream().map(v -> v.toString()).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (revid != null) {

			sb.append("revid")
					.append("=")
					.append(revid.stream().map(v -> v.toString()).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (logid != null) {

			sb.append("logid")
					.append("=")
					.append(logid.stream().map(v -> v.toString()).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (add != null) {

			sb.append("add")
					.append("=")
					.append(add.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (remove != null) {

			sb.append("remove")
					.append("=")
					.append(remove.stream().map(v -> v).collect(Collectors.joining("|")));

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

		if (token != null) {

			sb.append("token").append("=").append(token);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (rcid != null) {

			req.addParameter(
					paramPrefix + "rcid",
					rcid.stream().map(v -> v.toString()).collect(Collectors.joining("|")));
		}

		if (revid != null) {

			req.addParameter(
					paramPrefix + "revid",
					revid.stream().map(v -> v.toString()).collect(Collectors.joining("|")));
		}

		if (logid != null) {

			req.addParameter(
					paramPrefix + "logid",
					logid.stream().map(v -> v.toString()).collect(Collectors.joining("|")));
		}

		if (add != null) {

			req.addParameter(
					paramPrefix + "add", add.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (remove != null) {

			req.addParameter(
					paramPrefix + "remove",
					remove.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (reason != null) {

			req.addParameter(paramPrefix + "reason", reason);
		}

		if (tags != null) {

			req.addParameter(
					paramPrefix + "tags",
					tags.stream().map(v -> v).collect(Collectors.joining("|")));
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
			c.accept(AAPITag.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPITag.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return true;
		}
	}
}
