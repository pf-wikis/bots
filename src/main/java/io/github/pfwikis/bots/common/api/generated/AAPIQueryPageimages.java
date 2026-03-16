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

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryPageimagesProp;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryPageimagesLicense;

import io.github.pfwikis.bots.common.api.generated.params.AAPIQueryProp.AAPIQueryPropModule;

/** Returns information about images on the page, such as thumbnail and presence of photos.
 */
public class AAPIQueryPageimages implements AAPIModule, AAPIQueryPropModule {

	public static AAPIQueryPageimages create() {

		AAPIQueryPageimages v = new AAPIQueryPageimages();

		return v;
	}

	private AAPIQueryPageimages() {}

	private List<AAPIQueryPageimagesProp> prop;

	private Integer thumbsize;

	private Integer limit = 100;

	private AAPIQueryPageimagesLicense license;

	private String langcode;

	/**<p>Which information to return:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryPageimages prop(AAPIQueryPageimagesProp... prop) {

		this.prop = List.of(prop);

		return this;
	}

	/**<p>Which information to return:
	 * </p>
	 * <dl></dl>
	 */
	public List<AAPIQueryPageimagesProp> getProp() {
		return this.prop;
	}

	/**Maximum width in pixels of thumbnail images.
	 */
	public AAPIQueryPageimages thumbsize(Integer thumbsize) {

		this.thumbsize = thumbsize;

		return this;
	}

	/**Maximum width in pixels of thumbnail images.
	 */
	public Integer getThumbsize() {
		return this.thumbsize;
	}

	/**Properties of how many pages to return.
	 */
	public AAPIQueryPageimages limit(Integer limit) {

		this.limit = limit;

		return this;
	}

	/**Properties of how many pages to return.
	 */
	public Integer getLimit() {
		return this.limit;
	}

	/**<p>Limit page images to a certain license type:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryPageimages license(AAPIQueryPageimagesLicense license) {

		this.license = license;

		return this;
	}

	/**<p>Limit page images to a certain license type:
	 * </p>
	 * <dl></dl>
	 */
	public AAPIQueryPageimagesLicense getLicense() {
		return this.license;
	}

	/**Code for the language the image is going to be rendered in if multiple languages are supported
	 */
	public AAPIQueryPageimages langcode(String langcode) {

		this.langcode = langcode;

		return this;
	}

	/**Code for the language the image is going to be rendered in if multiple languages are supported
	 */
	public String getLangcode() {
		return this.langcode;
	}

	public String toString() {
		var sb = new StringBuilder().append("AAPIQueryPageimages(");

		if (prop != null) {

			sb.append("piprop")
					.append("=")
					.append(
							prop.stream()
									.map(v -> v.getJsonValue())
									.collect(Collectors.joining("|")));

			sb.append(", ");
		}

		if (thumbsize != null) {

			sb.append("pithumbsize").append("=").append(thumbsize.toString());

			sb.append(", ");
		}

		if (limit != null) {

			sb.append("pilimit").append("=").append(limit.toString());

			sb.append(", ");
		}

		if (license != null) {

			sb.append("pilicense").append("=").append(license.getJsonValue());

			sb.append(", ");
		}

		if (langcode != null) {

			sb.append("pilangcode").append("=").append(langcode);

			sb.append(", ");
		}

		return sb.append(")").toString();
	}

	@Override
	public void buildRequest(AAPI api, ClassicRequestBuilder req, String paramPrefix) {

		if (prop != null) {

			req.addParameter(
					paramPrefix + "piprop",
					prop.stream().map(v -> v.getJsonValue()).collect(Collectors.joining("|")));
		}

		if (thumbsize != null) {

			req.addParameter(paramPrefix + "pithumbsize", thumbsize.toString());
		}

		if (limit != null) {

			req.addParameter(paramPrefix + "pilimit", limit.toString());
		}

		if (license != null) {

			req.addParameter(paramPrefix + "pilicense", license.getJsonValue());
		}

		if (langcode != null) {

			req.addParameter(paramPrefix + "pilangcode", langcode);
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
			c.accept(AAPIQueryPageimages.this);
		}

		@Override
		public <T> T mapReduce(Function<AAPIModule, T> map, BinaryOperator<T> reduce) {
			T value = map.apply(AAPIQueryPageimages.this);

			return value;
		}

		@Override
		protected boolean internalRequiresPost() {
			return false;
		}
	}
}
