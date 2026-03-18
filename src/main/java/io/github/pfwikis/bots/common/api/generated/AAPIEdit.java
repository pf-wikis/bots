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

import io.github.pfwikis.bots.common.api.generated.params.AAPIEditWatchlist;

import io.github.pfwikis.bots.common.api.generated.params.AAPIEditContentformat;

import io.github.pfwikis.bots.common.api.generated.params.AAPIEditContentmodel;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Create and edit pages.
 */
public class AAPIEdit implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIEdit create(@NonNull ContainsPageRef title) {

		AAPIEdit v = new AAPIEdit();

		v.title = title;

		return v;
	}

	private AAPIEdit() {}

	private String section;

	private String sectiontitle;

	private String text;

	private String summary;

	private List<String> tags;

	private Boolean minor;

	private Boolean notminor;

	private Boolean bot;

	private Long baserevid;

	private java.time.Instant basetimestamp;

	private java.time.Instant starttimestamp;

	private Boolean recreate;

	private Boolean createonly;

	private Boolean nocreate;

	private AAPIEditWatchlist watchlist;

	private String md5;

	private String prependtext;

	private String appendtext;

	private Long undo;

	private Long undoafter;

	private Boolean redirect;

	private AAPIEditContentformat contentformat;

	private AAPIEditContentmodel contentmodel;

	private String token;

	private String returnto;

	private String returntoquery;

	private String returntoanchor;

	private ContainsPageRef title;

	/**Section identifier. <kbd>0</kbd> for the top section, <kbd>new</kbd> for a new section. Often a positive integer, but can also be non-numeric.
	 */
	public AAPIEdit section(String section) {

		this.section = section;

		return this;
	}

	/**Section identifier. <kbd>0</kbd> for the top section, <kbd>new</kbd> for a new section. Often a positive integer, but can also be non-numeric.
	 */
	public String getSection() {
		return this.section;
	}

	/**The title for a new section when using <var>section=new</var>.
	 */
	public AAPIEdit sectiontitle(String sectiontitle) {

		this.sectiontitle = sectiontitle;

		return this;
	}

	/**The title for a new section when using <var>section=new</var>.
	 */
	public String getSectiontitle() {
		return this.sectiontitle;
	}

	/**Page content.
	 */
	public AAPIEdit text(String text) {

		this.text = text;

		return this;
	}

	/**Page content.
	 */
	public String getText() {
		return this.text;
	}

	/**<p>Edit summary.
	 * </p><p>When this parameter is not provided or empty, <a href="/w/index.php?title=Mw:Special:MyLanguage/Autosummary&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Autosummary (page does not exist)">an edit summary may be generated automatically</a>.
	 * </p><p>When using <var>section=new</var> and <var>sectiontitle</var> is not provided, the value of this parameter is used for the section title instead, and an edit summary is generated automatically.
	 * </p>
	 */
	public AAPIEdit summary(String summary) {

		this.summary = summary;

		return this;
	}

	/**<p>Edit summary.
	 * </p><p>When this parameter is not provided or empty, <a href="/w/index.php?title=Mw:Special:MyLanguage/Autosummary&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Autosummary (page does not exist)">an edit summary may be generated automatically</a>.
	 * </p><p>When using <var>section=new</var> and <var>sectiontitle</var> is not provided, the value of this parameter is used for the section title instead, and an edit summary is generated automatically.
	 * </p>
	 */
	public String getSummary() {
		return this.summary;
	}

	/**Change tags to apply to the revision.
	 */
	public AAPIEdit tags(String... tags) {

		this.tags = List.of(tags);

		return this;
	}

	/**Change tags to apply to the revision.
	 */
	public List<String> getTags() {
		return this.tags;
	}

	/**Mark this edit as a minor edit.
	 */
	public AAPIEdit minor(Boolean minor) {

		this.minor = minor;

		return this;
	}

	/**Mark this edit as a minor edit.
	 */
	public Boolean getMinor() {
		return this.minor;
	}

	/**Do not mark this edit as a minor edit even if the "Mark all edits minor by default" user preference is set.
	 */
	public AAPIEdit notminor(Boolean notminor) {

		this.notminor = notminor;

		return this;
	}

	/**Do not mark this edit as a minor edit even if the "Mark all edits minor by default" user preference is set.
	 */
	public Boolean getNotminor() {
		return this.notminor;
	}

	/**Mark this edit as a bot edit.
	 */
	public AAPIEdit bot(Boolean bot) {

		this.bot = bot;

		return this;
	}

	/**Mark this edit as a bot edit.
	 */
	public Boolean getBot() {
		return this.bot;
	}

	/**ID of the base revision, used to detect edit conflicts. May be obtained through <a href="/wiki/Special:ApiHelp/query%2Brevisions" title="Special:ApiHelp/query+revisions">action=query&amp;prop=revisions</a>. Self-conflicts cause the edit to fail unless basetimestamp is set.
	 */
	public AAPIEdit baserevid(Long baserevid) {

		this.baserevid = baserevid;

		return this;
	}

	/**ID of the base revision, used to detect edit conflicts. May be obtained through <a href="/wiki/Special:ApiHelp/query%2Brevisions" title="Special:ApiHelp/query+revisions">action=query&amp;prop=revisions</a>. Self-conflicts cause the edit to fail unless basetimestamp is set.
	 */
	public Long getBaserevid() {
		return this.baserevid;
	}

	/**Timestamp of the base revision, used to detect edit conflicts. May be obtained through <a href="/wiki/Special:ApiHelp/query%2Brevisions" title="Special:ApiHelp/query+revisions">action=query&amp;prop=revisions&amp;rvprop=timestamp</a>. Self-conflicts are ignored.
	 */
	public AAPIEdit basetimestamp(java.time.Instant basetimestamp) {

		this.basetimestamp = basetimestamp;

		return this;
	}

	/**Timestamp of the base revision, used to detect edit conflicts. May be obtained through <a href="/wiki/Special:ApiHelp/query%2Brevisions" title="Special:ApiHelp/query+revisions">action=query&amp;prop=revisions&amp;rvprop=timestamp</a>. Self-conflicts are ignored.
	 */
	public java.time.Instant getBasetimestamp() {
		return this.basetimestamp;
	}

	/**Timestamp when the editing process began, used to detect edit conflicts. An appropriate value may be obtained using <var><a href="/wiki/Special:ApiHelp/main" title="Special:ApiHelp/main">curtimestamp</a></var> when beginning the edit process (e.g. when loading the page content to edit).
	 */
	public AAPIEdit starttimestamp(java.time.Instant starttimestamp) {

		this.starttimestamp = starttimestamp;

		return this;
	}

	/**Timestamp when the editing process began, used to detect edit conflicts. An appropriate value may be obtained using <var><a href="/wiki/Special:ApiHelp/main" title="Special:ApiHelp/main">curtimestamp</a></var> when beginning the edit process (e.g. when loading the page content to edit).
	 */
	public java.time.Instant getStarttimestamp() {
		return this.starttimestamp;
	}

	/**Override any errors about the page having been deleted in the meantime.
	 */
	public AAPIEdit recreate(Boolean recreate) {

		this.recreate = recreate;

		return this;
	}

	/**Override any errors about the page having been deleted in the meantime.
	 */
	public Boolean getRecreate() {
		return this.recreate;
	}

	/**Don't edit the page if it exists already.
	 */
	public AAPIEdit createonly(Boolean createonly) {

		this.createonly = createonly;

		return this;
	}

	/**Don't edit the page if it exists already.
	 */
	public Boolean getCreateonly() {
		return this.createonly;
	}

	/**Throw an error if the page doesn't exist.
	 */
	public AAPIEdit nocreate(Boolean nocreate) {

		this.nocreate = nocreate;

		return this;
	}

	/**Throw an error if the page doesn't exist.
	 */
	public Boolean getNocreate() {
		return this.nocreate;
	}

	/**Unconditionally add or remove the page from the current user's watchlist, use preferences (ignored for bot users) or do not change watch.
	 */
	public AAPIEdit watchlist(AAPIEditWatchlist watchlist) {

		this.watchlist = watchlist;

		return this;
	}

	/**Unconditionally add or remove the page from the current user's watchlist, use preferences (ignored for bot users) or do not change watch.
	 */
	public AAPIEditWatchlist getWatchlist() {
		return this.watchlist;
	}

	/**The MD5 hash of the text parameter, or the prependtext and appendtext parameters concatenated. If set, the edit won't be done unless the hash is correct.
	 */
	public AAPIEdit md5(String md5) {

		this.md5 = md5;

		return this;
	}

	/**The MD5 hash of the text parameter, or the prependtext and appendtext parameters concatenated. If set, the edit won't be done unless the hash is correct.
	 */
	public String getMd5() {
		return this.md5;
	}

	/**Add this text to the beginning of the page or section. Overrides text.
	 */
	public AAPIEdit prependtext(String prependtext) {

		this.prependtext = prependtext;

		return this;
	}

	/**Add this text to the beginning of the page or section. Overrides text.
	 */
	public String getPrependtext() {
		return this.prependtext;
	}

	/**<p>Add this text to the end of the page or section. Overrides text.
	 * </p><p>Use section=new to append a new section, rather than this parameter.
	 * </p>
	 */
	public AAPIEdit appendtext(String appendtext) {

		this.appendtext = appendtext;

		return this;
	}

	/**<p>Add this text to the end of the page or section. Overrides text.
	 * </p><p>Use section=new to append a new section, rather than this parameter.
	 * </p>
	 */
	public String getAppendtext() {
		return this.appendtext;
	}

	/**Undo this revision. Overrides text, prependtext and appendtext.
	 */
	public AAPIEdit undo(Long undo) {

		this.undo = undo;

		return this;
	}

	/**Undo this revision. Overrides text, prependtext and appendtext.
	 */
	public Long getUndo() {
		return this.undo;
	}

	/**Undo all revisions from undo to this one. If not set, just undo one revision.
	 */
	public AAPIEdit undoafter(Long undoafter) {

		this.undoafter = undoafter;

		return this;
	}

	/**Undo all revisions from undo to this one. If not set, just undo one revision.
	 */
	public Long getUndoafter() {
		return this.undoafter;
	}

	/**Automatically resolve redirects.
	 */
	public AAPIEdit redirect(Boolean redirect) {

		this.redirect = redirect;

		return this;
	}

	/**Automatically resolve redirects.
	 */
	public Boolean getRedirect() {
		return this.redirect;
	}

	/**Content serialization format used for the input text.
	 */
	public AAPIEdit contentformat(AAPIEditContentformat contentformat) {

		this.contentformat = contentformat;

		return this;
	}

	/**Content serialization format used for the input text.
	 */
	public AAPIEditContentformat getContentformat() {
		return this.contentformat;
	}

	/**Content model of the new content.
	 */
	public AAPIEdit contentmodel(AAPIEditContentmodel contentmodel) {

		this.contentmodel = contentmodel;

		return this;
	}

	/**Content model of the new content.
	 */
	public AAPIEditContentmodel getContentmodel() {
		return this.contentmodel;
	}

	/**<p>A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 * </p>
	 * <p>The token should always be sent as the last parameter, or at least after the text parameter.
	 * </p>
	 */
	public AAPIEdit token(String token) {

		this.token = token;

		return this;
	}

	/**<p>A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 * </p>
	 * <p>The token should always be sent as the last parameter, or at least after the text parameter.
	 * </p>
	 */
	public String getToken() {
		return this.token;
	}

	/**Page title. If saving the edit created a temporary account, the API may respond with an URL that the client should visit to complete logging in. If this parameter is provided, the URL will redirect to the given page, instead of the page that was edited.
	 */
	public AAPIEdit returnto(String returnto) {

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
	public AAPIEdit returntoquery(String returntoquery) {

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
	public AAPIEdit returntoanchor(String returntoanchor) {

		this.returntoanchor = returntoanchor;

		return this;
	}

	/**URL fragment (with leading <kbd>#</kbd>). If saving the edit created a temporary account, the API may respond with an URL that the client should visit to complete logging in. If this parameter is provided, the URL will redirect to a page with the given fragment.
	 */
	public String getReturntoanchor() {
		return this.returntoanchor;
	}

	public ContainsPageRef getTitle() {
		return this.title;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIEdit(");

		if (title != null) {
			sb.append("title=").append(title).append(", ");
		}

		if (section != null) {

			sb.append("section").append("=").append(section);

			sb.append(", ");
		}

		if (sectiontitle != null) {

			sb.append("sectiontitle").append("=").append(sectiontitle);

			sb.append(", ");
		}

		if (text != null) {

			sb.append("text").append("=").append(text);

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

		if (minor != null) {

			sb.append("minor").append("=").append(minor.toString());

			sb.append(", ");
		}

		if (notminor != null) {

			sb.append("notminor").append("=").append(notminor.toString());

			sb.append(", ");
		}

		if (bot != null) {

			sb.append("bot").append("=").append(bot.toString());

			sb.append(", ");
		}

		if (baserevid != null) {

			sb.append("baserevid").append("=").append(baserevid.toString());

			sb.append(", ");
		}

		if (basetimestamp != null) {

			sb.append("basetimestamp")
					.append("=")
					.append(
							basetimestamp
									.truncatedTo(java.time.temporal.ChronoUnit.SECONDS)
									.toString());

			sb.append(", ");
		}

		if (starttimestamp != null) {

			sb.append("starttimestamp")
					.append("=")
					.append(
							starttimestamp
									.truncatedTo(java.time.temporal.ChronoUnit.SECONDS)
									.toString());

			sb.append(", ");
		}

		if (recreate != null) {

			sb.append("recreate").append("=").append(recreate.toString());

			sb.append(", ");
		}

		if (createonly != null) {

			sb.append("createonly").append("=").append(createonly.toString());

			sb.append(", ");
		}

		if (nocreate != null) {

			sb.append("nocreate").append("=").append(nocreate.toString());

			sb.append(", ");
		}

		if (watchlist != null) {

			sb.append("watchlist").append("=").append(watchlist.getJsonValue());

			sb.append(", ");
		}

		if (md5 != null) {

			sb.append("md5").append("=").append(md5);

			sb.append(", ");
		}

		if (prependtext != null) {

			sb.append("prependtext").append("=").append(prependtext);

			sb.append(", ");
		}

		if (appendtext != null) {

			sb.append("appendtext").append("=").append(appendtext);

			sb.append(", ");
		}

		if (undo != null) {

			sb.append("undo").append("=").append(undo.toString());

			sb.append(", ");
		}

		if (undoafter != null) {

			sb.append("undoafter").append("=").append(undoafter.toString());

			sb.append(", ");
		}

		if (redirect != null) {

			sb.append("redirect").append("=").append(redirect.toString());

			sb.append(", ");
		}

		if (contentformat != null) {

			sb.append("contentformat").append("=").append(contentformat.getJsonValue());

			sb.append(", ");
		}

		if (contentmodel != null) {

			sb.append("contentmodel").append("=").append(contentmodel.getJsonValue());

			sb.append(", ");
		}

		if (token != null) {

			sb.append("token").append("=").append(token);

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

		if (title != null) {

			if (title.toPageRef().hasId()) {
				req.addParameter(
						paramPrefix + "pageid", Integer.toString(title.toPageRef().getId()));
			} else {
				req.addParameter(paramPrefix + "title", title.toPageRef().getTitle().toFullTitle());
			}
		}

		if (section != null) {

			req.addParameter(paramPrefix + "section", section);
		}

		if (sectiontitle != null) {

			req.addParameter(paramPrefix + "sectiontitle", sectiontitle);
		}

		if (text != null) {

			req.addParameter(paramPrefix + "text", text);
		}

		if (summary != null) {

			req.addParameter(paramPrefix + "summary", summary);
		}

		if (tags != null) {

			req.addParameter(
					paramPrefix + "tags",
					tags.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (minor != null) {

			req.addParameter(paramPrefix + "minor", minor.toString());
		}

		if (notminor != null) {

			req.addParameter(paramPrefix + "notminor", notminor.toString());
		}

		if (bot != null) {

			req.addParameter(paramPrefix + "bot", bot.toString());
		}

		if (baserevid != null) {

			req.addParameter(paramPrefix + "baserevid", baserevid.toString());
		}

		if (basetimestamp != null) {

			req.addParameter(
					paramPrefix + "basetimestamp",
					basetimestamp.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (starttimestamp != null) {

			req.addParameter(
					paramPrefix + "starttimestamp",
					starttimestamp.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (recreate != null) {

			req.addParameter(paramPrefix + "recreate", recreate.toString());
		}

		if (createonly != null) {

			req.addParameter(paramPrefix + "createonly", createonly.toString());
		}

		if (nocreate != null) {

			req.addParameter(paramPrefix + "nocreate", nocreate.toString());
		}

		if (watchlist != null) {

			req.addParameter(paramPrefix + "watchlist", watchlist.getJsonValue());
		}

		if (md5 != null) {

			req.addParameter(paramPrefix + "md5", md5);
		}

		if (prependtext != null) {

			req.addParameter(paramPrefix + "prependtext", prependtext);
		}

		if (appendtext != null) {

			req.addParameter(paramPrefix + "appendtext", appendtext);
		}

		if (undo != null) {

			req.addParameter(paramPrefix + "undo", undo.toString());
		}

		if (undoafter != null) {

			req.addParameter(paramPrefix + "undoafter", undoafter.toString());
		}

		if (redirect != null) {

			req.addParameter(paramPrefix + "redirect", redirect.toString());
		}

		if (contentformat != null) {

			req.addParameter(paramPrefix + "contentformat", contentformat.getJsonValue());
		}

		if (contentmodel != null) {

			req.addParameter(paramPrefix + "contentmodel", contentmodel.getJsonValue());
		}

		token = api.requestToken(AAPIQueryTokensType.CSRF);

		if (token != null) {

			req.addParameter(paramPrefix + "token", token);
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
			c.accept(AAPIEdit.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIEdit.this);

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
