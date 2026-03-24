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

import io.github.pfwikis.bots.common.api.generated.params.AAPISmwinfoInfo;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** API module to retrieve information about Semantic MediaWiki statistics and other meta information.
 */
public class AAPISmwinfo implements AAPIModule, AAPIMainActionModule {

	public static AAPISmwinfo create() {

		AAPISmwinfo v = new AAPISmwinfo();

		return v;
	}

	private AAPISmwinfo() {}

	private List<AAPISmwinfoInfo> info;

	/**<span class="apihelp-empty">(no description)</span>
	 */
	public AAPISmwinfo info(AAPISmwinfoInfo info) {
		this.info = List.of(info);

		return this;
	}

	/**<span class="apihelp-empty">(no description)</span>
	 */
	public AAPISmwinfo info(AAPISmwinfoInfo... info) {
		this.info = List.of(info);
		return this;
	}

	/**<span class="apihelp-empty">(no description)</span>
	 */
	public List<AAPISmwinfoInfo> getInfo() {
		return this.info;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPISmwinfo(");

		if (info != null) {

			sb.append("info")
					.append("=")
					.append(
							info.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (info != null) {

			req.addParameter(
					paramPrefix + "info",
					info.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
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
			c.accept(AAPISmwinfo.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPISmwinfo.this);

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
