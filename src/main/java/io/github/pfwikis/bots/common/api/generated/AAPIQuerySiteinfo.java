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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQuerySiteinfoProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQuerySiteinfoFilteriw;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryMeta.AAPIQueryMetaModule;

/** Return general information about the site.
 */
public class AAPIQuerySiteinfo implements AAPIModule, AAPIQueryMetaModule {

	public static AAPIQuerySiteinfo create() {

		AAPIQuerySiteinfo v = new AAPIQuerySiteinfo();

		return v;
	}

	private AAPIQuerySiteinfo() {}

	private List<AAPIQuerySiteinfoProp> prop;

	private AAPIQuerySiteinfoFilteriw filteriw;

	private Boolean showalldb;

	private Boolean numberingroup;

	private String inlanguagecode;

	/**<p>Which information to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQuerySiteinfo prop(AAPIQuerySiteinfoProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which information to get:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQuerySiteinfoProp> getProp() {
		return this.prop;
	}

	/**Return only local or only nonlocal entries of the interwiki map.
	 */
	public AAPIQuerySiteinfo filteriw(AAPIQuerySiteinfoFilteriw filteriw) {

		this.filteriw = filteriw;

		return this;
	}

	/**Return only local or only nonlocal entries of the interwiki map.
	 */
	public AAPIQuerySiteinfoFilteriw getFilteriw() {
		return this.filteriw;
	}

	/**List all database servers, not just the one lagging the most.
	 */
	public AAPIQuerySiteinfo showalldb(Boolean showalldb) {

		this.showalldb = showalldb;

		return this;
	}

	/**List all database servers, not just the one lagging the most.
	 */
	public Boolean getShowalldb() {
		return this.showalldb;
	}

	/**Lists the number of users in user groups.
	 */
	public AAPIQuerySiteinfo numberingroup(Boolean numberingroup) {

		this.numberingroup = numberingroup;

		return this;
	}

	/**Lists the number of users in user groups.
	 */
	public Boolean getNumberingroup() {
		return this.numberingroup;
	}

	/**Language code for localised language names (best effort) and skin names.
	 */
	public AAPIQuerySiteinfo inlanguagecode(String inlanguagecode) {

		this.inlanguagecode = inlanguagecode;

		return this;
	}

	/**Language code for localised language names (best effort) and skin names.
	 */
	public String getInlanguagecode() {
		return this.inlanguagecode;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQuerySiteinfo(");

		if (prop != null) {

			sb.append("siprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (filteriw != null) {

			sb.append("sifilteriw").append("=").append(filteriw.getJsonValue());

			sb.append(", ");
		}

		if (showalldb != null) {

			sb.append("sishowalldb").append("=").append(showalldb.toString());

			sb.append(", ");
		}

		if (numberingroup != null) {

			sb.append("sinumberingroup").append("=").append(numberingroup.toString());

			sb.append(", ");
		}

		if (inlanguagecode != null) {

			sb.append("siinlanguagecode").append("=").append(inlanguagecode);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "siprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (filteriw != null) {

			req.addParameter(paramPrefix + "sifilteriw", filteriw.getJsonValue());
		}

		if (showalldb != null) {

			req.addParameter(paramPrefix + "sishowalldb", showalldb.toString());
		}

		if (numberingroup != null) {

			req.addParameter(paramPrefix + "sinumberingroup", numberingroup.toString());
		}

		if (inlanguagecode != null) {

			req.addParameter(paramPrefix + "siinlanguagecode", inlanguagecode);
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
			c.accept(AAPIQuerySiteinfo.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQuerySiteinfo.this);

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
