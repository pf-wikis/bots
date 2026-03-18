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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryTemplatesDir;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** Returns all pages transcluded on the given pages.
 */
public class AAPIQueryTemplates
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryPropModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryTemplates create() {

		AAPIQueryTemplates v = new AAPIQueryTemplates();

		return v;
	}

	private AAPIQueryTemplates() {}

	private List<NS> namespace;

	private Integer limit;

	private List<String> templates;

	private AAPIQueryTemplatesDir dir;

	/**Show templates in these namespaces only.
	 */
	public AAPIQueryTemplates namespace(NS... namespace) {

		this.namespace = List.of(namespace);

		return this;
	}

	/**Show templates in these namespaces only.
	 */
	public List<NS> getNamespace() {
		return this.namespace;
	}

	/**How many templates to return.
	 */
	public AAPIQueryTemplates limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many templates to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**Only list these templates. Useful for checking whether a certain page uses a certain template.
	 */
	public AAPIQueryTemplates templates(String... templates) {

		this.templates = List.of(templates);

		return this;
	}

	/**Only list these templates. Useful for checking whether a certain page uses a certain template.
	 */
	public List<String> getTemplates() {
		return this.templates;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryTemplates dir(AAPIQueryTemplatesDir dir) {

		this.dir = dir;

		return this;
	}

	/**The direction in which to list.
	 */
	public AAPIQueryTemplatesDir getDir() {
		return this.dir;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryTemplates(");

		if (namespace != null) {

			sb.append("tlnamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("tllimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (templates != null) {

			sb.append("tltemplates")
					.append("=")
					.append(templates.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (dir != null) {

			sb.append("tldir").append("=").append(dir.getJsonValue());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "tlnamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "tllimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "tllimit", "5000");
		}

		if (templates != null) {

			req.addParameter(
					paramPrefix + "tltemplates",
					templates.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (dir != null) {

			req.addParameter(paramPrefix + "tldir", dir.getJsonValue());
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
			c.accept(AAPIQueryTemplates.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryTemplates.this);

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
