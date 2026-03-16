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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryImageusageDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryImageusageFilterredir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** Find all pages that use the given image title.
 */
public class AAPIQueryImageusage
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryImageusage create(@NonNull ContainsPageRef title) {

		AAPIQueryImageusage v = new AAPIQueryImageusage();

		v.title = title;

		return v;
	}

	private AAPIQueryImageusage() {}

	private List<NS> namespace;

	private AAPIQueryImageusageDir dir;

	private AAPIQueryImageusageFilterredir filterredir;

	private Integer limit;

	private Boolean redirect;

	private ContainsPageRef title;

	/**The namespace to enumerate.
	 */
	public AAPIQueryImageusage namespace(NS namespace) {
		this.namespace = List.of(namespace);

		return this;
	}

	/**The namespace to enumerate.
	 */
	public AAPIQueryImageusage namespace(NS... namespace) {
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
	public AAPIQueryImageusage dir(AAPIQueryImageusageDir dir) {
		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryImageusageDir getDir() {
		return this.dir;
	}

	/**How to filter for redirects. If set to nonredirects when iuredirect is enabled, this is only applied to the second level.
	 */
	public AAPIQueryImageusage filterredir(AAPIQueryImageusageFilterredir filterredir) {
		this.filterredir = filterredir;

		return this;
	}

	/**How to filter for redirects. If set to nonredirects when iuredirect is enabled, this is only applied to the second level.
	 */
	public AAPIQueryImageusageFilterredir getFilterredir() {
		return this.filterredir;
	}

	/**How many total pages to return. If <var>iuredirect</var> is enabled, the limit applies to each level separately (which means up to 2 * <var>iulimit</var> results may be returned).
	 */
	public AAPIQueryImageusage limit(Integer limit) {
		this.limit = limit;

		return this;
	}

	/**How many total pages to return. If <var>iuredirect</var> is enabled, the limit applies to each level separately (which means up to 2 * <var>iulimit</var> results may be returned).
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**If linking page is a redirect, find all pages that link to that redirect as well. Maximum limit is halved.
	 */
	public AAPIQueryImageusage redirect(Boolean redirect) {
		this.redirect = redirect;

		return this;
	}

	/**If linking page is a redirect, find all pages that link to that redirect as well. Maximum limit is halved.
	 */
	public Boolean getRedirect() {
		return this.redirect;
	}

	public ContainsPageRef getTitle() {
		return this.title;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryImageusage(");

		if (title != null) {
			sb.append("title=").append(title).append(", ");
		}

		if (namespace != null) {

			sb.append("iunamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("iudir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (filterredir != null) {

			sb.append("iufilterredir").append("=").append(filterredir.getJsonValue());

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("iulimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (redirect != null) {

			sb.append("iuredirect").append("=").append(redirect.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (title != null) {

			if (title.toPageRef().hasId()) {
				req.addParameter(
						paramPrefix + "iupageid", Integer.toString(title.toPageRef().getId()));
			} else {
				req.addParameter(paramPrefix + "iutitle", title.toPageTitle().toFullTitle());
			}
		}

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "iunamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "iudir", dir.getJsonValue());
		}

		if (filterredir != null) {

			req.addParameter(paramPrefix + "iufilterredir", filterredir.getJsonValue());
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "iulimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "iulimit", "5000");
		}

		if (redirect != null) {

			req.addParameter(paramPrefix + "iuredirect", redirect.toString());
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
			c.accept(AAPIQueryImageusage.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryImageusage.this);

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
