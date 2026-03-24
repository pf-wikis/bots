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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryExtlinksProtocol;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

/** Returns all external URLs (not interwikis) from the given pages.
 */
public class AAPIQueryExtlinks implements AAPIModule, AAPIQueryPropModule {

	public static AAPIQueryExtlinks create() {

		AAPIQueryExtlinks v = new AAPIQueryExtlinks();

		return v;
	}

	private AAPIQueryExtlinks() {}

	private Integer limit;

	private AAPIQueryExtlinksProtocol protocol;

	private String query;

	/**How many links to return.
	 */
	public AAPIQueryExtlinks limit(Integer limit) {
		this.limit = limit;

		return this;
	}

	/**How many links to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**Protocol of the URL. If empty and <var>elquery</var> is set, the protocol is <kbd>http</kbd> and <kbd>https</kbd>. Leave both this and <var>elquery</var> empty to list all external links.
	 */
	public AAPIQueryExtlinks protocol(AAPIQueryExtlinksProtocol protocol) {
		this.protocol = protocol;

		return this;
	}

	/**Protocol of the URL. If empty and <var>elquery</var> is set, the protocol is <kbd>http</kbd> and <kbd>https</kbd>. Leave both this and <var>elquery</var> empty to list all external links.
	 */
	public AAPIQueryExtlinksProtocol getProtocol() {
		return this.protocol;
	}

	/**Search string without protocol. Useful for checking whether a certain page contains a certain external url.
	 */
	public AAPIQueryExtlinks query(String query) {
		this.query = query;

		return this;
	}

	/**Search string without protocol. Useful for checking whether a certain page contains a certain external url.
	 */
	public String getQuery() {
		return this.query;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryExtlinks(");

		if (limit != null) {

			sb.append("ellimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (protocol != null) {

			sb.append("elprotocol").append("=").append(protocol.getJsonValue());

			sb.append(", ");
		}

		if (query != null) {

			sb.append("elquery").append("=").append(query);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (limit != null) {

			req.addParameter(paramPrefix + "ellimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "ellimit", "5000");
		}

		if (protocol != null) {

			req.addParameter(paramPrefix + "elprotocol", protocol.getJsonValue());
		}

		if (query != null) {

			req.addParameter(paramPrefix + "elquery", query);
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
			c.accept(AAPIQueryExtlinks.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryExtlinks.this);

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
