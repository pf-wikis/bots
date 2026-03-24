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

import io.github.pfwikis.bots.common.api.generated.params.AAPIAskApi_version;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** API module to query Semantic MediaWiki using the ask language.
 */
public class AAPIAsk implements AAPIModule, AAPIMainActionModule {

	public static AAPIAsk create(@NonNull String query) {

		AAPIAsk v = new AAPIAsk();

		v.query = query;

		return v;
	}

	private AAPIAsk() {}

	private String query;

	private AAPIAskApi_version api_version;

	/**<span class="apihelp-empty">(no description)</span>
	 */
	public String getQuery() {
		return this.query;
	}

	/**<p>Output formatting:
	 * </p>
	 * <dl><dt>2</dt>
	 * <dd>Backwards-compatible format using {} for the result list.</dd>
	 * <dt>3</dt>
	 * <dd>Experimental format using [] as result list.</dd></dl>
	 */
	public AAPIAsk api_version(AAPIAskApi_version api_version) {
		this.api_version = api_version;

		return this;
	}

	/**<p>Output formatting:
	 * </p>
	 * <dl><dt>2</dt>
	 * <dd>Backwards-compatible format using {} for the result list.</dd>
	 * <dt>3</dt>
	 * <dd>Experimental format using [] as result list.</dd></dl>
	 */
	public AAPIAskApi_version getApi_version() {
		return this.api_version;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIAsk(");

		if (query != null) {

			sb.append("query").append("=").append(query);

			sb.append(", ");
		}

		if (api_version != null) {

			sb.append("api_version").append("=").append(api_version.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (query != null) {

			req.addParameter(paramPrefix + "query", query);
		}

		if (api_version != null) {

			req.addParameter(paramPrefix + "api_version", api_version.getJsonValue());
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
			c.accept(AAPIAsk.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIAsk.this);

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
