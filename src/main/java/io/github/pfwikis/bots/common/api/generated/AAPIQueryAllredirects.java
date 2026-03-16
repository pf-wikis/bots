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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllredirectsProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllredirectsDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** List all redirects to a namespace.
 */
public class AAPIQueryAllredirects
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryAllredirects create() {

		AAPIQueryAllredirects v = new AAPIQueryAllredirects();

		return v;
	}

	private AAPIQueryAllredirects() {}

	private String from;

	private String to;

	private String prefix;

	private Boolean unique;

	private List<AAPIQueryAllredirectsProp> prop;

	private NS namespace;

	private Integer limit;

	private AAPIQueryAllredirectsDir dir;

	/**The title of the redirect to start enumerating from.
	 */
	public AAPIQueryAllredirects from(String from) {
		this.from = from;

		return this;
	}

	/**The title of the redirect to start enumerating from.
	 */
	public String getFrom() {
		return this.from;
	}

	/**The title of the redirect to stop enumerating at.
	 */
	public AAPIQueryAllredirects to(String to) {
		this.to = to;

		return this;
	}

	/**The title of the redirect to stop enumerating at.
	 */
	public String getTo() {
		return this.to;
	}

	/**Search for all target pages that begin with this value.
	 */
	public AAPIQueryAllredirects prefix(String prefix) {
		this.prefix = prefix;

		return this;
	}

	/**Search for all target pages that begin with this value.
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/**Only show distinct target pages. Cannot be used with arprop=ids|fragment|interwiki.
	 * When used as a generator, yields target pages instead of source pages.
	 */
	public AAPIQueryAllredirects unique(Boolean unique) {
		this.unique = unique;

		return this;
	}

	/**Only show distinct target pages. Cannot be used with arprop=ids|fragment|interwiki.
	 * When used as a generator, yields target pages instead of source pages.
	 */
	public Boolean getUnique() {
		return this.unique;
	}

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryAllredirects prop(AAPIQueryAllredirectsProp prop) {
		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryAllredirects prop(AAPIQueryAllredirectsProp... prop) {
		this.prop = List.of(prop);
		return this;
	}

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryAllredirectsProp> getProp() {
		return this.prop;
	}

	/**The namespace to enumerate.
	 */
	public AAPIQueryAllredirects namespace(NS namespace) {
		this.namespace = namespace;

		return this;
	}

	/**The namespace to enumerate.
	 */
	public NS getNamespace() {
		return this.namespace;
	}

	/**How many total items to return.
	 */
	public AAPIQueryAllredirects limit(Integer limit) {
		this.limit = limit;

		return this;
	}

	/**How many total items to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryAllredirects dir(AAPIQueryAllredirectsDir dir) {
		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryAllredirectsDir getDir() {
		return this.dir;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryAllredirects(");

		if (from != null) {

			sb.append("arfrom").append("=").append(from);

			sb.append(", ");
		}

		if (to != null) {

			sb.append("arto").append("=").append(to);

			sb.append(", ");
		}

		if (prefix != null) {

			sb.append("arprefix").append("=").append(prefix);

			sb.append(", ");
		}

		if (unique != null) {

			sb.append("arunique").append("=").append(unique.toString());

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("arprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("arnamespace").append("=").append(Integer.toString(namespace.getId()));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("arlimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("ardir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (from != null) {

			req.addParameter(paramPrefix + "arfrom", from);
		}

		if (to != null) {

			req.addParameter(paramPrefix + "arto", to);
		}

		if (prefix != null) {

			req.addParameter(paramPrefix + "arprefix", prefix);
		}

		if (unique != null) {

			req.addParameter(paramPrefix + "arunique", unique.toString());
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "arprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (namespace != null) {

			req.addParameter(paramPrefix + "arnamespace", Integer.toString(namespace.getId()));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "arlimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "arlimit", "5000");
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "ardir", dir.getJsonValue());
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
			c.accept(AAPIQueryAllredirects.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryAllredirects.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}

		@Override
		protected boolean internalRequiresPagination() {
			return limit == null;
		}
	}
}
