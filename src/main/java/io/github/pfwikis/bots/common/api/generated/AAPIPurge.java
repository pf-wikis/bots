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

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator;

import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorModule;
import io.github.pfwikis.bots.common.api.generated.params.AAPIPurgeGenerator.AAPIPurgeGeneratorSubmodule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Purge the cache for the given titles.
 */
public class AAPIPurge implements AAPIModule, AAPIMainActionModule {

	public static AAPIPurge create() {

		AAPIPurge v = new AAPIPurge();

		return v;
	}

	private AAPIPurge() {}

	private Boolean forcelinkupdate;

	private Boolean forcerecursivelinkupdate;

	private List<String> titles;

	private List<Long> pageids;

	private List<Long> revids;

	private AAPIPurgeGeneratorSubmodule generator;

	private Boolean redirects;

	private Boolean converttitles;

	/**Update the links tables and do other secondary data updates.
	 */
	public AAPIPurge forcelinkupdate(Boolean forcelinkupdate) {

		this.forcelinkupdate = forcelinkupdate;

		return this;
	}

	/**Update the links tables and do other secondary data updates.
	 */
	public Boolean getForcelinkupdate() {
		return this.forcelinkupdate;
	}

	/**Same as <kbd>forcelinkupdate</kbd>, and update the links tables for any page that uses this page as a template.
	 */
	public AAPIPurge forcerecursivelinkupdate(Boolean forcerecursivelinkupdate) {

		this.forcerecursivelinkupdate = forcerecursivelinkupdate;

		return this;
	}

	/**Same as <kbd>forcelinkupdate</kbd>, and update the links tables for any page that uses this page as a template.
	 */
	public Boolean getForcerecursivelinkupdate() {
		return this.forcerecursivelinkupdate;
	}

	/**A list of titles to work on.
	 */
	public AAPIPurge titles(String... titles) {

		this.titles = List.of(titles);

		return this;
	}

	/**A list of titles to work on.
	 */
	public List<String> getTitles() {
		return this.titles;
	}

	/**A list of page IDs to work on.
	 */
	public AAPIPurge pageids(Long... pageids) {

		this.pageids = List.of(pageids);

		return this;
	}

	/**A list of page IDs to work on.
	 */
	public List<Long> getPageids() {
		return this.pageids;
	}

	/**A list of revision IDs to work on. Note that almost all query modules will convert revision IDs to the corresponding page ID and work on the latest revision instead. Only <kbd>prop=revisions</kbd> uses exact revisions for its response.
	 */
	public AAPIPurge revids(Long... revids) {

		this.revids = List.of(revids);

		return this;
	}

	/**A list of revision IDs to work on. Note that almost all query modules will convert revision IDs to the corresponding page ID and work on the latest revision instead. Only <kbd>prop=revisions</kbd> uses exact revisions for its response.
	 */
	public List<Long> getRevids() {
		return this.revids;
	}

	/**<p>Get the list of pages to work on by executing the specified query module.
	 * </p><p><strong>Note:</strong> Generator parameter names must be prefixed with a "g", see examples.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIPurge generator(AAPIPurgeGeneratorModule generator) {

		this.generator = AAPIPurgeGenerator.createSubmodule(generator);

		return this;
	}

	/**<p>Get the list of pages to work on by executing the specified query module.
	 * </p><p><strong>Note:</strong> Generator parameter names must be prefixed with a "g", see examples.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIPurgeGeneratorSubmodule getGenerator() {
		return this.generator;
	}

	/**Automatically resolve redirects in <var>titles</var>, <var>pageids</var>, and <var>revids</var>, and in pages returned by <var>generator</var>.
	 */
	public AAPIPurge redirects(Boolean redirects) {

		this.redirects = redirects;

		return this;
	}

	/**Automatically resolve redirects in <var>titles</var>, <var>pageids</var>, and <var>revids</var>, and in pages returned by <var>generator</var>.
	 */
	public Boolean getRedirects() {
		return this.redirects;
	}

	/**Convert titles to other variants if necessary. Only works if the wiki's content language supports variant conversion. Languages that support variant conversion include ban, en, crh, gan, iu, ku, mni, sh, shi, sr, tg, tly, uz, wuu, zgh and zh.
	 */
	public AAPIPurge converttitles(Boolean converttitles) {

		this.converttitles = converttitles;

		return this;
	}

	/**Convert titles to other variants if necessary. Only works if the wiki's content language supports variant conversion. Languages that support variant conversion include ban, en, crh, gan, iu, ku, mni, sh, shi, sr, tg, tly, uz, wuu, zgh and zh.
	 */
	public Boolean getConverttitles() {
		return this.converttitles;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIPurge(");

		if (forcelinkupdate != null) {

			sb.append("forcelinkupdate").append("=").append(forcelinkupdate.toString());

			sb.append(", ");
		}

		if (forcerecursivelinkupdate != null) {

			sb.append("forcerecursivelinkupdate")
					.append("=")
					.append(forcerecursivelinkupdate.toString());

			sb.append(", ");
		}

		if (titles != null) {

			sb.append("titles")
					.append("=")
					.append(titles.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (pageids != null) {

			sb.append("pageids")
					.append("=")
					.append(
							pageids.stream()
									.map(v -> v.toString())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (revids != null) {

			sb.append("revids")
					.append("=")
					.append(
							revids.stream()
									.map(v -> v.toString())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (generator != null) {

			sb.append("generator").append("=").append(generator.getValue().toString());

			sb.append(", ");
		}

		if (redirects != null) {

			sb.append("redirects").append("=").append(redirects.toString());

			sb.append(", ");
		}

		if (converttitles != null) {

			sb.append("converttitles").append("=").append(converttitles.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (forcelinkupdate != null) {

			req.addParameter(paramPrefix + "forcelinkupdate", forcelinkupdate.toString());
		}

		if (forcerecursivelinkupdate != null) {

			req.addParameter(
					paramPrefix + "forcerecursivelinkupdate", forcerecursivelinkupdate.toString());
		}

		if (titles != null) {

			req.addParameter(
					paramPrefix + "titles",
					titles.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (pageids != null) {

			req.addParameter(
					paramPrefix + "pageids",
					pageids.stream().map(v -> v.toString()).collect(Collectors.joining("|")));
		}

		if (revids != null) {

			req.addParameter(
					paramPrefix + "revids",
					revids.stream().map(v -> v.toString()).collect(Collectors.joining("|")));
		}

		if (generator != null) {

			req.addParameter(paramPrefix + "generator", generator.getKey().getJsonValue());

			generator.getValue().buildRequest(api, req, paramPrefix + "g");
		}

		if (redirects != null) {

			req.addParameter(paramPrefix + "redirects", redirects.toString());
		}

		if (converttitles != null) {

			req.addParameter(paramPrefix + "converttitles", converttitles.toString());
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
			c.accept(AAPIPurge.this);

			if (generator != null) {

				generator.getValue().builder().forEachModule(c);
			}
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIPurge.this);

			if (generator != null) {

				value = reduce.apply(value, generator.getValue().builder().mapReduce(map, reduce));
			}

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
