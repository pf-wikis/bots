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

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Display help for the specified modules.
 */
public class AAPIHelp implements AAPIModule, AAPIMainActionModule {

	public static AAPIHelp create() {

		AAPIHelp v = new AAPIHelp();

		return v;
	}

	private AAPIHelp() {}

	private List<String> modules;

	private Boolean submodules;

	private Boolean recursivesubmodules;

	private Boolean wrap;

	private Boolean toc;

	/**Modules to display help for (values of the <var>action</var> and <var>format</var> parameters, or <kbd>main</kbd>). Can specify submodules with a <kbd>+</kbd>.
	 */
	public AAPIHelp modules(String modules) {
		this.modules = List.of(modules);

		return this;
	}

	/**Modules to display help for (values of the <var>action</var> and <var>format</var> parameters, or <kbd>main</kbd>). Can specify submodules with a <kbd>+</kbd>.
	 */
	public AAPIHelp modules(String... modules) {
		this.modules = List.of(modules);
		return this;
	}

	/**Modules to display help for (values of the <var>action</var> and <var>format</var> parameters, or <kbd>main</kbd>). Can specify submodules with a <kbd>+</kbd>.
	 */
	public List<String> getModules() {
		return this.modules;
	}

	/**Include help for submodules of the named module.
	 */
	public AAPIHelp submodules(Boolean submodules) {
		this.submodules = submodules;

		return this;
	}

	/**Include help for submodules of the named module.
	 */
	public Boolean getSubmodules() {
		return this.submodules;
	}

	/**Include help for submodules recursively.
	 */
	public AAPIHelp recursivesubmodules(Boolean recursivesubmodules) {
		this.recursivesubmodules = recursivesubmodules;

		return this;
	}

	/**Include help for submodules recursively.
	 */
	public Boolean getRecursivesubmodules() {
		return this.recursivesubmodules;
	}

	/**Wrap the output in a standard API response structure.
	 */
	public AAPIHelp wrap(Boolean wrap) {
		this.wrap = wrap;

		return this;
	}

	/**Wrap the output in a standard API response structure.
	 */
	public Boolean getWrap() {
		return this.wrap;
	}

	/**Include a table of contents in the HTML output.
	 */
	public AAPIHelp toc(Boolean toc) {
		this.toc = toc;

		return this;
	}

	/**Include a table of contents in the HTML output.
	 */
	public Boolean getToc() {
		return this.toc;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIHelp(");

		if (modules != null) {

			sb.append("modules")
					.append("=")
					.append(modules.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (submodules != null) {

			sb.append("submodules").append("=").append(submodules.toString());

			sb.append(", ");
		}

		if (recursivesubmodules != null) {

			sb.append("recursivesubmodules").append("=").append(recursivesubmodules.toString());

			sb.append(", ");
		}

		if (wrap != null) {

			sb.append("wrap").append("=").append(wrap.toString());

			sb.append(", ");
		}

		if (toc != null) {

			sb.append("toc").append("=").append(toc.toString());

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

		if (submodules != null) {

			req.addParameter(paramPrefix + "submodules", submodules.toString());
		}

		if (recursivesubmodules != null) {

			req.addParameter(paramPrefix + "recursivesubmodules", recursivesubmodules.toString());
		}

		if (wrap != null) {

			req.addParameter(paramPrefix + "wrap", wrap.toString());
		}

		if (toc != null) {

			req.addParameter(paramPrefix + "toc", toc.toString());
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
			c.accept(AAPIHelp.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIHelp.this);

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
