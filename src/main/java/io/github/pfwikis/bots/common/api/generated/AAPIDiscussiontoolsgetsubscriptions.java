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

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Get the subscription statuses of given topics.
 */
public class AAPIDiscussiontoolsgetsubscriptions implements AAPIModule, AAPIMainActionModule {

	public static AAPIDiscussiontoolsgetsubscriptions create(@NonNull String... commentname) {

		AAPIDiscussiontoolsgetsubscriptions v = new AAPIDiscussiontoolsgetsubscriptions();

		v.commentname = List.of(commentname);

		return v;
	}

	private AAPIDiscussiontoolsgetsubscriptions() {}

	private List<String> commentname;

	/**Names of the topics to check
	 */
	public List<String> getCommentname() {
		return this.commentname;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIDiscussiontoolsgetsubscriptions(");

		if (commentname != null) {

			sb.append("commentname")
					.append("=")
					.append(commentname.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (commentname != null) {

			req.addParameter(
					paramPrefix + "commentname",
					commentname.stream().map(v -> v).collect(Collectors.joining("|")));
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
			c.accept(AAPIDiscussiontoolsgetsubscriptions.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIDiscussiontoolsgetsubscriptions.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
