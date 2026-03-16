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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;
import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropSubmodule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListModule;
import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryList.AAPIQueryListSubmodule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryMeta;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryMeta.AAPIQueryMetaModule;
import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryMeta.AAPIQueryMetaSubmodule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryExportschema;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorModule;
import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryGenerator.AAPIQueryGeneratorSubmodule;

import io.github.pfwikis.bots.common.api.generated.params.AAPIMainAction.AAPIMainActionModule;

/** <p>Fetch data from and about MediaWiki.
 * </p>
 * <p>All data modifications will first have to use query to acquire a token to prevent abuse from malicious sites.
 * </p>
 */
public class AAPIQuery implements AAPIModule, AAPIMainActionModule {

	public static AAPIQuery create() {

		AAPIQuery v = new AAPIQuery();

		return v;
	}

	private AAPIQuery() {}

	private List<AAPIQueryPropSubmodule> prop;

	private List<AAPIQueryListSubmodule> list;

	private List<AAPIQueryMetaSubmodule> meta;

	private Boolean indexpageids;

	private Boolean export;

	private Boolean exportnowrap;

	private AAPIQueryExportschema exportschema;

	private Boolean iwurl;

	private Boolean rawcontinue;

	private List<Long> revids;

	private AAPIQueryGeneratorSubmodule generator;

	private Boolean redirects;

	private Boolean converttitles;

	private List<ContainsPageRef> titles;

	/**<p>Which properties to get for the queried pages.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQuery prop(AAPIQueryPropModule prop) {
		this.prop = List.of(AAPIQueryProp.createSubmodule(prop));
		return this;
	}

	/**<p>Which properties to get for the queried pages.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQuery prop(AAPIQueryPropModule... prop) {
		this.prop = AAPIQueryProp.createSubmodule(prop);
		return this;
	}

	/**<p>Which properties to get for the queried pages.
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryPropSubmodule> getProp() {
		return this.prop;
	}

	/**<p>Which lists to get.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQuery list(AAPIQueryListModule list) {
		this.list = List.of(AAPIQueryList.createSubmodule(list));
		return this;
	}

	/**<p>Which lists to get.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQuery list(AAPIQueryListModule... list) {
		this.list = AAPIQueryList.createSubmodule(list);
		return this;
	}

	/**<p>Which lists to get.
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryListSubmodule> getList() {
		return this.list;
	}

	/**<p>Which metadata to get.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQuery meta(AAPIQueryMetaModule meta) {
		this.meta = List.of(AAPIQueryMeta.createSubmodule(meta));
		return this;
	}

	/**<p>Which metadata to get.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQuery meta(AAPIQueryMetaModule... meta) {
		this.meta = AAPIQueryMeta.createSubmodule(meta);
		return this;
	}

	/**<p>Which metadata to get.
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryMetaSubmodule> getMeta() {
		return this.meta;
	}

	/**Include an additional pageids section listing all returned page IDs.
	 */
	public AAPIQuery indexpageids(Boolean indexpageids) {
		this.indexpageids = indexpageids;

		return this;
	}

	/**Include an additional pageids section listing all returned page IDs.
	 */
	public Boolean getIndexpageids() {
		return this.indexpageids;
	}

	/**Export the current revisions of all given or generated pages.
	 */
	public AAPIQuery export(Boolean export) {
		this.export = export;

		return this;
	}

	/**Export the current revisions of all given or generated pages.
	 */
	public Boolean getExport() {
		return this.export;
	}

	/**Return the export XML without wrapping it in an XML result (same format as <a href="/wiki/Special:Export" title="Special:Export">Special:Export</a>). Can only be used with query+export.
	 */
	public AAPIQuery exportnowrap(Boolean exportnowrap) {
		this.exportnowrap = exportnowrap;

		return this;
	}

	/**Return the export XML without wrapping it in an XML result (same format as <a href="/wiki/Special:Export" title="Special:Export">Special:Export</a>). Can only be used with query+export.
	 */
	public Boolean getExportnowrap() {
		return this.exportnowrap;
	}

