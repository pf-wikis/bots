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

import io.github.pfwikis.bots.common.api.generated.params.AAPIEchocreateeventSection;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Manually trigger a notification to a user
 */
public class AAPIEchocreateevent implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIEchocreateevent create(
			@NonNull String header,
			@NonNull String content,
			@NonNull AAPIEchocreateeventSection section) {

		AAPIEchocreateevent v = new AAPIEchocreateevent();

		v.header = header;

		v.content = content;

		v.section = section;

		return v;
	}

	private AAPIEchocreateevent() {}

	private String user;

	private String header;

	private String content;

	private String page;

	private AAPIEchocreateeventSection section;

	private Boolean email;

	private String token;

	/**User to send the notification to
	 */
	public AAPIEchocreateevent user(String user) {
		this.user = user;

		return this;
	}

	/**User to send the notification to
	 */
	public String getUser() {
		return this.user;
	}

	/**Header content of the notification
	 */
	public String getHeader() {
		return this.header;
	}

	/**Body content of the notification
	 */
	public String getContent() {
		return this.content;
	}

	/**Page to link to in the notification
	 */
	public AAPIEchocreateevent page(String page) {
		this.page = page;

		return this;
	}

	/**Page to link to in the notification
	 */
	public String getPage() {
		return this.page;
	}

	/**Section where notification would be delivered
	 */
	public AAPIEchocreateeventSection getSection() {
		return this.section;
	}

	/**Whether to send an email as well
	 */
	public AAPIEchocreateevent email(Boolean email) {
		this.email = email;

		return this;
	}

	/**Whether to send an email as well
	 */
	public Boolean getEmail() {
		return this.email;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIEchocreateevent token(String token) {
		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIEchocreateevent(");

		if (user != null) {

			sb.append("user").append("=").append(user);

			sb.append(", ");
		}

		if (header != null) {

			sb.append("header").append("=").append(header);

			sb.append(", ");
		}

		if (content != null) {

			sb.append("content").append("=").append(content);

			sb.append(", ");
		}

		if (page != null) {

			sb.append("page").append("=").append(page);

			sb.append(", ");
		}

		if (section != null) {

			sb.append("section").append("=").append(section.getJsonValue());

			sb.append(", ");
		}

		if (email != null) {

			sb.append("email").append("=").append(email.toString());

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

		if (user != null) {

			req.addParameter(paramPrefix + "user", user);
		}

		if (header != null) {

			req.addParameter(paramPrefix + "header", header);
		}

		if (content != null) {

			req.addParameter(paramPrefix + "content", content);
		}

		if (page != null) {

			req.addParameter(paramPrefix + "page", page);
		}

		if (section != null) {

			req.addParameter(paramPrefix + "section", section.getJsonValue());
		}

		if (email != null) {

			req.addParameter(paramPrefix + "email", email.toString());
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
			c.accept(AAPIEchocreateevent.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIEchocreateevent.this);

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
