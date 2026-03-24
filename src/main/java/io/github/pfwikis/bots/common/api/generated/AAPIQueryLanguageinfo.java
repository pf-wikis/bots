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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryLanguageinfoProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryMeta.AAPIQueryMetaModule;

/** <p>Return information about available languages.
 * </p>
 * <p><a href="/w/index.php?title=Mw:Special:MyLanguage/API:Continue&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/API:Continue (page does not exist)">Continuation</a> may be applied if retrieving the information takes too long for one request.
 * </p>
 */
public class AAPIQueryLanguageinfo implements AAPIModule, AAPIQueryMetaModule {

	public static AAPIQueryLanguageinfo create() {

		AAPIQueryLanguageinfo v = new AAPIQueryLanguageinfo();

		return v;
	}

	private AAPIQueryLanguageinfo() {}

	private List<AAPIQueryLanguageinfoProp> prop;

	private List<String> code;

	/**<p>Which information to get for each language.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryLanguageinfo prop(AAPIQueryLanguageinfoProp prop) {
		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which information to get for each language.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryLanguageinfo prop(AAPIQueryLanguageinfoProp... prop) {
		this.prop = List.of(prop);
		return this;
	}

	/**<p>Which information to get for each language.
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryLanguageinfoProp> getProp() {
		return this.prop;
	}

	/**Language codes of the languages that should be returned, or <code>*</code> for all languages.
	 */
	public AAPIQueryLanguageinfo code(String code) {
		this.code = List.of(code);

		return this;
	}

	/**Language codes of the languages that should be returned, or <code>*</code> for all languages.
	 */
	public AAPIQueryLanguageinfo code(String... code) {
		this.code = List.of(code);
		return this;
	}

	/**Language codes of the languages that should be returned, or <code>*</code> for all languages.
	 */
	public List<String> getCode() {
		return this.code;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryLanguageinfo(");

		if (prop != null) {

			sb.append("liprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (code != null) {

			sb.append("licode")
					.append("=")
					.append(code.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "liprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (code != null) {

			req.addParameter(
					paramPrefix + "licode",
					code.stream().map(v -> v).collect(Collectors.joining("|")));
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
			c.accept(AAPIQueryLanguageinfo.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryLanguageinfo.this);

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
