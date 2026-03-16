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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQuerySearchQiprofile;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQuerySearchWhat;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQuerySearchInfo;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQuerySearchProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQuerySearchSort;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** Perform a full text search.
 */
public class AAPIQuerySearch
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQuerySearch create(@NonNull String search) {

		AAPIQuerySearch v = new AAPIQuerySearch();

		v.search = search;

		return v;
	}

	private AAPIQuerySearch() {}

	private String search;

	private List<NS> namespace;

	private Integer limit = 5000;

	private AAPIQuerySearchQiprofile qiprofile;

	private AAPIQuerySearchWhat what;

	private List<AAPIQuerySearchInfo> info;

	private List<AAPIQuerySearchProp> prop;

	private Boolean interwiki;

	private Boolean enablerewrites;

	private AAPIQuerySearchSort sort;

	/**Search for page titles or content matching this value. You can use the search string to invoke special search features, depending on what the wiki's search backend implements.
	 */
	public String getSearch() {
		return this.search;
	}

	/**Search only within these namespaces.
	 */
	public AAPIQuerySearch namespace(NS... namespace) {

		this.namespace = List.of(namespace);

		return this;
	}

	/**Search only within these namespaces.
	 */
	public List<NS> getNamespace() {
		return this.namespace;
	}

	/**How many total pages to return.
	 */
	public AAPIQuerySearch limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many total pages to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**<p>Query independent profile to use (affects ranking algorithm).
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQuerySearch qiprofile(AAPIQuerySearchQiprofile qiprofile) {

		this.qiprofile = qiprofile;

		return this;
	}

	/**<p>Query independent profile to use (affects ranking algorithm).
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQuerySearchQiprofile getQiprofile() {
		return this.qiprofile;
	}

	/**Which type of search to perform.
	 */
	public AAPIQuerySearch what(AAPIQuerySearchWhat what) {

		this.what = what;

		return this;
	}

	/**Which type of search to perform.
	 */
	public AAPIQuerySearchWhat getWhat() {
		return this.what;
	}

	/**Which metadata to return.
	 */
	public AAPIQuerySearch info(AAPIQuerySearchInfo... info) {

		this.info = List.of(info);

		return this;
	}

	/**Which metadata to return.
	 */
	public List<AAPIQuerySearchInfo> getInfo() {
		return this.info;
	}

	/**<p>Which properties to return:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQuerySearch prop(AAPIQuerySearchProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which properties to return:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQuerySearchProp> getProp() {
		return this.prop;
	}

	/**Include interwiki results in the search, if available.
	 */
	public AAPIQuerySearch interwiki(Boolean interwiki) {

		this.interwiki = interwiki;

		return this;
	}

	/**Include interwiki results in the search, if available.
	 */
	public Boolean getInterwiki() {
		return this.interwiki;
	}

	/**Enable internal query rewriting. Some search backends can rewrite the query into another which is thought to provide better results, for instance by correcting spelling errors.
	 */
	public AAPIQuerySearch enablerewrites(Boolean enablerewrites) {

		this.enablerewrites = enablerewrites;

		return this;
	}

	/**Enable internal query rewriting. Some search backends can rewrite the query into another which is thought to provide better results, for instance by correcting spelling errors.
	 */
	public Boolean getEnablerewrites() {
		return this.enablerewrites;
	}

	/**Set the sort order of returned results.
	 */
	public AAPIQuerySearch sort(AAPIQuerySearchSort sort) {

		this.sort = sort;

		return this;
	}

	/**Set the sort order of returned results.
	 */
	public AAPIQuerySearchSort getSort() {
		return this.sort;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQuerySearch(");

		if (search != null) {

			sb.append("srsearch").append("=").append(search);

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("srnamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("srlimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (qiprofile != null) {

			sb.append("srqiprofile").append("=").append(qiprofile.getJsonValue());

			sb.append(", ");
		}

		if (what != null) {

			sb.append("srwhat").append("=").append(what.getJsonValue());

			sb.append(", ");
		}

		if (info != null) {

			sb.append("srinfo")
					.append("=")
					.append(
							info.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("srprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (interwiki != null) {

			sb.append("srinterwiki").append("=").append(interwiki.toString());

			sb.append(", ");
		}

		if (enablerewrites != null) {

			sb.append("srenablerewrites").append("=").append(enablerewrites.toString());

			sb.append(", ");
		}

		if (sort != null) {

			sb.append("srsort").append("=").append(sort.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (search != null) {

			req.addParameter(paramPrefix + "srsearch", search);
		}

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "srnamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "srlimit", limit.toString());
		}

		if (qiprofile != null) {

			req.addParameter(paramPrefix + "srqiprofile", qiprofile.getJsonValue());
		}

		if (what != null) {

			req.addParameter(paramPrefix + "srwhat", what.getJsonValue());
		}

		if (info != null) {

			req.addParameter(
					paramPrefix + "srinfo",
					info.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "srprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (interwiki != null) {

			req.addParameter(paramPrefix + "srinterwiki", interwiki.toString());
		}

		if (enablerewrites != null) {

			req.addParameter(paramPrefix + "srenablerewrites", enablerewrites.toString());
		}

		if (sort != null) {

			req.addParameter(paramPrefix + "srsort", sort.getJsonValue());
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
			c.accept(AAPIQuerySearch.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQuerySearch.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
