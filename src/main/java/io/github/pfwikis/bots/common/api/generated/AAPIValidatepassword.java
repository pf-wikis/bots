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

/** <p>Validate a password against the wiki's password policies.
 * </p>
 * <p>Validity is reported as <samp>Good</samp> if the password is acceptable, <samp>Change</samp> if the password may be used for login but must be changed, or <samp>Invalid</samp> if the password is not usable.
 * </p>
 */
public class AAPIValidatepassword implements AAPIModule, AAPIMainActionModule {

	public static AAPIValidatepassword create(@NonNull String password) {

		AAPIValidatepassword v = new AAPIValidatepassword();

		v.password = password;

		return v;
	}

	private AAPIValidatepassword() {}

	private String password;

	private String user;

	private String email;

	private String realname;

	/**Password to validate.
	 */
	public String getPassword() {
		return this.password;
	}

	/**Username, for use when testing account creation. The named user must not exist.
	 */
	public AAPIValidatepassword user(String user) {
		this.user = user;

		return this;
	}

	/**Username, for use when testing account creation. The named user must not exist.
	 */
	public String getUser() {
		return this.user;
	}

	/**Email address, for use when testing account creation.
	 */
	public AAPIValidatepassword email(String email) {
		this.email = email;

		return this;
	}

	/**Email address, for use when testing account creation.
	 */
	public String getEmail() {
		return this.email;
	}

	/**Real name, for use when testing account creation.
	 */
	public AAPIValidatepassword realname(String realname) {
		this.realname = realname;

		return this;
	}

	/**Real name, for use when testing account creation.
	 */
	public String getRealname() {
		return this.realname;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIValidatepassword(");

		if (password != null) {

			sb.append("password").append("=").append(password);

			sb.append(", ");
		}

		if (user != null) {

			sb.append("user").append("=").append(user);

			sb.append(", ");
		}

		if (email != null) {

			sb.append("email").append("=").append(email);

			sb.append(", ");
		}

		if (realname != null) {

			sb.append("realname").append("=").append(realname);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (password != null) {

			req.addParameter(paramPrefix + "password", password);
		}

		if (user != null) {

			req.addParameter(paramPrefix + "user", user);
		}

		if (email != null) {

			req.addParameter(paramPrefix + "email", email);
		}

		if (realname != null) {

			req.addParameter(paramPrefix + "realname", realname);
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
			c.accept(AAPIValidatepassword.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIValidatepassword.this);

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
