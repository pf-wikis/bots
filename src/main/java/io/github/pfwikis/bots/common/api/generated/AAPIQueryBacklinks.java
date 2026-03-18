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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryBacklinksDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryBacklinksFilterredir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** Find all pages that link to the given page.
 */
public class AAPIQueryBacklinks
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryBacklinks create() {

		AAPIQueryBacklinks v = new AAPIQueryBacklinks();

		return v;
	}

	private AAPIQueryBacklinks() {}

	private String title;

	private Long pageid;

	private List<NS> namespace;

	private AAPIQueryBacklinksDir dir;

	private AAPIQueryBacklinksFilterredir filterredir;

	private Integer limit;

	private Boolean redirect;

	/**Title to search. Cannot be used together with <var>blpageid</var>.
	 */
	public AAPIQueryBacklinks title(String title) {

		this.title = title;

		return this;
	}

	/**Title to search. Cannot be used together with <var>blpageid</var>.
	 */
	public String getTitle() {
		return this.title;
	}

	/**Page ID to search. Cannot be used together with <var>bltitle</var>.
	 */
	public AAPIQueryBacklinks pageid(Long pageid) {

		this.pageid = pageid;

		return this;
	}

	/**Page ID to search. Cannot be used together with <var>bltitle</var>.
	 */
	public Long getPageid() {
		return this.pageid;
	}

	/**The namespace to enumerate.
	 */
	public AAPIQueryBacklinks namespace(NS... namespace) {

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
	public AAPIQueryBacklinks dir(AAPIQueryBacklinksDir dir) {

		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryBacklinksDir getDir() {
		return this.dir;
	}

	/**How to filter for redirects. If set to <kbd>nonredirects</kbd> when <var>blredirect</var> is enabled, this is only applied to the second level.
	 */
	public AAPIQueryBacklinks filterredir(AAPIQueryBacklinksFilterredir filterredir) {

		this.filterredir = filterredir;

		return this;
	}

	/**How to filter for redirects. If set to <kbd>nonredirects</kbd> when <var>blredirect</var> is enabled, this is only applied to the second level.
	 */
	public AAPIQueryBacklinksFilterredir getFilterredir() {
		return this.filterredir;
	}

	/**How many total pages to return. If <var>blredirect</var> is enabled, the limit applies to each level separately (which means up to 2 * <var>bllimit</var> results may be returned).
	 */
	public AAPIQueryBacklinks limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many total pages to return. If <var>blredirect</var> is enabled, the limit applies to each level separately (which means up to 2 * <var>bllimit</var> results may be returned).
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**If linking page is a redirect, find all pages that link to that redirect as well. Maximum limit is halved.
	 */
	public AAPIQueryBacklinks redirect(Boolean redirect) {

		this.redirect = redirect;

		return this;
	}

	/**If linking page is a redirect, find all pages that link to that redirect as well. Maximum limit is halved.
	 */
	public Boolean getRedirect() {
		return this.redirect;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryBacklinks(");

		if (title != null) {

			sb.append("bltitle").append("=").append(title);

			sb.append(", ");
		}

		if (pageid != null) {

			sb.append("blpageid").append("=").append(pageid.toString());

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("blnamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("bldir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (filterredir != null) {

			sb.append("blfilterredir").append("=").append(filterredir.getJsonValue());

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("bllimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (redirect != null) {

			sb.append("blredirect").append("=").append(redirect.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (title != null) {

			req.addParameter(paramPrefix + "bltitle", title);
		}

		if (pageid != null) {

			req.addParameter(paramPrefix + "blpageid", pageid.toString());
		}

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "blnamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "bldir", dir.getJsonValue());
		}

		if (filterredir != null) {

			req.addParameter(paramPrefix + "blfilterredir", filterredir.getJsonValue());
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "bllimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "bllimit", "5000");
		}

		if (redirect != null) {

			req.addParameter(paramPrefix + "blredirect", redirect.toString());
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
			c.accept(AAPIQueryBacklinks.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryBacklinks.this);

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
