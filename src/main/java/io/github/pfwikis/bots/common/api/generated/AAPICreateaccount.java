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

import io.github.pfwikis.bots.common.api.generated.params.AAPICreateaccountMessageformat;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** <p>Create a new user account.
 * </p>
 *
 * <p>The general procedure to use this module is:
 * </p>
 * <ol><li>Fetch the fields available from <kbd><a href="/wiki/Special:ApiHelp/query%2Bauthmanagerinfo" title="Special:ApiHelp/query+authmanagerinfo">action=query&amp;meta=authmanagerinfo</a></kbd> with <kbd>amirequestsfor=create</kbd>, and a <kbd>createaccount</kbd> token from <kbd><a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a></kbd>.</li>
 * <li>Present the fields to the user, and obtain their submission.</li>
 * <li>Post to this module, supplying <var>createreturnurl</var> and any relevant fields.</li>
 * <li>Check the <samp>status</samp> in the response.
 * <ul><li>If you received <samp>PASS</samp> or <samp>FAIL</samp>, you're done. The operation either succeeded or it didn't.</li>
 * <li>If you received <samp>UI</samp>, present the new fields to the user and obtain their submission. Then post to this module with <var>createcontinue</var> and the relevant fields set, and repeat step 4.</li>
 * <li>If you received <samp>REDIRECT</samp>, direct the user to the <samp>redirecttarget</samp> and wait for the return to <var>createreturnurl</var>. Then post to this module with <var>createcontinue</var> and any fields passed to the return URL, and repeat step 4.</li>
 * <li>If you received <samp>RESTART</samp>, that means the authentication worked but we don't have a linked user account. You might treat this as <samp>UI</samp> or as <samp>FAIL</samp>.</li></ul></li></ol>
 */
public class AAPICreateaccount implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPICreateaccount create() {

		AAPICreateaccount v = new AAPICreateaccount();

