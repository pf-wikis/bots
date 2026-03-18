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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryPagepropsProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

/** Get various page properties defined in the page content.
 */
public class AAPIQueryPageprops implements AAPIModule, AAPIQueryPropModule {

	public static AAPIQueryPageprops create() {

		AAPIQueryPageprops v = new AAPIQueryPageprops();

		return v;
	}

	private AAPIQueryPageprops() {}

	private List<AAPIQueryPagepropsProp> prop;

	/**Only list these page properties (<kbd><a href="/wiki/Special:ApiHelp/query%2Bpagepropnames" title="Special:ApiHelp/query+pagepropnames">action=query&amp;list=pagepropnames</a></kbd> returns page property names in use). Useful for checking whether pages use a certain page property.
	 */
	public AAPIQueryPageprops prop(AAPIQueryPagepropsProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**Only list these page properties (<kbd><a href="/wiki/Special:ApiHelp/query%2Bpagepropnames" title="Special:ApiHelp/query+pagepropnames">action=query&amp;list=pagepropnames</a></kbd> returns page property names in use). Useful for checking whether pages use a certain page property.
	 */
	public List<AAPIQueryPagepropsProp> getProp() {
		return this.prop;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryPageprops(");

		if (prop != null) {

			sb.append("ppprop")
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

		if (prop != null) {

			req.addParameter(
					paramPrefix + "ppprop",
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
			c.accept(AAPIQueryPageprops.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryPageprops.this);

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
