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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryRandomFilterredir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** <p>Get a set of random pages.
 * </p>
 * <p>Pages are listed in a fixed sequence, only the starting point is random. This means that if, for example, <samp>Main Page</samp> is the first random page in the list, <samp>List of fictional monkeys</samp> will <em>always</em> be second, <samp>List of people on stamps of Vanuatu</samp> third, etc.
 * </p>
 */
public class AAPIQueryRandom
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryRandom create() {

		AAPIQueryRandom v = new AAPIQueryRandom();

		return v;
	}

	private AAPIQueryRandom() {}

	private List<NS> namespace;

	private AAPIQueryRandomFilterredir filterredir;

	private Integer limit;

	/**Return pages in these namespaces only.
	 */
	public AAPIQueryRandom namespace(NS... namespace) {

		this.namespace = List.of(namespace);

		return this;
	}

	/**Return pages in these namespaces only.
	 */
	public List<NS> getNamespace() {
		return this.namespace;
	}

	/**How to filter for redirects.
	 */
	public AAPIQueryRandom filterredir(AAPIQueryRandomFilterredir filterredir) {

		this.filterredir = filterredir;

		return this;
	}

	/**How to filter for redirects.
	 */
	public AAPIQueryRandomFilterredir getFilterredir() {
		return this.filterredir;
	}

	/**Limit how many random pages will be returned.
	 */
	public AAPIQueryRandom limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**Limit how many random pages will be returned.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryRandom(");

		if (namespace != null) {

			sb.append("rnnamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (filterredir != null) {

			sb.append("rnfilterredir").append("=").append(filterredir.getJsonValue());

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("rnlimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "rnnamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (filterredir != null) {

			req.addParameter(paramPrefix + "rnfilterredir", filterredir.getJsonValue());
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "rnlimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "rnlimit", "5000");
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
			c.accept(AAPIQueryRandom.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryRandom.this);

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
