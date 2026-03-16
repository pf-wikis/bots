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

import io.github.pfwikis.bots.common.api.generated.params.AAPIUserrightsAdd;

import io.github.pfwikis.bots.common.api.generated.params.AAPIUserrightsRemove;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Change a user's group membership.
 */
public class AAPIUserrights implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIUserrights create() {

		AAPIUserrights v = new AAPIUserrights();

		return v;
	}

	private AAPIUserrights() {}

	private String user;

	private List<AAPIUserrightsAdd> add;

	private List<String> expiry;

	private List<AAPIUserrightsRemove> remove;

	private String reason;

	private String token;

	private List<String> tags;

	private Boolean watchuser;

	/**User.
	 */
	public AAPIUserrights user(String user) {

		this.user = user;

		return this;
	}

	/**User.
	 */
	public String getUser() {
		return this.user;
	}

	/**Add the user to these groups, or if they are already a member, update the expiry of their membership in that group.
	 */
	public AAPIUserrights add(AAPIUserrightsAdd... add) {

		this.add = List.of(add);

		return this;
	}

	/**Add the user to these groups, or if they are already a member, update the expiry of their membership in that group.
	 */
	public List<AAPIUserrightsAdd> getAdd() {
		return this.add;
	}

	/**Expiry timestamps. May be relative (e.g. <kbd>5 months</kbd> or <kbd>2 weeks</kbd>) or absolute (e.g. <kbd>2014-09-18T12:34:56Z</kbd>). If only one timestamp is set, it will be used for all groups passed to the <var>add</var> parameter. Use <kbd>infinite</kbd>, <kbd>indefinite</kbd>, <kbd>infinity</kbd>, or <kbd>never</kbd> for a never-expiring user group.
	 */
	public AAPIUserrights expiry(String... expiry) {

		this.expiry = List.of(expiry);

		return this;
	}

	/**Expiry timestamps. May be relative (e.g. <kbd>5 months</kbd> or <kbd>2 weeks</kbd>) or absolute (e.g. <kbd>2014-09-18T12:34:56Z</kbd>). If only one timestamp is set, it will be used for all groups passed to the <var>add</var> parameter. Use <kbd>infinite</kbd>, <kbd>indefinite</kbd>, <kbd>infinity</kbd>, or <kbd>never</kbd> for a never-expiring user group.
	 */
	public List<String> getExpiry() {
		return this.expiry;
	}

	/**Remove the user from these groups.
	 */
	public AAPIUserrights remove(AAPIUserrightsRemove... remove) {

		this.remove = List.of(remove);

		return this;
	}

	/**Remove the user from these groups.
	 */
	public List<AAPIUserrightsRemove> getRemove() {
		return this.remove;
	}

	/**Reason for the change.
	 */
	public AAPIUserrights reason(String reason) {

		this.reason = reason;

		return this;
	}

	/**Reason for the change.
	 */
	public String getReason() {
		return this.reason;
	}

	/**<p>A "userrights" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 * </p>
	 * <p>For compatibility, the token used in the web UI is also accepted.
	 * </p>
	 */
	public AAPIUserrights token(String token) {

		this.token = token;

		return this;
	}

	/**<p>A "userrights" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 * </p>
	 * <p>For compatibility, the token used in the web UI is also accepted.
	 * </p>
	 */
	public String getToken() {
		return this.token;
	}

	/**Change tags to apply to the entry in the user rights log.
	 */
	public AAPIUserrights tags(String... tags) {

		this.tags = List.of(tags);

		return this;
	}

	/**Change tags to apply to the entry in the user rights log.
	 */
	public List<String> getTags() {
		return this.tags;
	}

	/**Watch the user's user and talk pages.
	 */
	public AAPIUserrights watchuser(Boolean watchuser) {

		this.watchuser = watchuser;

		return this;
	}

	/**Watch the user's user and talk pages.
	 */
	public Boolean getWatchuser() {
		return this.watchuser;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIUserrights(");

		if (user != null) {

			sb.append("user").append("=").append(user);

			sb.append(", ");
		}

		if (add != null) {

			sb.append("add")
					.append("=")
					.append(
							add.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (expiry != null) {

			sb.append("expiry")
					.append("=")
					.append(expiry.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (remove != null) {

			sb.append("remove")
					.append("=")
					.append(
							remove.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (reason != null) {

			sb.append("reason").append("=").append(reason);

			sb.append(", ");
		}

		if (token != null) {

			sb.append("token").append("=").append(token);

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

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (user != null) {

			req.addParameter(paramPrefix + "user", user);
		}

		if (add != null) {

			req.addParameter(
					paramPrefix + "add",
					add.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (expiry != null) {

			req.addParameter(
					paramPrefix + "expiry",
					expiry.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (remove != null) {

			req.addParameter(
					paramPrefix + "remove",
					remove.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (reason != null) {

			req.addParameter(paramPrefix + "reason", reason);
		}

		token = api.requestToken(AAPIQueryTokensType.USERRIGHTS);

		if (token != null) {

			req.addParameter(paramPrefix + "token", token);
		}

		if (tags != null) {

			req.addParameter(
					paramPrefix + "tags",
					tags.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (watchuser != null) {

			req.addParameter(paramPrefix + "watchuser", watchuser.toString());
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
			c.accept(AAPIUserrights.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIUserrights.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return true;
		}
	}
}
