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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryExturlusageProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryExturlusageProtocol;

import io.github.pfwikis.bots.common.api.generated.params.AAPIImagerotateGenerator.AAPIImagerotateGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPISetnotificationtimestampGenerator.AAPISetnotificationtimestampGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIWatchGenerator.AAPIWatchGeneratorModule;

/** Enumerate pages that contain a given URL.
 */
public class AAPIQueryExturlusage
		implements AAPIModule,
				AAPIImagerotateGeneratorModule,
				AAPIPurgeGeneratorModule,
				AAPIQueryListModule,
				AAPIQueryGeneratorModule,
				AAPISetnotificationtimestampGeneratorModule,
				AAPITemplatedataGeneratorModule,
				AAPIWatchGeneratorModule {

	public static AAPIQueryExturlusage create() {

		AAPIQueryExturlusage v = new AAPIQueryExturlusage();

		return v;
	}

	private AAPIQueryExturlusage() {}

	private List<AAPIQueryExturlusageProp> prop;

	private AAPIQueryExturlusageProtocol protocol;

	private String query;

	private List<NS> namespace;

	private Integer limit = 5000;

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryExturlusage prop(AAPIQueryExturlusageProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which pieces of information to include:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryExturlusageProp> getProp() {
		return this.prop;
	}

	/**Protocol of the URL. If empty and <var>euquery</var> is set, the protocol is <kbd>http</kbd> and <kbd>https</kbd>. Leave both this and <var>euquery</var> empty to list all external links.
	 */
	public AAPIQueryExturlusage protocol(AAPIQueryExturlusageProtocol protocol) {

		this.protocol = protocol;

		return this;
	}

	/**Protocol of the URL. If empty and <var>euquery</var> is set, the protocol is <kbd>http</kbd> and <kbd>https</kbd>. Leave both this and <var>euquery</var> empty to list all external links.
	 */
	public AAPIQueryExturlusageProtocol getProtocol() {
		return this.protocol;
	}

	/**Search string without protocol. See <a href="/wiki/Special:LinkSearch" title="Special:LinkSearch">Special:LinkSearch</a>. Leave empty to list all external links.
	 */
	public AAPIQueryExturlusage query(String query) {

		this.query = query;

		return this;
	}

	/**Search string without protocol. See <a href="/wiki/Special:LinkSearch" title="Special:LinkSearch">Special:LinkSearch</a>. Leave empty to list all external links.
	 */
	public String getQuery() {
		return this.query;
	}

	/**The page namespaces to enumerate.
	 */
	public AAPIQueryExturlusage namespace(NS... namespace) {

		this.namespace = List.of(namespace);

		return this;
	}

	/**The page namespaces to enumerate.
	 */
	public List<NS> getNamespace() {
		return this.namespace;
	}

	/**How many pages to return.
	 */
	public AAPIQueryExturlusage limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**How many pages to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryExturlusage(");

		if (prop != null) {

			sb.append("euprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (protocol != null) {

			sb.append("euprotocol").append("=").append(protocol.getJsonValue());

			sb.append(", ");
		}

		if (query != null) {

			sb.append("euquery").append("=").append(query);

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("eunamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("eulimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "euprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (protocol != null) {

			req.addParameter(paramPrefix + "euprotocol", protocol.getJsonValue());
		}

		if (query != null) {

			req.addParameter(paramPrefix + "euquery", query);
		}

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "eunamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "eulimit", limit.toString());
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
			c.accept(AAPIQueryExturlusage.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryExturlusage.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
