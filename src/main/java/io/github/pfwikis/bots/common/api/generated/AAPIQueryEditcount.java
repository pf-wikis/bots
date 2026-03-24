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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;

/** List number of edits of given users.
 */
public class AAPIQueryEditcount implements AAPIModule, AAPIQueryListModule {

	public static AAPIQueryEditcount create(@NonNull String user) {

		AAPIQueryEditcount v = new AAPIQueryEditcount();

		v.user = List.of(user);

		return v;
	}

	private AAPIQueryEditcount() {}

	private List<String> user;

	private List<NS> namespace;

	/**The users to retrieve number of edits for.
	 */
	public List<String> getUser() {
		return this.user;
	}

	/**Only list number of edits in these namespaces.
	 */
	public AAPIQueryEditcount namespace(NS namespace) {
		this.namespace = List.of(namespace);

		return this;
	}

	/**Only list number of edits in these namespaces.
	 */
	public AAPIQueryEditcount namespace(NS... namespace) {
		this.namespace = List.of(namespace);
		return this;
	}

	/**Only list number of edits in these namespaces.
	 */
	public List<NS> getNamespace() {
		return this.namespace;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryEditcount(");

		if (user != null) {

			sb.append("ecuser")
					.append("=")
					.append(user.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("ecnamespace")
					.append("=")
					.append(
							namespace.stream()
									.map(v -> Integer.toString(v.getId()))
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (user != null) {

			req.addParameter(
					paramPrefix + "ecuser",
					user.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (namespace != null) {

			req.addParameter(
					paramPrefix + "ecnamespace",
					namespace.stream()
							.map(v -> Integer.toString(v.getId()))
							.collect(Collectors.joining("|")));
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
			c.accept(AAPIQueryEditcount.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryEditcount.this);

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
