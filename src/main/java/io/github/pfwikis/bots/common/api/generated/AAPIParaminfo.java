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

import io.github.pfwikis.bots.common.api.generated.params.AAPIParaminfoHelpformat;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Obtain information about API modules.
 */
public class AAPIParaminfo implements AAPIModule, AAPIMainActionModule {

	public static AAPIParaminfo create() {

		AAPIParaminfo v = new AAPIParaminfo();

		return v;
	}

	private AAPIParaminfo() {}

	private List<String> modules;

	private AAPIParaminfoHelpformat helpformat;

	/**List of module names (values of the <var>action</var> and <var>format</var> parameters, or <kbd>main</kbd>). Can specify submodules with a <kbd>+</kbd>, or all submodules with <kbd>+*</kbd>, or all submodules recursively with <kbd>+**</kbd>.
	 */
	public AAPIParaminfo modules(String... modules) {

		this.modules = List.of(modules);

		return this;
	}

	/**List of module names (values of the <var>action</var> and <var>format</var> parameters, or <kbd>main</kbd>). Can specify submodules with a <kbd>+</kbd>, or all submodules with <kbd>+*</kbd>, or all submodules recursively with <kbd>+**</kbd>.
	 */
	public List<String> getModules() {
		return this.modules;
	}

	/**Format of help strings.
	 */
	public AAPIParaminfo helpformat(AAPIParaminfoHelpformat helpformat) {

		this.helpformat = helpformat;

		return this;
	}

	/**Format of help strings.
	 */
	public AAPIParaminfoHelpformat getHelpformat() {
		return this.helpformat;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIParaminfo(");

		if (modules != null) {

			sb.append("modules")
					.append("=")
					.append(modules.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (helpformat != null) {

			sb.append("helpformat").append("=").append(helpformat.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (modules != null) {

			req.addParameter(
					paramPrefix + "modules",
					modules.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (helpformat != null) {

			req.addParameter(paramPrefix + "helpformat", helpformat.getJsonValue());
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
			c.accept(AAPIParaminfo.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIParaminfo.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
