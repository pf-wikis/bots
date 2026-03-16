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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGadgetcategoriesProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

/** Returns a list of gadget categories.
 */
public class AAPIQueryGadgetcategories implements AAPIModule, AAPIQueryListModule {

	public static AAPIQueryGadgetcategories create() {

		AAPIQueryGadgetcategories v = new AAPIQueryGadgetcategories();

		return v;
	}

	private AAPIQueryGadgetcategories() {}

	private List<AAPIQueryGadgetcategoriesProp> prop;

	private List<String> names;

	/**<p>What gadget category information to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryGadgetcategories prop(AAPIQueryGadgetcategoriesProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>What gadget category information to get:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryGadgetcategoriesProp> getProp() {
		return this.prop;
	}

	/**Names of categories to retrieve.
	 */
	public AAPIQueryGadgetcategories names(String... names) {

		this.names = List.of(names);

		return this;
	}

	/**Names of categories to retrieve.
	 */
	public List<String> getNames() {
		return this.names;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryGadgetcategories(");

		if (prop != null) {

			sb.append("gcprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (names != null) {

			sb.append("gcnames")
					.append("=")
					.append(names.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "gcprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (names != null) {

			req.addParameter(
					paramPrefix + "gcnames",
					names.stream().map(v -> v).collect(Collectors.joining("|")));
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
			c.accept(AAPIQueryGadgetcategories.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryGadgetcategories.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
