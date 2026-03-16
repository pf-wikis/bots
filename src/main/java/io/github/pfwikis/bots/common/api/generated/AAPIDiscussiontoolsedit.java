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

import io.github.pfwikis.bots.common.api.generated.params.AAPIDiscussiontoolseditPaction;

import io.github.pfwikis.bots.common.api.generated.params.AAPIDiscussiontoolseditAutosubscribe;

import io.github.pfwikis.bots.common.api.generated.params.AAPIDiscussiontoolseditUseskin;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Post a message on a discussion page.
 */
public class AAPIDiscussiontoolsedit implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIDiscussiontoolsedit create(
			@NonNull AAPIDiscussiontoolseditPaction paction, @NonNull String page) {

		AAPIDiscussiontoolsedit v = new AAPIDiscussiontoolsedit();

		v.paction = paction;

		v.page = page;

		return v;
	}

	private AAPIDiscussiontoolsedit() {}

	private AAPIDiscussiontoolseditPaction paction;

	private AAPIDiscussiontoolseditAutosubscribe autosubscribe;

	private String page;

	private String token;

	private String formtoken;

	private String commentname;

	private String commentid;

	private String wikitext;

	private String html;

	private String summary;

	private String sectiontitle;

	private Boolean allownosectiontitle;

	private AAPIDiscussiontoolseditUseskin useskin;

	private String watchlist;

	private String captchaid;

	private String captchaword;

	private String nocontent;

	private List<String> tags;

	private String returnto;

	private String returntoquery;

	private String returntoanchor;

	/**<p>Action to perform.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIDiscussiontoolseditPaction getPaction() {
		return this.paction;
	}

	/**Automatically subscribe the user to the talk page thread?
	 */
	public AAPIDiscussiontoolsedit autosubscribe(
			AAPIDiscussiontoolseditAutosubscribe autosubscribe) {

		this.autosubscribe = autosubscribe;

		return this;
	}

	/**Automatically subscribe the user to the talk page thread?
	 */
	public AAPIDiscussiontoolseditAutosubscribe getAutosubscribe() {
		return this.autosubscribe;
	}

	/**The page to perform actions on.
	 */
	public String getPage() {
		return this.page;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIDiscussiontoolsedit token(String token) {

		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	/**An optional unique ID generated in the client to prevent double-posting.
	 */
	public AAPIDiscussiontoolsedit formtoken(String formtoken) {

		this.formtoken = formtoken;

		return this;
	}

	/**An optional unique ID generated in the client to prevent double-posting.
	 */
	public String getFormtoken() {
		return this.formtoken;
	}

	/**Name of the comment to reply to. Only used when <var>paction</var> is <var>addcomment</var>.
	 */
	public AAPIDiscussiontoolsedit commentname(String commentname) {

		this.commentname = commentname;

		return this;
	}

	/**Name of the comment to reply to. Only used when <var>paction</var> is <var>addcomment</var>.
	 */
	public String getCommentname() {
		return this.commentname;
	}

	/**ID of the comment to reply to. Only used when <var>paction</var> is <var>addcomment</var>. Overrides <var>commentname</var>.
	 */
	public AAPIDiscussiontoolsedit commentid(String commentid) {

		this.commentid = commentid;

		return this;
	}

	/**ID of the comment to reply to. Only used when <var>paction</var> is <var>addcomment</var>. Overrides <var>commentname</var>.
	 */
	public String getCommentid() {
		return this.commentid;
	}

	/**Content to post, as wikitext. Cannot be used together with <var>html</var>.
	 */
	public AAPIDiscussiontoolsedit wikitext(String wikitext) {

		this.wikitext = wikitext;

		return this;
	}

	/**Content to post, as wikitext. Cannot be used together with <var>html</var>.
	 */
	public String getWikitext() {
		return this.wikitext;
	}

	/**Content to post, as HTML. Cannot be used together with <var>wikitext</var>.
	 */
	public AAPIDiscussiontoolsedit html(String html) {

		this.html = html;

		return this;
	}

	/**Content to post, as HTML. Cannot be used together with <var>wikitext</var>.
	 */
	public String getHtml() {
		return this.html;
	}

	/**Edit summary.
	 */
	public AAPIDiscussiontoolsedit summary(String summary) {

		this.summary = summary;

		return this;
	}

	/**Edit summary.
	 */
	public String getSummary() {
		return this.summary;
	}

	/**The title for a new section when using <var>$1section=new</var>. Only used when <var>paction</var> is <var>addtopic</var>.
	 */
	public AAPIDiscussiontoolsedit sectiontitle(String sectiontitle) {

		this.sectiontitle = sectiontitle;

		return this;
	}

	/**The title for a new section when using <var>$1section=new</var>. Only used when <var>paction</var> is <var>addtopic</var>.
	 */
	public String getSectiontitle() {
		return this.sectiontitle;
	}

	/**Allow posting a new section without a title.
	 */
	public AAPIDiscussiontoolsedit allownosectiontitle(Boolean allownosectiontitle) {

		this.allownosectiontitle = allownosectiontitle;

		return this;
	}

	/**Allow posting a new section without a title.
	 */
	public Boolean getAllownosectiontitle() {
		return this.allownosectiontitle;
	}

	/**Apply the selected skin to the parser output. May affect the following properties: <kbd>text</kbd>, <kbd>langlinks</kbd>, <kbd>headitems</kbd>, <kbd>modules</kbd>, <kbd>jsconfigvars</kbd>, <kbd>indicators</kbd>.
	 */
	public AAPIDiscussiontoolsedit useskin(AAPIDiscussiontoolseditUseskin useskin) {

		this.useskin = useskin;

		return this;
	}

	/**Apply the selected skin to the parser output. May affect the following properties: <kbd>text</kbd>, <kbd>langlinks</kbd>, <kbd>headitems</kbd>, <kbd>modules</kbd>, <kbd>jsconfigvars</kbd>, <kbd>indicators</kbd>.
	 */
	public AAPIDiscussiontoolseditUseskin getUseskin() {
		return this.useskin;
	}

	/**Unconditionally add or remove the page from the current user's watchlist, use preferences (ignored for bot users) or do not change watch.
	 */
	public AAPIDiscussiontoolsedit watchlist(String watchlist) {

		this.watchlist = watchlist;

		return this;
	}

	/**Unconditionally add or remove the page from the current user's watchlist, use preferences (ignored for bot users) or do not change watch.
	 */
	public String getWatchlist() {
		return this.watchlist;
	}

	/**Captcha ID (when saving with a captcha response).
	 */
	public AAPIDiscussiontoolsedit captchaid(String captchaid) {

		this.captchaid = captchaid;

		return this;
	}

	/**Captcha ID (when saving with a captcha response).
	 */
	public String getCaptchaid() {
		return this.captchaid;
	}

	/**Answer to the captcha (when saving with a captcha response).
	 */
	public AAPIDiscussiontoolsedit captchaword(String captchaword) {

		this.captchaword = captchaword;

		return this;
	}

	/**Answer to the captcha (when saving with a captcha response).
	 */
	public String getCaptchaword() {
		return this.captchaword;
	}

	/**Omit the HTML content of the new revision in the response.
	 */
	public AAPIDiscussiontoolsedit nocontent(String nocontent) {

		this.nocontent = nocontent;

		return this;
	}

	/**Omit the HTML content of the new revision in the response.
	 */
	public String getNocontent() {
		return this.nocontent;
	}

	/**Change tags to apply to the edit.
	 */
	public AAPIDiscussiontoolsedit tags(String... tags) {

		this.tags = List.of(tags);

		return this;
	}

	/**Change tags to apply to the edit.
	 */
	public List<String> getTags() {
		return this.tags;
	}

	/**Page title. If saving the edit created a temporary account, the API may respond with an URL that the client should visit to complete logging in. If this parameter is provided, the URL will redirect to the given page, instead of the page that was edited.
	 */
	public AAPIDiscussiontoolsedit returnto(String returnto) {

		this.returnto = returnto;

		return this;
	}

	/**Page title. If saving the edit created a temporary account, the API may respond with an URL that the client should visit to complete logging in. If this parameter is provided, the URL will redirect to the given page, instead of the page that was edited.
	 */
	public String getReturnto() {
		return this.returnto;
	}

	/**URL query parameters (with leading <kbd>?</kbd>). If saving the edit created a temporary account, the API may respond with an URL that the client should visit to complete logging in. If this parameter is provided, the URL will redirect to a page with the given query parameters.
	 */
	public AAPIDiscussiontoolsedit returntoquery(String returntoquery) {

		this.returntoquery = returntoquery;

		return this;
	}

	/**URL query parameters (with leading <kbd>?</kbd>). If saving the edit created a temporary account, the API may respond with an URL that the client should visit to complete logging in. If this parameter is provided, the URL will redirect to a page with the given query parameters.
	 */
	public String getReturntoquery() {
		return this.returntoquery;
	}

	/**URL fragment (with leading <kbd>#</kbd>). If saving the edit created a temporary account, the API may respond with an URL that the client should visit to complete logging in. If this parameter is provided, the URL will redirect to a page with the given fragment.
	 */
	public AAPIDiscussiontoolsedit returntoanchor(String returntoanchor) {

		this.returntoanchor = returntoanchor;

		return this;
	}

	/**URL fragment (with leading <kbd>#</kbd>). If saving the edit created a temporary account, the API may respond with an URL that the client should visit to complete logging in. If this parameter is provided, the URL will redirect to a page with the given fragment.
	 */
	public String getReturntoanchor() {
		return this.returntoanchor;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIDiscussiontoolsedit(");

		if (paction != null) {

			sb.append("paction").append("=").append(paction.getJsonValue());

			sb.append(", ");
		}

		if (autosubscribe != null) {

			sb.append("autosubscribe").append("=").append(autosubscribe.getJsonValue());

			sb.append(", ");
		}

		if (page != null) {

			sb.append("page").append("=").append(page);

			sb.append(", ");
		}

		if (token != null) {

			sb.append("token").append("=").append(token);

			sb.append(", ");
		}

		if (formtoken != null) {

			sb.append("formtoken").append("=").append(formtoken);

			sb.append(", ");
		}

		if (commentname != null) {

			sb.append("commentname").append("=").append(commentname);

			sb.append(", ");
		}

		if (commentid != null) {

			sb.append("commentid").append("=").append(commentid);

			sb.append(", ");
		}

		if (wikitext != null) {

			sb.append("wikitext").append("=").append(wikitext);

			sb.append(", ");
		}

		if (html != null) {

			sb.append("html").append("=").append(html);

			sb.append(", ");
		}

		if (summary != null) {

			sb.append("summary").append("=").append(summary);

			sb.append(", ");
		}

		if (sectiontitle != null) {

			sb.append("sectiontitle").append("=").append(sectiontitle);

			sb.append(", ");
		}

		if (allownosectiontitle != null) {

			sb.append("allownosectiontitle").append("=").append(allownosectiontitle.toString());

			sb.append(", ");
		}

		if (useskin != null) {

			sb.append("useskin").append("=").append(useskin.getJsonValue());

			sb.append(", ");
		}

		if (watchlist != null) {

			sb.append("watchlist").append("=").append(watchlist);

			sb.append(", ");
		}

		if (captchaid != null) {

			sb.append("captchaid").append("=").append(captchaid);

			sb.append(", ");
		}

		if (captchaword != null) {

			sb.append("captchaword").append("=").append(captchaword);

			sb.append(", ");
		}

		if (nocontent != null) {

			sb.append("nocontent").append("=").append(nocontent);

			sb.append(", ");
		}

		if (tags != null) {

			sb.append("tags")
					.append("=")
					.append(tags.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (returnto != null) {

			sb.append("returnto").append("=").append(returnto);

			sb.append(", ");
		}

		if (returntoquery != null) {

			sb.append("returntoquery").append("=").append(returntoquery);

			sb.append(", ");
		}

		if (returntoanchor != null) {

			sb.append("returntoanchor").append("=").append(returntoanchor);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (paction != null) {

			req.addParameter(paramPrefix + "paction", paction.getJsonValue());
		}

		if (autosubscribe != null) {

			req.addParameter(paramPrefix + "autosubscribe", autosubscribe.getJsonValue());
		}

		if (page != null) {

			req.addParameter(paramPrefix + "page", page);
		}

		token = api.requestToken(AAPIQueryTokensType.CSRF);

		if (token != null) {

			req.addParameter(paramPrefix + "token", token);
		}

		if (formtoken != null) {

			req.addParameter(paramPrefix + "formtoken", formtoken);
		}

		if (commentname != null) {

			req.addParameter(paramPrefix + "commentname", commentname);
		}

		if (commentid != null) {

			req.addParameter(paramPrefix + "commentid", commentid);
		}

		if (wikitext != null) {

			req.addParameter(paramPrefix + "wikitext", wikitext);
		}

		if (html != null) {

			req.addParameter(paramPrefix + "html", html);
		}

		if (summary != null) {

			req.addParameter(paramPrefix + "summary", summary);
		}

		if (sectiontitle != null) {

			req.addParameter(paramPrefix + "sectiontitle", sectiontitle);
		}

		if (allownosectiontitle != null) {

			req.addParameter(paramPrefix + "allownosectiontitle", allownosectiontitle.toString());
		}

		if (useskin != null) {

			req.addParameter(paramPrefix + "useskin", useskin.getJsonValue());
		}

		if (watchlist != null) {

			req.addParameter(paramPrefix + "watchlist", watchlist);
		}

		if (captchaid != null) {

			req.addParameter(paramPrefix + "captchaid", captchaid);
		}

		if (captchaword != null) {

			req.addParameter(paramPrefix + "captchaword", captchaword);
		}

		if (nocontent != null) {

			req.addParameter(paramPrefix + "nocontent", nocontent);
		}

		if (tags != null) {

			req.addParameter(
					paramPrefix + "tags",
					tags.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (returnto != null) {

			req.addParameter(paramPrefix + "returnto", returnto);
		}

		if (returntoquery != null) {

			req.addParameter(paramPrefix + "returntoquery", returntoquery);
		}

		if (returntoanchor != null) {

			req.addParameter(paramPrefix + "returntoanchor", returntoanchor);
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
			c.accept(AAPIDiscussiontoolsedit.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIDiscussiontoolsedit.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return true;
		}
	}
}
