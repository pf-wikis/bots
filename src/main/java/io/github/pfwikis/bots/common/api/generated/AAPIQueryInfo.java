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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryInfoProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryInfoTestactionsdetail;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryInfoEditintrostyle;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

/** Get basic page information.
 */
public class AAPIQueryInfo implements AAPIModule, AAPIQueryPropModule {

	public static AAPIQueryInfo create() {

		AAPIQueryInfo v = new AAPIQueryInfo();

		return v;
	}

	private AAPIQueryInfo() {}

	private List<AAPIQueryInfoProp> prop;

	private String linkcontext;

	private List<String> testactions;

	private AAPIQueryInfoTestactionsdetail testactionsdetail;

	private Boolean testactionsautocreate;

	private String preloadcustom;

	private List<String> preloadparams;

	private Boolean preloadnewsection;

	private AAPIQueryInfoEditintrostyle editintrostyle;

	private List<String> editintroskip;

	private String editintrocustom;

	/**<p>Which additional properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryInfo prop(AAPIQueryInfoProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which additional properties to get:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryInfoProp> getProp() {
		return this.prop;
	}

	/**The context title to use when determining extra CSS classes (e.g. link colors) when <var>inprop</var> contains <var>linkclasses</var>.
	 */
	public AAPIQueryInfo linkcontext(String linkcontext) {

		this.linkcontext = linkcontext;

		return this;
	}

	/**The context title to use when determining extra CSS classes (e.g. link colors) when <var>inprop</var> contains <var>linkclasses</var>.
	 */
	public String getLinkcontext() {
		return this.linkcontext;
	}

	/**Test whether the current user can perform certain actions on the page.
	 */
	public AAPIQueryInfo testactions(String... testactions) {

		this.testactions = List.of(testactions);

		return this;
	}

	/**Test whether the current user can perform certain actions on the page.
	 */
	public List<String> getTestactions() {
		return this.testactions;
	}

	/**<p>Detail level for <var>intestactions</var>. Use the <a href="/wiki/Special:ApiHelp/main" title="Special:ApiHelp/main">main module's</a> <var>errorformat</var> and <var>errorlang</var> parameters to control the format of the messages returned.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryInfo testactionsdetail(AAPIQueryInfoTestactionsdetail testactionsdetail) {

		this.testactionsdetail = testactionsdetail;

		return this;
	}

	/**<p>Detail level for <var>intestactions</var>. Use the <a href="/wiki/Special:ApiHelp/main" title="Special:ApiHelp/main">main module's</a> <var>errorformat</var> and <var>errorlang</var> parameters to control the format of the messages returned.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryInfoTestactionsdetail getTestactionsdetail() {
		return this.testactionsdetail;
	}

	/**Test whether performing <var>intestactions</var> would automatically create a temporary account.
	 */
	public AAPIQueryInfo testactionsautocreate(Boolean testactionsautocreate) {

		this.testactionsautocreate = testactionsautocreate;

		return this;
	}

	/**Test whether performing <var>intestactions</var> would automatically create a temporary account.
	 */
	public Boolean getTestactionsautocreate() {
		return this.testactionsautocreate;
	}

	/**Title of a custom page to use as preloaded content.
	 */
	public AAPIQueryInfo preloadcustom(String preloadcustom) {

		this.preloadcustom = preloadcustom;

		return this;
	}

	/**Title of a custom page to use as preloaded content.
	 */
	public String getPreloadcustom() {
		return this.preloadcustom;
	}

	/**Parameters for the custom page being used as preloaded content.
	 */
	public AAPIQueryInfo preloadparams(String... preloadparams) {

		this.preloadparams = List.of(preloadparams);

		return this;
	}

	/**Parameters for the custom page being used as preloaded content.
	 */
	public List<String> getPreloadparams() {
		return this.preloadparams;
	}

	/**Return preloaded content for a new section on the page, rather than a new page.
	 */
	public AAPIQueryInfo preloadnewsection(Boolean preloadnewsection) {

		this.preloadnewsection = preloadnewsection;

		return this;
	}

	/**Return preloaded content for a new section on the page, rather than a new page.
	 */
	public Boolean getPreloadnewsection() {
		return this.preloadnewsection;
	}

