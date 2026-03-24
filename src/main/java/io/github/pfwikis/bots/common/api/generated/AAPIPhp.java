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

import io.github.pfwikis.bots.common.api.generated.params.AAPIPhpFormatversion;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainFormat.AAPIMainFormatModule;

/** Output data in serialized PHP format.
 */
public class AAPIPhp implements AAPIModule, AAPIMainFormatModule {

	public static AAPIPhp create() {

		AAPIPhp v = new AAPIPhp();

		return v;
	}

	private AAPIPhp() {}

	private AAPIPhpFormatversion formatversion;

	/**<p>Output formatting
	 * </p>
	 * <dl></dl>
	 */
	public AAPIPhp formatversion(AAPIPhpFormatversion formatversion) {
		this.formatversion = formatversion;

		return this;
	}

	/**<p>Output formatting
	 * </p>
	 * <dl></dl>
	 */
	public AAPIPhpFormatversion getFormatversion() {
		return this.formatversion;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIPhp(");

		if (formatversion != null) {

			sb.append("formatversion").append("=").append(formatversion.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

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
			c.accept(AAPIPhp.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIPhp.this);

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
