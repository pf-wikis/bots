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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAlllinksProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAlllinksDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** Enumerate all links that point to a given namespace.
 */
public class AAPIQueryAlllinks
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryAlllinks create() {

		AAPIQueryAlllinks v = new AAPIQueryAlllinks();

		return v;
	}

	private AAPIQueryAlllinks() {}

	private String from;

	private String to;

	private String prefix;

	private Boolean unique;

	private List<AAPIQueryAlllinksProp> prop;

	private NS namespace;

	private Integer limit;

	private AAPIQueryAlllinksDir dir;

	/**The title of the link to start enumerating from.
	 */
	public AAPIQueryAlllinks from(String from) {

		this.from = from;

		return this;
	}

	/**The title of the link to start enumerating from.
	 */
	public String getFrom() {
		return this.from;
	}

	/**The title of the link to stop enumerating at.
	 */
	public AAPIQueryAlllinks to(String to) {

		this.to = to;

		return this;
	}

	/**The title of the link to stop enumerating at.
	 */
	public String getTo() {
		return this.to;
	}

	/**Search for all linked titles that begin with this value.
	 */
	public AAPIQueryAlllinks prefix(String prefix) {

		this.prefix = prefix;

		return this;
	}

	/**Search for all linked titles that begin with this value.
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/**Only show distinct linked titles. Cannot be used with <kbd>alprop=ids</kbd>.
	 * When used as a generator, yields target pages instead of source pages.
	 */
	public AAPIQueryAlllinks unique(Boolean unique) {

		this.unique = unique;

		return this;
	}

	/**Only show distinct linked titles. Cannot be used with <kbd>alprop=ids</kbd>.
	 * When used as a generator, yields target pages instead of source pages.
	 */
	public Boolean getUnique() {
		return this.unique;
	}

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryAlllinks prop(AAPIQueryAlllinksProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryAlllinksProp> getProp() {
		return this.prop;
	}

	/**The namespace to enumerate.
	 */
	public AAPIQueryAlllinks namespace(NS namespace) {

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
	public AAPIQueryAlllinks limit(Integer limit) {

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
	public AAPIQueryAlllinks dir(AAPIQueryAlllinksDir dir) {

		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryAlllinksDir getDir() {
		return this.dir;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryAlllinks(");

		if (from != null) {

			sb.append("alfrom").append("=").append(from);

			sb.append(", ");
		}

		if (to != null) {

			sb.append("alto").append("=").append(to);

			sb.append(", ");
		}

		if (prefix != null) {

			sb.append("alprefix").append("=").append(prefix);

			sb.append(", ");
		}

		if (unique != null) {

			sb.append("alunique").append("=").append(unique.toString());

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("alprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("alnamespace").append("=").append(Integer.toString(namespace.getId()));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("allimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("aldir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (from != null) {

			req.addParameter(paramPrefix + "alfrom", from);
		}

		if (to != null) {

			req.addParameter(paramPrefix + "alto", to);
		}

		if (prefix != null) {

			req.addParameter(paramPrefix + "alprefix", prefix);
		}

		if (unique != null) {

			req.addParameter(paramPrefix + "alunique", unique.toString());
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "alprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (namespace != null) {

			req.addParameter(paramPrefix + "alnamespace", Integer.toString(namespace.getId()));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "allimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "allimit", "5000");
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "aldir", dir.getJsonValue());
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
			c.accept(AAPIQueryAlllinks.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryAlllinks.this);

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
