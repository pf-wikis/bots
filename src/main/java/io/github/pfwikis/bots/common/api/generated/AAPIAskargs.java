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

import io.github.pfwikis.bots.common.api.generated.params.AAPIAskargsApi_version;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** API module to query Semantic MediaWiki using the ask language as list of conditions, printouts and parameters.
 */
public class AAPIAskargs implements AAPIModule, AAPIMainActionModule {

	public static AAPIAskargs create(@NonNull String conditions) {

		AAPIAskargs v = new AAPIAskargs();

		v.conditions = List.of(conditions);

		return v;
	}

	private AAPIAskargs() {}

	private List<String> conditions;

	private List<String> printouts;

	private List<String> parameters;

	private AAPIAskargsApi_version api_version;

	/**<span class="apihelp-empty">(no description)</span>
	 */
	public List<String> getConditions() {
		return this.conditions;
	}

	/**<span class="apihelp-empty">(no description)</span>
	 */
	public AAPIAskargs printouts(String printouts) {
		this.printouts = List.of(printouts);

		return this;
	}

	/**<span class="apihelp-empty">(no description)</span>
	 */
	public AAPIAskargs printouts(String... printouts) {
		this.printouts = List.of(printouts);
		return this;
	}

	/**<span class="apihelp-empty">(no description)</span>
	 */
	public List<String> getPrintouts() {
		return this.printouts;
	}

	/**<span class="apihelp-empty">(no description)</span>
	 */
	public AAPIAskargs parameters(String parameters) {
		this.parameters = List.of(parameters);

		return this;
	}

	/**<span class="apihelp-empty">(no description)</span>
	 */
	public AAPIAskargs parameters(String... parameters) {
		this.parameters = List.of(parameters);
		return this;
	}

	/**<span class="apihelp-empty">(no description)</span>
	 */
	public List<String> getParameters() {
		return this.parameters;
	}

	/**<p>Output formatting:
	 * </p>
	 * <dl><dt>2</dt>
	 * <dd>Backwards-compatible format using {} for the result list.</dd>
	 * <dt>3</dt>
	 * <dd>Experimental format using [] as result list.</dd></dl>
	 */
	public AAPIAskargs api_version(AAPIAskargsApi_version api_version) {
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
	public AAPIAskargsApi_version getApi_version() {
		return this.api_version;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIAskargs(");

		if (conditions != null) {

			sb.append("conditions")
					.append("=")
					.append(conditions.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (printouts != null) {

			sb.append("printouts")
					.append("=")
					.append(printouts.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (parameters != null) {

			sb.append("parameters")
					.append("=")
					.append(parameters.stream().map(v -> v).collect(Collectors.joining("|")));

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

		if (conditions != null) {

			req.addParameter(
					paramPrefix + "conditions",
					conditions.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (printouts != null) {

			req.addParameter(
					paramPrefix + "printouts",
					printouts.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (parameters != null) {

			req.addParameter(
					paramPrefix + "parameters",
					parameters.stream().map(v -> v).collect(Collectors.joining("|")));
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
			c.accept(AAPIAskargs.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIAskargs.this);

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
