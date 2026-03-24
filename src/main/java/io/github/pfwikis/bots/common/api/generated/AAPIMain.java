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

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;
import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionSubmodule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainFormat;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainFormat.AAPIMainFormatModule;
import io.github.pfwikis.bots.common.api.generated.params.AAPIMainFormat.AAPIMainFormatSubmodule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAssert;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainErrorformat;

/**
 * <div class="hlist plainlinks api-main-links">
 * <ul><li><a href="/w/index.php?title=Mw:Special:MyLanguage/API:Main_page&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/API:Main page (page does not exist)">Documentation</a></li>
 * <li><a href="/w/index.php?title=Mw:Special:MyLanguage/API:Etiquette&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/API:Etiquette (page does not exist)">Etiquette &amp; usage guidelines</a></li>
 * <li><a href="/w/index.php?title=Mw:Special:MyLanguage/API:FAQ&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/API:FAQ (page does not exist)">FAQ</a></li>
 * <li><a target="_blank" rel="nofollow noreferrer noopener" class="external text" href="https://lists.wikimedia.org/postorius/lists/mediawiki-api.lists.wikimedia.org/">Mailing list</a></li>
 * <li><a target="_blank" rel="nofollow noreferrer noopener" class="external text" href="https://lists.wikimedia.org/postorius/lists/mediawiki-api-announce.lists.wikimedia.org/">API Announcements</a></li>
 * <li><a target="_blank" rel="nofollow noreferrer noopener" class="external text" href="https://phabricator.wikimedia.org/maniphest/query/GebfyV4uCaLd/#R">Bugs &amp; requests</a></li></ul>
 * </div>
 * <p><strong>Status:</strong> The MediaWiki API is a mature and stable interface that is actively supported and improved. While we try to avoid it, we may occasionally need to make breaking changes; subscribe to <a target="_blank" rel="nofollow noreferrer noopener" class="external text" href="https://lists.wikimedia.org/hyperkitty/list/mediawiki-api-announce@lists.wikimedia.org/">the mediawiki-api-announce mailing list</a> for notice of updates.
 * </p><p><strong>Erroneous requests:</strong> When erroneous requests are sent to the API, an HTTP header will be sent with the key "MediaWiki-API-Error" and then both the value of the header and the error code sent back will be set to the same value. For more information see <a href="/w/index.php?title=Mw:Special:MyLanguage/API:Errors_and_warnings&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/API:Errors and warnings (page does not exist)">API: Errors and warnings</a>.
 * </p>
 * <p class="mw-apisandbox-link"><strong>Testing:</strong> For ease of testing API requests, see <a href="/wiki/Special:ApiSandbox" title="Special:ApiSandbox">Special:ApiSandbox</a>.</p>
 */
public class AAPIMain implements AAPIModule {

	public static AAPIMain create() {

		AAPIMain v = new AAPIMain();

		return v;
	}

	private AAPIMain() {}

	private AAPIMainActionSubmodule action;

	private AAPIMainFormatSubmodule format;

	private Long maxlag;

	private Long smaxage;

	private Long maxage;

	private AAPIMainAssert _assert;

	private String assertuser;

	private String requestid;

	private Boolean servedby;

	private Boolean curtimestamp;

	private Boolean responselanginfo;

	private String origin;

	private String uselang;

	private String variant;

	private AAPIMainErrorformat errorformat;

	private String errorlang;

	private Boolean errorsuselocal;

	/**<p>Which action to perform.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIMain action(AAPIMainActionModule action) {
		this.action = AAPIMainAction.createSubmodule(action);

		return this;
	}

	/**<p>Which action to perform.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIMainActionSubmodule getAction() {
		return this.action;
	}

	/**<p>The format of the output.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIMain format(AAPIMainFormatModule format) {
		this.format = AAPIMainFormat.createSubmodule(format);

		return this;
	}

	/**<p>The format of the output.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIMainFormatSubmodule getFormat() {
		return this.format;
	}

	/**Maximum lag can be used when MediaWiki is installed on a database replicated cluster. To save actions causing any more site replication lag, this parameter can make the client wait until the replication lag is less than the specified value. In case of excessive lag, error code <samp>maxlag</samp> is returned with a message like <samp>Waiting for $host: $lag seconds lagged</samp>.<br />See <a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:Maxlag_parameter&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:Maxlag parameter (page does not exist)">Manual: Maxlag parameter</a> for more information.
	 */
	public AAPIMain maxlag(Long maxlag) {
		this.maxlag = maxlag;

		return this;
	}

