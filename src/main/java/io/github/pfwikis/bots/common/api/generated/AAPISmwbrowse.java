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

import io.github.pfwikis.bots.common.api.generated.params.AAPISmwbrowseBrowse;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** API module to support browse activities for different entity types in Semantic MediaWiki.
 */
public class AAPISmwbrowse implements AAPIModule, AAPIMainActionModule {

	public static AAPISmwbrowse create(
			@NonNull AAPISmwbrowseBrowse browse, @NonNull String params) {

		AAPISmwbrowse v = new AAPISmwbrowse();

		v.browse = browse;

		v.params = params;

		return v;
	}

	private AAPISmwbrowse() {}

	private AAPISmwbrowseBrowse browse;

	private String params;

	/**<span class="apihelp-empty">(no description)</span>
	 */
	public AAPISmwbrowseBrowse getBrowse() {
		return this.browse;
	}

	/**<span class="apihelp-empty">(no description)</span>
	 */
	public String getParams() {
		return this.params;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPISmwbrowse(");

		if (browse != null) {

			sb.append("browse").append("=").append(browse.getJsonValue());

			sb.append(", ");
		}

		if (params != null) {

			sb.append("params").append("=").append(params);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (browse != null) {

			req.addParameter(paramPrefix + "browse", browse.getJsonValue());
		}

		if (params != null) {

			req.addParameter(paramPrefix + "params", params);
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
			c.accept(AAPISmwbrowse.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPISmwbrowse.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
