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

import io.github.pfwikis.bots.common.api.generated.params.AAPIVeforallParsoidUtilsFrom;

import io.github.pfwikis.bots.common.api.generated.params.AAPIVeforallParsoidUtilsTo;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Convert text back and forth from Wikitext to HTML
 */
public class AAPIVeforallParsoidUtils implements AAPIModule, AAPIMainActionModule {

	public static AAPIVeforallParsoidUtils create(
			@NonNull AAPIVeforallParsoidUtilsFrom from,
			@NonNull AAPIVeforallParsoidUtilsTo to,
			@NonNull String content) {

		AAPIVeforallParsoidUtils v = new AAPIVeforallParsoidUtils();

		v.from = from;

		v.to = to;

		v.content = content;

		return v;
	}

	private AAPIVeforallParsoidUtils() {}

	private AAPIVeforallParsoidUtilsFrom from;

	private AAPIVeforallParsoidUtilsTo to;

	private String content;

	private String title;

	private Long pageid;

	/**'html' or 'wikitext'
	 */
	public AAPIVeforallParsoidUtilsFrom getFrom() {
		return this.from;
	}

	/**'html' or 'wikitext'
	 */
	public AAPIVeforallParsoidUtilsTo getTo() {
		return this.to;
	}

	/**content to be converted
	 */
	public String getContent() {
		return this.content;
	}

	/**title of the page holding the content
	 */
	public AAPIVeforallParsoidUtils title(String title) {
		this.title = title;

		return this;
	}

	/**title of the page holding the content
	 */
	public String getTitle() {
		return this.title;
	}

	/**page ID of the page holding the content
	 */
	public AAPIVeforallParsoidUtils pageid(Long pageid) {
		this.pageid = pageid;

		return this;
	}

	/**page ID of the page holding the content
	 */
	public Long getPageid() {
		return this.pageid;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIVeforallParsoidUtils(");

		if (from != null) {

			sb.append("from").append("=").append(from.getJsonValue());

			sb.append(", ");
		}

		if (to != null) {

			sb.append("to").append("=").append(to.getJsonValue());

			sb.append(", ");
		}

		if (content != null) {

			sb.append("content").append("=").append(content);

			sb.append(", ");
		}

		if (title != null) {

			sb.append("title").append("=").append(title);

			sb.append(", ");
		}

		if (pageid != null) {

			sb.append("pageid").append("=").append(pageid.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (from != null) {

			req.addParameter(paramPrefix + "from", from.getJsonValue());
		}

		if (to != null) {

			req.addParameter(paramPrefix + "to", to.getJsonValue());
		}

		if (content != null) {

			req.addParameter(paramPrefix + "content", content);
		}

		if (title != null) {

			req.addParameter(paramPrefix + "title", title);
		}

		if (pageid != null) {

			req.addParameter(paramPrefix + "pageid", pageid.toString());
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
			c.accept(AAPIVeforallParsoidUtils.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIVeforallParsoidUtils.this);

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
