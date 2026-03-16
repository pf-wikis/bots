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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryCirrusbuilddocBuilders;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

/** Dump of a CirrusSearch article document from the database servers
 */
public class AAPIQueryCirrusbuilddoc implements AAPIModule, AAPIQueryPropModule {

	public static AAPIQueryCirrusbuilddoc create() {

		AAPIQueryCirrusbuilddoc v = new AAPIQueryCirrusbuilddoc();

		return v;
	}

	private AAPIQueryCirrusbuilddoc() {}

	private List<AAPIQueryCirrusbuilddocBuilders> builders;

	private String limiterprofile;

	/**Type of data to extract
	 */
	public AAPIQueryCirrusbuilddoc builders(AAPIQueryCirrusbuilddocBuilders... builders) {

		this.builders = List.of(builders);

		return this;
	}

	/**Type of data to extract
	 */
	public List<AAPIQueryCirrusbuilddocBuilders> getBuilders() {
		return this.builders;
	}

	/**Profile to use when limiting the size of the document
	 */
	public AAPIQueryCirrusbuilddoc limiterprofile(String limiterprofile) {

		this.limiterprofile = limiterprofile;

		return this;
	}

	/**Profile to use when limiting the size of the document
	 */
	public String getLimiterprofile() {
		return this.limiterprofile;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryCirrusbuilddoc(");

		if (builders != null) {

			sb.append("cbbuilders")
					.append("=")
					.append(
							builders.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limiterprofile != null) {

			sb.append("cblimiterprofile").append("=").append(limiterprofile);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (builders != null) {

			req.addParameter(
					paramPrefix + "cbbuilders",
					builders.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (limiterprofile != null) {

			req.addParameter(paramPrefix + "cblimiterprofile", limiterprofile);
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
			c.accept(AAPIQueryCirrusbuilddoc.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryCirrusbuilddoc.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
