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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryEmbeddedinDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryEmbeddedinFilterredir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** Find all pages that embed (transclude) the given title.
 */
public class AAPIQueryEmbeddedin
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryEmbeddedin create() {

		AAPIQueryEmbeddedin v = new AAPIQueryEmbeddedin();

		return v;
	}

	private AAPIQueryEmbeddedin() {}

	private String title;

	private Long pageid;

	private List<NS> namespace;

	private AAPIQueryEmbeddedinDir dir;

	private AAPIQueryEmbeddedinFilterredir filterredir;

	private Integer limit;

	/**Title to search. Cannot be used together with eipageid.
	 */
	public AAPIQueryEmbeddedin title(String title) {

		this.title = title;

		return this;
	}

	/**Title to search. Cannot be used together with eipageid.
	 */
	public String getTitle() {
		return this.title;
	}

	/**Page ID to search. Cannot be used together with eititle.
	 */
	public AAPIQueryEmbeddedin pageid(Long pageid) {

		this.pageid = pageid;

		return this;
	}

	/**Page ID to search. Cannot be used together with eititle.
	 */
	public Long getPageid() {
		return this.pageid;
	}

	/**The namespace to enumerate.
	 */
	public AAPIQueryEmbeddedin namespace(NS... namespace) {

		this.namespace = List.of(namespace);

		return this;
	}

	/**The namespace to enumerate.
	 */
	public List<NS> getNamespace() {
		return this.namespace;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryEmbeddedin dir(AAPIQueryEmbeddedinDir dir) {

		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryEmbeddedinDir getDir() {
		return this.dir;
	}

	/**How to filter for redirects.
	 */
	public AAPIQueryEmbeddedin filterredir(AAPIQueryEmbeddedinFilterredir filterredir) {

		this.filterredir = filterredir;

		return this;
	}

	/**How to filter for redirects.
	 */
	public AAPIQueryEmbeddedinFilterredir getFilterredir() {
		return this.filterredir;
	}

	/**How many total pages to return.
	 */
	public AAPIQueryEmbeddedin limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many total pages to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryEmbeddedin(");

		if (title != null) {

			sb.append("eititle").append("=").append(title);

			sb.append(", ");
		}

		if (pageid != null) {

			sb.append("eipageid").append("=").append(pageid.toString());

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("einamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("eidir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (filterredir != null) {

			sb.append("eifilterredir").append("=").append(filterredir.getJsonValue());

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("eilimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (title != null) {

			req.addParameter(paramPrefix + "eititle", title);
		}

		if (pageid != null) {

			req.addParameter(paramPrefix + "eipageid", pageid.toString());
		}

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "einamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "eidir", dir.getJsonValue());
		}

		if (filterredir != null) {

			req.addParameter(paramPrefix + "eifilterredir", filterredir.getJsonValue());
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "eilimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "eilimit", "5000");
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
			c.accept(AAPIQueryEmbeddedin.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryEmbeddedin.this);

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
