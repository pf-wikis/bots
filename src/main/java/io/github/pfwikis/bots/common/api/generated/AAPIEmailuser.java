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

/** Email a user.
 */
public class AAPIEmailuser implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIEmailuser create(
			@NonNull String target, @NonNull String subject, @NonNull String text) {

		AAPIEmailuser v = new AAPIEmailuser();

		v.target = target;

		v.subject = subject;

		v.text = text;

		return v;
	}

	private AAPIEmailuser() {}

	private String target;

	private String subject;

	private String text;

	private Boolean ccme;

	private String token;

	/**User to send the email to.
	 */
	public String getTarget() {
		return this.target;
	}

	/**Subject header.
	 */
	public String getSubject() {
		return this.subject;
	}

	/**Email body.
	 */
	public String getText() {
		return this.text;
	}

	/**Send a copy of this mail to me.
	 */
	public AAPIEmailuser ccme(Boolean ccme) {

		this.ccme = ccme;

		return this;
	}

	/**Send a copy of this mail to me.
	 */
	public Boolean getCcme() {
		return this.ccme;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIEmailuser token(String token) {

		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIEmailuser(");

		if (target != null) {

			sb.append("target").append("=").append(target);

			sb.append(", ");
		}

		if (subject != null) {

			sb.append("subject").append("=").append(subject);

			sb.append(", ");
		}

		if (text != null) {

			sb.append("text").append("=").append(text);

			sb.append(", ");
		}

		if (ccme != null) {

			sb.append("ccme").append("=").append(ccme.toString());

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

		if (target != null) {

			req.addParameter(paramPrefix + "target", target);
		}

		if (subject != null) {

			req.addParameter(paramPrefix + "subject", subject);
		}

		if (text != null) {

			req.addParameter(paramPrefix + "text", text);
		}

		if (ccme != null) {

			req.addParameter(paramPrefix + "ccme", ccme.toString());
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
			c.accept(AAPIEmailuser.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIEmailuser.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return true;
		}
	}
}
