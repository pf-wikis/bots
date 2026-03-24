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

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** <p>Import a page from another wiki, or from an XML file.
 * </p>
 * <p>Note that the HTTP POST must be done as a file upload (i.e. using multipart/form-data) when sending a file for the <var>xml</var> parameter.
 * </p>
 */
public class AAPIImport implements AAPIModule, AAPITokenModule, AAPIMainActionModule {

	public static AAPIImport create() {

		AAPIImport v = new AAPIImport();

		return v;
	}

	private AAPIImport() {}

	private String summary;

	private byte[] xml;

	private String interwikiprefix;

	private String interwikisource;

	private String interwikipage;

	private Boolean fullhistory;

	private Boolean templates;

	private NS namespace;

	private Boolean assignknownusers;

	private String rootpage;

	private List<String> tags;

	private String token;

	/**Log entry import summary.
	 */
	public AAPIImport summary(String summary) {
		this.summary = summary;

		return this;
	}

	/**Log entry import summary.
	 */
	public String getSummary() {
		return this.summary;
	}

	/**Uploaded XML file.
	 */
	public AAPIImport xml(byte[] xml) {
		this.xml = xml;

		return this;
	}

	/**Uploaded XML file.
	 */
	public byte[] getXml() {
		return this.xml;
	}

	/**For uploaded imports: interwiki prefix to apply to unknown usernames (and known users if <var>assignknownusers</var> is set).
	 */
	public AAPIImport interwikiprefix(String interwikiprefix) {
		this.interwikiprefix = interwikiprefix;

		return this;
	}

	/**For uploaded imports: interwiki prefix to apply to unknown usernames (and known users if <var>assignknownusers</var> is set).
	 */
	public String getInterwikiprefix() {
		return this.interwikiprefix;
	}

	/**For interwiki imports: wiki to import from.
	 */
	public AAPIImport interwikisource(String interwikisource) {
		this.interwikisource = interwikisource;

		return this;
	}

	/**For interwiki imports: wiki to import from.
	 */
	public String getInterwikisource() {
		return this.interwikisource;
	}

	/**For interwiki imports: page to import.
	 */
	public AAPIImport interwikipage(String interwikipage) {
		this.interwikipage = interwikipage;

		return this;
	}

	/**For interwiki imports: page to import.
	 */
	public String getInterwikipage() {
		return this.interwikipage;
	}

	/**For interwiki imports: import the full history, not just the current version.
	 */
	public AAPIImport fullhistory(Boolean fullhistory) {
		this.fullhistory = fullhistory;

		return this;
	}

	/**For interwiki imports: import the full history, not just the current version.
	 */
	public Boolean getFullhistory() {
		return this.fullhistory;
	}

	/**For interwiki imports: import all included templates as well.
	 */
	public AAPIImport templates(Boolean templates) {
		this.templates = templates;

		return this;
	}

	/**For interwiki imports: import all included templates as well.
	 */
	public Boolean getTemplates() {
		return this.templates;
	}

	/**Import to this namespace. Cannot be used together with <var>rootpage</var>.
	 */
	public AAPIImport namespace(NS namespace) {
		this.namespace = namespace;

		return this;
	}

	/**Import to this namespace. Cannot be used together with <var>rootpage</var>.
	 */
	public NS getNamespace() {
		return this.namespace;
	}

	/**Assign edits to local users where the named user exists locally.
	 */
	public AAPIImport assignknownusers(Boolean assignknownusers) {
		this.assignknownusers = assignknownusers;

		return this;
	}

	/**Assign edits to local users where the named user exists locally.
	 */
	public Boolean getAssignknownusers() {
		return this.assignknownusers;
	}

	/**Import as subpage of this page. Cannot be used together with <var>namespace</var>.
	 */
	public AAPIImport rootpage(String rootpage) {
		this.rootpage = rootpage;

		return this;
	}

	/**Import as subpage of this page. Cannot be used together with <var>namespace</var>.
	 */
	public String getRootpage() {
		return this.rootpage;
	}

	/**Change tags to apply to the entry in the import log and to the null revision on the imported pages.
	 */
	public AAPIImport tags(String tags) {
		this.tags = List.of(tags);

		return this;
	}

	/**Change tags to apply to the entry in the import log and to the null revision on the imported pages.
	 */
	public AAPIImport tags(String... tags) {
		this.tags = List.of(tags);
		return this;
	}

	/**Change tags to apply to the entry in the import log and to the null revision on the imported pages.
	 */
	public List<String> getTags() {
		return this.tags;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public AAPIImport token(String token) {
		this.token = token;

		return this;
	}

	/**A "csrf" token retrieved from <a href="/wiki/Special:ApiHelp/query%2Btokens" title="Special:ApiHelp/query+tokens">action=query&amp;meta=tokens</a>
	 */
	public String getToken() {
		return this.token;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIImport(");

		if (summary != null) {

			sb.append("summary").append("=").append(summary);

			sb.append(", ");
		}

		if (xml != null) {

			sb.append("xml").append("=").append(xml.toString());

			sb.append(", ");
		}

		if (interwikiprefix != null) {

			sb.append("interwikiprefix").append("=").append(interwikiprefix);

			sb.append(", ");
		}

		if (interwikisource != null) {

			sb.append("interwikisource").append("=").append(interwikisource);

			sb.append(", ");
		}

		if (interwikipage != null) {

			sb.append("interwikipage").append("=").append(interwikipage);

			sb.append(", ");
		}

		if (fullhistory != null) {

			sb.append("fullhistory").append("=").append(fullhistory.toString());

			sb.append(", ");
		}

		if (templates != null) {

			sb.append("templates").append("=").append(templates.toString());

			sb.append(", ");
		}

		if (namespace != null) {

			sb.append("namespace").append("=").append(Integer.toString(namespace.getId()));

			sb.append(", ");
		}

		if (assignknownusers != null) {

			sb.append("assignknownusers").append("=").append(assignknownusers.toString());

			sb.append(", ");
		}

		if (rootpage != null) {

			sb.append("rootpage").append("=").append(rootpage);

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

		if (summary != null) {

			req.addParameter(paramPrefix + "summary", summary);
		}

		if (xml != null) {

			req.addParameter(paramPrefix + "xml", xml.toString());
		}

		if (interwikiprefix != null) {

			req.addParameter(paramPrefix + "interwikiprefix", interwikiprefix);
		}

		if (interwikisource != null) {

			req.addParameter(paramPrefix + "interwikisource", interwikisource);
		}

		if (interwikipage != null) {

			req.addParameter(paramPrefix + "interwikipage", interwikipage);
		}

		if (fullhistory != null) {

			req.addParameter(paramPrefix + "fullhistory", fullhistory.toString());
		}

		if (templates != null) {

			req.addParameter(paramPrefix + "templates", templates.toString());
		}

		if (namespace != null) {

			req.addParameter(paramPrefix + "namespace", Integer.toString(namespace.getId()));
		}

		if (assignknownusers != null) {

			req.addParameter(paramPrefix + "assignknownusers", assignknownusers.toString());
		}

		if (rootpage != null) {

			req.addParameter(paramPrefix + "rootpage", rootpage);
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
			c.accept(AAPIImport.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIImport.this);

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
