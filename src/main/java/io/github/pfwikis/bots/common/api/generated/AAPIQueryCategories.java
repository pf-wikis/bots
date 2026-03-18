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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryCategoriesProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryCategoriesShow;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryCategoriesDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** List all categories the pages belong to.
 */
public class AAPIQueryCategories
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryPropModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryCategories create() {

		AAPIQueryCategories v = new AAPIQueryCategories();

		return v;
	}

	private AAPIQueryCategories() {}

	private List<AAPIQueryCategoriesProp> prop;

	private List<AAPIQueryCategoriesShow> show;

	private Integer limit;

	private List<String> categories;

	private AAPIQueryCategoriesDir dir;

	/**<p>Which additional properties to get for each category:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryCategories prop(AAPIQueryCategoriesProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which additional properties to get for each category:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryCategoriesProp> getProp() {
		return this.prop;
	}

	/**Which kind of categories to show.
	 */
	public AAPIQueryCategories show(AAPIQueryCategoriesShow... show) {

		this.show = List.of(show);

		return this;
	}

	/**Which kind of categories to show.
	 */
	public List<AAPIQueryCategoriesShow> getShow() {
		return this.show;
	}

	/**How many categories to return.
	 */
	public AAPIQueryCategories limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many categories to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**Only list these categories. Useful for checking whether a certain page is in a certain category.
	 */
	public AAPIQueryCategories categories(String... categories) {

		this.categories = List.of(categories);

		return this;
	}

	/**Only list these categories. Useful for checking whether a certain page is in a certain category.
	 */
	public List<String> getCategories() {
		return this.categories;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryCategories dir(AAPIQueryCategoriesDir dir) {

		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryCategoriesDir getDir() {
		return this.dir;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryCategories(");

		if (prop != null) {

			sb.append("clprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (show != null) {

			sb.append("clshow")
					.append("=")
					.append(
							show.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("cllimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (categories != null) {

			sb.append("clcategories")
					.append("=")
					.append(categories.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("cldir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "clprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (show != null) {

			req.addParameter(
					paramPrefix + "clshow",
					show.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "cllimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "cllimit", "5000");
		}

		if (categories != null) {

			req.addParameter(
					paramPrefix + "clcategories",
					categories.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "cldir", dir.getJsonValue());
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
			c.accept(AAPIQueryCategories.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryCategories.this);

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
