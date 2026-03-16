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

import io.github.pfwikis.bots.common.api.generated.params.AAPIEchomuteType;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Mute or unmute notifications from certain users or pages.
 */
public class AAPIEchomute implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIEchomute create(@NonNull AAPIEchomuteType type) {

		AAPIEchomute v = new AAPIEchomute();

		v.type = type;

		return v;
	}

	private AAPIEchomute() {}

	private AAPIEchomuteType type;

	private List<String> mute;

	private List<String> unmute;

	private String token;

	/**Which mute list to add to or remove from
	 */
	public AAPIEchomuteType getType() {
		return this.type;
	}

	/**Pages or users to add to the mute list
	 */
	public AAPIEchomute mute(String mute) {
		this.mute = List.of(mute);

		return this;
	}

	/**Pages or users to add to the mute list
	 */
	public AAPIEchomute mute(String... mute) {
		this.mute = List.of(mute);
		return this;
	}

	/**Pages or users to add to the mute list
	 */
	public List<String> getMute() {
		return this.mute;
	}

	/**Pages or users to remove from the mute list
	 */
	public AAPIEchomute unmute(String unmute) {
		this.unmute = List.of(unmute);

		return this;
	}

	/**Pages or users to remove from the mute list
	 */
	public AAPIEchomute unmute(String... unmute) {
		this.unmute = List.of(unmute);
		return this;
	}

	/**Pages or users to remove from the mute list
	 */
	public List<String> getUnmute() {
		return this.unmute;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIEchomute token(String token) {
		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIEchomute(");

		if (type != null) {

			sb.append("type").append("=").append(type.getJsonValue());

			sb.append(", ");
		}

		if (mute != null) {

			sb.append("mute")
					.append("=")
					.append(mute.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (unmute != null) {

			sb.append("unmute")
					.append("=")
					.append(unmute.stream().map(v -> v).collect(Collectors.joining("|")));

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

		if (mute != null) {

			req.addParameter(
					paramPrefix + "mute",
					mute.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (unmute != null) {

			req.addParameter(
					paramPrefix + "unmute",
					unmute.stream().map(v -> v).collect(Collectors.joining("|")));
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
			c.accept(AAPIEchomute.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIEchomute.this);

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
