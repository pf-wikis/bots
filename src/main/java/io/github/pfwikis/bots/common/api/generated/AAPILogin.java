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

/** <p>Log in and get authentication cookies.
 * </p>
 * <p>This action should only be used in combination with <a href="/wiki/Special:BotPasswords" title="Special:BotPasswords">Special:BotPasswords</a>; use for main-account login is deprecated and may fail without warning. To safely log in to the main account, use <kbd><a href="/wiki/Special:ApiHelp/clientlogin" title="Special:ApiHelp/clientlogin">action=clientlogin</a></kbd>.
 * </p>
 */
public class AAPILogin implements AAPIModule, AAPIMainActionModule {

	public static AAPILogin create() {

		AAPILogin v = new AAPILogin();

		return v;
	}

	private AAPILogin() {}

	private String name;

	private String password;

	private String domain;

	private String token;

	/**Username.
	 */
	public AAPILogin name(String name) {

		this.name = name;

		return this;
	}

	/**Username.
	 */
	public String getName() {
		return this.name;
	}

	/**Password.
	 */
	public AAPILogin password(String password) {

		this.password = password;

		return this;
	}

	/**Password.
	 */
	public String getPassword() {
		return this.password;
	}

	/**Domain (optional).
	 */
	public AAPILogin domain(String domain) {

		this.domain = domain;

		return this;
	}

	/**Domain (optional).
	 */
	public String getDomain() {
		return this.domain;
	}

	/**A "login" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPILogin token(String token) {

		this.token = token;

		return this;
	}

	/**A "login" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPILogin(");

		if (name != null) {

			sb.append("lgname").append("=").append(name);

			sb.append(", ");
		}

		if (password != null) {

			sb.append("lgpassword").append("=").append(password);

			sb.append(", ");
		}

		if (domain != null) {

			sb.append("lgdomain").append("=").append(domain);

			sb.append(", ");
		}

		if (token != null) {

			sb.append("lgtoken").append("=").append(token);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (name != null) {

			req.addParameter(paramPrefix + "lgname", name);
		}

		if (password != null) {

			req.addParameter(paramPrefix + "lgpassword", password);
		}

		if (domain != null) {

			req.addParameter(paramPrefix + "lgdomain", domain);
		}

		if (token != null) {

			req.addParameter(paramPrefix + "lgtoken", token);
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
			c.accept(AAPILogin.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPILogin.this);

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
