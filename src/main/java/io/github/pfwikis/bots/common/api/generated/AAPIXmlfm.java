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

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainFormat.AAPIMainFormatModule;

/** Output data in XML format (pretty-print in HTML).
 */
public class AAPIXmlfm implements AAPIModule, AAPIMainFormatModule {

	public static AAPIXmlfm create() {

		AAPIXmlfm v = new AAPIXmlfm();

		return v;
	}

	private AAPIXmlfm() {}

	private Boolean wrappedhtml;

	private String xslt;

	private Boolean includexmlnamespace;

	/**Return the pretty-printed HTML and associated ResourceLoader modules as a JSON object.
	 */
	public AAPIXmlfm wrappedhtml(Boolean wrappedhtml) {

		this.wrappedhtml = wrappedhtml;

		return this;
	}

	/**Return the pretty-printed HTML and associated ResourceLoader modules as a JSON object.
	 */
	public Boolean getWrappedhtml() {
		return this.wrappedhtml;
	}

	/**If specified, adds the named page as an XSL stylesheet. The value must be a title in the MediaWiki namespace ending in <code>.xsl</code>.
	 */
	public AAPIXmlfm xslt(String xslt) {

		this.xslt = xslt;

		return this;
	}

	/**If specified, adds the named page as an XSL stylesheet. The value must be a title in the MediaWiki namespace ending in <code>.xsl</code>.
	 */
	public String getXslt() {
		return this.xslt;
	}

	/**If specified, adds an XML namespace.
	 */
	public AAPIXmlfm includexmlnamespace(Boolean includexmlnamespace) {

		this.includexmlnamespace = includexmlnamespace;

		return this;
	}

	/**If specified, adds an XML namespace.
	 */
	public Boolean getIncludexmlnamespace() {
		return this.includexmlnamespace;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIXmlfm(");

		if (wrappedhtml != null) {

			sb.append("wrappedhtml").append("=").append(wrappedhtml.toString());

			sb.append(", ");
		}

		if (xslt != null) {

			sb.append("xslt").append("=").append(xslt);

			sb.append(", ");
		}

		if (includexmlnamespace != null) {

			sb.append("includexmlnamespace").append("=").append(includexmlnamespace.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (wrappedhtml != null) {

			req.addParameter(paramPrefix + "wrappedhtml", wrappedhtml.toString());
		}

		if (xslt != null) {

			req.addParameter(paramPrefix + "xslt", xslt);
		}

		if (includexmlnamespace != null) {

			req.addParameter(paramPrefix + "includexmlnamespace", includexmlnamespace.toString());
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
			c.accept(AAPIXmlfm.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIXmlfm.this);

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
