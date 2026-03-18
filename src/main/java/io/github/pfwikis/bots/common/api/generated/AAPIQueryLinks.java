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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryLinksDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** Returns all links from the given pages.
 */
public class AAPIQueryLinks
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryPropModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryLinks create() {

		AAPIQueryLinks v = new AAPIQueryLinks();

		return v;
	}

	private AAPIQueryLinks() {}

	private List<NS> namespace;

	private Integer limit;

	private List<String> titles;

	private AAPIQueryLinksDir dir;

	/**Show links in these namespaces only.
	 */
	public AAPIQueryLinks namespace(NS... namespace) {

		this.namespace = List.of(namespace);

		return this;
	}

	/**Show links in these namespaces only.
	 */
	public List<NS> getNamespace() {
		return this.namespace;
	}

	/**How many links to return.
	 */
	public AAPIQueryLinks limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many links to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**Only list links to these titles. Useful for checking whether a certain page links to a certain title.
	 */
	public AAPIQueryLinks titles(String... titles) {

		this.titles = List.of(titles);

		return this;
	}

	/**Only list links to these titles. Useful for checking whether a certain page links to a certain title.
	 */
	public List<String> getTitles() {
		return this.titles;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryLinks dir(AAPIQueryLinksDir dir) {

		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryLinksDir getDir() {
		return this.dir;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryLinks(");

		if (namespace != null) {

			sb.append("plnamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("pllimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (titles != null) {

			sb.append("pltitles")
					.append("=")
					.append(titles.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("pldir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "plnamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "pllimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "pllimit", "5000");
		}

		if (titles != null) {

			req.addParameter(
					paramPrefix + "pltitles",
					titles.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "pldir", dir.getJsonValue());
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
			c.accept(AAPIQueryLinks.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryLinks.this);

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
