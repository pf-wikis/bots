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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryUserinfoProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryMeta.AAPIQueryMetaModule;

/** Get information about the current user.
 */
public class AAPIQueryUserinfo implements AAPIModule, AAPIQueryMetaModule {

	public static AAPIQueryUserinfo create() {

		AAPIQueryUserinfo v = new AAPIQueryUserinfo();

		return v;
	}

	private AAPIQueryUserinfo() {}

	private List<AAPIQueryUserinfoProp> prop;

	private String attachedwiki;

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryUserinfo prop(AAPIQueryUserinfoProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryUserinfoProp> getProp() {
		return this.prop;
	}

	/**With <kbd>uiprop=centralids</kbd>, indicate whether the user is attached with the wiki identified by this ID.
	 */
	public AAPIQueryUserinfo attachedwiki(String attachedwiki) {

		this.attachedwiki = attachedwiki;

		return this;
	}

	/**With <kbd>uiprop=centralids</kbd>, indicate whether the user is attached with the wiki identified by this ID.
	 */
	public String getAttachedwiki() {
		return this.attachedwiki;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryUserinfo(");

		if (prop != null) {

			sb.append("uiprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (attachedwiki != null) {

			sb.append("uiattachedwiki").append("=").append(attachedwiki);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "uiprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (attachedwiki != null) {

			req.addParameter(paramPrefix + "uiattachedwiki", attachedwiki);
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
			c.accept(AAPIQueryUserinfo.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryUserinfo.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
