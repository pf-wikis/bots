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

import io.github.pfwikis.bots.common.api.generated.params.AAPIParseProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIParseUseskin;

import io.github.pfwikis.bots.common.api.generated.params.AAPIParseContentformat;

import io.github.pfwikis.bots.common.api.generated.params.AAPIParseContentmodel;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** <p>Parses content and returns parser output.
 * </p>
 * <p>See the various prop-modules of <kbd><a href="/wiki/Special:ApiHelp/query" title="Special:ApiHelp/query">action=query</a></kbd> to get information from the current version of a page.
 * </p><p>There are several ways to specify the text to parse:
 * </p>
 * <ol><li>Specify a page or revision, using <var>page</var>, <var>pageid</var>, or <var>oldid</var>.</li>
 * <li>Specify content explicitly, using <var>text</var>, <var>title</var>, <var>revid</var>, and <var>contentmodel</var>.</li>
 * <li>Specify only a summary to parse. <var>prop</var> should be given an empty value.</li></ol>
 */
public class AAPIParse implements AAPIModule, AAPIMainActionModule {

	public static AAPIParse create() {

		AAPIParse v = new AAPIParse();

		return v;
	}

	private AAPIParse() {}

	private String title;

	private String text;

	private Long revid;

	private String summary;

	private Boolean redirects;

	private Long oldid;

	private List<AAPIParseProp> prop;

	private String wrapoutputclass;

	private Boolean usearticle;

	private Boolean parsoid;

	private Boolean pst;

	private Boolean onlypst;

	private String section;

	private String sectiontitle;

	private Boolean disablelimitreport;

	private Boolean disableeditsection;

	private Boolean disablestylededuplication;

	private Boolean showstrategykeys;

	private Boolean preview;

	private Boolean sectionpreview;

	private Boolean disabletoc;

	private AAPIParseUseskin useskin;

	private AAPIParseContentformat contentformat;

	private AAPIParseContentmodel contentmodel;

	private ContainsPageRef page;

	/**Title of page the text belongs to. If omitted, <var>contentmodel</var> must be specified, and <a href="/w/index.php?title=API&amp;action=edit&amp;redlink=1" class="new" title="API (page does not exist)">API</a> will be used as the title.
	 */
	public AAPIParse title(String title) {

		this.title = title;

		return this;
	}

	/**Title of page the text belongs to. If omitted, <var>contentmodel</var> must be specified, and <a href="/w/index.php?title=API&amp;action=edit&amp;redlink=1" class="new" title="API (page does not exist)">API</a> will be used as the title.
	 */
	public String getTitle() {
		return this.title;
	}

	/**Text to parse. Use <var>title</var> or <var>contentmodel</var> to control the content model.
	 */
	public AAPIParse text(String text) {

		this.text = text;

		return this;
	}

	/**Text to parse. Use <var>title</var> or <var>contentmodel</var> to control the content model.
	 */
	public String getText() {
		return this.text;
	}

	/**Revision ID, for <code>{{REVISIONID}}</code> and similar variables.
	 */
	public AAPIParse revid(Long revid) {

		this.revid = revid;

		return this;
	}

	/**Revision ID, for <code>{{REVISIONID}}</code> and similar variables.
	 */
	public Long getRevid() {
		return this.revid;
	}

	/**Summary to parse.
	 */
	public AAPIParse summary(String summary) {

		this.summary = summary;

		return this;
	}

	/**Summary to parse.
	 */
	public String getSummary() {
		return this.summary;
	}

	/**If <var>page</var> or <var>pageid</var> is set to a redirect, resolve it.
	 */
	public AAPIParse redirects(Boolean redirects) {

		this.redirects = redirects;

		return this;
	}

	/**If <var>page</var> or <var>pageid</var> is set to a redirect, resolve it.
	 */
	public Boolean getRedirects() {
		return this.redirects;
	}

	/**Parse the content of this revision. Overrides <var>page</var> and <var>pageid</var>.
	 */
	public AAPIParse oldid(Long oldid) {

		this.oldid = oldid;

		return this;
	}

	/**Parse the content of this revision. Overrides <var>page</var> and <var>pageid</var>.
	 */
	public Long getOldid() {
		return this.oldid;
	}

	/**<p>Which pieces of information to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIParse prop(AAPIParseProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which pieces of information to get:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIParseProp> getProp() {
		return this.prop;
	}

	/**CSS class to use to wrap the parser output.
	 */
	public AAPIParse wrapoutputclass(String wrapoutputclass) {

		this.wrapoutputclass = wrapoutputclass;

		return this;
	}

