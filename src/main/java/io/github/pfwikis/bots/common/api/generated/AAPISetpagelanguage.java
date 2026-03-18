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

import io.github.pfwikis.bots.common.api.generated.params.AAPISetpagelanguageLang;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** <p>Change the language of a page.
 * </p>
 * <p>Changing the language of a page is not allowed on this wiki.
 * </p><p>Enable <var><a href="/w/index.php?title=Mw:Special:MyLanguage/Manual:$wgPageLanguageUseDB&amp;action=edit&amp;redlink=1" class="new" title="Mw:Special:MyLanguage/Manual:$wgPageLanguageUseDB (page does not exist)">$wgPageLanguageUseDB</a></var> to use this action.
 * </p>
 */
public class AAPISetpagelanguage implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPISetpagelanguage create(@NonNull AAPISetpagelanguageLang lang) {

		AAPISetpagelanguage v = new AAPISetpagelanguage();

		v.lang = lang;

		return v;
	}

	private AAPISetpagelanguage() {}

	private String title;

	private Long pageid;

	private AAPISetpagelanguageLang lang;

	private String reason;

	private List<String> tags;

	private String token;

	/**Title of the page whose language you wish to change. Cannot be used together with <var>pageid</var>.
	 */
	public AAPISetpagelanguage title(String title) {

		this.title = title;

		return this;
	}

	/**Title of the page whose language you wish to change. Cannot be used together with <var>pageid</var>.
	 */
	public String getTitle() {
		return this.title;
	}

	/**Page ID of the page whose language you wish to change. Cannot be used together with <var>title</var>.
	 */
	public AAPISetpagelanguage pageid(Long pageid) {

		this.pageid = pageid;

		return this;
	}

	/**Page ID of the page whose language you wish to change. Cannot be used together with <var>title</var>.
	 */
	public Long getPageid() {
		return this.pageid;
	}

	/**Language code of the language to change the page to. Use <kbd>default</kbd> to reset the page to the wiki's default content language.
	 */
	public AAPISetpagelanguageLang getLang() {
		return this.lang;
	}

	/**Reason for the change.
	 */
	public AAPISetpagelanguage reason(String reason) {

		this.reason = reason;

		return this;
	}

	/**Reason for the change.
	 */
	public String getReason() {
		return this.reason;
	}

	/**Change tags to apply to the log entry resulting from this action.
	 */
	public AAPISetpagelanguage tags(String... tags) {

		this.tags = List.of(tags);

		return this;
	}

	/**Change tags to apply to the log entry resulting from this action.
	 */
	public List<String> getTags() {
		return this.tags;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPISetpagelanguage token(String token) {

		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPISetpagelanguage(");

		if (title != null) {

			sb.append("title").append("=").append(title);

			sb.append(", ");
		}

		if (pageid != null) {

			sb.append("pageid").append("=").append(pageid.toString());

			sb.append(", ");
		}

		if (lang != null) {

			sb.append("lang").append("=").append(lang.getJsonValue());

			sb.append(", ");
		}

		if (reason != null) {

			sb.append("reason").append("=").append(reason);

			sb.append(", ");
		}

		if (tags != null) {

			sb.append("tags")
					.append("=")
					.append(tags.stream().map(v -> v).collect(Collectors.joining("|")));

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

		if (lang != null) {

			req.addParameter(paramPrefix + "lang", lang.getJsonValue());
		}

		if (reason != null) {

			req.addParameter(paramPrefix + "reason", reason);
		}

		if (tags != null) {

			req.addParameter(
					paramPrefix + "tags",
					tags.stream().map(v -> v).collect(Collectors.joining("|")));
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
			c.accept(AAPISetpagelanguage.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPISetpagelanguage.this);

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
