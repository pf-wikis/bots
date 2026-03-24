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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryTokensType;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryMeta.AAPIQueryMetaModule;

/** Gets tokens for data-modifying actions.
 */
public class AAPIQueryTokens implements AAPIModule, AAPIQueryMetaModule {

	public static AAPIQueryTokens create() {

		AAPIQueryTokens v = new AAPIQueryTokens();

		return v;
	}

	private AAPIQueryTokens() {}

	private List<AAPIQueryTokensType> type;

	/**Types of token to request.
	 */
	public AAPIQueryTokens type(AAPIQueryTokensType type) {
		this.type = List.of(type);

		return this;
	}

	/**Types of token to request.
	 */
	public AAPIQueryTokens type(AAPIQueryTokensType... type) {
		this.type = List.of(type);
		return this;
	}

	/**Types of token to request.
	 */
	public List<AAPIQueryTokensType> getType() {
		return this.type;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryTokens(");

		if (type != null) {

			sb.append("type")
					.append("=")
					.append(
							type.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (type != null) {

			req.addParameter(
					paramPrefix + "type",
					type.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
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
			c.accept(AAPIQueryTokens.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryTokens.this);

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
