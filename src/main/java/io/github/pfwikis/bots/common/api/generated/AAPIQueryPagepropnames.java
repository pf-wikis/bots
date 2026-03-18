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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

/** List all page property names in use on the wiki.
 */
public class AAPIQueryPagepropnames implements AAPIModule, AAPIQueryListModule {

	public static AAPIQueryPagepropnames create() {

		AAPIQueryPagepropnames v = new AAPIQueryPagepropnames();

		return v;
	}

	private AAPIQueryPagepropnames() {}

	private Integer limit;

	/**The maximum number of names to return.
	 */
	public AAPIQueryPagepropnames limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**The maximum number of names to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryPagepropnames(");

		if (limit != null) {

			sb.append("ppnlimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (limit != null) {

			req.addParameter(paramPrefix + "ppnlimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "ppnlimit", "5000");
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
			c.accept(AAPIQueryPagepropnames.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryPagepropnames.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}

		@Override
		protected boolean internalRequiresPagination() {
			return limit != null;
		}
	}
}