	/**CSS class to use to wrap the parser output.
	 */
	public String getWrapoutputclass() {
		return this.wrapoutputclass;
	}

	/**Use the ArticleParserOptions hook to ensure the options used match those used for article page views
	 */
	public AAPIParse usearticle(Boolean usearticle) {

		this.usearticle = usearticle;

		return this;
	}

	/**Use the ArticleParserOptions hook to ensure the options used match those used for article page views
	 */
	public Boolean getUsearticle() {
		return this.usearticle;
	}

	/**Generate HTML conforming to the <a href="/w/index.php?title=Mw:Specs/HTML&amp;action=edit&amp;redlink=1" class="new" title="Mw:Specs/HTML (page does not exist)">MediaWiki DOM spec</a> using <a href="/w/index.php?title=Mw:Parsoid&amp;action=edit&amp;redlink=1" class="new" title="Mw:Parsoid (page does not exist)">Parsoid</a>.
	 */
	public AAPIParse parsoid(Boolean parsoid) {

		this.parsoid = parsoid;

		return this;
	}

	/**Generate HTML conforming to the <a href="/w/index.php?title=Mw:Specs/HTML&amp;action=edit&amp;redlink=1" class="new" title="Mw:Specs/HTML (page does not exist)">MediaWiki DOM spec</a> using <a href="/w/index.php?title=Mw:Parsoid&amp;action=edit&amp;redlink=1" class="new" title="Mw:Parsoid (page does not exist)">Parsoid</a>.
	 */
	public Boolean getParsoid() {
		return this.parsoid;
	}

	/**Do a pre-save transform on the input before parsing it. Only valid when used with text.
	 */
	public AAPIParse pst(Boolean pst) {

		this.pst = pst;

		return this;
	}

	/**Do a pre-save transform on the input before parsing it. Only valid when used with text.
	 */
	public Boolean getPst() {
		return this.pst;
	}

	/**Do a pre-save transform (PST) on the input, but don't parse it. Returns the same wikitext, after a PST has been applied. Only valid when used with <var>text</var>.
	 */
	public AAPIParse onlypst(Boolean onlypst) {

		this.onlypst = onlypst;

		return this;
	}

	/**Do a pre-save transform (PST) on the input, but don't parse it. Returns the same wikitext, after a PST has been applied. Only valid when used with <var>text</var>.
	 */
	public Boolean getOnlypst() {
		return this.onlypst;
	}

	/**<p>Only parse the content of the section with this identifier.
	 * </p><p>When <kbd>new</kbd>, parse <var>text</var> and <var>sectiontitle</var> as if adding a new section to the page.
	 * </p><p><kbd>new</kbd> is allowed only when specifying <var>text</var>.
	 * </p>
	 */
	public AAPIParse section(String section) {

		this.section = section;

		return this;
	}

	/**<p>Only parse the content of the section with this identifier.
	 * </p><p>When <kbd>new</kbd>, parse <var>text</var> and <var>sectiontitle</var> as if adding a new section to the page.
	 * </p><p><kbd>new</kbd> is allowed only when specifying <var>text</var>.
	 * </p>
	 */
	public String getSection() {
		return this.section;
	}

	/**<p>New section title when <var>section</var> is <kbd>new</kbd>.
	 * </p><p>Unlike page editing, this does not fall back to <var>summary</var> when omitted or empty.
	 * </p>
	 */
	public AAPIParse sectiontitle(String sectiontitle) {

		this.sectiontitle = sectiontitle;

		return this;
	}

	/**<p>New section title when <var>section</var> is <kbd>new</kbd>.
	 * </p><p>Unlike page editing, this does not fall back to <var>summary</var> when omitted or empty.
	 * </p>
	 */
	public String getSectiontitle() {
		return this.sectiontitle;
	}

	/**Omit the limit report ("NewPP limit report") from the parser output.
	 */
	public AAPIParse disablelimitreport(Boolean disablelimitreport) {

		this.disablelimitreport = disablelimitreport;

		return this;
	}

	/**Omit the limit report ("NewPP limit report") from the parser output.
	 */
	public Boolean getDisablelimitreport() {
		return this.disablelimitreport;
	}

	/**Omit edit section links from the parser output.
	 */
	public AAPIParse disableeditsection(Boolean disableeditsection) {

		this.disableeditsection = disableeditsection;

		return this;
	}

