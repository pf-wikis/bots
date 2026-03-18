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

import io.github.pfwikis.bots.common.api.generated.params.AAPIPhpfmFormatversion;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainFormat.AAPIMainFormatModule;

/** Output data in serialized PHP format (pretty-print in HTML).
 */
public class AAPIPhpfm implements AAPIModule, AAPIMainFormatModule {

	public static AAPIPhpfm create() {

		AAPIPhpfm v = new AAPIPhpfm();

		return v;
	}

	private AAPIPhpfm() {}

	private Boolean wrappedhtml;

	private AAPIPhpfmFormatversion formatversion;

	/**Return the pretty-printed HTML and associated ResourceLoader modules as a JSON object.
	 */
	public AAPIPhpfm wrappedhtml(Boolean wrappedhtml) {

		this.wrappedhtml = wrappedhtml;

		return this;
	}

	/**Return the pretty-printed HTML and associated ResourceLoader modules as a JSON object.
	 */
	public Boolean getWrappedhtml() {
		return this.wrappedhtml;
	}

	/**<p>Output formatting
	 * </p>
	 * <dl></dl>
	 */
	public AAPIPhpfm formatversion(AAPIPhpfmFormatversion formatversion) {

		this.formatversion = formatversion;

		return this;
	}

	/**<p>Output formatting
	 * </p>
	 * <dl></dl>
	 */
	public AAPIPhpfmFormatversion getFormatversion() {
		return this.formatversion;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIPhpfm(");

		if (wrappedhtml != null) {

			sb.append("wrappedhtml").append("=").append(wrappedhtml.toString());

			sb.append(", ");
		}

		if (formatversion != null) {

			sb.append("formatversion").append("=").append(formatversion.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (wrappedhtml != null) {

			req.addParameter(paramPrefix + "wrappedhtml", wrappedhtml.toString());
		}

		if (formatversion != null) {

			req.addParameter(paramPrefix + "formatversion", formatversion.getJsonValue());
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
			c.accept(AAPIPhpfm.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIPhpfm.this);

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
