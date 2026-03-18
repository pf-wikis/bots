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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryIwlinksProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryIwlinksDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

/** Returns all interwiki links from the given pages.
 */
public class AAPIQueryIwlinks implements AAPIModule, AAPIQueryPropModule {

	public static AAPIQueryIwlinks create() {

		AAPIQueryIwlinks v = new AAPIQueryIwlinks();

		return v;
	}

	private AAPIQueryIwlinks() {}

	private List<AAPIQueryIwlinksProp> prop;

	private String prefix;

	private String title;

	private AAPIQueryIwlinksDir dir;

	private Integer limit;

	/**<p>Which additional properties to get for each interwiki link:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryIwlinks prop(AAPIQueryIwlinksProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which additional properties to get for each interwiki link:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryIwlinksProp> getProp() {
		return this.prop;
	}

	/**Only return interwiki links with this prefix.
	 */
	public AAPIQueryIwlinks prefix(String prefix) {

		this.prefix = prefix;

		return this;
	}

	/**Only return interwiki links with this prefix.
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/**Interwiki link to search for. Must be used with <var>iwprefix</var>.
	 */
	public AAPIQueryIwlinks title(String title) {

		this.title = title;

		return this;
	}

	/**Interwiki link to search for. Must be used with <var>iwprefix</var>.
	 */
	public String getTitle() {
		return this.title;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryIwlinks dir(AAPIQueryIwlinksDir dir) {

		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryIwlinksDir getDir() {
		return this.dir;
	}

	/**How many interwiki links to return.
	 */
	public AAPIQueryIwlinks limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many interwiki links to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryIwlinks(");

		if (prop != null) {

			sb.append("iwprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (prefix != null) {

			sb.append("iwprefix").append("=").append(prefix);

			sb.append(", ");
		}

		if (title != null) {

			sb.append("iwtitle").append("=").append(title);

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("iwdir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("iwlimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "iwprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (prefix != null) {

			req.addParameter(paramPrefix + "iwprefix", prefix);
		}

		if (title != null) {

			req.addParameter(paramPrefix + "iwtitle", title);
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "iwdir", dir.getJsonValue());
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "iwlimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "iwlimit", "5000");
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
			c.accept(AAPIQueryIwlinks.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryIwlinks.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}

		@Override
		protected boolean internalRequiresPagination() {
			return limit != null;
		}
	}
}
