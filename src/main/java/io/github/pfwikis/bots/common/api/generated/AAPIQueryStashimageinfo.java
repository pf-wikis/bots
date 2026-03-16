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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryStashimageinfoProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

/** Returns file information for stashed files.
 */
public class AAPIQueryStashimageinfo implements AAPIModule, AAPIQueryPropModule {

	public static AAPIQueryStashimageinfo create() {

		AAPIQueryStashimageinfo v = new AAPIQueryStashimageinfo();

		return v;
	}

	private AAPIQueryStashimageinfo() {}

	private List<String> filekey;

	private List<AAPIQueryStashimageinfoProp> prop;

	private Long urlwidth;

	private Long urlheight;

	private String urlparam;

	/**Key that identifies a previous upload that was stashed temporarily.
	 */
	public AAPIQueryStashimageinfo filekey(String filekey) {
		this.filekey = List.of(filekey);

		return this;
	}

	/**Key that identifies a previous upload that was stashed temporarily.
	 */
	public AAPIQueryStashimageinfo filekey(String... filekey) {
		this.filekey = List.of(filekey);
		return this;
	}

	/**Key that identifies a previous upload that was stashed temporarily.
	 */
	public List<String> getFilekey() {
		return this.filekey;
	}

	/**<p>Which file information to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryStashimageinfo prop(AAPIQueryStashimageinfoProp prop) {
		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which file information to get:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryStashimageinfo prop(AAPIQueryStashimageinfoProp... prop) {
		this.prop = List.of(prop);
		return this;
	}

	/**<p>Which file information to get:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryStashimageinfoProp> getProp() {
		return this.prop;
	}

	/**If siiprop=url is set, a URL to an image scaled to this width will be returned.
	 * For performance reasons if this option is used, no more than 50 scaled images will be returned.
	 */
	public AAPIQueryStashimageinfo urlwidth(Long urlwidth) {
		this.urlwidth = urlwidth;

		return this;
	}

	/**If siiprop=url is set, a URL to an image scaled to this width will be returned.
	 * For performance reasons if this option is used, no more than 50 scaled images will be returned.
	 */
	public Long getUrlwidth() {
		return this.urlwidth;
	}

	/**Similar to siiurlwidth.
	 */
	public AAPIQueryStashimageinfo urlheight(Long urlheight) {
		this.urlheight = urlheight;

		return this;
	}

	/**Similar to siiurlwidth.
	 */
	public Long getUrlheight() {
		return this.urlheight;
	}

	/**A handler specific parameter string. For example, PDFs might use <kbd>page15-100px</kbd>. <var>siiurlwidth</var> must be used and be consistent with <var>siiurlparam</var>.
	 */
	public AAPIQueryStashimageinfo urlparam(String urlparam) {
		this.urlparam = urlparam;

		return this;
	}

	/**A handler specific parameter string. For example, PDFs might use <kbd>page15-100px</kbd>. <var>siiurlwidth</var> must be used and be consistent with <var>siiurlparam</var>.
	 */
	public String getUrlparam() {
		return this.urlparam;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryStashimageinfo(");

		if (filekey != null) {

			sb.append("siifilekey")
					.append("=")
					.append(filekey.stream().map(v -> v).collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (prop != null) {

			sb.append("siiprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (urlwidth != null) {

			sb.append("siiurlwidth").append("=").append(urlwidth.toString());

			sb.append(", ");
		}

		if (urlheight != null) {

			sb.append("siiurlheight").append("=").append(urlheight.toString());

			sb.append(", ");
		}

		if (urlparam != null) {

			sb.append("siiurlparam").append("=").append(urlparam);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (filekey != null) {

			req.addParameter(
					paramPrefix + "siifilekey",
					filekey.stream().map(v -> v).collect(Collectors.joining("|")));
		}

		if (prop != null) {

			req.addParameter(
					paramPrefix + "siiprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (urlwidth != null) {

			req.addParameter(paramPrefix + "siiurlwidth", urlwidth.toString());
		}

		if (urlheight != null) {

			req.addParameter(paramPrefix + "siiurlheight", urlheight.toString());
		}

		if (urlparam != null) {

			req.addParameter(paramPrefix + "siiurlparam", urlparam);
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
			c.accept(AAPIQueryStashimageinfo.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryStashimageinfo.this);

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
