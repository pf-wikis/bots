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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAlltransclusionsProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAlltransclusionsDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** List all transclusions (pages embedded using &#123;&#123;x&#125;&#125;), including non-existing.
 */
public class AAPIQueryAlltransclusions
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryAlltransclusions create() {

		AAPIQueryAlltransclusions v = new AAPIQueryAlltransclusions();

		return v;
	}

	private AAPIQueryAlltransclusions() {}

	private String from;

	private String to;

	private String prefix;

	private Boolean unique;

	private List<AAPIQueryAlltransclusionsProp> prop;

	private NS namespace;

	private Integer limit;

	private AAPIQueryAlltransclusionsDir dir;

	/**The title of the transclusion to start enumerating from.
	 */
	public AAPIQueryAlltransclusions from(String from) {

		this.from = from;

		return this;
	}

	/**The title of the transclusion to start enumerating from.
	 */
	public String getFrom() {
		return this.from;
	}

	/**The title of the transclusion to stop enumerating at.
	 */
	public AAPIQueryAlltransclusions to(String to) {

		this.to = to;

		return this;
	}

	/**The title of the transclusion to stop enumerating at.
	 */
	public String getTo() {
		return this.to;
	}

	/**Search for all transcluded titles that begin with this value.
	 */
	public AAPIQueryAlltransclusions prefix(String prefix) {

		this.prefix = prefix;

		return this;
	}

	/**Search for all transcluded titles that begin with this value.
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/**Only show distinct transcluded titles. Cannot be used with atprop=ids.
	 * When used as a generator, yields target pages instead of source pages.
	 */
	public AAPIQueryAlltransclusions unique(Boolean unique) {

		this.unique = unique;

		return this;
	}

	/**Only show distinct transcluded titles. Cannot be used with atprop=ids.
	 * When used as a generator, yields target pages instead of source pages.
	 */
	public Boolean getUnique() {
		return this.unique;
	}

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryAlltransclusions prop(AAPIQueryAlltransclusionsProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryAlltransclusionsProp> getProp() {
		return this.prop;
	}

	/**The namespace to enumerate.
	 */
	public AAPIQueryAlltransclusions namespace(NS namespace) {

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
	public AAPIQueryAlltransclusions limit(Integer limit) {

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
	public AAPIQueryAlltransclusions dir(AAPIQueryAlltransclusionsDir dir) {

		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryAlltransclusionsDir getDir() {
		return this.dir;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryAlltransclusions(");

		if (from != null) {

			sb.append("atfrom").append("=").append(from);

			sb.append(", ");
		}

		if (to != null) {

			sb.append("atto").append("=").append(to);

			sb.append(", ");
		}

		if (prefix != null) {

			sb.append("atprefix").append("=").append(prefix);

			sb.append(", ");
		}

		if (unique != null) {

			sb.append("atunique").append("=").append(unique.toString());

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("atprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("atnamespace").append("=").append(Integer.toString(namespace.getId()));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("atlimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("atdir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (from != null) {

			req.addParameter(paramPrefix + "atfrom", from);
		}

		if (to != null) {

			req.addParameter(paramPrefix + "atto", to);
		}

		if (prefix != null) {

			req.addParameter(paramPrefix + "atprefix", prefix);
		}

		if (unique != null) {

			req.addParameter(paramPrefix + "atunique", unique.toString());
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "atprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (namespace != null) {

			req.addParameter(paramPrefix + "atnamespace", Integer.toString(namespace.getId()));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "atlimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "atlimit", "5000");
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "atdir", dir.getJsonValue());
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
			c.accept(AAPIQueryAlltransclusions.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryAlltransclusions.this);

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
