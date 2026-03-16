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

import io.github.pfwikis.bots.common.api.generated.params.AAPIOptionsResetkinds;

import io.github.pfwikis.bots.common.api.generated.params.AAPIOptionsGlobal;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** <p>Change preferences of the current user.
 * </p>
 * <p>Only options which are registered in core or in one of installed extensions, or options with keys prefixed with <code>userjs-</code> (intended to be used by user scripts), can be set.
 * </p>
 */
public class AAPIOptions implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIOptions create() {

		AAPIOptions v = new AAPIOptions();

		return v;
	}

	private AAPIOptions() {}

	private Boolean reset;

	private List<AAPIOptionsResetkinds> resetkinds;

	private List<String> change;

	private String optionname;

	private String optionvalue;

	private AAPIOptionsGlobal global;

	private String token;

	/**Resets preferences to the site defaults.
	 */
	public AAPIOptions reset(Boolean reset) {
		this.reset = reset;

		return this;
	}

	/**Resets preferences to the site defaults.
	 */
	public Boolean getReset() {
		return this.reset;
	}

	/**List of types of options to reset when the <var>reset</var> option is set.
	 */
	public AAPIOptions resetkinds(AAPIOptionsResetkinds resetkinds) {
		this.resetkinds = List.of(resetkinds);

		return this;
	}

	/**List of types of options to reset when the <var>reset</var> option is set.
	 */
	public AAPIOptions resetkinds(AAPIOptionsResetkinds... resetkinds) {
		this.resetkinds = List.of(resetkinds);
		return this;
	}

	/**List of types of options to reset when the <var>reset</var> option is set.
	 */
	public List<AAPIOptionsResetkinds> getResetkinds() {
		return this.resetkinds;
	}

	/**List of changes, formatted name=value (e.g. skin=vector). If no value is given (not even an equals sign), e.g., optionname|otheroption|..., the option will be reset to its default value. If any value passed contains the pipe character (<kbd>|</kbd>), use the <a href="/wiki/Special:ApiHelp/main#main/datatypes" title="Special:ApiHelp/main">alternative multiple-value separator</a> for correct operation.
	 */
	public AAPIOptions change(String change) {
		this.change = List.of(change);

		return this;
	}

	/**List of changes, formatted name=value (e.g. skin=vector). If no value is given (not even an equals sign), e.g., optionname|otheroption|..., the option will be reset to its default value. If any value passed contains the pipe character (<kbd>|</kbd>), use the <a href="/wiki/Special:ApiHelp/main#main/datatypes" title="Special:ApiHelp/main">alternative multiple-value separator</a> for correct operation.
	 */
	public AAPIOptions change(String... change) {
		this.change = List.of(change);
		return this;
	}

	/**List of changes, formatted name=value (e.g. skin=vector). If no value is given (not even an equals sign), e.g., optionname|otheroption|..., the option will be reset to its default value. If any value passed contains the pipe character (<kbd>|</kbd>), use the <a href="/wiki/Special:ApiHelp/main#main/datatypes" title="Special:ApiHelp/main">alternative multiple-value separator</a> for correct operation.
	 */
	public List<String> getChange() {
		return this.change;
	}

	/**The name of the option that should be set to the value given by <var>optionvalue</var>.
	 */
	public AAPIOptions optionname(String optionname) {
		this.optionname = optionname;

		return this;
	}

	/**The name of the option that should be set to the value given by <var>optionvalue</var>.
	 */
	public String getOptionname() {
		return this.optionname;
	}

	/**The value for the option specified by <var>optionname</var>. When <var>optionname</var> is set but <var>optionvalue</var> is omitted, the option will be reset to its default value.
	 */
	public AAPIOptions optionvalue(String optionvalue) {
		this.optionvalue = optionvalue;

		return this;
	}

	/**The value for the option specified by <var>optionname</var>. When <var>optionname</var> is set but <var>optionvalue</var> is omitted, the option will be reset to its default value.
	 */
	public String getOptionvalue() {
		return this.optionvalue;
	}

	/**<p>What to do if the option was set globally using the GlobalPreferences extension.
	 * </p>
	 * <ul><li><kbd>ignore</kbd>: Do nothing. The option remains with its previous value.</li>
	 * <li><kbd>override</kbd>: Add a local override.</li>
	 * <li><kbd>update</kbd>: Update the option globally.</li></ul>
	 */
	public AAPIOptions global(AAPIOptionsGlobal global) {
		this.global = global;

		return this;
	}

	/**<p>What to do if the option was set globally using the GlobalPreferences extension.
	 * </p>
	 * <ul><li><kbd>ignore</kbd>: Do nothing. The option remains with its previous value.</li>
	 * <li><kbd>override</kbd>: Add a local override.</li>
	 * <li><kbd>update</kbd>: Update the option globally.</li></ul>
	 */
	public AAPIOptionsGlobal getGlobal() {
		return this.global;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIOptions token(String token) {
		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIOptions(");

		if (reset != null) {

			sb.append("reset").append("=").append(reset.toString());

			sb.append(", ");
		}

		if (resetkinds != null) {

			sb.append("resetkinds")
					.append("=")
					.append(
							resetkinds.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (change != null) {

			sb.append("change")
					.append("=")
					.append(change.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (optionname != null) {

			sb.append("optionname").append("=").append(optionname);

			sb.append(", ");
		}

		if (optionvalue != null) {

			sb.append("optionvalue").append("=").append(optionvalue);

			sb.append(", ");
		}

		if (global != null) {

			sb.append("global").append("=").append(global.getJsonValue());

			sb.append(", ");
		}

		if (token != null) {

			sb.append("token").append("=").append(token);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (reset != null) {

			req.addParameter(paramPrefix + "reset", reset.toString());
		}

		if (resetkinds != null) {

			req.addParameter(
					paramPrefix + "resetkinds",
					resetkinds.stream()
							.map(v -> v.getJsonValue())
							.collect(Collectors.joining("|")));
		}

		if (change != null) {

			req.addParameter(
					paramPrefix + "change",
					change.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (optionname != null) {

			req.addParameter(paramPrefix + "optionname", optionname);
		}

		if (optionvalue != null) {

			req.addParameter(paramPrefix + "optionvalue", optionvalue);
		}

		if (global != null) {

			req.addParameter(paramPrefix + "global", global.getJsonValue());
		}

		token = api.requestToken(AAPIQueryTokensType.CSRF);

		if (token != null) {

			req.addParameter(paramPrefix + "token", token);
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
			c.accept(AAPIOptions.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIOptions.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return true;
		}

		@Override
		protected boolean internalRequiresPagination() {
			return true;
		}
	}
}
