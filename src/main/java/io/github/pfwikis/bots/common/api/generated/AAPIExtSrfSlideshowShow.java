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

/** ⧼apihelp-ext.srf.slideshow.show-summary⧽
 *
 */
public class AAPIExtSrfSlideshowShow implements AAPIModule, AAPIMainActionModule {

	public static AAPIExtSrfSlideshowShow create(@NonNull Long pageid, @NonNull String template) {

		AAPIExtSrfSlideshowShow v = new AAPIExtSrfSlideshowShow();

		v.pageid = pageid;

		v.template = template;

		return v;
	}

	private AAPIExtSrfSlideshowShow() {}

	private Long pageid;

	private String template;

	private String printouts;

	/**<span class="apihelp-empty">(no description)</span>
	 */
	public Long getPageid() {
		return this.pageid;
	}

	/**<span class="apihelp-empty">(no description)</span>
	 */
	public String getTemplate() {
		return this.template;
	}

	/**<span class="apihelp-empty">(no description)</span>
	 */
	public AAPIExtSrfSlideshowShow printouts(String printouts) {
		this.printouts = printouts;

		return this;
	}

	/**<span class="apihelp-empty">(no description)</span>
	 */
	public String getPrintouts() {
		return this.printouts;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIExtSrfSlideshowShow(");

		if (pageid != null) {

			sb.append("pageid").append("=").append(pageid.toString());

			sb.append(", ");
		}

		if (template != null) {

			sb.append("template").append("=").append(template);

			sb.append(", ");
		}

		if (printouts != null) {

			sb.append("printouts").append("=").append(printouts);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (pageid != null) {

			req.addParameter(paramPrefix + "pageid", pageid.toString());
		}

		if (template != null) {

			req.addParameter(paramPrefix + "template", template);
		}

		if (printouts != null) {

			req.addParameter(paramPrefix + "printouts", printouts);
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
			c.accept(AAPIExtSrfSlideshowShow.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIExtSrfSlideshowShow.this);

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
