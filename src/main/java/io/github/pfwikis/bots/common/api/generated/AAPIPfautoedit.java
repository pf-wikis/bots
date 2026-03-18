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

/** Create or edit a page using a form defined by the Page Forms extension.
 */
public class AAPIPfautoedit implements AAPIModule, AAPIMainActionModule {

	public static AAPIPfautoedit create() {

		AAPIPfautoedit v = new AAPIPfautoedit();

		return v;
	}

	private AAPIPfautoedit() {}

	private String form;

	private String target;

	private String query;

	private String preload;

	/**The Page Forms form to use
	 */
	public AAPIPfautoedit form(String form) {

		this.form = form;

		return this;
	}

	/**The Page Forms form to use
	 */
	public String getForm() {
		return this.form;
	}

	/**The name of the page to be created or edited
	 */
	public AAPIPfautoedit target(String target) {

		this.target = target;

		return this;
	}

	/**The name of the page to be created or edited
	 */
	public String getTarget() {
		return this.target;
	}

	/**The query string
	 */
	public AAPIPfautoedit query(String query) {

		this.query = query;

		return this;
	}

	/**The query string
	 */
	public String getQuery() {
		return this.query;
	}

	/**The name of a page to preload in the form
	 */
	public AAPIPfautoedit preload(String preload) {

		this.preload = preload;

		return this;
	}

	/**The name of a page to preload in the form
	 */
	public String getPreload() {
		return this.preload;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIPfautoedit(");

		if (form != null) {

			sb.append("form").append("=").append(form);

			sb.append(", ");
		}

		if (target != null) {

			sb.append("target").append("=").append(target);

			sb.append(", ");
		}

		if (query != null) {

			sb.append("query").append("=").append(query);

			sb.append(", ");
		}

		if (preload != null) {

			sb.append("preload").append("=").append(preload);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (form != null) {

			req.addParameter(paramPrefix + "form", form);
		}

		if (target != null) {

			req.addParameter(paramPrefix + "target", target);
		}

		if (query != null) {

			req.addParameter(paramPrefix + "query", query);
		}

		if (preload != null) {

			req.addParameter(paramPrefix + "preload", preload);
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
			c.accept(AAPIPfautoedit.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIPfautoedit.this);

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
