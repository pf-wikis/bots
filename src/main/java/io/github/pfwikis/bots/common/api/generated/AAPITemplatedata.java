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

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator;

import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorModule;
import io.github.pfwikis.bots.common.api.generated.params.AAPITemplatedataGenerator.AAPITemplatedataGeneratorSubmodule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** Fetch data stored by the TemplateData extension.
 */
public class AAPITemplatedata implements AAPIModule, AAPIMainActionModule {

	public static AAPITemplatedata create() {

		AAPITemplatedata v = new AAPITemplatedata();

		return v;
	}

	private AAPITemplatedata() {}

	private Boolean includeMissingTitles;

	private String lang;

	private List<String> titles;

	private List<Integer> pageids;

	private List<Integer> revids;

	private AAPITemplatedataGeneratorSubmodule generator;

	private Boolean redirects;

	private Boolean converttitles;

	/**Return data about titles even if they are missing or lack TemplateData. By default titles are only returned if they exist and have TemplateData.
	 */
	public AAPITemplatedata includeMissingTitles(Boolean includeMissingTitles) {

		this.includeMissingTitles = includeMissingTitles;

		return this;
	}

	/**Return data about titles even if they are missing or lack TemplateData. By default titles are only returned if they exist and have TemplateData.
	 */
	public Boolean getIncludeMissingTitles() {
		return this.includeMissingTitles;
	}

	/**Return localized values in this language. By default all available translations are returned.
	 */
	public AAPITemplatedata lang(String lang) {

		this.lang = lang;

		return this;
	}

	/**Return localized values in this language. By default all available translations are returned.
	 */
	public String getLang() {
		return this.lang;
	}

	/**A list of titles to work on.
	 */
	public AAPITemplatedata titles(String... titles) {

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
	public AAPITemplatedata pageids(Integer... pageids) {

		this.pageids = List.of(pageids);

		return this;
	}

	/**A list of page IDs to work on.
	 */
	public List<Integer> getPageids() {
		return this.pageids;
	}

	/**A list of revision IDs to work on. Note that almost all query modules will convert revision IDs to the corresponding page ID and work on the latest revision instead. Only <kbd>prop=revisions</kbd> uses exact revisions for its response.
	 */
	public AAPITemplatedata revids(Integer... revids) {

		this.revids = List.of(revids);

		return this;
	}

	/**A list of revision IDs to work on. Note that almost all query modules will convert revision IDs to the corresponding page ID and work on the latest revision instead. Only <kbd>prop=revisions</kbd> uses exact revisions for its response.
	 */
	public List<Integer> getRevids() {
		return this.revids;
	}

	/**<p>Get the list of pages to work on by executing the specified query module.
	 * </p><p><strong>Note:</strong> Generator parameter names must be prefixed with a "g", see examples.
	 * </p>
	 * <dl></dl>
	 */
	public AAPITemplatedata generator(AAPITemplatedataGeneratorModule generator) {

		this.generator = AAPITemplatedataGenerator.createSubmodule(generator);

		return this;
	}

	/**<p>Get the list of pages to work on by executing the specified query module.
	 * </p><p><strong>Note:</strong> Generator parameter names must be prefixed with a "g", see examples.
	 * </p>
	 * <dl></dl>
	 */
	public AAPITemplatedataGeneratorSubmodule getGenerator() {
		return this.generator;
	}

	/**Automatically resolve redirects in <var>titles</var>, <var>pageids</var>, and <var>revids</var>, and in pages returned by <var>generator</var>.
	 */
	public AAPITemplatedata redirects(Boolean redirects) {

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
	public AAPITemplatedata converttitles(Boolean converttitles) {

		this.converttitles = converttitles;

		return this;
	}

	/**Convert titles to other variants if necessary. Only works if the wiki's content language supports variant conversion. Languages that support variant conversion include ban, en, crh, gan, iu, ku, mni, sh, shi, sr, tg, tly, uz, wuu, zgh and zh.
	 */
	public Boolean getConverttitles() {
		return this.converttitles;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPITemplatedata(");

		if (includeMissingTitles != null) {

			sb.append("includeMissingTitles").append("=").append(includeMissingTitles.toString());

			sb.append(", ");
		}

		if (lang != null) {

			sb.append("lang").append("=").append(lang);

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

		if (includeMissingTitles != null) {

			req.addParameter(paramPrefix + "includeMissingTitles", includeMissingTitles.toString());
		}

		if (lang != null) {

			req.addParameter(paramPrefix + "lang", lang);
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
			c.accept(AAPITemplatedata.this);

			if (generator != null) {

				generator.getValue().builder().forEachModule(c);
			}
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPITemplatedata.this);

			if (generator != null) {

				value = reduce.apply(value, generator.getValue().builder().mapReduce(map, reduce));
			}

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