	/**Maximum lag can be used when MediaWiki is installed on a database replicated cluster. To save actions causing any more site replication lag, this parameter can make the client wait until the replication lag is less than the specified value. In case of excessive lag, error code <samp>maxlag</samp> is returned with a message like <samp>Waiting for $host: $lag seconds lagged</samp>.<br />See <a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:Maxlag_parameter&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:Maxlag parameter (page does not exist)">Manual: Maxlag parameter</a> for more information.
	 */
	public Long getMaxlag() {
		return this.maxlag;
	}

	/**Set the <code>s-maxage</code> HTTP cache control header to this many seconds. Errors are never cached.
	 */
	public AAPIMain smaxage(Long smaxage) {
		this.smaxage = smaxage;

		return this;
	}

	/**Set the <code>s-maxage</code> HTTP cache control header to this many seconds. Errors are never cached.
	 */
	public Long getSmaxage() {
		return this.smaxage;
	}

	/**Set the <code>max-age</code> HTTP cache control header to this many seconds. Errors are never cached.
	 */
	public AAPIMain maxage(Long maxage) {
		this.maxage = maxage;

		return this;
	}

	/**Set the <code>max-age</code> HTTP cache control header to this many seconds. Errors are never cached.
	 */
	public Long getMaxage() {
		return this.maxage;
	}

	/**Verify that the user is logged in (including possibly as a temporary user) if set to <kbd>user</kbd>, <em>not</em> logged in if set to <kbd>anon</kbd>, or has the bot user right if <kbd>bot</kbd>.
	 */
	public AAPIMain _assert(AAPIMainAssert _assert) {
		this._assert = _assert;

		return this;
	}

	/**Verify that the user is logged in (including possibly as a temporary user) if set to <kbd>user</kbd>, <em>not</em> logged in if set to <kbd>anon</kbd>, or has the bot user right if <kbd>bot</kbd>.
	 */
	public AAPIMainAssert get_assert() {
		return this._assert;
	}

	/**Verify the current user is the named user.
	 */
	public AAPIMain assertuser(String assertuser) {
		this.assertuser = assertuser;

		return this;
	}

	/**Verify the current user is the named user.
	 */
	public String getAssertuser() {
		return this.assertuser;
	}

	/**Any value given here will be included in the response. May be used to distinguish requests.
	 */
	public AAPIMain requestid(String requestid) {
		this.requestid = requestid;

		return this;
	}

	/**Any value given here will be included in the response. May be used to distinguish requests.
	 */
	public String getRequestid() {
		return this.requestid;
	}

	/**Include the hostname that served the request in the results.
	 */
	public AAPIMain servedby(Boolean servedby) {
		this.servedby = servedby;

		return this;
	}

	/**Include the hostname that served the request in the results.
	 */
	public Boolean getServedby() {
		return this.servedby;
	}

	/**Include the current timestamp in the result.
	 */
	public AAPIMain curtimestamp(Boolean curtimestamp) {
		this.curtimestamp = curtimestamp;

		return this;
	}

	/**Include the current timestamp in the result.
	 */
	public Boolean getCurtimestamp() {
		return this.curtimestamp;
	}

	/**Include the languages used for <var>uselang</var> and <var>errorlang</var> in the result.
	 */
	public AAPIMain responselanginfo(Boolean responselanginfo) {
		this.responselanginfo = responselanginfo;

		return this;
	}

	/**Include the languages used for <var>uselang</var> and <var>errorlang</var> in the result.
	 */
	public Boolean getResponselanginfo() {
		return this.responselanginfo;
	}

	/**<p>When accessing the API using a cross-domain AJAX request (CORS), set this to the originating domain. This must be included in any pre-flight request, and therefore must be part of the request URI (not the POST body).
	 * </p><p>For authenticated requests, this must match one of the origins in the <code>Origin</code> header exactly, so it has to be set to something like <kbd><a target="_blank" rel="nofollow noreferrer noopener" class="external free" href="https://en.wikipedia.org">https://en.wikipedia.org</a></kbd> or <kbd><a target="_blank" rel="nofollow noreferrer noopener" class="external free" href="https://meta.wikimedia.org">https://meta.wikimedia.org</a></kbd>. If this parameter does not match the <code>Origin</code> header, a 403 response will be returned. If this parameter matches the <code>Origin</code> header and the origin is allowed, the <code>Access-Control-Allow-Origin</code> and <code>Access-Control-Allow-Credentials</code> headers will be set.
	 * </p><p>For non-authenticated requests, specify the value <kbd>*</kbd>. This will cause the <code>Access-Control-Allow-Origin</code> header to be set, but <code>Access-Control-Allow-Credentials</code> will be <code>false</code> and all user-specific data will be restricted.
	 * </p>
	 */
	public AAPIMain origin(String origin) {
		this.origin = origin;

		return this;
	}

