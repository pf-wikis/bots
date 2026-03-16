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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryIwbacklinksProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryIwbacklinksDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** <p>Find all pages that link to the given interwiki link.
 * </p>
 * <p>Can be used to find all links with a prefix, or all links to a title (with a given prefix). Using neither parameter is effectively "all interwiki links".
 * </p>
 */
public class AAPIQueryIwbacklinks
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryIwbacklinks create() {

		AAPIQueryIwbacklinks v = new AAPIQueryIwbacklinks();

		return v;
	}

	private AAPIQueryIwbacklinks() {}

	private String prefix;

	private String title;

	private Integer limit = 5000;

	private List<AAPIQueryIwbacklinksProp> prop;

	private AAPIQueryIwbacklinksDir dir;

	/**Prefix for the interwiki.
	 */
	public AAPIQueryIwbacklinks prefix(String prefix) {

		this.prefix = prefix;

		return this;
	}

	/**Prefix for the interwiki.
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/**Interwiki link to search for. Must be used with <var>iwblblprefix</var>.
	 */
	public AAPIQueryIwbacklinks title(String title) {

		this.title = title;

		return this;
	}

	/**Interwiki link to search for. Must be used with <var>iwblblprefix</var>.
	 */
	public String getTitle() {
		return this.title;
	}

	/**How many total pages to return.
	 */
	public AAPIQueryIwbacklinks limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many total pages to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**<p>Which properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryIwbacklinks prop(AAPIQueryIwbacklinksProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryIwbacklinksProp> getProp() {
		return this.prop;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryIwbacklinks dir(AAPIQueryIwbacklinksDir dir) {

		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryIwbacklinksDir getDir() {
		return this.dir;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryIwbacklinks(");

		if (prefix != null) {

			sb.append("iwblprefix").append("=").append(prefix);

			sb.append(", ");
		}

		if (title != null) {

			sb.append("iwbltitle").append("=").append(title);

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("iwbllimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("iwblprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("iwbldir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prefix != null) {

			req.addParameter(paramPrefix + "iwblprefix", prefix);
		}

		if (title != null) {

			req.addParameter(paramPrefix + "iwbltitle", title);
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "iwbllimit", limit.toString());
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "iwblprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "iwbldir", dir.getJsonValue());
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
			c.accept(AAPIQueryIwbacklinks.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryIwbacklinks.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
