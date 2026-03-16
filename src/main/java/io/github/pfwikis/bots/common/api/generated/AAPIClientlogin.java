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

import io.github.pfwikis.bots.common.api.generated.params.AAPIClientloginMessageformat;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** <p>Log in to the wiki using the interactive flow.
 * </p>
 *
 * <p>The general procedure to use this module is:
 * </p>
 * <ol><li>Fetch the fields available from <kbd><a href="/wiki/Special:ApiHelp/query%2Bauthmanagerinfo" title="Special:ApiHelp/query+authmanagerinfo">action=query&amp;meta=authmanagerinfo</a></kbd> with <kbd>amirequestsfor=login</kbd>, and a <kbd>login</kbd> token from <kbd><a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a></kbd>.</li>
 * <li>Present the fields to the user, and obtain their submission.</li>
 * <li>Post to this module, supplying <var>loginreturnurl</var> and any relevant fields.</li>
 * <li>Check the <samp>status</samp> in the response.
 * <ul><li>If you received <samp>PASS</samp> or <samp>FAIL</samp>, you're done. The operation either succeeded or it didn't.</li>
 * <li>If you received <samp>UI</samp>, present the new fields to the user and obtain their submission. Then post to this module with <var>logincontinue</var> and the relevant fields set, and repeat step 4.</li>
 * <li>If you received <samp>REDIRECT</samp>, direct the user to the <samp>redirecttarget</samp> and wait for the return to <var>loginreturnurl</var>. Then post to this module with <var>logincontinue</var> and any fields passed to the return URL, and repeat step 4.</li>
 * <li>If you received <samp>RESTART</samp>, that means the authentication worked but we don't have a linked user account. You might treat this as <samp>UI</samp> or as <samp>FAIL</samp>.</li></ul></li></ol>
 */
public class AAPIClientlogin implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIClientlogin create() {

		AAPIClientlogin v = new AAPIClientlogin();

		return v;
	}

	private AAPIClientlogin() {}

	private List<String> requests;

	private AAPIClientloginMessageformat messageformat;

	private Boolean mergerequestfields;

	private Boolean preservestate;

	private String returnurl;

	private String token;

	/**Only use these authentication requests, by the <samp>id</samp> returned from <kbd><a href="/wiki/Special:ApiHelp/query%2Bauthmanagerinfo" title="Special:ApiHelp/query+authmanagerinfo">action=query&amp;meta=authmanagerinfo</a></kbd> with <kbd>amirequestsfor=login</kbd> or from a previous response from this module.
	 */
	public AAPIClientlogin requests(String requests) {
		this.requests = List.of(requests);

		return this;
	}

	/**Only use these authentication requests, by the <samp>id</samp> returned from <kbd><a href="/wiki/Special:ApiHelp/query%2Bauthmanagerinfo" title="Special:ApiHelp/query+authmanagerinfo">action=query&amp;meta=authmanagerinfo</a></kbd> with <kbd>amirequestsfor=login</kbd> or from a previous response from this module.
	 */
	public AAPIClientlogin requests(String... requests) {
		this.requests = List.of(requests);
		return this;
	}

	/**Only use these authentication requests, by the <samp>id</samp> returned from <kbd><a href="/wiki/Special:ApiHelp/query%2Bauthmanagerinfo" title="Special:ApiHelp/query+authmanagerinfo">action=query&amp;meta=authmanagerinfo</a></kbd> with <kbd>amirequestsfor=login</kbd> or from a previous response from this module.
	 */
	public List<String> getRequests() {
		return this.requests;
	}

	/**Format to use for returning messages.
	 */
	public AAPIClientlogin messageformat(AAPIClientloginMessageformat messageformat) {
		this.messageformat = messageformat;

		return this;
	}

	/**Format to use for returning messages.
	 */
	public AAPIClientloginMessageformat getMessageformat() {
		return this.messageformat;
	}

	/**Merge field information for all authentication requests into one array.
	 */
	public AAPIClientlogin mergerequestfields(Boolean mergerequestfields) {
		this.mergerequestfields = mergerequestfields;

		return this;
	}

	/**Merge field information for all authentication requests into one array.
	 */
	public Boolean getMergerequestfields() {
		return this.mergerequestfields;
	}

	/**Preserve state from a previous failed login attempt, if possible.
	 */
	public AAPIClientlogin preservestate(Boolean preservestate) {
		this.preservestate = preservestate;

		return this;
	}

	/**Preserve state from a previous failed login attempt, if possible.
	 */
	public Boolean getPreservestate() {
		return this.preservestate;
	}

	/**<p>Return URL for third-party authentication flows, must be absolute. Either this or <var>logincontinue</var> is required.
	 * </p><p>Upon receiving a <samp>REDIRECT</samp> response, you will typically open a browser or web view to the specified <samp>redirecttarget</samp> URL for a third-party authentication flow. When that completes, the third party will send the browser or web view to this URL. You should extract any query or POST parameters from the URL and pass them as a <var>logincontinue</var> request to this API module.
	 * </p>
	 */
	public AAPIClientlogin returnurl(String returnurl) {
		this.returnurl = returnurl;

		return this;
	}

	/**<p>Return URL for third-party authentication flows, must be absolute. Either this or <var>logincontinue</var> is required.
	 * </p><p>Upon receiving a <samp>REDIRECT</samp> response, you will typically open a browser or web view to the specified <samp>redirecttarget</samp> URL for a third-party authentication flow. When that completes, the third party will send the browser or web view to this URL. You should extract any query or POST parameters from the URL and pass them as a <var>logincontinue</var> request to this API module.
	 * </p>
	 */
	public String getReturnurl() {
		return this.returnurl;
	}

	/**A "login" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIClientlogin token(String token) {
		this.token = token;

		return this;
	}

	/**A "login" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIClientlogin(");

		if (requests != null) {

			sb.append("loginrequests")
					.append("=")
					.append(requests.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (messageformat != null) {

			sb.append("loginmessageformat").append("=").append(messageformat.getJsonValue());

			sb.append(", ");
		}

		if (mergerequestfields != null) {

			sb.append("loginmergerequestfields").append("=").append(mergerequestfields.toString());

			sb.append(", ");
		}

		if (preservestate != null) {

			sb.append("loginpreservestate").append("=").append(preservestate.toString());

			sb.append(", ");
		}

		if (returnurl != null) {

			sb.append("loginreturnurl").append("=").append(returnurl);

			sb.append(", ");
		}

		if (token != null) {

			sb.append("logintoken").append("=").append(token);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (requests != null) {

			req.addParameter(
					paramPrefix + "loginrequests",
					requests.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (messageformat != null) {

			req.addParameter(paramPrefix + "loginmessageformat", messageformat.getJsonValue());
		}

		if (mergerequestfields != null) {

			req.addParameter(
					paramPrefix + "loginmergerequestfields", mergerequestfields.toString());
		}

		if (preservestate != null) {

			req.addParameter(paramPrefix + "loginpreservestate", preservestate.toString());
		}

		if (returnurl != null) {

			req.addParameter(paramPrefix + "loginreturnurl", returnurl);
		}

		token = api.requestToken(AAPIQueryTokensType.LOGIN);

		if (token != null) {

			req.addParameter(paramPrefix + "logintoken", token);
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
			c.accept(AAPIClientlogin.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIClientlogin.this);

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
