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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryImageinfoProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

/** Returns file information and upload history.
 */
public class AAPIQueryImageinfo implements AAPIModule, AAPIQueryPropModule {

	public static AAPIQueryImageinfo create() {

		AAPIQueryImageinfo v = new AAPIQueryImageinfo();

		return v;
	}

	private AAPIQueryImageinfo() {}

	private List<AAPIQueryImageinfoProp> prop;

	private Integer limit;

	private java.time.Instant start;

	private java.time.Instant end;

	private Long urlwidth;

	private Long urlheight;

	private String metadataversion;

	private String extmetadatalanguage;

	private Boolean extmetadatamultilang;

	private List<String> extmetadatafilter;

	private String urlparam;

	private String badfilecontexttitle;

	private Boolean localonly;

	/**<p>Which file information to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryImageinfo prop(AAPIQueryImageinfoProp prop) {
		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which file information to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryImageinfo prop(AAPIQueryImageinfoProp... prop) {
		this.prop = List.of(prop);
		return this;
	}

	/**<p>Which file information to get:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryImageinfoProp> getProp() {
		return this.prop;
	}

	/**How many file revisions to return per file.
	 */
	public AAPIQueryImageinfo limit(Integer limit) {
		this.limit = limit;

		return this;
	}

	/**How many file revisions to return per file.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**Timestamp to start listing from.
	 */
	public AAPIQueryImageinfo start(java.time.Instant start) {
		this.start = start;

		return this;
	}

	/**Timestamp to start listing from.
	 */
	public java.time.Instant getStart() {
		return this.start;
	}

	/**Timestamp to stop listing at.
	 */
	public AAPIQueryImageinfo end(java.time.Instant end) {
		this.end = end;

		return this;
	}

	/**Timestamp to stop listing at.
	 */
	public java.time.Instant getEnd() {
		return this.end;
	}

	/**If iiprop=url is set, a URL to an image scaled to this width will be returned.
	 * For performance reasons if this option is used, no more than 50 scaled images will be returned.
	 */
	public AAPIQueryImageinfo urlwidth(Long urlwidth) {
		this.urlwidth = urlwidth;

		return this;
	}

	/**If iiprop=url is set, a URL to an image scaled to this width will be returned.
	 * For performance reasons if this option is used, no more than 50 scaled images will be returned.
	 */
	public Long getUrlwidth() {
		return this.urlwidth;
	}

	/**Similar to iiurlwidth.
	 */
	public AAPIQueryImageinfo urlheight(Long urlheight) {
		this.urlheight = urlheight;

		return this;
	}

	/**Similar to iiurlwidth.
	 */
	public Long getUrlheight() {
		return this.urlheight;
	}

	/**Version of metadata to use. If <kbd>latest</kbd> is specified, use latest version. Defaults to <kbd>1</kbd> for backwards compatibility.
	 */
	public AAPIQueryImageinfo metadataversion(String metadataversion) {
		this.metadataversion = metadataversion;

		return this;
	}

	/**Version of metadata to use. If <kbd>latest</kbd> is specified, use latest version. Defaults to <kbd>1</kbd> for backwards compatibility.
	 */
	public String getMetadataversion() {
		return this.metadataversion;
	}

	/**What language to fetch extmetadata in. This affects both which translation to fetch, if multiple are available, as well as how things like numbers and various values are formatted.
	 */
	public AAPIQueryImageinfo extmetadatalanguage(String extmetadatalanguage) {
		this.extmetadatalanguage = extmetadatalanguage;

		return this;
	}

	/**What language to fetch extmetadata in. This affects both which translation to fetch, if multiple are available, as well as how things like numbers and various values are formatted.
	 */
	public String getExtmetadatalanguage() {
		return this.extmetadatalanguage;
	}

	/**If translations for extmetadata property are available, fetch all of them.
	 */
	public AAPIQueryImageinfo extmetadatamultilang(Boolean extmetadatamultilang) {
		this.extmetadatamultilang = extmetadatamultilang;

		return this;
	}

	/**If translations for extmetadata property are available, fetch all of them.
	 */
	public Boolean getExtmetadatamultilang() {
		return this.extmetadatamultilang;
	}

	/**If specified and non-empty, only these keys will be returned for iiprop=extmetadata.
	 */
	public AAPIQueryImageinfo extmetadatafilter(String extmetadatafilter) {
		this.extmetadatafilter = List.of(extmetadatafilter);

		return this;
	}

	/**If specified and non-empty, only these keys will be returned for iiprop=extmetadata.
	 */
	public AAPIQueryImageinfo extmetadatafilter(String... extmetadatafilter) {
		this.extmetadatafilter = List.of(extmetadatafilter);
		return this;
	}

	/**If specified and non-empty, only these keys will be returned for iiprop=extmetadata.
	 */
	public List<String> getExtmetadatafilter() {
		return this.extmetadatafilter;
	}