	/**Target the given version of the XML dump format when exporting. Can only be used with <var>query+export</var>.
	 */
	public AAPIQuery exportschema(AAPIQueryExportschema exportschema) {
		this.exportschema = exportschema;

		return this;
	}

	/**Target the given version of the XML dump format when exporting. Can only be used with <var>query+export</var>.
	 */
	public AAPIQueryExportschema getExportschema() {
		return this.exportschema;
	}

	/**Whether to get the full URL if the title is an interwiki link.
	 */
	public AAPIQuery iwurl(Boolean iwurl) {
		this.iwurl = iwurl;

		return this;
	}

	/**Whether to get the full URL if the title is an interwiki link.
	 */
	public Boolean getIwurl() {
		return this.iwurl;
	}

	/**Return raw <samp>query-continue</samp> data for continuation.
	 */
	public AAPIQuery rawcontinue(Boolean rawcontinue) {
		this.rawcontinue = rawcontinue;

		return this;
	}

	/**Return raw <samp>query-continue</samp> data for continuation.
	 */
	public Boolean getRawcontinue() {
		return this.rawcontinue;
	}

	/**A list of revision IDs to work on. Note that almost all query modules will convert revision IDs to the corresponding page ID and work on the latest revision instead. Only <kbd>prop=revisions</kbd> uses exact revisions for its response.
	 */
	public AAPIQuery revids(Long revids) {
		this.revids = List.of(revids);

		return this;
	}

