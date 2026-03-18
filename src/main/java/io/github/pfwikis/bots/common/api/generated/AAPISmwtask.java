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

import io.github.pfwikis.bots.common.api.generated.params.AAPISmwtaskTask;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** API module to execute Semantic MediaWiki related tasks (for internal use only, not for public use).
 */
public class AAPISmwtask implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPISmwtask create(@NonNull AAPISmwtaskTask task) {

		AAPISmwtask v = new AAPISmwtask();

		v.task = task;

		return v;
	}

	private AAPISmwtask() {}

	private AAPISmwtaskTask task;

	private String params;

	private String token;

	/**Defines the task type
	 */
	public AAPISmwtaskTask getTask() {
		return this.task;
	}

	/**JSON encoded parameters that match the selected task type requirement
	 */
	public AAPISmwtask params(String params) {

		this.params = params;

		return this;
	}

	/**JSON encoded parameters that match the selected task type requirement
	 */
	public String getParams() {
		return this.params;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPISmwtask token(String token) {

		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPISmwtask(");

		if (task != null) {

			sb.append("task").append("=").append(task.getJsonValue());

			sb.append(", ");
		}

		if (params != null) {

			sb.append("params").append("=").append(params);

			sb.append(", ");
		}

		if (token != null) {

			sb.append("token").append("=").append(token);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (task != null) {

			req.addParameter(paramPrefix + "task", task.getJsonValue());
		}

		if (params != null) {

			req.addParameter(paramPrefix + "params", params);
		}

		token = api.requestToken(AAPIQueryTokensType.CSRF);

		if (token != null) {

			req.addParameter(paramPrefix + "token", token);
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
			c.accept(AAPISmwtask.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPISmwtask.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return true;
		}

		@Override
		protected boolean internalRequiresPagination() {
			return true;
		}
	}
}