	/**A handler specific parameter string. For example, PDFs might use <kbd>page15-100px</kbd>. <var>iiurlwidth</var> must be used and be consistent with <var>iiurlparam</var>.
	 */
	public AAPIQueryImageinfo urlparam(String urlparam) {
		this.urlparam = urlparam;

		return this;
	}

	/**A handler specific parameter string. For example, PDFs might use <kbd>page15-100px</kbd>. <var>iiurlwidth</var> must be used and be consistent with <var>iiurlparam</var>.
	 */
	public String getUrlparam() {
		return this.urlparam;
	}

	/**If <kbd>badfilecontexttitleprop=badfile</kbd> is set, this is the page title used when evaluating the <a href="/wiki/MediaWiki:Bad_image_list" title="MediaWiki:Bad image list">MediaWiki:Bad image list</a>
	 */
	public AAPIQueryImageinfo badfilecontexttitle(String badfilecontexttitle) {
		this.badfilecontexttitle = badfilecontexttitle;

		return this;
	}

	/**If <kbd>badfilecontexttitleprop=badfile</kbd> is set, this is the page title used when evaluating the <a href="/wiki/MediaWiki:Bad_image_list" title="MediaWiki:Bad image list">MediaWiki:Bad image list</a>
	 */
	public String getBadfilecontexttitle() {
		return this.badfilecontexttitle;
	}

	/**Look only for files in the local repository.
	 */
	public AAPIQueryImageinfo localonly(Boolean localonly) {
		this.localonly = localonly;

		return this;
	}

	/**Look only for files in the local repository.
	 */
	public Boolean getLocalonly() {
		return this.localonly;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryImageinfo(");

		if (prop != null) {

			sb.append("iiprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("iilimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (start != null) {

			sb.append("iistart")
					.append("=")
					.append(start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (end != null) {

			sb.append("iiend")
					.append("=")
					.append(end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());

			sb.append(", ");
		}

		if (urlwidth != null) {

			sb.append("iiurlwidth").append("=").append(urlwidth.toString());

			sb.append(", ");
		}

		if (urlheight != null) {

			sb.append("iiurlheight").append("=").append(urlheight.toString());

			sb.append(", ");
		}

		if (metadataversion != null) {

			sb.append("iimetadataversion").append("=").append(metadataversion);

			sb.append(", ");
		}

		if (extmetadatalanguage != null) {

			sb.append("iiextmetadatalanguage").append("=").append(extmetadatalanguage);

			sb.append(", ");
		}

		if (extmetadatamultilang != null) {

			sb.append("iiextmetadatamultilang").append("=").append(extmetadatamultilang.toString());

			sb.append(", ");
		}

		if (extmetadatafilter != null) {

			sb.append("iiextmetadatafilter")
					.append("=")
					.append(
							extmetadatafilter.stream()
									.map(v -> v)
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (urlparam != null) {

			sb.append("iiurlparam").append("=").append(urlparam);

			sb.append(", ");
		}

		if (badfilecontexttitle != null) {

			sb.append("iibadfilecontexttitle").append("=").append(badfilecontexttitle);

			sb.append(", ");
		}

		if (localonly != null) {

			sb.append("iilocalonly").append("=").append(localonly.toString());

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "iiprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "iilimit", limit.toString());

		} else {
			req.addParameter(paramPrefix + "iilimit", "5000");
		}

		if (start != null) {

			req.addParameter(
					paramPrefix + "iistart",
					start.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (end != null) {

			req.addParameter(
					paramPrefix + "iiend",
					end.truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString());
		}

		if (urlwidth != null) {

			req.addParameter(paramPrefix + "iiurlwidth", urlwidth.toString());
		}

		if (urlheight != null) {

			req.addParameter(paramPrefix + "iiurlheight", urlheight.toString());
		}

		if (metadataversion != null) {

			req.addParameter(paramPrefix + "iimetadataversion", metadataversion);
		}

		if (extmetadatalanguage != null) {

			req.addParameter(paramPrefix + "iiextmetadatalanguage", extmetadatalanguage);
		}

		if (extmetadatamultilang != null) {

			req.addParameter(
					paramPrefix + "iiextmetadatamultilang", extmetadatamultilang.toString());
		}

		if (extmetadatafilter != null) {

			req.addParameter(
					paramPrefix + "iiextmetadatafilter",
					extmetadatafilter.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (urlparam != null) {

			req.addParameter(paramPrefix + "iiurlparam", urlparam);
		}

		if (badfilecontexttitle != null) {

			req.addParameter(paramPrefix + "iibadfilecontexttitle", badfilecontexttitle);
		}

		if (localonly != null) {

			req.addParameter(paramPrefix + "iilocalonly", localonly.toString());
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
			c.accept(AAPIQueryImageinfo.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryImageinfo.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}

		@Override
		protected boolean internalRequiresPagination() {
			return limit == null;
		}
	}
}
