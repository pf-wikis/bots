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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGadgetsProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

/** Returns a list of gadgets used on this wiki.
 */
public class AAPIQueryGadgets implements AAPIModule, AAPIQueryListModule {

	public static AAPIQueryGadgets create() {

		AAPIQueryGadgets v = new AAPIQueryGadgets();

		return v;
	}

	private AAPIQueryGadgets() {}

	private List<AAPIQueryGadgetsProp> prop;

	private List<String> categories;

	private List<String> ids;

	private Boolean allowedonly;

	private Boolean enabledonly;

	/**<p>What gadget information to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryGadgets prop(AAPIQueryGadgetsProp prop) {
		this.prop = List.of(prop);

		return this;
	}

	/**<p>What gadget information to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryGadgets prop(AAPIQueryGadgetsProp... prop) {
		this.prop = List.of(prop);
		return this;
	}

	/**<p>What gadget information to get:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryGadgetsProp> getProp() {
		return this.prop;
	}

	/**Gadgets from what categories to retrieve.
	 */
	public AAPIQueryGadgets categories(String categories) {
		this.categories = List.of(categories);

		return this;
	}

	/**Gadgets from what categories to retrieve.
	 */
	public AAPIQueryGadgets categories(String... categories) {
		this.categories = List.of(categories);
		return this;
	}

	/**Gadgets from what categories to retrieve.
	 */
	public List<String> getCategories() {
		return this.categories;
	}

	/**IDs of gadgets to retrieve.
	 */
	public AAPIQueryGadgets ids(String ids) {
		this.ids = List.of(ids);

		return this;
	}

	/**IDs of gadgets to retrieve.
	 */
	public AAPIQueryGadgets ids(String... ids) {
		this.ids = List.of(ids);
		return this;
	}

	/**IDs of gadgets to retrieve.
	 */
	public List<String> getIds() {
		return this.ids;
	}

	/**List only gadgets allowed to current user.
	 */
	public AAPIQueryGadgets allowedonly(Boolean allowedonly) {
		this.allowedonly = allowedonly;

		return this;
	}

	/**List only gadgets allowed to current user.
	 */
	public Boolean getAllowedonly() {
		return this.allowedonly;
	}

	/**List only gadgets enabled by current user.
	 */
	public AAPIQueryGadgets enabledonly(Boolean enabledonly) {
		this.enabledonly = enabledonly;

		return this;
	}

	/**List only gadgets enabled by current user.
	 */
	public Boolean getEnabledonly() {
		return this.enabledonly;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryGadgets(");

		if (prop != null) {

			sb.append("gaprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (categories != null) {

			sb.append("gacategories")
					.append("=")
					.append(categories.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (ids != null) {

			sb.append("gaids")
					.append("=")
					.append(ids.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (allowedonly != null) {

			sb.append("gaallowedonly").append("=").append(allowedonly.toString());

			sb.append(", ");
		}

		if (enabledonly != null) {

			sb.append("gaenabledonly").append("=").append(enabledonly.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "gaprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (categories != null) {

			req.addParameter(
					paramPrefix + "gacategories",
					categories.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (ids != null) {

			req.addParameter(
					paramPrefix + "gaids",
					ids.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (allowedonly != null) {

			req.addParameter(paramPrefix + "gaallowedonly", allowedonly.toString());
		}

		if (enabledonly != null) {

			req.addParameter(paramPrefix + "gaenabledonly", enabledonly.toString());
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
			c.accept(AAPIQueryGadgets.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryGadgets.this);

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