	/**Some intro messages come with optional wrapper frames. Use <kbd>moreframes</kbd> to include them or <kbd>lessframes</kbd> to omit them.
	 */
	public AAPIQueryInfo editintrostyle(AAPIQueryInfoEditintrostyle editintrostyle) {

		this.editintrostyle = editintrostyle;

		return this;
	}

	/**Some intro messages come with optional wrapper frames. Use <kbd>moreframes</kbd> to include them or <kbd>lessframes</kbd> to omit them.
	 */
	public AAPIQueryInfoEditintrostyle getEditintrostyle() {
		return this.editintrostyle;
	}

	/**List of intro messages to remove from the response. Use this if a specific message is not relevant to your tool, or if the information is conveyed in a different way.
	 */
	public AAPIQueryInfo editintroskip(String... editintroskip) {

		this.editintroskip = List.of(editintroskip);

		return this;
	}

	/**List of intro messages to remove from the response. Use this if a specific message is not relevant to your tool, or if the information is conveyed in a different way.
	 */
	public List<String> getEditintroskip() {
		return this.editintroskip;
	}

	/**Title of a custom page to use as an additional intro message.
	 */
	public AAPIQueryInfo editintrocustom(String editintrocustom) {

		this.editintrocustom = editintrocustom;

		return this;
	}

	/**Title of a custom page to use as an additional intro message.
	 */
	public String getEditintrocustom() {
		return this.editintrocustom;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryInfo(");

		if (prop != null) {

			sb.append("inprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (linkcontext != null) {

			sb.append("inlinkcontext").append("=").append(linkcontext);

			sb.append(", ");
		}

		if (testactions != null) {

			sb.append("intestactions")
					.append("=")
					.append(testactions.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (testactionsdetail != null) {

			sb.append("intestactionsdetail").append("=").append(testactionsdetail.getJsonValue());

			sb.append(", ");
		}

		if (testactionsautocreate != null) {

			sb.append("intestactionsautocreate")
					.append("=")
					.append(testactionsautocreate.toString());

			sb.append(", ");
		}

		if (preloadcustom != null) {

			sb.append("inpreloadcustom").append("=").append(preloadcustom);

			sb.append(", ");
		}

		if (preloadparams != null) {

			sb.append("inpreloadparams")
					.append("=")
					.append(preloadparams.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (preloadnewsection != null) {

			sb.append("inpreloadnewsection").append("=").append(preloadnewsection.toString());

			sb.append(", ");
		}

		if (editintrostyle != null) {

			sb.append("ineditintrostyle").append("=").append(editintrostyle.getJsonValue());

			sb.append(", ");
		}

		if (editintroskip != null) {

			sb.append("ineditintroskip")
					.append("=")
					.append(editintroskip.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (editintrocustom != null) {

			sb.append("ineditintrocustom").append("=").append(editintrocustom);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "inprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (linkcontext != null) {

			req.addParameter(paramPrefix + "inlinkcontext", linkcontext);
		}

		if (testactions != null) {

			req.addParameter(
					paramPrefix + "intestactions",
					testactions.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (testactionsdetail != null) {

			req.addParameter(paramPrefix + "intestactionsdetail", testactionsdetail.getJsonValue());
		}

		if (testactionsautocreate != null) {

			req.addParameter(
					paramPrefix + "intestactionsautocreate", testactionsautocreate.toString());
		}

		if (preloadcustom != null) {

			req.addParameter(paramPrefix + "inpreloadcustom", preloadcustom);
		}

		if (preloadparams != null) {

			req.addParameter(
					paramPrefix + "inpreloadparams",
					preloadparams.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (preloadnewsection != null) {

			req.addParameter(paramPrefix + "inpreloadnewsection", preloadnewsection.toString());
		}

		if (editintrostyle != null) {

			req.addParameter(paramPrefix + "ineditintrostyle", editintrostyle.getJsonValue());
		}

		if (editintroskip != null) {

			req.addParameter(
					paramPrefix + "ineditintroskip",
					editintroskip.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (editintrocustom != null) {

			req.addParameter(paramPrefix + "ineditintrocustom", editintrocustom);
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
			c.accept(AAPIQueryInfo.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryInfo.this);

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