		return v;
	}

	private AAPICreateaccount() {}

	private List<String> requests;

	private AAPICreateaccountMessageformat messageformat;

	private Boolean mergerequestfields;

	private Boolean preservestate;

	private String returnurl;

	private String token;

	/**Only use these authentication requests, by the <samp>id</samp> returned from <kbd><a href="/wiki/Special:ApiHelp/query%2Bauthmanagerinfo" title="Special:ApiHelp/query+authmanagerinfo">action=query&amp;meta=authmanagerinfo</a></kbd> with <kbd>amirequestsfor=create</kbd> or from a previous response from this module.
	 */
	public AAPICreateaccount requests(String... requests) {

		this.requests = List.of(requests);

		return this;
	}

	/**Only use these authentication requests, by the <samp>id</samp> returned from <kbd><a href="/wiki/Special:ApiHelp/query%2Bauthmanagerinfo" title="Special:ApiHelp/query+authmanagerinfo">action=query&amp;meta=authmanagerinfo</a></kbd> with <kbd>amirequestsfor=create</kbd> or from a previous response from this module.
	 */
	public List<String> getRequests() {
		return this.requests;
	}

	/**Format to use for returning messages.
	 */
	public AAPICreateaccount messageformat(AAPICreateaccountMessageformat messageformat) {

		this.messageformat = messageformat;

		return this;
	}

	/**Format to use for returning messages.
	 */
	public AAPICreateaccountMessageformat getMessageformat() {
		return this.messageformat;
	}

	/**Merge field information for all authentication requests into one array.
	 */
	public AAPICreateaccount mergerequestfields(Boolean mergerequestfields) {

		this.mergerequestfields = mergerequestfields;

		return this;
	}

	/**Merge field information for all authentication requests into one array.
	 */
	public Boolean getMergerequestfields() {
		return this.mergerequestfields;
	}

	/**<p>Preserve state from a previous failed login attempt, if possible.
	 * </p>
	 * <p>If <kbd><a href="/wiki/Special:ApiHelp/query%2Bauthmanagerinfo" title="Special:ApiHelp/query+authmanagerinfo">action=query&amp;meta=authmanagerinfo</a></kbd> returned true for <samp>hasprimarypreservedstate</samp>, requests marked as <samp>primary-required</samp> should be omitted. If it returned a non-empty value for <samp>preservedusername</samp>, that username must be used for the <var>username</var> parameter.
	 * </p>
	 */
	public AAPICreateaccount preservestate(Boolean preservestate) {

		this.preservestate = preservestate;

		return this;
	}

	/**<p>Preserve state from a previous failed login attempt, if possible.
	 * </p>
	 * <p>If <kbd><a href="/wiki/Special:ApiHelp/query%2Bauthmanagerinfo" title="Special:ApiHelp/query+authmanagerinfo">action=query&amp;meta=authmanagerinfo</a></kbd> returned true for <samp>hasprimarypreservedstate</samp>, requests marked as <samp>primary-required</samp> should be omitted. If it returned a non-empty value for <samp>preservedusername</samp>, that username must be used for the <var>username</var> parameter.
	 * </p>
	 */
	public Boolean getPreservestate() {
		return this.preservestate;
	}

	/**<p>Return URL for third-party authentication flows, must be absolute. Either this or <var>createcontinue</var> is required.
	 * </p><p>Upon receiving a <samp>REDIRECT</samp> response, you will typically open a browser or web view to the specified <samp>redirecttarget</samp> URL for a third-party authentication flow. When that completes, the third party will send the browser or web view to this URL. You should extract any query or POST parameters from the URL and pass them as a <var>createcontinue</var> request to this API module.
	 * </p>
	 */
	public AAPICreateaccount returnurl(String returnurl) {

		this.returnurl = returnurl;

		return this;
	}

	/**<p>Return URL for third-party authentication flows, must be absolute. Either this or <var>createcontinue</var> is required.
	 * </p><p>Upon receiving a <samp>REDIRECT</samp> response, you will typically open a browser or web view to the specified <samp>redirecttarget</samp> URL for a third-party authentication flow. When that completes, the third party will send the browser or web view to this URL. You should extract any query or POST parameters from the URL and pass them as a <var>createcontinue</var> request to this API module.
	 * </p>
	 */
	public String getReturnurl() {
		return this.returnurl;
	}

	/**A "createaccount" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPICreateaccount token(String token) {

		this.token = token;

		return this;
	}

	/**A "createaccount" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPICreateaccount(");

		if (requests != null) {

			sb.append("createrequests")
					.append("=")
					.append(requests.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (messageformat != null) {

			sb.append("createmessageformat").append("=").append(messageformat.getJsonValue());

			sb.append(", ");
		}

		if (mergerequestfields != null) {

			sb.append("createmergerequestfields").append("=").append(mergerequestfields.toString());

			sb.append(", ");
		}

		if (preservestate != null) {

			sb.append("createpreservestate").append("=").append(preservestate.toString());

			sb.append(", ");
		}

		if (returnurl != null) {

			sb.append("createreturnurl").append("=").append(returnurl);

			sb.append(", ");
		}

		if (token != null) {

			sb.append("createtoken").append("=").append(token);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (requests != null) {

			req.addParameter(
					paramPrefix + "createrequests",
					requests.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (messageformat != null) {

			req.addParameter(paramPrefix + "createmessageformat", messageformat.getJsonValue());
		}

		if (mergerequestfields != null) {

			req.addParameter(
					paramPrefix + "createmergerequestfields", mergerequestfields.toString());
		}

		if (preservestate != null) {

			req.addParameter(paramPrefix + "createpreservestate", preservestate.toString());
		}

		if (returnurl != null) {

			req.addParameter(paramPrefix + "createreturnurl", returnurl);
		}

		token = api.requestToken(AAPIQueryTokensType.CREATEACCOUNT);

		if (token != null) {

			req.addParameter(paramPrefix + "createtoken", token);
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
			c.accept(AAPICreateaccount.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPICreateaccount.this);

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
