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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryTagsProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

/** List change tags.
 */
public class AAPIQueryTags implements AAPIModule, AAPIQueryListModule {

	public static AAPIQueryTags create() {

		AAPIQueryTags v = new AAPIQueryTags();

		return v;
	}

	private AAPIQueryTags() {}

	private Integer limit;

	private List<AAPIQueryTagsProp> prop;

	/**The maximum number of tags to list.
	 */
	public AAPIQueryTags limit(Integer limit) {
		this.limit = limit;

		return this;
	}

	/**The maximum number of tags to list.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**<p>Which properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryTags prop(AAPIQueryTagsProp prop) {
		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryTags prop(AAPIQueryTagsProp... prop) {
		this.prop = List.of(prop);
		return this;
	}

	/**<p>Which properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryTagsProp> getProp() {
		return this.prop;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryTags(");

		if (limit != null) {

			sb.append("tglimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("tgprop")
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

		if (limit != null) {

			req.addParameter(paramPrefix + "tglimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "tglimit", "5000");
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "tgprop",
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
			c.accept(AAPIQueryTags.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryTags.this);

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
