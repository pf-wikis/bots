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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllmessagesProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryAllmessagesCustomised;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryMeta.AAPIQueryMetaModule;

/** Return messages from this site.
 */
public class AAPIQueryAllmessages implements AAPIModule, AAPIQueryMetaModule {

	public static AAPIQueryAllmessages create() {

		AAPIQueryAllmessages v = new AAPIQueryAllmessages();

		return v;
	}

	private AAPIQueryAllmessages() {}

	private List<String> messages;

	private List<AAPIQueryAllmessagesProp> prop;

	private Boolean enableparser;

	private Boolean nocontent;

	private Boolean includelocal;

	private List<String> args;

	private String filter;

	private AAPIQueryAllmessagesCustomised customised;

	private String lang;

	private String from;

	private String to;

	private String title;

	private String prefix;

	/**Which messages to output. <kbd>*</kbd> (default) means all messages.
	 */
	public AAPIQueryAllmessages messages(String... messages) {

		this.messages = List.of(messages);

		return this;
	}

	/**Which messages to output. <kbd>*</kbd> (default) means all messages.
	 */
	public List<String> getMessages() {
		return this.messages;
	}

	/**Which properties to get.
	 */
	public AAPIQueryAllmessages prop(AAPIQueryAllmessagesProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**Which properties to get.
	 */
	public List<AAPIQueryAllmessagesProp> getProp() {
		return this.prop;
	}

	/**Set to enable parser, will preprocess the wikitext of message (substitute magic words, handle templates, etc.).
	 */
	public AAPIQueryAllmessages enableparser(Boolean enableparser) {

		this.enableparser = enableparser;

		return this;
	}

	/**Set to enable parser, will preprocess the wikitext of message (substitute magic words, handle templates, etc.).
	 */
	public Boolean getEnableparser() {
		return this.enableparser;
	}

	/**If set, do not include the content of the messages in the output.
	 */
	public AAPIQueryAllmessages nocontent(Boolean nocontent) {

		this.nocontent = nocontent;

		return this;
	}

	/**If set, do not include the content of the messages in the output.
	 */
	public Boolean getNocontent() {
		return this.nocontent;
	}

	/**Also include local messages, i.e. messages that don't exist in the software but do exist as in the MediaWiki namespace.
	 * This lists all MediaWiki-namespace pages, so it will also list those that aren't really messages such as <a href="/wiki/MediaWiki:Common.js" title="MediaWiki:Common.js">Common.js</a>.
	 */
	public AAPIQueryAllmessages includelocal(Boolean includelocal) {

		this.includelocal = includelocal;

		return this;
	}

	/**Also include local messages, i.e. messages that don't exist in the software but do exist as in the MediaWiki namespace.
	 * This lists all MediaWiki-namespace pages, so it will also list those that aren't really messages such as <a href="/wiki/MediaWiki:Common.js" title="MediaWiki:Common.js">Common.js</a>.
	 */
	public Boolean getIncludelocal() {
		return this.includelocal;
	}

	/**Arguments to be substituted into message.
	 */
	public AAPIQueryAllmessages args(String... args) {

		this.args = List.of(args);

		return this;
	}

	/**Arguments to be substituted into message.
	 */
	public List<String> getArgs() {
		return this.args;
	}

	/**Return only messages with names that contain this string.
	 */
	public AAPIQueryAllmessages filter(String filter) {

		this.filter = filter;

		return this;
	}

	/**Return only messages with names that contain this string.
	 */
	public String getFilter() {
		return this.filter;
	}

	/**Return only messages in this customisation state.
	 */
	public AAPIQueryAllmessages customised(AAPIQueryAllmessagesCustomised customised) {

		this.customised = customised;

		return this;
	}

	/**Return only messages in this customisation state.
	 */
	public AAPIQueryAllmessagesCustomised getCustomised() {
		return this.customised;
	}

	/**Return messages in this language.
	 */
	public AAPIQueryAllmessages lang(String lang) {

		this.lang = lang;

		return this;
	}

	/**Return messages in this language.
	 */
	public String getLang() {
		return this.lang;
	}

	/**Return messages starting at this message.
	 */
	public AAPIQueryAllmessages from(String from) {

		this.from = from;

		return this;
	}

	/**Return messages starting at this message.
	 */
	public String getFrom() {
		return this.from;
	}

	/**Return messages ending at this message.
	 */
	public AAPIQueryAllmessages to(String to) {

		this.to = to;

		return this;
	}

	/**Return messages ending at this message.
	 */
	public String getTo() {
		return this.to;
	}

	/**Page name to use as context when parsing message (for amenableparser option).
	 */
	public AAPIQueryAllmessages title(String title) {

		this.title = title;

		return this;
	}

	/**Page name to use as context when parsing message (for amenableparser option).
	 */
	public String getTitle() {
		return this.title;
	}

	/**Return messages with this prefix.
	 */
	public AAPIQueryAllmessages prefix(String prefix) {

		this.prefix = prefix;

		return this;
	}

	/**Return messages with this prefix.
	 */
	public String getPrefix() {
		return this.prefix;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryAllmessages(");

		if (messages != null) {

			sb.append("ammessages")
					.append("=")
					.append(messages.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("amprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (enableparser != null) {

			sb.append("amenableparser").append("=").append(enableparser.toString());

			sb.append(", ");
		}

		if (nocontent != null) {

			sb.append("amnocontent").append("=").append(nocontent.toString());

			sb.append(", ");
		}

		if (includelocal != null) {

			sb.append("amincludelocal").append("=").append(includelocal.toString());

			sb.append(", ");
		}

		if (args != null) {

			sb.append("amargs")
					.append("=")
					.append(args.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (filter != null) {

			sb.append("amfilter").append("=").append(filter);

			sb.append(", ");
		}

		if (customised != null) {

			sb.append("amcustomised").append("=").append(customised.getJsonValue());

			sb.append(", ");
		}

		if (lang != null) {

			sb.append("amlang").append("=").append(lang);

			sb.append(", ");
		}

		if (from != null) {

			sb.append("amfrom").append("=").append(from);

			sb.append(", ");
		}

		if (to != null) {

			sb.append("amto").append("=").append(to);

			sb.append(", ");
		}

		if (title != null) {

			sb.append("amtitle").append("=").append(title);

			sb.append(", ");
		}

		if (prefix != null) {

			sb.append("amprefix").append("=").append(prefix);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (messages != null) {

			req.addParameter(
					paramPrefix + "ammessages",
					messages.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "amprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (enableparser != null) {

			req.addParameter(paramPrefix + "amenableparser", enableparser.toString());
		}

		if (nocontent != null) {

			req.addParameter(paramPrefix + "amnocontent", nocontent.toString());
		}

		if (includelocal != null) {

			req.addParameter(paramPrefix + "amincludelocal", includelocal.toString());
		}

		if (args != null) {

			req.addParameter(
					paramPrefix + "amargs",
					args.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (filter != null) {

			req.addParameter(paramPrefix + "amfilter", filter);
		}

		if (customised != null) {

			req.addParameter(paramPrefix + "amcustomised", customised.getJsonValue());
		}

		if (lang != null) {

			req.addParameter(paramPrefix + "amlang", lang);
		}

		if (from != null) {

			req.addParameter(paramPrefix + "amfrom", from);
		}

		if (to != null) {

			req.addParameter(paramPrefix + "amto", to);
		}

		if (title != null) {

			req.addParameter(paramPrefix + "amtitle", title);
		}

		if (prefix != null) {

			req.addParameter(paramPrefix + "amprefix", prefix);
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
			c.accept(AAPIQueryAllmessages.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryAllmessages.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