	/**A list of revision IDs to work on. Note that almost all query modules will convert revision IDs to the corresponding page ID and work on the latest revision instead. Only <kbd>prop=revisions</kbd> uses exact revisions for its response.
	 */
	public AAPIQuery revids(Long... revids) {
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
	public AAPIQuery generator(AAPIQueryGeneratorModule generator) {
		this.generator = AAPIQueryGenerator.createSubmodule(generator);

		return this;
	}

	/**<p>Get the list of pages to work on by executing the specified query module.
	 * </p><p><strong>Note:</strong> Generator parameter names must be prefixed with a "g", see examples.
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryGeneratorSubmodule getGenerator() {
		return this.generator;
	}

	/**Automatically resolve redirects in <var>query+titles</var>, <var>query+pageids</var>, and <var>query+revids</var>, and in pages returned by <var>query+generator</var>.
	 */
	public AAPIQuery redirects(Boolean redirects) {
		this.redirects = redirects;

		return this;
	}

	/**Automatically resolve redirects in <var>query+titles</var>, <var>query+pageids</var>, and <var>query+revids</var>, and in pages returned by <var>query+generator</var>.
	 */
	public Boolean getRedirects() {
		return this.redirects;
	}

	/**Convert titles to other variants if necessary. Only works if the wiki's content language supports variant conversion. Languages that support variant conversion include ban, en, crh, gan, iu, ku, mni, sh, shi, sr, tg, tly, uz, wuu, zgh and zh.
	 */
	public AAPIQuery converttitles(Boolean converttitles) {
		this.converttitles = converttitles;

		return this;
	}

	/**Convert titles to other variants if necessary. Only works if the wiki's content language supports variant conversion. Languages that support variant conversion include ban, en, crh, gan, iu, ku, mni, sh, shi, sr, tg, tly, uz, wuu, zgh and zh.
	 */
	public Boolean getConverttitles() {
		return this.converttitles;
	}

	public AAPIQuery titles(ContainsPageRef titles) {
		this.titles = List.of(titles);
		return this;
	}

	public AAPIQuery titles(ContainsPageRef... titles) {
		this.titles = List.of(titles);
		return this;
	}

	public List<ContainsPageRef> getTitles() {
		return this.titles;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQuery(");

		if (titles != null) {
			sb.append("titles=").append(titles).append(", ");
		}

		if (prop != null) {

			sb.append("prop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getValue().toString())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (list != null) {

			sb.append("list")
					.append("=")
					.append(
							list.stream()
									.map(v -> v.getValue().toString())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (meta != null) {

			sb.append("meta")
					.append("=")
					.append(
							meta.stream()
									.map(v -> v.getValue().toString())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (indexpageids != null) {

			sb.append("indexpageids").append("=").append(indexpageids.toString());

			sb.append(", ");
		}

		if (export != null) {

			sb.append("export").append("=").append(export.toString());

			sb.append(", ");
		}

		if (exportnowrap != null) {

			sb.append("exportnowrap").append("=").append(exportnowrap.toString());

			sb.append(", ");
		}

		if (exportschema != null) {

			sb.append("exportschema").append("=").append(exportschema.getJsonValue());

			sb.append(", ");
		}

		if (iwurl != null) {

			sb.append("iwurl").append("=").append(iwurl.toString());

			sb.append(", ");
		}

		if (rawcontinue != null) {

			sb.append("rawcontinue").append("=").append(rawcontinue.toString());

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

		if (titles != null) {

			if (titles.stream().allMatch(cpr -> cpr.toPageRef().hasId())) {
				String val =
						titles.stream()
								.map(p -> Integer.toString(p.toPageRef().getId()))
								.collect(Collectors.joining("|"));
				req.addParameter(paramPrefix + "pageids", val);
			} else {
				String val =
						titles.stream()
								.map(p -> p.toPageTitle().toFullTitle())
								.collect(Collectors.joining("|"));
				req.addParameter(paramPrefix + "titles", val);
			}
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "prop",
					prop.stream()
							.map(v -> v.getKey().getJsonValue())
							.collect(Collectors.joining("|")));

			prop.forEach(v -> v.getValue().buildRequest(api, req, paramPrefix));
		}

		if (list != null) {

			req.addParameter(
					paramPrefix + "list",
					list.stream()
							.map(v -> v.getKey().getJsonValue())
							.collect(Collectors.joining("|")));

			list.forEach(v -> v.getValue().buildRequest(api, req, paramPrefix));
		}

		if (meta != null) {

			req.addParameter(
					paramPrefix + "meta",
					meta.stream()
							.map(v -> v.getKey().getJsonValue())
							.collect(Collectors.joining("|")));

			meta.forEach(v -> v.getValue().buildRequest(api, req, paramPrefix));
		}

		if (indexpageids != null) {

			req.addParameter(paramPrefix + "indexpageids", indexpageids.toString());
		}

		if (export != null) {

			req.addParameter(paramPrefix + "export", export.toString());
		}

		if (exportnowrap != null) {

			req.addParameter(paramPrefix + "exportnowrap", exportnowrap.toString());
		}

		if (exportschema != null) {

			req.addParameter(paramPrefix + "exportschema", exportschema.getJsonValue());
		}

		if (iwurl != null) {

			req.addParameter(paramPrefix + "iwurl", iwurl.toString());
		}

		if (rawcontinue != null) {

			req.addParameter(paramPrefix + "rawcontinue", rawcontinue.toString());
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
			c.accept(AAPIQuery.this);

			if (prop != null) {

				prop.forEach(v -> v.getValue().builder().forEachModule(c));
			}

			if (list != null) {

				list.forEach(v -> v.getValue().builder().forEachModule(c));
			}

			if (meta != null) {

				meta.forEach(v -> v.getValue().builder().forEachModule(c));
			}

			if (generator != null) {

				generator.getValue().builder().forEachModule(c);
			}
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQuery.this);

			if (prop != null) {

				value =
						reduce.apply(
								value,
								prop.stream().map(e -> e.getValue()).map(map).reduce(reduce).get());
			}

			if (list != null) {

				value =
						reduce.apply(
								value,
								list.stream().map(e -> e.getValue()).map(map).reduce(reduce).get());
			}

			if (meta != null) {

				value =
						reduce.apply(
								value,
								meta.stream().map(e -> e.getValue()).map(map).reduce(reduce).get());
			}

			if (generator != null) {

				value = reduce.apply(value, generator.getValue().builder().mapReduce(map, reduce));
			}

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
