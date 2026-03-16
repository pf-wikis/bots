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

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** <p>Perform a prefix search for page titles.
 * </p>
 * <p>Despite the similarity in names, this module is not intended to be equivalent to <a href="/wiki/Special:PrefixIndex" title="Special:PrefixIndex">Special:PrefixIndex</a>; for that, see <kbd><a href="/wiki/Special:ApiHelp/query%2Ballpages" title="Special:ApiHelp/query+allpages">action=query&amp;list=allpages</a></kbd> with the <kbd>apprefix</kbd> parameter. The purpose of this module is similar to <kbd><a href="/wiki/Special:ApiHelp/opensearch" title="Special:ApiHelp/opensearch">action=opensearch</a></kbd>: to take user input and provide the best-matching titles. Depending on the search engine backend, this might include typo correction, redirect avoidance, or other heuristics.
 * </p>
 */
public class AAPIQueryPrefixsearch
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryPrefixsearch create(@NonNull String search) {

		AAPIQueryPrefixsearch v = new AAPIQueryPrefixsearch();

		v.search = search;

		return v;
	}

	private AAPIQueryPrefixsearch() {}

	private String search;

	private List<NS> namespace;

	private Integer limit;

	/**Search string.
	 */
	public String getSearch() {
		return this.search;
	}

	/**Namespaces to search. Ignored if <var>pssearch</var> begins with a valid namespace prefix.
	 */
	public AAPIQueryPrefixsearch namespace(NS namespace) {
		this.namespace = List.of(namespace);

		return this;
	}

	/**Namespaces to search. Ignored if <var>pssearch</var> begins with a valid namespace prefix.
	 */
	public AAPIQueryPrefixsearch namespace(NS... namespace) {
		this.namespace = List.of(namespace);
		return this;
	}

	/**Namespaces to search. Ignored if <var>pssearch</var> begins with a valid namespace prefix.
	 */
	public List<NS> getNamespace() {
		return this.namespace;
	}

	/**Maximum number of results to return.
	 */
	public AAPIQueryPrefixsearch limit(Integer limit) {
		this.limit = limit;

		return this;
	}

	/**Maximum number of results to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryPrefixsearch(");

		if (search != null) {

			sb.append("pssearch").append("=").append(search);

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("psnamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("pslimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (search != null) {

			req.addParameter(paramPrefix + "pssearch", search);
		}

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "psnamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "pslimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "pslimit", "5000");
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
			c.accept(AAPIQueryPrefixsearch.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryPrefixsearch.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}

		@Override
		protected boolean internalRequiresPagination() {
			return limit == null;
		}
	}
}