	/**Omit edit section links from the parser output.
	 */
	public Boolean getDisableeditsection() {
		return this.disableeditsection;
	}

	/**Do not deduplicate inline stylesheets in the parser output.
	 */
	public AAPIParse disablestylededuplication(Boolean disablestylededuplication) {

		this.disablestylededuplication = disablestylededuplication;

		return this;
	}

	/**Do not deduplicate inline stylesheets in the parser output.
	 */
	public Boolean getDisablestylededuplication() {
		return this.disablestylededuplication;
	}

	/**Whether to include internal merge strategy information in jsconfigvars.
	 */
	public AAPIParse showstrategykeys(Boolean showstrategykeys) {

		this.showstrategykeys = showstrategykeys;

		return this;
	}

	/**Whether to include internal merge strategy information in jsconfigvars.
	 */
	public Boolean getShowstrategykeys() {
		return this.showstrategykeys;
	}

	/**Parse in preview mode.
	 */
	public AAPIParse preview(Boolean preview) {

		this.preview = preview;

		return this;
	}

	/**Parse in preview mode.
	 */
	public Boolean getPreview() {
		return this.preview;
	}

	/**Parse in section preview mode (enables preview mode too).
	 */
	public AAPIParse sectionpreview(Boolean sectionpreview) {

		this.sectionpreview = sectionpreview;

		return this;
	}

	/**Parse in section preview mode (enables preview mode too).
	 */
	public Boolean getSectionpreview() {
		return this.sectionpreview;
	}

	/**Omit table of contents in output.
	 */
	public AAPIParse disabletoc(Boolean disabletoc) {

		this.disabletoc = disabletoc;

		return this;
	}

	/**Omit table of contents in output.
	 */
	public Boolean getDisabletoc() {
		return this.disabletoc;
	}

	/**Apply the selected skin to the parser output. May affect the following properties: <kbd>text</kbd>, <kbd>langlinks</kbd>, <kbd>headitems</kbd>, <kbd>modules</kbd>, <kbd>jsconfigvars</kbd>, <kbd>indicators</kbd>.
	 */
	public AAPIParse useskin(AAPIParseUseskin useskin) {

		this.useskin = useskin;

		return this;
	}

	/**Apply the selected skin to the parser output. May affect the following properties: <kbd>text</kbd>, <kbd>langlinks</kbd>, <kbd>headitems</kbd>, <kbd>modules</kbd>, <kbd>jsconfigvars</kbd>, <kbd>indicators</kbd>.
	 */
	public AAPIParseUseskin getUseskin() {
		return this.useskin;
	}

	/**Content serialization format used for the input text. Only valid when used with text.
	 */
	public AAPIParse contentformat(AAPIParseContentformat contentformat) {

		this.contentformat = contentformat;

		return this;
	}

	/**Content serialization format used for the input text. Only valid when used with text.
	 */
	public AAPIParseContentformat getContentformat() {
		return this.contentformat;
	}

	/**Content model of the input text. If omitted, title must be specified, and default will be the model of the specified title. Only valid when used with text.
	 */
	public AAPIParse contentmodel(AAPIParseContentmodel contentmodel) {

		this.contentmodel = contentmodel;

		return this;
	}

	/**Content model of the input text. If omitted, title must be specified, and default will be the model of the specified title. Only valid when used with text.
	 */
	public AAPIParseContentmodel getContentmodel() {
		return this.contentmodel;
	}

	public AAPIParse page(ContainsPageRef page) {

		this.page = page;

		return this;
	}

