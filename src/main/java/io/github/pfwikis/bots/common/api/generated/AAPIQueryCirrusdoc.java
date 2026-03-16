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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

/** Dump of a CirrusSearch article document from the search servers
 */
public class AAPIQueryCirrusdoc implements AAPIModule, AAPIQueryPropModule {

	public static AAPIQueryCirrusdoc create() {

		AAPIQueryCirrusdoc v = new AAPIQueryCirrusdoc();

		return v;
	}

	private AAPIQueryCirrusdoc() {}

	private List<String> includes;

	/**Define which fields should be returned by the search.
	 */
	public AAPIQueryCirrusdoc includes(String... includes) {

		this.includes = List.of(includes);

		return this;
	}

	/**Define which fields should be returned by the search.
	 */
	public List<String> getIncludes() {
		return this.includes;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryCirrusdoc(");

		if (includes != null) {

			sb.append("cdincludes")
					.append("=")
					.append(includes.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (includes != null) {

			req.addParameter(
					paramPrefix + "cdincludes",
					includes.stream().map(v -> v).collect(Collectors.joining("|")));
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
			c.accept(AAPIQueryCirrusdoc.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryCirrusdoc.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
