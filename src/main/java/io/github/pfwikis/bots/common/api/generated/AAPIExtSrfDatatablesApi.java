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

/** ⧼apihelp-ext.srf.datatables.api-summary⧽
 *
 */
public class AAPIExtSrfDatatablesApi implements AAPIModule, AAPIMainActionModule {

	public static AAPIExtSrfDatatablesApi create(@NonNull String data) {

		AAPIExtSrfDatatablesApi v = new AAPIExtSrfDatatablesApi();

		v.data = data;

		return v;
	}

	private AAPIExtSrfDatatablesApi() {}

	private String data;

	/**<span class="apihelp-empty">(no description)</span>
	 */
	public String getData() {
		return this.data;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIExtSrfDatatablesApi(");

		if (data != null) {

			sb.append("data").append("=").append(data);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (data != null) {

			req.addParameter(paramPrefix + "data", data);
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
			c.accept(AAPIExtSrfDatatablesApi.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIExtSrfDatatablesApi.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
