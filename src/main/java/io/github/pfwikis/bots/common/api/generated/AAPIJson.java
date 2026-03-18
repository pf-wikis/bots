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

import io.github.pfwikis.bots.common.api.generated.params.AAPIJsonFormatversion;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainFormat.AAPIMainFormatModule;

/** Output data in JSON format.
 */
public class AAPIJson implements AAPIModule, AAPIMainFormatModule {

	public static AAPIJson create() {

		AAPIJson v = new AAPIJson();

		return v;
	}

	private AAPIJson() {}

	private String callback;

	private Boolean utf8;

	private Boolean ascii;

	private AAPIJsonFormatversion formatversion;

	/**If specified, wraps the output into a given function call. For safety, all user-specific data will be restricted.
	 */
	public AAPIJson callback(String callback) {

		this.callback = callback;

		return this;
	}

	/**If specified, wraps the output into a given function call. For safety, all user-specific data will be restricted.
	 */
	public String getCallback() {
		return this.callback;
	}

	/**If specified, encodes most (but not all) non-ASCII characters as UTF-8 instead of replacing them with hexadecimal escape sequences. Default when <var>formatversion</var> is not <kbd>1</kbd>.
	 */
	public AAPIJson utf8(Boolean utf8) {

		this.utf8 = utf8;

		return this;
	}

	/**If specified, encodes most (but not all) non-ASCII characters as UTF-8 instead of replacing them with hexadecimal escape sequences. Default when <var>formatversion</var> is not <kbd>1</kbd>.
	 */
	public Boolean getUtf8() {
		return this.utf8;
	}

	/**If specified, encodes all non-ASCII using hexadecimal escape sequences. Default when <var>formatversion</var> is <kbd>1</kbd>.
	 */
	public AAPIJson ascii(Boolean ascii) {

		this.ascii = ascii;

		return this;
	}

	/**If specified, encodes all non-ASCII using hexadecimal escape sequences. Default when <var>formatversion</var> is <kbd>1</kbd>.
	 */
	public Boolean getAscii() {
		return this.ascii;
	}

	/**<p>Output formatting
	 * </p>
	 * <dl></dl>
	 */
	public AAPIJson formatversion(AAPIJsonFormatversion formatversion) {

		this.formatversion = formatversion;

		return this;
	}

	/**<p>Output formatting
	 * </p>
	 * <dl></dl>
	 */
	public AAPIJsonFormatversion getFormatversion() {
		return this.formatversion;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIJson(");

		if (callback != null) {

			sb.append("callback").append("=").append(callback);

			sb.append(", ");
		}

		if (utf8 != null) {

			sb.append("utf8").append("=").append(utf8.toString());

			sb.append(", ");
		}

		if (ascii != null) {

			sb.append("ascii").append("=").append(ascii.toString());

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

		if (callback != null) {

			req.addParameter(paramPrefix + "callback", callback);
		}

		if (utf8 != null) {

			req.addParameter(paramPrefix + "utf8", utf8.toString());
		}

		if (ascii != null) {

			req.addParameter(paramPrefix + "ascii", ascii.toString());
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
			c.accept(AAPIJson.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIJson.this);

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