	public ContainsPageRef getPage() {
		return this.page;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIParse(");

		if (page != null) {
			sb.append("page=").append(page).append(", ");
		}

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

		if (summary != null) {

			sb.append("summary").append("=").append(summary);

			sb.append(", ");
		}

		if (redirects != null) {

			sb.append("redirects").append("=").append(redirects.toString());

			sb.append(", ");
		}

		if (oldid != null) {

			sb.append("oldid").append("=").append(oldid.toString());

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

		if (wrapoutputclass != null) {

			sb.append("wrapoutputclass").append("=").append(wrapoutputclass);

			sb.append(", ");
		}

		if (usearticle != null) {

			sb.append("usearticle").append("=").append(usearticle.toString());

			sb.append(", ");
		}

		if (parsoid != null) {

			sb.append("parsoid").append("=").append(parsoid.toString());

			sb.append(", ");
		}

		if (pst != null) {

			sb.append("pst").append("=").append(pst.toString());

			sb.append(", ");
		}

		if (onlypst != null) {

			sb.append("onlypst").append("=").append(onlypst.toString());

			sb.append(", ");
		}

		if (section != null) {

			sb.append("section").append("=").append(section);

			sb.append(", ");
		}

		if (sectiontitle != null) {

			sb.append("sectiontitle").append("=").append(sectiontitle);

			sb.append(", ");
		}

		if (disablelimitreport != null) {

			sb.append("disablelimitreport").append("=").append(disablelimitreport.toString());

			sb.append(", ");
		}

		if (disableeditsection != null) {

			sb.append("disableeditsection").append("=").append(disableeditsection.toString());

			sb.append(", ");
		}

		if (disablestylededuplication != null) {

			sb.append("disablestylededuplication")
					.append("=")
					.append(disablestylededuplication.toString());

			sb.append(", ");
		}

		if (showstrategykeys != null) {

			sb.append("showstrategykeys").append("=").append(showstrategykeys.toString());

			sb.append(", ");
		}

		if (preview != null) {

			sb.append("preview").append("=").append(preview.toString());

			sb.append(", ");
		}

		if (sectionpreview != null) {

			sb.append("sectionpreview").append("=").append(sectionpreview.toString());

			sb.append(", ");
		}

		if (disabletoc != null) {

			sb.append("disabletoc").append("=").append(disabletoc.toString());

			sb.append(", ");
		}

		if (useskin != null) {

			sb.append("useskin").append("=").append(useskin.getJsonValue());

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

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (page != null) {

			if (page.toPageRef().hasId()) {
				req.addParameter(
						paramPrefix + "pageid", Integer.toString(page.toPageRef().getId()));
			} else {
				req.addParameter(paramPrefix + "page", page.toPageRef().getTitle().toFullTitle());
			}
		}

		if (title != null) {

			req.addParameter(paramPrefix + "title", title);
		}

		if (text != null) {

			req.addParameter(paramPrefix + "text", text);
		}

		if (revid != null) {

			req.addParameter(paramPrefix + "revid", revid.toString());
		}

		if (summary != null) {

			req.addParameter(paramPrefix + "summary", summary);
		}

		if (redirects != null) {

			req.addParameter(paramPrefix + "redirects", redirects.toString());
		}

		if (oldid != null) {

			req.addParameter(paramPrefix + "oldid", oldid.toString());
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "prop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (wrapoutputclass != null) {

			req.addParameter(paramPrefix + "wrapoutputclass", wrapoutputclass);
		}

		if (usearticle != null) {

			req.addParameter(paramPrefix + "usearticle", usearticle.toString());
		}

		if (parsoid != null) {

			req.addParameter(paramPrefix + "parsoid", parsoid.toString());
		}

		if (pst != null) {

			req.addParameter(paramPrefix + "pst", pst.toString());
		}

		if (onlypst != null) {

			req.addParameter(paramPrefix + "onlypst", onlypst.toString());
		}

		if (section != null) {

			req.addParameter(paramPrefix + "section", section);
		}

		if (sectiontitle != null) {

			req.addParameter(paramPrefix + "sectiontitle", sectiontitle);
		}

		if (disablelimitreport != null) {

			req.addParameter(paramPrefix + "disablelimitreport", disablelimitreport.toString());
		}

		if (disableeditsection != null) {

			req.addParameter(paramPrefix + "disableeditsection", disableeditsection.toString());
		}

		if (disablestylededuplication != null) {

			req.addParameter(
					paramPrefix + "disablestylededuplication",
					disablestylededuplication.toString());
		}

		if (showstrategykeys != null) {

			req.addParameter(paramPrefix + "showstrategykeys", showstrategykeys.toString());
		}

		if (preview != null) {

			req.addParameter(paramPrefix + "preview", preview.toString());
		}

		if (sectionpreview != null) {

			req.addParameter(paramPrefix + "sectionpreview", sectionpreview.toString());
		}

		if (disabletoc != null) {

			req.addParameter(paramPrefix + "disabletoc", disabletoc.toString());
		}

		if (useskin != null) {

			req.addParameter(paramPrefix + "useskin", useskin.getJsonValue());
		}

		if (contentformat != null) {

			req.addParameter(paramPrefix + "contentformat", contentformat.getJsonValue());
		}

		if (contentmodel != null) {

			req.addParameter(paramPrefix + "contentmodel", contentmodel.getJsonValue());
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
			c.accept(AAPIParse.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIParse.this);

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
