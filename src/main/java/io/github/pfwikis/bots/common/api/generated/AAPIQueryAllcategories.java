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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllcategoriesDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllcategoriesProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** Enumerate all categories.
 */
public class AAPIQueryAllcategories
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryAllcategories create() {

		AAPIQueryAllcategories v = new AAPIQueryAllcategories();

		return v;
	}

	private AAPIQueryAllcategories() {}

	private String from;

	private String to;

	private String prefix;

	private AAPIQueryAllcategoriesDir dir;

	private Long min;

	private Long max;

	private Integer limit;

	private List<AAPIQueryAllcategoriesProp> prop;

	/**The category to start enumerating from.
	 */
	public AAPIQueryAllcategories from(String from) {

		this.from = from;

		return this;
	}

	/**The category to start enumerating from.
	 */
	public String getFrom() {
		return this.from;
	}

	/**The category to stop enumerating at.
	 */
	public AAPIQueryAllcategories to(String to) {

		this.to = to;

		return this;
	}

	/**The category to stop enumerating at.
	 */
	public String getTo() {
		return this.to;
	}

	/**Search for all category titles that begin with this value.
	 */
	public AAPIQueryAllcategories prefix(String prefix) {

		this.prefix = prefix;

		return this;
	}

	/**Search for all category titles that begin with this value.
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/**Direction to sort in.
	 */
	public AAPIQueryAllcategories dir(AAPIQueryAllcategoriesDir dir) {

		this.dir = dir;

		return this;
	}

	/**Direction to sort in.
	 */
	public AAPIQueryAllcategoriesDir getDir() {
		return this.dir;
	}

	/**Only return categories with at least this many members.
	 */
	public AAPIQueryAllcategories min(Long min) {

		this.min = min;

		return this;
	}

	/**Only return categories with at least this many members.
	 */
	public Long getMin() {
		return this.min;
	}

	/**Only return categories with at most this many members.
	 */
	public AAPIQueryAllcategories max(Long max) {

		this.max = max;

		return this;
	}

	/**Only return categories with at most this many members.
	 */
	public Long getMax() {
		return this.max;
	}

	/**How many categories to return.
	 */
	public AAPIQueryAllcategories limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many categories to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**<p>Which properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryAllcategories prop(AAPIQueryAllcategoriesProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryAllcategoriesProp> getProp() {
		return this.prop;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryAllcategories(");

		if (from != null) {

			sb.append("acfrom").append("=").append(from);

			sb.append(", ");
		}

		if (to != null) {

			sb.append("acto").append("=").append(to);

			sb.append(", ");
		}

		if (prefix != null) {

			sb.append("acprefix").append("=").append(prefix);

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("acdir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (min != null) {

			sb.append("acmin").append("=").append(min.toString());

			sb.append(", ");
		}

		if (max != null) {

			sb.append("acmax").append("=").append(max.toString());

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("aclimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("acprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (from != null) {

			req.addParameter(paramPrefix + "acfrom", from);
		}

		if (to != null) {

			req.addParameter(paramPrefix + "acto", to);
		}

		if (prefix != null) {

			req.addParameter(paramPrefix + "acprefix", prefix);
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "acdir", dir.getJsonValue());
		}

		if (min != null) {

			req.addParameter(paramPrefix + "acmin", min.toString());
		}

		if (max != null) {

			req.addParameter(paramPrefix + "acmax", max.toString());
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "aclimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "aclimit", "5000");
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "acprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
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
			c.accept(AAPIQueryAllcategories.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryAllcategories.this);

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
