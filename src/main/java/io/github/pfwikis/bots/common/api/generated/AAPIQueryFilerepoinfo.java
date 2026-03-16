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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryFilerepoinfoProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryMeta.AAPIQueryMetaModule;

/** Return meta information about image repositories configured on the wiki.
 */
public class AAPIQueryFilerepoinfo implements AAPIModule, AAPIQueryMetaModule {

	public static AAPIQueryFilerepoinfo create() {

		AAPIQueryFilerepoinfo v = new AAPIQueryFilerepoinfo();

		return v;
	}

	private AAPIQueryFilerepoinfo() {}

	private List<AAPIQueryFilerepoinfoProp> prop;

	/**<p>Which repository properties to get (properties available may vary on other wikis).
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryFilerepoinfo prop(AAPIQueryFilerepoinfoProp prop) {
		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which repository properties to get (properties available may vary on other wikis).
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryFilerepoinfo prop(AAPIQueryFilerepoinfoProp... prop) {
		this.prop = List.of(prop);
		return this;
	}

	/**<p>Which repository properties to get (properties available may vary on other wikis).
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryFilerepoinfoProp> getProp() {
		return this.prop;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryFilerepoinfo(");

		if (prop != null) {

			sb.append("friprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "friprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
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
			c.accept(AAPIQueryFilerepoinfo.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryFilerepoinfo.this);

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
