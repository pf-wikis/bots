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

import io.github.pfwikis.bots.common.api.generated.params.AAPIChecktokenType;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Check the validity of a token from <kbd><a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a></kbd>.
 */
public class AAPIChecktoken implements AAPIModule, AAPIMainActionModule {

	public static AAPIChecktoken create(@NonNull AAPIChecktokenType type, @NonNull String token) {

		AAPIChecktoken v = new AAPIChecktoken();

		v.type = type;

		v.token = token;

		return v;
	}

	private AAPIChecktoken() {}

	private AAPIChecktokenType type;

	private String token;

	private Long maxtokenage;

	/**Type of token being tested.
	 */
	public AAPIChecktokenType getType() {
		return this.type;
	}

	/**Token to test.
	 */
	public String getToken() {
		return this.token;
	}

	/**Maximum allowed age of the token, in seconds.
	 */
	public AAPIChecktoken maxtokenage(Long maxtokenage) {
		this.maxtokenage = maxtokenage;

		return this;
	}

	/**Maximum allowed age of the token, in seconds.
	 */
	public Long getMaxtokenage() {
		return this.maxtokenage;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIChecktoken(");

		if (type != null) {

			sb.append("type").append("=").append(type.getJsonValue());

			sb.append(", ");
		}

		if (token != null) {

			sb.append("token").append("=").append(token);

			sb.append(", ");
		}

		if (maxtokenage != null) {

			sb.append("maxtokenage").append("=").append(maxtokenage.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (type != null) {

			req.addParameter(paramPrefix + "type", type.getJsonValue());
		}

		if (token != null) {

			req.addParameter(paramPrefix + "token", token);
		}

		if (maxtokenage != null) {

			req.addParameter(paramPrefix + "maxtokenage", maxtokenage.toString());
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
			c.accept(AAPIChecktoken.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIChecktoken.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}

		@Override
		protected boolean internalRequiresPagination() {
			return true;
		}
	}
}
