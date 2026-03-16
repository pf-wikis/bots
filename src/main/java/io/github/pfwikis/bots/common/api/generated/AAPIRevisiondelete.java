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

import io.github.pfwikis.bots.common.api.generated.params.AAPIRevisiondeleteType;

import io.github.pfwikis.bots.common.api.generated.params.AAPIRevisiondeleteHide;

import io.github.pfwikis.bots.common.api.generated.params.AAPIRevisiondeleteShow;

import io.github.pfwikis.bots.common.api.generated.params.AAPIRevisiondeleteSuppress;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Delete and undelete revisions.
 */
public class AAPIRevisiondelete implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIRevisiondelete create(
			@NonNull AAPIRevisiondeleteType type, @NonNull String ids) {

		AAPIRevisiondelete v = new AAPIRevisiondelete();

		v.type = type;

		v.ids = List.of(ids);

		return v;
	}

	private AAPIRevisiondelete() {}

	private AAPIRevisiondeleteType type;

	private String target;

	private List<String> ids;

	private List<AAPIRevisiondeleteHide> hide;

	private List<AAPIRevisiondeleteShow> show;

	private AAPIRevisiondeleteSuppress suppress;

	private String reason;

	private List<String> tags;

	private String token;

	/**Type of revision deletion being performed.
	 */
	public AAPIRevisiondeleteType getType() {
		return this.type;
	}

	/**Page title for the revision deletion, if required for the type.
	 */
	public AAPIRevisiondelete target(String target) {
		this.target = target;

		return this;
	}

	/**Page title for the revision deletion, if required for the type.
	 */
	public String getTarget() {
		return this.target;
	}

	/**Identifiers for the revisions to be deleted.
	 */
	public List<String> getIds() {
		return this.ids;
	}

	/**What to hide for each revision.
	 */
	public AAPIRevisiondelete hide(AAPIRevisiondeleteHide hide) {
		this.hide = List.of(hide);

		return this;
	}

	/**What to hide for each revision.
	 */
	public AAPIRevisiondelete hide(AAPIRevisiondeleteHide... hide) {
		this.hide = List.of(hide);
		return this;
	}

	/**What to hide for each revision.
	 */
	public List<AAPIRevisiondeleteHide> getHide() {
		return this.hide;
	}

	/**What to unhide for each revision.
	 */
	public AAPIRevisiondelete show(AAPIRevisiondeleteShow show) {
		this.show = List.of(show);

		return this;
	}

	/**What to unhide for each revision.
	 */
	public AAPIRevisiondelete show(AAPIRevisiondeleteShow... show) {
		this.show = List.of(show);
		return this;
	}

	/**What to unhide for each revision.
	 */
	public List<AAPIRevisiondeleteShow> getShow() {
		return this.show;
	}

	/**Whether to suppress data from administrators as well as others.
	 */
	public AAPIRevisiondelete suppress(AAPIRevisiondeleteSuppress suppress) {
		this.suppress = suppress;

		return this;
	}

	/**Whether to suppress data from administrators as well as others.
	 */
	public AAPIRevisiondeleteSuppress getSuppress() {
		return this.suppress;
	}

	/**Reason for the deletion or undeletion.
	 */
	public AAPIRevisiondelete reason(String reason) {
		this.reason = reason;

		return this;
	}

	/**Reason for the deletion or undeletion.
	 */
	public String getReason() {
		return this.reason;
	}

	/**Tags to apply to the entry in the deletion log.
	 */
	public AAPIRevisiondelete tags(String tags) {
		this.tags = List.of(tags);

		return this;
	}

	/**Tags to apply to the entry in the deletion log.
	 */
	public AAPIRevisiondelete tags(String... tags) {
		this.tags = List.of(tags);
		return this;
	}

	/**Tags to apply to the entry in the deletion log.
	 */
	public List<String> getTags() {
		return this.tags;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIRevisiondelete token(String token) {
		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIRevisiondelete(");

		if (type != null) {

			sb.append("type").append("=").append(type.getJsonValue());

			sb.append(", ");
		}

		if (target != null) {

			sb.append("target").append("=").append(target);

			sb.append(", ");
		}

		if (ids != null) {

			sb.append("ids")
					.append("=")
					.append(ids.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (hide != null) {

			sb.append("hide")
					.append("=")
					.append(
							hide.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (show != null) {

			sb.append("show")
					.append("=")
					.append(
							show.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (suppress != null) {

			sb.append("suppress").append("=").append(suppress.getJsonValue());

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

		if (type != null) {

			req.addParameter(paramPrefix + "type", type.getJsonValue());
		}

		if (target != null) {

			req.addParameter(paramPrefix + "target", target);
		}

		if (ids != null) {

			req.addParameter(
					paramPrefix + "ids", ids.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (hide != null) {

			req.addParameter(
					paramPrefix + "hide",
					hide.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (show != null) {

			req.addParameter(
					paramPrefix + "show",
					show.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (suppress != null) {

			req.addParameter(paramPrefix + "suppress", suppress.getJsonValue());
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
			c.accept(AAPIRevisiondelete.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIRevisiondelete.this);

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
