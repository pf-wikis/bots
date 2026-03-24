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

import io.github.pfwikis.bots.common.api.generated.params.AAPILinkaccountMessageformat;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** <p>Link an account from a third-party provider to the current user.
 * </p>
 *
 * <p>The general procedure to use this module is:
 * </p>
 * <ol><li>Fetch the fields available from <kbd><a href="/wiki/Special:ApiHelp/query%2Bauthmanagerinfo" title="Special:ApiHelp/query+authmanagerinfo">action=query&amp;meta=authmanagerinfo</a></kbd> with <kbd>amirequestsfor=link</kbd>, and a <kbd>csrf</kbd> token from <kbd><a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a></kbd>.</li>
 * <li>Present the fields to the user, and obtain their submission.</li>
 * <li>Post to this module, supplying <var>linkreturnurl</var> and any relevant fields.</li>
 * <li>Check the <samp>status</samp> in the response.
 * <ul><li>If you received <samp>PASS</samp> or <samp>FAIL</samp>, you're done. The operation either succeeded or it didn't.</li>
 * <li>If you received <samp>UI</samp>, present the new fields to the user and obtain their submission. Then post to this module with <var>linkcontinue</var> and the relevant fields set, and repeat step 4.</li>
 * <li>If you received <samp>REDIRECT</samp>, direct the user to the <samp>redirecttarget</samp> and wait for the return to <var>linkreturnurl</var>. Then post to this module with <var>linkcontinue</var> and any fields passed to the return URL, and repeat step 4.</li>
 * <li>If you received <samp>RESTART</samp>, that means the authentication worked but we don't have a linked user account. You might treat this as <samp>UI</samp> or as <samp>FAIL</samp>.</li></ul></li></ol>
 */
public class AAPILinkaccount implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPILinkaccount create() {

		AAPILinkaccount v = new AAPILinkaccount();

		return v;
	}

	private AAPILinkaccount() {}

	private List<String> requests;

	private AAPILinkaccountMessageformat messageformat;

	private Boolean mergerequestfields;

	private String returnurl;

	private String token;

	/**Only use these authentication requests, by the <samp>id</samp> returned from <kbd><a href="/wiki/Special:ApiHelp/query%2Bauthmanagerinfo" title="Special:ApiHelp/query+authmanagerinfo">action=query&amp;meta=authmanagerinfo</a></kbd> with <kbd>amirequestsfor=link</kbd> or from a previous response from this module.
	 */
	public AAPILinkaccount requests(String requests) {
		this.requests = List.of(requests);

		return this;
	}

	/**Only use these authentication requests, by the <samp>id</samp> returned from <kbd><a href="/wiki/Special:ApiHelp/query%2Bauthmanagerinfo" title="Special:ApiHelp/query+authmanagerinfo">action=query&amp;meta=authmanagerinfo</a></kbd> with <kbd>amirequestsfor=link</kbd> or from a previous response from this module.
	 */
	public AAPILinkaccount requests(String... requests) {
		this.requests = List.of(requests);
		return this;
	}

	/**Only use these authentication requests, by the <samp>id</samp> returned from <kbd><a href="/wiki/Special:ApiHelp/query%2Bauthmanagerinfo" title="Special:ApiHelp/query+authmanagerinfo">action=query&amp;meta=authmanagerinfo</a></kbd> with <kbd>amirequestsfor=link</kbd> or from a previous response from this module.
	 */
	public List<String> getRequests() {
		return this.requests;
	}

	/**Format to use for returning messages.
	 */
	public AAPILinkaccount messageformat(AAPILinkaccountMessageformat messageformat) {
		this.messageformat = messageformat;

		return this;
	}

	/**Format to use for returning messages.
	 */
	public AAPILinkaccountMessageformat getMessageformat() {
		return this.messageformat;
	}

	/**Merge field information for all authentication requests into one array.
	 */
	public AAPILinkaccount mergerequestfields(Boolean mergerequestfields) {
		this.mergerequestfields = mergerequestfields;

		return this;
	}

	/**Merge field information for all authentication requests into one array.
	 */
	public Boolean getMergerequestfields() {
		return this.mergerequestfields;
	}

	/**<p>Return URL for third-party authentication flows, must be absolute. Either this or <var>linkcontinue</var> is required.
	 * </p><p>Upon receiving a <samp>REDIRECT</samp> response, you will typically open a browser or web view to the specified <samp>redirecttarget</samp> URL for a third-party authentication flow. When that completes, the third party will send the browser or web view to this URL. You should extract any query or POST parameters from the URL and pass them as a <var>linkcontinue</var> request to this API module.
	 * </p>
	 */
	public AAPILinkaccount returnurl(String returnurl) {
		this.returnurl = returnurl;

		return this;
	}

	/**<p>Return URL for third-party authentication flows, must be absolute. Either this or <var>linkcontinue</var> is required.
	 * </p><p>Upon receiving a <samp>REDIRECT</samp> response, you will typically open a browser or web view to the specified <samp>redirecttarget</samp> URL for a third-party authentication flow. When that completes, the third party will send the browser or web view to this URL. You should extract any query or POST parameters from the URL and pass them as a <var>linkcontinue</var> request to this API module.
	 * </p>
	 */
	public String getReturnurl() {
		return this.returnurl;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPILinkaccount token(String token) {
		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPILinkaccount(");

		if (requests != null) {

			sb.append("linkrequests")
					.append("=")
					.append(requests.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (messageformat != null) {

			sb.append("linkmessageformat").append("=").append(messageformat.getJsonValue());

			sb.append(", ");
		}

		if (mergerequestfields != null) {

			sb.append("linkmergerequestfields").append("=").append(mergerequestfields.toString());

			sb.append(", ");
		}

		if (returnurl != null) {

			sb.append("linkreturnurl").append("=").append(returnurl);

			sb.append(", ");
		}

		if (token != null) {

			sb.append("linktoken").append("=").append(token);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (requests != null) {

			req.addParameter(
					paramPrefix + "linkrequests",
					requests.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (messageformat != null) {

			req.addParameter(paramPrefix + "linkmessageformat", messageformat.getJsonValue());
		}

		if (mergerequestfields != null) {

			req.addParameter(paramPrefix + "linkmergerequestfields", mergerequestfields.toString());
		}

		if (returnurl != null) {

			req.addParameter(paramPrefix + "linkreturnurl", returnurl);
		}

		token = api.requestToken(AAPIQueryTokensType.CSRF);

		if (token != null) {

			req.addParameter(paramPrefix + "linktoken", token);
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
			c.accept(AAPILinkaccount.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPILinkaccount.this);

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
