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

import io.github.pfwikis.bots.common.api.generated.params.AAPIManagetagsOperation;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Perform management tasks relating to change tags.
 */
public class AAPIManagetags implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIManagetags create(
			@NonNull AAPIManagetagsOperation operation, @NonNull String tag) {

		AAPIManagetags v = new AAPIManagetags();

		v.operation = operation;

		v.tag = tag;

		return v;
	}

	private AAPIManagetags() {}

	private AAPIManagetagsOperation operation;

	private String tag;

	private String reason;

	private Boolean ignorewarnings;

	private List<String> tags;

	private String token;

	/**<p>Which operation to perform:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIManagetagsOperation getOperation() {
		return this.operation;
	}

	/**Tag to create, delete, activate or deactivate. For tag creation, the tag must not exist. For tag deletion, the tag must exist. For tag activation, the tag must exist and not be in use by an extension. For tag deactivation, the tag must be currently active and manually defined.
	 */
	public String getTag() {
		return this.tag;
	}

	/**An optional reason for creating, deleting, activating or deactivating the tag.
	 */
	public AAPIManagetags reason(String reason) {
		this.reason = reason;

		return this;
	}

	/**An optional reason for creating, deleting, activating or deactivating the tag.
	 */
	public String getReason() {
		return this.reason;
	}

	/**Whether to ignore any warnings that are issued during the operation.
	 */
	public AAPIManagetags ignorewarnings(Boolean ignorewarnings) {
		this.ignorewarnings = ignorewarnings;

		return this;
	}

	/**Whether to ignore any warnings that are issued during the operation.
	 */
	public Boolean getIgnorewarnings() {
		return this.ignorewarnings;
	}

	/**Change tags to apply to the entry in the tag management log.
	 */
	public AAPIManagetags tags(String tags) {
		this.tags = List.of(tags);

		return this;
	}

	/**Change tags to apply to the entry in the tag management log.
	 */
	public AAPIManagetags tags(String... tags) {
		this.tags = List.of(tags);
		return this;
	}

	/**Change tags to apply to the entry in the tag management log.
	 */
	public List<String> getTags() {
		return this.tags;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIManagetags token(String token) {
		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIManagetags(");

		if (operation != null) {

			sb.append("operation").append("=").append(operation.getJsonValue());

			sb.append(", ");
		}

		if (tag != null) {

			sb.append("tag").append("=").append(tag);

			sb.append(", ");
		}

		if (reason != null) {

			sb.append("reason").append("=").append(reason);

			sb.append(", ");
		}

		if (ignorewarnings != null) {

			sb.append("ignorewarnings").append("=").append(ignorewarnings.toString());

			sb.append(", ");
		}

		if (tags != null) {

			sb.append("tags")
					.append("=")
					.append(tags.stream().map(v -> v).collect(Collectors.joining("|")));

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

		if (operation != null) {

			req.addParameter(paramPrefix + "operation", operation.getJsonValue());
		}

		if (tag != null) {

			req.addParameter(paramPrefix + "tag", tag);
		}

		if (reason != null) {

			req.addParameter(paramPrefix + "reason", reason);
		}

		if (ignorewarnings != null) {

			req.addParameter(paramPrefix + "ignorewarnings", ignorewarnings.toString());
		}

		if (tags != null) {

			req.addParameter(
					paramPrefix + "tags",
					tags.stream().map(v -> v).collect(Collectors.joining("|")));
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
			c.accept(AAPIManagetags.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIManagetags.this);

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
