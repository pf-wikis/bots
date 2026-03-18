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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryTranscludedinProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryTranscludedinShow;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** Find all pages that transclude the given pages.
 */
public class AAPIQueryTranscludedin
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryPropModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryTranscludedin create() {

		AAPIQueryTranscludedin v = new AAPIQueryTranscludedin();

		return v;
	}

	private AAPIQueryTranscludedin() {}

	private List<AAPIQueryTranscludedinProp> prop;

	private List<NS> namespace;

	private List<AAPIQueryTranscludedinShow> show;

	private Integer limit;

	/**<p>Which properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryTranscludedin prop(AAPIQueryTranscludedinProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryTranscludedinProp> getProp() {
		return this.prop;
	}

	/**Only include pages in these namespaces.
	 */
	public AAPIQueryTranscludedin namespace(NS... namespace) {

		this.namespace = List.of(namespace);

		return this;
	}

	/**Only include pages in these namespaces.
	 */
	public List<NS> getNamespace() {
		return this.namespace;
	}

	/**<p>Show only items that meet these criteria:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryTranscludedin show(AAPIQueryTranscludedinShow... show) {

		this.show = List.of(show);

		return this;
	}

	/**<p>Show only items that meet these criteria:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryTranscludedinShow> getShow() {
		return this.show;
	}

	/**How many to return.
	 */
	public AAPIQueryTranscludedin limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryTranscludedin(");

		if (prop != null) {

			sb.append("tiprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("tinamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (show != null) {

			sb.append("tishow")
					.append("=")
					.append(
							show.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("tilimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "tiprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "tinamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (show != null) {

			req.addParameter(
					paramPrefix + "tishow",
					show.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "tilimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "tilimit", "5000");
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
			c.accept(AAPIQueryTranscludedin.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryTranscludedin.this);

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
