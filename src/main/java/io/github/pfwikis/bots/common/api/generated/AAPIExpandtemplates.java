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

import io.github.pfwikis.bots.common.api.generated.params.AAPIExpandtemplatesProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Expands all templates within wikitext.
 */
public class AAPIExpandtemplates implements AAPIModule, AAPIMainActionModule {

	public static AAPIExpandtemplates create(@NonNull String text) {

		AAPIExpandtemplates v = new AAPIExpandtemplates();

		v.text = text;

		return v;
	}

	private AAPIExpandtemplates() {}

	private String title;

	private String text;

	private Long revid;

	private List<AAPIExpandtemplatesProp> prop;

	private Boolean includecomments;

	private Boolean showstrategykeys;

	/**Title of the page.
	 */
	public AAPIExpandtemplates title(String title) {
		this.title = title;

		return this;
	}

	/**Title of the page.
	 */
	public String getTitle() {
		return this.title;
	}

	/**Wikitext to convert.
	 */
	public String getText() {
		return this.text;
	}

	/**Revision ID, for <code>{{REVISIONID}}</code> and similar variables.
	 */
	public AAPIExpandtemplates revid(Long revid) {
		this.revid = revid;

		return this;
	}

	/**Revision ID, for <code>{{REVISIONID}}</code> and similar variables.
	 */
	public Long getRevid() {
		return this.revid;
	}

	/**<p>Which pieces of information to get.
	 * </p><p>Note that if no values are selected, the result will contain the wikitext, but the output will be in a deprecated format.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIExpandtemplates prop(AAPIExpandtemplatesProp prop) {
		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which pieces of information to get.
	 * </p><p>Note that if no values are selected, the result will contain the wikitext, but the output will be in a deprecated format.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIExpandtemplates prop(AAPIExpandtemplatesProp... prop) {
		this.prop = List.of(prop);
		return this;
	}

	/**<p>Which pieces of information to get.
	 * </p><p>Note that if no values are selected, the result will contain the wikitext, but the output will be in a deprecated format.
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIExpandtemplatesProp> getProp() {
		return this.prop;
	}

	/**Whether to include HTML comments in the output.
	 */
	public AAPIExpandtemplates includecomments(Boolean includecomments) {
		this.includecomments = includecomments;

		return this;
	}

	/**Whether to include HTML comments in the output.
	 */
	public Boolean getIncludecomments() {
		return this.includecomments;
	}

	/**Whether to include internal merge strategy information in jsconfigvars.
	 */
	public AAPIExpandtemplates showstrategykeys(Boolean showstrategykeys) {
		this.showstrategykeys = showstrategykeys;

		return this;
	}

	/**Whether to include internal merge strategy information in jsconfigvars.
	 */
	public Boolean getShowstrategykeys() {
		return this.showstrategykeys;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIExpandtemplates(");

		if (title != null) {

			sb.append("title").append("=").append(title);

			sb.append(", ");
		}

		if (text != null) {

			sb.append("text").append("=").append(text);

			sb.append(", ");
		}

		if (revid != null) {

			sb.append("revid").append("=").append(revid.toString());

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("prop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (includecomments != null) {

			sb.append("includecomments").append("=").append(includecomments.toString());

			sb.append(", ");
		}

		if (showstrategykeys != null) {

			sb.append("showstrategykeys").append("=").append(showstrategykeys.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (title != null) {

			req.addParameter(paramPrefix + "title", title);
		}

		if (text != null) {

			req.addParameter(paramPrefix + "text", text);
		}

		if (revid != null) {

			req.addParameter(paramPrefix + "revid", revid.toString());
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "prop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (includecomments != null) {

			req.addParameter(paramPrefix + "includecomments", includecomments.toString());
		}

		if (showstrategykeys != null) {

			req.addParameter(paramPrefix + "showstrategykeys", showstrategykeys.toString());
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
			c.accept(AAPIExpandtemplates.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIExpandtemplates.this);

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
