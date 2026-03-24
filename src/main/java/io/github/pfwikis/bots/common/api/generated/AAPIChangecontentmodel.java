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

import io.github.pfwikis.bots.common.api.generated.params.AAPIChangecontentmodelModel;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Change the content model of a page
 */
public class AAPIChangecontentmodel implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIChangecontentmodel create(@NonNull AAPIChangecontentmodelModel model) {

		AAPIChangecontentmodel v = new AAPIChangecontentmodel();

		v.model = model;

		return v;
	}

	private AAPIChangecontentmodel() {}

	private String title;

	private Long pageid;

	private String summary;

	private List<String> tags;

	private AAPIChangecontentmodelModel model;

	private Boolean bot;

	private String token;

	/**Title of the page to change the contentmodel of. Cannot be used together with <var>pageid</var>.
	 */
	public AAPIChangecontentmodel title(String title) {
		this.title = title;

		return this;
	}

	/**Title of the page to change the contentmodel of. Cannot be used together with <var>pageid</var>.
	 */
	public String getTitle() {
		return this.title;
	}

	/**Page ID of the page to change the contentmodel of. Cannot be used together with <var>title</var>.
	 */
	public AAPIChangecontentmodel pageid(Long pageid) {
		this.pageid = pageid;

		return this;
	}

	/**Page ID of the page to change the contentmodel of. Cannot be used together with <var>title</var>.
	 */
	public Long getPageid() {
		return this.pageid;
	}

	/**Edit summary and log entry reason
	 */
	public AAPIChangecontentmodel summary(String summary) {
		this.summary = summary;

		return this;
	}

	/**Edit summary and log entry reason
	 */
	public String getSummary() {
		return this.summary;
	}

	/**Change tags to apply to the log entry and edit.
	 */
	public AAPIChangecontentmodel tags(String tags) {
		this.tags = List.of(tags);

		return this;
	}

	/**Change tags to apply to the log entry and edit.
	 */
	public AAPIChangecontentmodel tags(String... tags) {
		this.tags = List.of(tags);
		return this;
	}

	/**Change tags to apply to the log entry and edit.
	 */
	public List<String> getTags() {
		return this.tags;
	}

	/**Content model of the new content.
	 */
	public AAPIChangecontentmodelModel getModel() {
		return this.model;
	}

	/**Mark the content model change with a bot flag.
	 */
	public AAPIChangecontentmodel bot(Boolean bot) {
		this.bot = bot;

		return this;
	}

	/**Mark the content model change with a bot flag.
	 */
	public Boolean getBot() {
		return this.bot;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIChangecontentmodel token(String token) {
		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIChangecontentmodel(");

		if (title != null) {

			sb.append("title").append("=").append(title);

			sb.append(", ");
		}

		if (pageid != null) {

			sb.append("pageid").append("=").append(pageid.toString());

			sb.append(", ");
		}

		if (summary != null) {

			sb.append("summary").append("=").append(summary);

			sb.append(", ");
		}

		if (tags != null) {

			sb.append("tags")
					.append("=")
					.append(tags.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (model != null) {

			sb.append("model").append("=").append(model.getJsonValue());

			sb.append(", ");
		}

		if (bot != null) {

			sb.append("bot").append("=").append(bot.toString());

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

		if (title != null) {

			req.addParameter(paramPrefix + "title", title);
		}

		if (pageid != null) {

			req.addParameter(paramPrefix + "pageid", pageid.toString());
		}

		if (summary != null) {

			req.addParameter(paramPrefix + "summary", summary);
		}

		if (tags != null) {

			req.addParameter(
					paramPrefix + "tags",
					tags.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (model != null) {

			req.addParameter(paramPrefix + "model", model.getJsonValue());
		}

		if (bot != null) {

			req.addParameter(paramPrefix + "bot", bot.toString());
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
			c.accept(AAPIChangecontentmodel.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIChangecontentmodel.this);

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
