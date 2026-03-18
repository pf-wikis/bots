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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryLinterrorsCategories;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

/** Get a list of lint errors
 */
public class AAPIQueryLinterrors implements AAPIModule, AAPIQueryListModule {

	public static AAPIQueryLinterrors create() {

		AAPIQueryLinterrors v = new AAPIQueryLinterrors();

		return v;
	}

	private AAPIQueryLinterrors() {}

	private List<AAPIQueryLinterrorsCategories> categories;

	private Integer limit;

	private List<NS> namespace;

	private List<Long> pageid;

	private String title;

	/**Categories of lint errors
	 */
	public AAPIQueryLinterrors categories(AAPIQueryLinterrorsCategories... categories) {

		this.categories = List.of(categories);

		return this;
	}

	/**Categories of lint errors
	 */
	public List<AAPIQueryLinterrorsCategories> getCategories() {
		return this.categories;
	}

	/**Number of results to query
	 */
	public AAPIQueryLinterrors limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**Number of results to query
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**Only include lint errors from the specified namespaces
	 */
	public AAPIQueryLinterrors namespace(NS... namespace) {

		this.namespace = List.of(namespace);

		return this;
	}

	/**Only include lint errors from the specified namespaces
	 */
	public List<NS> getNamespace() {
		return this.namespace;
	}

	/**Only include lint errors from the specified page IDs
	 */
	public AAPIQueryLinterrors pageid(Long... pageid) {

		this.pageid = List.of(pageid);

		return this;
	}

	/**Only include lint errors from the specified page IDs
	 */
	public List<Long> getPageid() {
		return this.pageid;
	}

	/**Only include lint errors from the specified page title
	 */
	public AAPIQueryLinterrors title(String title) {

		this.title = title;

		return this;
	}

	/**Only include lint errors from the specified page title
	 */
	public String getTitle() {
		return this.title;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryLinterrors(");

		if (categories != null) {

			sb.append("lntcategories")
					.append("=")
					.append(
							categories.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("lntlimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("lntnamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (pageid != null) {

			sb.append("lntpageid")
					.append("=")
					.append(
							pageid.stream()
									.map(v -> v.toString())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (title != null) {

			sb.append("lnttitle").append("=").append(title);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (categories != null) {

			req.addParameter(
					paramPrefix + "lntcategories",
					categories.stream()
							.map(v -> v.getJsonValue())
							.collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "lntlimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "lntlimit", "5000");
		}

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "lntnamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (pageid != null) {

			req.addParameter(
					paramPrefix + "lntpageid",
					pageid.stream().map(v -> v.toString()).collect(Collectors.joining("|")));
		}

		if (title != null) {

			req.addParameter(paramPrefix + "lnttitle", title);
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
			c.accept(AAPIQueryLinterrors.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryLinterrors.this);

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