	/**<p>When accessing the API using a cross-domain AJAX request (CORS), set this to the originating domain. This must be included in any pre-flight request, and therefore must be part of the request URI (not the POST body).
	 * </p><p>For authenticated requests, this must match one of the origins in the <code>Origin</code> header exactly, so it has to be set to something like <kbd><a target="_blank" rel="nofollow noreferrer noopener" class="external free" href="https://en.wikipedia.org">https://en.wikipedia.org</a></kbd> or <kbd><a target="_blank" rel="nofollow noreferrer noopener" class="external free" href="https://meta.wikimedia.org">https://meta.wikimedia.org</a></kbd>. If this parameter does not match the <code>Origin</code> header, a 403 response will be returned. If this parameter matches the <code>Origin</code> header and the origin is allowed, the <code>Access-Control-Allow-Origin</code> and <code>Access-Control-Allow-Credentials</code> headers will be set.
	 * </p><p>For non-authenticated requests, specify the value <kbd>*</kbd>. This will cause the <code>Access-Control-Allow-Origin</code> header to be set, but <code>Access-Control-Allow-Credentials</code> will be <code>false</code> and all user-specific data will be restricted.
	 * </p>
	 */
	public String getOrigin() {
		return this.origin;
	}

	/**Language to use for message translations. <kbd><a href="/wiki/Special:ApiHelp/query%2Bsiteinfo" title="Special:ApiHelp/query+siteinfo">action=query&amp;meta=siteinfo&amp;siprop=languages</a></kbd> returns a list of language codes. You can specify <kbd>user</kbd> to use the current user's language preference or <kbd>content</kbd> to use this wiki's content language.
	 */
	public AAPIMain uselang(String uselang) {
		this.uselang = uselang;

		return this;
	}

	/**Language to use for message translations. <kbd><a href="/wiki/Special:ApiHelp/query%2Bsiteinfo" title="Special:ApiHelp/query+siteinfo">action=query&amp;meta=siteinfo&amp;siprop=languages</a></kbd> returns a list of language codes. You can specify <kbd>user</kbd> to use the current user's language preference or <kbd>content</kbd> to use this wiki's content language.
	 */
	public String getUselang() {
		return this.uselang;
	}

	/**Variant of the language. Only works if the base language supports variant conversion.
	 */
	public AAPIMain variant(String variant) {
		this.variant = variant;

		return this;
	}

	/**Variant of the language. Only works if the base language supports variant conversion.
	 */
	public String getVariant() {
		return this.variant;
	}

	/**<p>Format to use for warning and error text output
	 * </p>
	 * <dl></dl>
	 */
	public AAPIMain errorformat(AAPIMainErrorformat errorformat) {
		this.errorformat = errorformat;

		return this;
	}

	/**<p>Format to use for warning and error text output
	 * </p>
	 * <dl></dl>
	 */
	public AAPIMainErrorformat getErrorformat() {
		return this.errorformat;
	}

	/**Language to use for warnings and errors. <kbd><a href="/wiki/Special:ApiHelp/query%2Bsiteinfo" title="Special:ApiHelp/query+siteinfo">action=query&amp;meta=siteinfo&amp;siprop=languages</a></kbd> returns a list of language codes. Specify <kbd>content</kbd> to use this wiki's content language or <kbd>uselang</kbd> to use the same value as the <var>uselang</var> parameter.
	 */
	public AAPIMain errorlang(String errorlang) {
		this.errorlang = errorlang;

		return this;
	}

	/**Language to use for warnings and errors. <kbd><a href="/wiki/Special:ApiHelp/query%2Bsiteinfo" title="Special:ApiHelp/query+siteinfo">action=query&amp;meta=siteinfo&amp;siprop=languages</a></kbd> returns a list of language codes. Specify <kbd>content</kbd> to use this wiki's content language or <kbd>uselang</kbd> to use the same value as the <var>uselang</var> parameter.
	 */
	public String getErrorlang() {
		return this.errorlang;
	}

	/**If given, error texts will use locally-customized messages from the MediaWiki namespace.
	 */
	public AAPIMain errorsuselocal(Boolean errorsuselocal) {
		this.errorsuselocal = errorsuselocal;

		return this;
	}

