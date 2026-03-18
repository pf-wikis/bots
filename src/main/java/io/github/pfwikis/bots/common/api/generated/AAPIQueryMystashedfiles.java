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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryMystashedfilesProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

/** Get a list of files in the current user's upload stash.
 */
public class AAPIQueryMystashedfiles implements AAPIModule, AAPIQueryListModule {

	public static AAPIQueryMystashedfiles create() {

		AAPIQueryMystashedfiles v = new AAPIQueryMystashedfiles();

		return v;
	}

	private AAPIQueryMystashedfiles() {}

	private List<AAPIQueryMystashedfilesProp> prop;

	private Integer limit;

	/**<p>Which properties to fetch for the files.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryMystashedfiles prop(AAPIQueryMystashedfilesProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which properties to fetch for the files.
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryMystashedfilesProp> getProp() {
		return this.prop;
	}

	/**How many files to get.
	 */
	public AAPIQueryMystashedfiles limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many files to get.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryMystashedfiles(");

		if (prop != null) {

			sb.append("msfprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("msflimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "msfprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "msflimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "msflimit", "5000");
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
			c.accept(AAPIQueryMystashedfiles.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryMystashedfiles.this);

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
