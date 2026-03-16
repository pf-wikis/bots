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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryPageswithpropProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryPageswithpropDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** List all pages using a given page property.
 */
public class AAPIQueryPageswithprop
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryPageswithprop create(@NonNull String propname) {

		AAPIQueryPageswithprop v = new AAPIQueryPageswithprop();

		v.propname = propname;

		return v;
	}

	private AAPIQueryPageswithprop() {}

	private String propname;

	private List<AAPIQueryPageswithpropProp> prop;

	private Integer limit = 5000;

	private AAPIQueryPageswithpropDir dir;

	/**Page property for which to enumerate pages (<kbd><a href="/wiki/Special:ApiHelp/query%2Bpagepropnames" title="Special:ApiHelp/query+pagepropnames">action=query&amp;list=pagepropnames</a></kbd> returns page property names in use).
	 */
	public String getPropname() {
		return this.propname;
	}

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryPageswithprop prop(AAPIQueryPageswithpropProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryPageswithpropProp> getProp() {
		return this.prop;
	}

	/**The maximum number of pages to return.
	 */
	public AAPIQueryPageswithprop limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**The maximum number of pages to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**In which direction to sort.
	 */
	public AAPIQueryPageswithprop dir(AAPIQueryPageswithpropDir dir) {

		this.dir = dir;

		return this;
	}

	/**In which direction to sort.
	 */
	public AAPIQueryPageswithpropDir getDir() {
		return this.dir;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryPageswithprop(");

		if (propname != null) {

			sb.append("pwppropname").append("=").append(propname);

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("pwpprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("pwplimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("pwpdir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (propname != null) {

			req.addParameter(paramPrefix + "pwppropname", propname);
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "pwpprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "pwplimit", limit.toString());
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "pwpdir", dir.getJsonValue());
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
			c.accept(AAPIQueryPageswithprop.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryPageswithprop.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