	/**If given, error texts will use locally-customized messages from the MediaWiki namespace.
	 */
	public Boolean getErrorsuselocal() {
		return this.errorsuselocal;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIMain(");

		if (action != null) {

			sb.append("action").append("=").append(action.getValue().toString());

			sb.append(", ");
		}

		if (format != null) {

			sb.append("format").append("=").append(format.getValue().toString());

			sb.append(", ");
		}

		if (maxlag != null) {

			sb.append("maxlag").append("=").append(maxlag.toString());

			sb.append(", ");
		}

		if (smaxage != null) {

			sb.append("smaxage").append("=").append(smaxage.toString());

			sb.append(", ");
		}

		if (maxage != null) {

			sb.append("maxage").append("=").append(maxage.toString());

			sb.append(", ");
		}

		if (_assert != null) {

			sb.append("assert").append("=").append(_assert.getJsonValue());

			sb.append(", ");
		}

		if (assertuser != null) {

			sb.append("assertuser").append("=").append(assertuser);

			sb.append(", ");
		}

		if (requestid != null) {

			sb.append("requestid").append("=").append(requestid);

			sb.append(", ");
		}

		if (servedby != null) {

			sb.append("servedby").append("=").append(servedby.toString());

			sb.append(", ");
		}

		if (curtimestamp != null) {

			sb.append("curtimestamp").append("=").append(curtimestamp.toString());

			sb.append(", ");
		}

		if (responselanginfo != null) {

			sb.append("responselanginfo").append("=").append(responselanginfo.toString());

			sb.append(", ");
		}

		if (origin != null) {

			sb.append("origin").append("=").append(origin);

			sb.append(", ");
		}

		if (uselang != null) {

			sb.append("uselang").append("=").append(uselang);

			sb.append(", ");
		}

		if (variant != null) {

			sb.append("variant").append("=").append(variant);

			sb.append(", ");
		}

		if (errorformat != null) {

			sb.append("errorformat").append("=").append(errorformat.getJsonValue());

			sb.append(", ");
		}

		if (errorlang != null) {

			sb.append("errorlang").append("=").append(errorlang);

			sb.append(", ");
		}

		if (errorsuselocal != null) {

			sb.append("errorsuselocal").append("=").append(errorsuselocal.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (action != null) {

			req.addParameter(paramPrefix + "action", action.getKey().getJsonValue());

			action.getValue().buildRequest(api, req, paramPrefix);
		}

		if (format != null) {

			req.addParameter(paramPrefix + "format", format.getKey().getJsonValue());

			format.getValue().buildRequest(api, req, paramPrefix);
		}

		if (maxlag != null) {

			req.addParameter(paramPrefix + "maxlag", maxlag.toString());
		}

		if (smaxage != null) {

			req.addParameter(paramPrefix + "smaxage", smaxage.toString());
		}

		if (maxage != null) {

			req.addParameter(paramPrefix + "maxage", maxage.toString());
		}

		if (_assert != null) {

			req.addParameter(paramPrefix + "assert", _assert.getJsonValue());
		}

		if (assertuser != null) {

			req.addParameter(paramPrefix + "assertuser", assertuser);
		}

		if (requestid != null) {

			req.addParameter(paramPrefix + "requestid", requestid);
		}

		if (servedby != null) {

			req.addParameter(paramPrefix + "servedby", servedby.toString());
		}

		if (curtimestamp != null) {

			req.addParameter(paramPrefix + "curtimestamp", curtimestamp.toString());
		}

		if (responselanginfo != null) {

			req.addParameter(paramPrefix + "responselanginfo", responselanginfo.toString());
		}

		if (origin != null) {

			req.addParameter(paramPrefix + "origin", origin);
		}

		if (uselang != null) {

			req.addParameter(paramPrefix + "uselang", uselang);
		}

		if (variant != null) {

			req.addParameter(paramPrefix + "variant", variant);
		}

		if (errorformat != null) {

			req.addParameter(paramPrefix + "errorformat", errorformat.getJsonValue());
		}

		if (errorlang != null) {

			req.addParameter(paramPrefix + "errorlang", errorlang);
		}

		if (errorsuselocal != null) {

			req.addParameter(paramPrefix + "errorsuselocal", errorsuselocal.toString());
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
			c.accept(AAPIMain.this);

			if (action != null) {

				action.getValue().builder().forEachModule(c);
			}

			if (format != null) {

				format.getValue().builder().forEachModule(c);
			}
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIMain.this);

			if (action != null) {

				value = reduce.apply(value, action.getValue().builder().mapReduce(map, reduce));
			}

			if (format != null) {

				value = reduce.apply(value, format.getValue().builder().mapReduce(map, reduce));
			}

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
