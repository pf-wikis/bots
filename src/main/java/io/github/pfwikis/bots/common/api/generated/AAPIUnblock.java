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

/** Unblock a user.
 */
public class AAPIUnblock implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIUnblock create() {

		AAPIUnblock v = new AAPIUnblock();

		return v;
	}

	private AAPIUnblock() {}

	private Long id;

	private String user;

	private String reason;

	private List<String> tags;

	private Boolean watchuser;

	private String token;

	/**ID of the block to unblock (obtained through <kbd>list=blocks</kbd>). Cannot be used together with <var>user</var>.
	 */
	public AAPIUnblock id(Long id) {

		this.id = id;

		return this;
	}

	/**ID of the block to unblock (obtained through <kbd>list=blocks</kbd>). Cannot be used together with <var>user</var>.
	 */
	public Long getId() {
		return this.id;
	}

	/**User to unblock. Cannot be used together with <var>id</var>.
	 */
	public AAPIUnblock user(String user) {

		this.user = user;

		return this;
	}

	/**User to unblock. Cannot be used together with <var>id</var>.
	 */
	public String getUser() {
		return this.user;
	}

	/**Reason for unblock.
	 */
	public AAPIUnblock reason(String reason) {

		this.reason = reason;

		return this;
	}

	/**Reason for unblock.
	 */
	public String getReason() {
		return this.reason;
	}

	/**Change tags to apply to the entry in the block log.
	 */
	public AAPIUnblock tags(String... tags) {

		this.tags = List.of(tags);

		return this;
	}

	/**Change tags to apply to the entry in the block log.
	 */
	public List<String> getTags() {
		return this.tags;
	}

	/**Watch the user's or IP address's user and talk pages.
	 */
	public AAPIUnblock watchuser(Boolean watchuser) {

		this.watchuser = watchuser;

		return this;
	}

	/**Watch the user's or IP address's user and talk pages.
	 */
	public Boolean getWatchuser() {
		return this.watchuser;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIUnblock token(String token) {

		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIUnblock(");

		if (id != null) {

			sb.append("id").append("=").append(id.toString());

			sb.append(", ");
		}

		if (user != null) {

			sb.append("user").append("=").append(user);

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

		if (watchuser != null) {

			sb.append("watchuser").append("=").append(watchuser.toString());

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

		if (id != null) {

			req.addParameter(paramPrefix + "id", id.toString());
		}

		if (user != null) {

			req.addParameter(paramPrefix + "user", user);
		}

		if (reason != null) {

			req.addParameter(paramPrefix + "reason", reason);
		}

		if (tags != null) {

			req.addParameter(
					paramPrefix + "tags",
					tags.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (watchuser != null) {

			req.addParameter(paramPrefix + "watchuser", watchuser.toString());
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
			c.accept(AAPIUnblock.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIUnblock.this);

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
