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

import io.github.pfwikis.bots.common.api.generated.params.AAPIOpensearchRedirects;

import io.github.pfwikis.bots.common.api.generated.params.AAPIOpensearchFormat;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Search the wiki using the OpenSearch protocol.
 */
public class AAPIOpensearch implements AAPIModule, AAPIMainActionModule {

	public static AAPIOpensearch create(@NonNull String search) {

		AAPIOpensearch v = new AAPIOpensearch();

		v.search = search;

		return v;
	}

	private AAPIOpensearch() {}

	private String search;

	private List<NS> namespace;

	private Integer limit;

	private AAPIOpensearchRedirects redirects;

	private AAPIOpensearchFormat format;

	private Boolean warningsaserror;

	/**Search string.
	 */
	public String getSearch() {
		return this.search;
	}

	/**Namespaces to search. Ignored if <var>search</var> begins with a valid namespace prefix.
	 */
	public AAPIOpensearch namespace(NS... namespace) {

		this.namespace = List.of(namespace);

		return this;
	}

	/**Namespaces to search. Ignored if <var>search</var> begins with a valid namespace prefix.
	 */
	public List<NS> getNamespace() {
		return this.namespace;
	}

	/**Maximum number of results to return.
	 */
	public AAPIOpensearch limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**Maximum number of results to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**<p>How to handle redirects:
	 * </p>
	 * <dl></dl>
	 * <p>For historical reasons, the default is "return" for format=json and "resolve" for other formats.
	 * </p>
	 */
	public AAPIOpensearch redirects(AAPIOpensearchRedirects redirects) {

		this.redirects = redirects;

		return this;
	}

	/**<p>How to handle redirects:
	 * </p>
	 * <dl></dl>
	 * <p>For historical reasons, the default is "return" for format=json and "resolve" for other formats.
	 * </p>
	 */
	public AAPIOpensearchRedirects getRedirects() {
		return this.redirects;
	}

	/**The format of the output.
	 */
	public AAPIOpensearch format(AAPIOpensearchFormat format) {

		this.format = format;

		return this;
	}

	/**The format of the output.
	 */
	public AAPIOpensearchFormat getFormat() {
		return this.format;
	}

	/**If warnings are raised with <kbd>format=json</kbd>, return an API error instead of ignoring them.
	 */
	public AAPIOpensearch warningsaserror(Boolean warningsaserror) {

		this.warningsaserror = warningsaserror;

		return this;
	}

	/**If warnings are raised with <kbd>format=json</kbd>, return an API error instead of ignoring them.
	 */
	public Boolean getWarningsaserror() {
		return this.warningsaserror;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIOpensearch(");

		if (search != null) {

			sb.append("search").append("=").append(search);

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("namespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("limit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (redirects != null) {

			sb.append("redirects").append("=").append(redirects.getJsonValue());

			sb.append(", ");
		}

		if (format != null) {

			sb.append("format").append("=").append(format.getJsonValue());

			sb.append(", ");
		}

		if (warningsaserror != null) {

			sb.append("warningsaserror").append("=").append(warningsaserror.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (search != null) {

			req.addParameter(paramPrefix + "search", search);
		}

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "namespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "limit", limit.toString());
		}

		if (redirects != null) {

			req.addParameter(paramPrefix + "redirects", redirects.getJsonValue());
		}

		if (format != null) {

			req.addParameter(paramPrefix + "format", format.getJsonValue());
		}

		if (warningsaserror != null) {

			req.addParameter(paramPrefix + "warningsaserror", warningsaserror.toString());
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
			c.accept(AAPIOpensearch.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIOpensearch.this);

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
